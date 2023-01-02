package com.microservices.services;

import com.microservices.domain.Payment;
import com.microservices.dto.PaymentDTO;
import com.microservices.enums.Status;
import com.microservices.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final RabbitTemplate rabbitTemplate;

    public Page<PaymentDTO> getAllPayment(Pageable page){
        return paymentRepository
                .findAll(page)
                .map(p -> modelMapper.map(p, PaymentDTO.class));
    }

    public PaymentDTO findById(Integer id){
        Payment payment= paymentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO createPayment(PaymentDTO paymentDTO){
        var payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setStatus(Status.CREATED);
        paymentRepository.save(payment);

        rabbitTemplate.convertAndSend("payments.ex", "", payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO updatePayment(Integer id, PaymentDTO paymentDTO){
        var payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setId(id);
        payment = paymentRepository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public void deletePayment(Integer id){
        paymentRepository.deleteById(id);
    }

    public void confirmStatus(Integer id) {
        var payment = paymentRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException());

        payment.setStatus(Status.CONFIRMED);
        paymentRepository.save(payment);
    }

    public void confirmStatus(Integer id, Status status) {
        var payment = paymentRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException());

        payment.setStatus(status);
        paymentRepository.save(payment);
    }
}
