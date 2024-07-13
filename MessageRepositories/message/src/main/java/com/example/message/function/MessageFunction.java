package com.example.message.function;


import com.example.message.RequestDTO.AccountMsgDto;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.logging.Logger;

@Configuration
public class MessageFunction {

//    private static final Logger log = (Logger) LoggerFactory.getLogger(MessageFunction.class);

    @Bean
    public Function<AccountMsgDto, AccountMsgDto> email(){
        return accountMsgDto -> {
//            log.info("Sending email with the details:"+accountMsgDto.toString());
            return accountMsgDto;
        };
    }

    @Bean
    public Function<AccountMsgDto, Long> sms(){
        return accountMsgDto -> {
//            log.info("Sending sms with the details:"+accountMsgDto.toString());
            return accountMsgDto.acoundNumber();
        };
    }




}
