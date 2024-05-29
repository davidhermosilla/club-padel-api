package com.club.padel.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.padel.constant.ClubPadelConstant;
import com.club.padel.exception.ClubPadelException;
import com.club.padel.exception.ExceptionErrorDetail;
import com.club.padel.model.Pista;
import com.club.padel.model.Ubicacion;
import com.club.padel.service.PistaService;
import com.club.padel.service.util.ClubPadelUtil;

@RestController
@RequestMapping(ClubPadelConstant.APP_PREFIX+"/pistas")
public class PistaController {
	// TODO: Añadir control de excepciones
	static final Logger log = LoggerFactory.getLogger(PistaController.class);
	
	@Autowired
	private MessageSource mensajes;
	
    @Autowired
    PistaService pistaService;
    
    @GetMapping("/test")
    public  ResponseEntity<String> test() {
    	log.info("Entrando en el metodo de test");
    	return new ResponseEntity<String>(ClubPadelUtil.getString(mensajes,"language.test"), HttpStatus.OK);
    }    
    
    @GetMapping("")
    public List<Pista> list() {
    	log.info("List");
    	List<Pista> list = pistaService.listAll();
    	System.out.println(list);
    	return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pista> get(@PathVariable Long id) {
        try {
        	Pista pista = pistaService.getPistas(id);
            return new ResponseEntity<Pista>(pista, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Pista>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/")
    public Pista add(@RequestBody PistaRequest pistaRequest) throws ClubPadelException {
    	try {
    		Pista pista = new Pista();
    		pista.setNombre(pistaRequest.getNombre());
    		pista.setUbicacion(pistaRequest.getUbicacion());
    		return pistaService.savePistas(pista);
    	} catch (Exception e) {
    		throw new ClubPadelException(HttpStatus.INTERNAL_SERVER_ERROR,ExceptionErrorDetail.EXCEPTION_UNEXPECTED, e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PistaRequest pistaRequest, @PathVariable Long id) {
        try {
        	Pista pista = new Pista();
        	
        	pista.setNombre(pistaRequest.getNombre());
        	pista.setUbicacion(pistaRequest.getUbicacion());
            pista.setId(id);
            pistaService.savePistas(pista);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pistaService.deletePistas(id);
    }

	public MessageSource getMensajes() {
		return mensajes;
	}

	public void setMensajes(MessageSource mensajes) {
		this.mensajes = mensajes;
	}
    
}

class PistaRequest {
    private String nombre;
    private Ubicacion ubicacion;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
}
