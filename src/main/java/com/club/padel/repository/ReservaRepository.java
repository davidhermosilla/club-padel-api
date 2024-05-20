package com.club.padel.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.club.padel.model.Pista;
import com.club.padel.model.Reserva;
import com.club.padel.model.Usuario;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    boolean existsByPistaAndFechaReservaAndHoraInicioLessThanAndHoraFinGreaterThan(
            Pista pista, LocalDate fechaReserva, LocalTime horaFin, LocalTime horaInicio);
    
    List<Reserva> findByUsuario(Usuario usuario);

}


