package com.club.padel.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.padel.constant.ClubPadelConstant;
import com.club.padel.model.Reserva;
import com.club.padel.service.ReservaService;

@RestController
@RequestMapping(ClubPadelConstant.APP_PREFIX+"/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaRequest reservaRequest) {
        try {
            Reserva reserva = reservaService.crearReserva(
                    reservaRequest.getUsername(),
                    reservaRequest.getPistaId(),
                    reservaRequest.getFechaReserva(),
                    reservaRequest.getHoraInicio(),
                    reservaRequest.getHoraFin()
            );
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
   
    @DeleteMapping("/{reservaId}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long reservaId) {
        try {
            reservaService.eliminarReserva(reservaId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/usuario/{username}")
    public ResponseEntity<List<Reserva>> obtenerReservasPorUsuario(@PathVariable String username) {
        try {
            List<Reserva> reservas = reservaService.obtenerReservasPorUsuario(username);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

class ReservaRequest {
    private Long usuarioId;
    private String username;
    private Long pistaId;
    private LocalDate fechaReserva;
    private LocalTime horaInicio;
    private LocalTime horaFin;
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Long getPistaId() {
		return pistaId;
	}
	public void setPistaId(Long pistaId) {
		this.pistaId = pistaId;
	}
	public LocalDate getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public LocalTime getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

    // Getters y setters
}
