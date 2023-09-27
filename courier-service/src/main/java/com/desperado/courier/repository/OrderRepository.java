package com.desperado.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

import com.desperado.courier.entities.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("""
            SELECT o
            FROM Order o
            WHERE DATE(o.completedTime) >= :startDate and DATE(o.completedTime) < :endDate and o.courier.id = :courierId            
            """)
    List<Order> findAllBetweenTwoDates(Long courierId, LocalDate startDate, LocalDate endDate);


}
