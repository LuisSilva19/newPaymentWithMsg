package com.microservices;

import com.microservices.domain.Payment;
import com.microservices.repository.PaymentRepository;
import db.MockDBConnection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration
public class test extends MockDBConnection {

    @Autowired
    PaymentRepository paymentRepository;
    
    @Test
    void testforTestsCOntainers(){
        Optional<Payment> byId = paymentRepository.findById(1);
        System.out.println("Fuck-all" + byId.get().getName());
        assertNotNull(byId.get());
    }
}
