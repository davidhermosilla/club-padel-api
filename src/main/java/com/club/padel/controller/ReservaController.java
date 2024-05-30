package com.club.padel.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.club.padel.constant.ClubPadelConstant;
import com.club.padel.model.Reserva;
import com.club.padel.service.ReservaService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping(ClubPadelConstant.APP_PREFIX+"/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("")
    public ResponseEntity<List<Reserva>> obtenerReservas() {
        List<Reserva> reservas = reservaService.obtenerReservas();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Reserva>> obtenerReservasPorFecha(@RequestParam("fecha") String fecha) {
        LocalDate fechaReserva = LocalDate.parse(fecha);
        List<Reserva> reservas = reservaService.obtenerReservasPorFecha(fechaReserva);
        return ResponseEntity.ok(reservas);
    }
    
    @GetMapping("/usuario/{username}")
    public ResponseEntity<List<Reserva>> obtenerReservasPorUsuario(@PathVariable String username) throws Exception {
        List<Reserva> reservas = reservaService.obtenerReservasPorUsuario(username);
        return ResponseEntity.ok(reservas);
    }
    
    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaRequest reservaRequest) throws Exception {
            Reserva reserva = reservaService.crearReserva(
                    reservaRequest.getUsername(),
                    reservaRequest.getPistaId(),
                    reservaRequest.getFechaReserva(),
                    reservaRequest.getHoraInicio(),
                    reservaRequest.getHoraFin()
            );
            return ResponseEntity.ok(reserva);
    }
   
    @DeleteMapping("/{reservaId}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long reservaId) throws Exception {
            reservaService.eliminarReserva(reservaId);
            return ResponseEntity.ok().build();
    }

}

class ReservaRequest {
    private Long usuarioId;
    private String username;
    private Long pistaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaReserva;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
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
