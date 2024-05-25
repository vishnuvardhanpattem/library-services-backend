package com.vishnu.springbootlibraryweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishnu.springbootlibraryweb.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Payment findByUserEmail(String userEmail);
}
