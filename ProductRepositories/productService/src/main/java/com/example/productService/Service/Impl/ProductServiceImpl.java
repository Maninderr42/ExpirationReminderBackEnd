package com.example.productService.Service.Impl;

import com.example.productService.Enum.ScheduleType;
import com.example.productService.Exception.ResourceNotFoundException;
import com.example.productService.Models.Product;
import com.example.productService.Models.Schedule;
import com.example.productService.Repository.ProductRespository;
import com.example.productService.Repository.ScheduleRespository;
import com.example.productService.RequestDto.EditProductRequestDTO;
import com.example.productService.RequestDto.ProductRequestDto;
import com.example.productService.Service.ProductService;
import com.example.productService.Transformation.ProductTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static com.example.productService.Enum.ScheduleType.appointment;
import static com.example.productService.Enum.ScheduleType.event;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRespository productRespository;

    @Autowired
    private ScheduleRespository scheduleRespository;

    @Override
    public String addProduct(ProductRequestDto productRequestDto) {

        Product product = ProductTransformation.convertProductEntity(productRequestDto);

        productRespository.save(product);

        return "product add SuccessFully " + product.getProductName();
    }

    @Override
    public List<Product> getProduct(String email) {

        List<Product> productList = productRespository.findByEmail(email);

        return productList;


    }

    @Override
    public String deleteProduct(String email, Integer productId) throws ResourceNotFoundException {

        List<Product> productList = productRespository.findByEmail(email);

        boolean isProductDelete = false;

        for (Product product : productList) {
            if (product.getProductId() == productId && product.getEmail().equals(email)) {
                productRespository.deleteById(productId);
                isProductDelete = true;
                break;
            }
        }

        if (!isProductDelete) {
            throw new ResourceNotFoundException("Product with ID " + productId + " not found" + email);
        }

        return "Product Delete SuccesFully";

    }
    @Override
    public List<Product> alertProduct(String email, String currentDate) {
        LocalDate date = LocalDate.parse(currentDate);

        LocalDate startDate = date.minusMonths(1);
        LocalDate endDate = date.plusMonths(3);

        List<Product> productList= productRespository.findProductsByExpiryDateRange(email, startDate, endDate);
        productList.sort(Comparator.comparing(Product::getExpiryDate));
        return  productList;
    }

    @Override
    public String editProduct(EditProductRequestDTO editProductRequestDTO) throws FileNotFoundException {

         Optional<Product> productOptional = productRespository.findById(editProductRequestDTO.getProductId());

        if (productOptional.isEmpty()) {
            throw new FileNotFoundException("Product Id Not found in the database");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(editProductRequestDTO.getExpiryDate(), formatter);

        Product product = productOptional.get();
        product.setProductName(editProductRequestDTO.getProductName());
        product.setPrice(editProductRequestDTO.getPrice());
        product.setDescription(editProductRequestDTO.getDescription());
        product.setQuantity(editProductRequestDTO.getQuantity());
        product.setExpiryDate(localDate);
        product.setType(editProductRequestDTO.getType());

        productRespository.save(product);

        return "Product Edit Successfully";


    }



    @Override
    public Map<Integer, Integer> yearExpired(String email, int year) {
        Map<Integer, Integer> expiryCounts = new TreeMap<>(); // TreeMap for sorted order

        for (int month = 1; month <= 12; month++) {
            LocalDate startOfMonth = LocalDate.of(year, month, 1);
            LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());

            int productCount = productRespository.countExpiredProductsByEmailAndMonth(email, year, month);
            int scheduleCount = scheduleRespository.countExpiredSchedulesByEmailAndMonth(email, year, month);
            expiryCounts.put(month, productCount + scheduleCount); // Use Integer keys
        }
        return expiryCounts;
    }


    @Override
    public Map<String, Integer> expiredByMonth(String email, String localDate){

        Map<String, Integer> expiryCounts = new HashMap<>();

        LocalDate date = LocalDate.parse(localDate);
        int year = date.getYear();
        int month = date.getMonthValue();

        int expiredProductsCount = productRespository.countExpiredProductsByEmailAndMonth(email, year, month);
        int expiredSchedulesCountevent = scheduleRespository.countExpiredSchedulesByEmailAndByMonth(email, year, month, ScheduleType.event);
        int expiredSchedulesCountappointment = scheduleRespository.countExpiredSchedulesByEmailAndByMonth(email, year, month, ScheduleType.appointment);

        expiryCounts.put("product", expiredProductsCount);
        expiryCounts.put("event", expiredSchedulesCountevent);
        expiryCounts.put("appointment", expiredSchedulesCountappointment);

        return expiryCounts;
    }

    @Override
    public Map<String, Long> HomeBar(String email, String localDate) {

        Map<String, Long> expiryCountsOneByOne = new HashMap<>();

        LocalDate date = LocalDate.parse(localDate);
        int year = date.getYear();
        int month = date.getMonthValue();
        LocalDate currentDate = LocalDate.now();

        // Total Expiration Data
        List<Product> productList = productRespository.findByEmail(email);
        List<Schedule> scheduleList = scheduleRespository.findByEmail(email);
        expiryCountsOneByOne.put("TotalData", (long) (productList.size() + scheduleList.size()));

        // Expired Total
        long futureExpiringProducts = productRespository.countFutureExpiringProductsByEmail(email, currentDate);
        long futureExpiringSchedules = scheduleRespository.countFutureExpiringSchedulesByEmail(email, currentDate);
        expiryCountsOneByOne.put("expiredTotal", futureExpiringProducts + futureExpiringSchedules);

        // Expired In This Month
        long expiredProductInMonth = productRespository.countExpiredProductsByEmailAndMonth(email, year, month);
        long expiredScheduleInMonth = scheduleRespository.countExpiredSchedulesByEmailAndMonth(email, year, month);
        expiryCountsOneByOne.put("expiredInMonth", expiredProductInMonth + expiredScheduleInMonth);

        // Expired Previous Day
        expiryCountsOneByOne.put("expiredPreviousDay", ((productList.size() + scheduleList.size())-(futureExpiringProducts+futureExpiringSchedules)));

        return expiryCountsOneByOne;
    }

    @Override
    public List<Map<Integer, Long>> getGraphCreatedDate(String email, int year) {
        Map<Integer, Long> totalItemsByCreatedDate = new TreeMap<>();
        Map<Integer, Long> futureExpiringItems = new TreeMap<>();
        Map<Integer, Long> alreadyExpiredItems = new TreeMap<>();

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();

        // Initialize all months with zero for consistent output structure
        for (int month = 1; month <= 12; month++) {
            totalItemsByCreatedDate.put(month, 0L);
            futureExpiringItems.put(month, 0L);
            alreadyExpiredItems.put(month, 0L);
        }

        for (int month = 1; month <= 12; month++) {
            LocalDate startOfMonth = LocalDate.of(year, month, 1);
            LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());

            // First Part: Count all items created in each month of the specified year
            long productCount = productRespository.countProductsByEmailAndCreatedMonth(email, year, month);
            totalItemsByCreatedDate.put(month, productCount);

            if (year == currentDate.getYear()) {
                // Second Part: Count future expiring items only if within the current year and future months
                if (month > currentMonth) {
                    long futureExpiringProducts = productRespository.countFutureExpiringProductsByEmail(email, endOfMonth);
                    long futureExpiringSchedules = scheduleRespository.countFutureExpiringSchedulesByEmail(email, endOfMonth);
                    futureExpiringItems.put(month, futureExpiringProducts + futureExpiringSchedules);
                }

                // Third Part: Count already expired items only if within the current year and past or current month
                if (month <= currentMonth) {
                    long alreadyExpiredProducts = productRespository.countAlreadyExpiredProductsByEmailAndMonth(email, year, month, currentDate);
                    long alreadyExpiredSchedules = scheduleRespository.countAlreadyExpiredSchedulesByEmailAndMonth(email, year, month, currentDate);
                    alreadyExpiredItems.put(month, alreadyExpiredProducts + alreadyExpiredSchedules);
                }
            }
        }

        // Compile all maps into a list and return
        List<Map<Integer, Long>> resultList = new ArrayList<>();
        resultList.add(futureExpiringItems);
        resultList.add(totalItemsByCreatedDate);
        resultList.add(alreadyExpiredItems);

        return resultList;
    }


}
