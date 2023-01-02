package com.microservices.controller;

import com.microservices.dto.PaymentDTO;
import com.microservices.enums.Status;
import com.microservices.services.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public Page<PaymentDTO> listPayment(@PageableDefault (size = 10) Pageable page){
        return paymentService.getAllPayment(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable("id")Integer id){
        var paymentDTO = paymentService.findById(id);
        return ResponseEntity.ok(paymentDTO);
    }

    @GetMapping("/port")
    public String getPort(@Value("${local.server.port}")String port){
        return String.format("response in port: %s", port);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody @Valid PaymentDTO paymentDTO, UriComponentsBuilder uriComponentsBuilder){
        var payment = paymentService.createPayment(paymentDTO);
        var address = uriComponentsBuilder.path("/Payment/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(address).body(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable("id") @NotNull Integer id, @RequestBody @Valid PaymentDTO paymentDTO){
        var payment = paymentService.updatePayment(id, paymentDTO);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDTO> deletePayment(@PathVariable("id") Integer id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirm")
    public void confirmPayment(@PathVariable("id") Integer id){
        paymentService.confirmStatus(id);
    }
}
