package com.informactos.its.repository;

import com.informactos.its.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findByProtocol(String protocol);
}