package com.club.padel.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club.padel.model.Pista;
import com.club.padel.model.Reserva;
import com.club.padel.model.Usuario;
import com.club.padel.repository.PistaRepository;
import com.club.padel.repository.ReservaRepository;
import com.club.padel.repository.UsuarioRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PistaRepository pistaRepository;

    public Reserva crearReserva(String username, Long pistaId, LocalDate fechaReserva, LocalTime horaInicio, LocalTime horaFin) throws Exception {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new Exception("Usuario no encontrado"));

        Pista pista = pistaRepository.findById(pistaId)
                .orElseThrow(() -> new Exception("Pista no encontrada"));

        LocalDate hoy = LocalDate.now();
        int diasAntelacion = usuario.getRol().getDiasAntelacion();

        if (fechaReserva.isBefore(hoy) || fechaReserva.isAfter(hoy.plusDays(diasAntelacion))) {
            throw new Exception("Fecha de reserva no permitida para el rol del usuario");
        }

        boolean conflictoHorario = reservaRepository.existsByPistaAndFechaReservaAndHoraInicioLessThanAndHoraFinGreaterThan(
                pista, fechaReserva, horaFin, horaInicio);

        if (conflictoHorario) {
            throw new Exception("La pista ya está reservada en ese horario");
        }

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setPista(pista);
        reserva.setFechaReserva(fechaReserva);
        reserva.setHoraInicio(horaInicio);
        reserva.setHoraFin(horaFin);
        reserva.setFechaHoraCreacion(LocalDateTime.now());

        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long reservaId) throws Exception {
        if (!reservaRepository.existsById(reservaId)) {
            throw new Exception("Reserva no encontrada");
        }
        reservaRepository.deleteById(reservaId);
    }
    
    public List<Reserva> obtenerReservasPorUsuario(String username) throws Exception {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new Exception("Usuario no encontrado"));
        return reservaRepository.findByUsuario(usuario);
    }
}
