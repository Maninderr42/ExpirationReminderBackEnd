package com.example.jwtAuthentication.Service.Impl;

import com.example.jwtAuthentication.Dto.RequsetDto.UserConnectionUpdateDTO;
import com.example.jwtAuthentication.Dto.RequsetDto.UserDetailUpdate;
import com.example.jwtAuthentication.Dto.RequsetDto.UserRequestDto;
import com.example.jwtAuthentication.Exceptions.UserAlreadyPresentException;
import com.example.jwtAuthentication.Exceptions.UserNotFoundException;
import com.example.jwtAuthentication.Models.Product;
import com.example.jwtAuthentication.Models.Schedule;
import com.example.jwtAuthentication.Models.User;
import com.example.jwtAuthentication.Repository.UserRepository;
import com.example.jwtAuthentication.Service.AuthService;
import com.example.jwtAuthentication.Trnasformations.UserTransformation;
import com.example.jwtAuthentication.Utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor

public class AuthServiceImpl implements AuthService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private final StreamBridge streamBridge;




    public String login(String username, String password)  {


        var authToken= new UsernamePasswordAuthenticationToken(username,password);

       var authenticate=authenticationManager.authenticate(authToken);

       //it give user details
//        ((UserDetails)(authenticate.getPrincipal())).getUsername();

        return JwtUtils.GenerateToken(((UserDetails)(authenticate.getPrincipal())).getUsername());


    }


    @Override
    public String signup(UserRequestDto userRequestDto) throws Exception {

        //building user entity
        User user = UserTransformation.convertEntity(userRequestDto);


        //check weather user already exist or not

        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());

        if(optionalUser.isPresent()){

            throw new UserAlreadyPresentException("this user name is already present");

        }

        //create authorities
       var authorities =  new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        //encript password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(authorities);


       User savedUser= userRepository.save(user);

        //generate token

        return JwtUtils.GenerateToken(user.getEmail());






    }

    public void sendCommication(User user ){
        List<Product> productList = restTemplate.getForObject("http://PRODUCTSERVICE/product/getProduct/" + user.getEmail(), List.class);

        List<Product> expiredProducts = new ArrayList<>();
        Date currentDate = new Date();

        for (Product product : productList) {
            if (product.getLocalDate().before(currentDate)) {
                expiredProducts.add(product);
            }
        }
        if (!expiredProducts.isEmpty()) {
            for (Product expiredProduct : expiredProducts) {
                Map<String, Object> notification = new HashMap<>();
                notification.put("userId", user.getId());
                notification.put("productName", expiredProduct.getProductName());
                notification.put("expiryDate", expiredProduct.getLocalDate());
                notification.put("message", "Your product " + expiredProduct.getProductName() + " has expired.");

                // Send the notification using StreamBridge
                streamBridge.send("notificationChannel", notification);

                log.info("Sent notification for expired product: " + expiredProduct.getProductName());
            }
        } else {
            log.info("No expired products found for user: " + user.getEmail());
        }
    }

    @Override
    public User getUser(String email) {

        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isEmpty()){
            return null;
        }

        List<Product> product = restTemplate.getForObject("http://PRODUCTSERVICE/product/getProduct/"+email, List.class);

        List<Schedule> scheduleList = restTemplate.getForObject("http://PRODUCTSERVICE/schedule/getSchedule/"+email, List.class);


        User user = optionalUser.get();
        user.setProductList(product);
        user.setScheduleList(scheduleList);
        return user;
    }

    @Override
    public String userNameUpdate(UserDetailUpdate userDetailUpdate) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(userDetailUpdate.getEmail());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = optionalUser.get();

        user.setFirstName(userDetailUpdate.getFirstName());
        user.setLastName(userDetailUpdate.getLastName());
        user.setBio(userDetailUpdate.getBio());
        user.setCountry(userDetailUpdate.getCountry());
        user.setState(userDetailUpdate.getState());
        user.setStreetAddress(userDetailUpdate.getStreetAddress());
        user.setBusinessName(userDetailUpdate.getBusinessName());
        user.setBusinessAddress(userDetailUpdate.getBusinessAddress());
        user.setBusinessType(userDetailUpdate.getBusinessType());


        userRepository.save(user);

        return "User data updated successfully";
    }

    @Override
    public String userConnectionUpdate(UserConnectionUpdateDTO userConnectionUpdateDTO) throws UserNotFoundException {

        Optional<User> optionalUser = userRepository.findUserByEmail(userConnectionUpdateDTO.getEmail());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = optionalUser.get();

        user.setAlternatePhoneNo(userConnectionUpdateDTO.getAlternatePhone());
        user.setAlternateEmail(userConnectionUpdateDTO.getEmail());

        userRepository.save(user);

        return "User data updated successfully";
    }


}
