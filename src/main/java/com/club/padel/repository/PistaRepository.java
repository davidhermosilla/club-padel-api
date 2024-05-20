package com.club.padel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.club.padel.model.Pista;

public interface PistaRepository extends JpaRepository<Pista, Long> {
    // Métodos de consulta personalizados si es necesario
}

