package com.club.padel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.club.padel.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	 boolean existsByUsername(String username);

	 Optional<Usuario> findByUsername(String username);   
}

