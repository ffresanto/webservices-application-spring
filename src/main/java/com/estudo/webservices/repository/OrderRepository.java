package com.estudo.webservices.repository;

import com.estudo.webservices.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
