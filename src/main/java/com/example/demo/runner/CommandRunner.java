package com.example.demo.runner;

import com.example.demo.entity.Currency;
import com.example.demo.entity.Status;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandRunner implements CommandLineRunner {
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public void run(String... args) throws Exception {

        Currency currency1 = new Currency();
        currency1.setCurrencyCode("BGN");
        Currency currency2 = new Currency();
        currency2.setCurrencyCode("USD");
        Currency currency3 = new Currency();
        currency3.setCurrencyCode("EUR");
        currencyRepository.saveAll(List.of(currency1, currency2, currency3));


        Status status1 = new Status();
        status1.setStatusName("ADMIN");
        Status status2 = new Status();
        status2.setStatusName("CUSTOMER");
        Status status3 = new Status();
        status3.setStatusName("GUEST");
        statusRepository.saveAll(List.of(status1, status2, status3));

    }
}
