package com.desperado.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desperado.courier.entities.courier.Courier;

import java.util.List;


public interface CourierRepository extends JpaRepository<Courier, Long> {


}
