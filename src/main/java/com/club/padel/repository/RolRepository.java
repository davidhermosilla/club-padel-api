package com.club.padel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.club.padel.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    // Métodos de consulta personalizados si es necesario
}

