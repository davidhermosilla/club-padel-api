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
import com.club.padel.model.Rol;
import com.club.padel.service.RolService;
import com.club.padel.service.util.ClubPadelUtil;
import com.club.padel.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import com.hpe.nfvd.container.exception.ContainerException;

@RestController
@RequestMapping(ClubPadelConstant.APP_PREFIX+"/roles")
public class RolController {
	// TODO: Añadir control de excepciones
	static final Logger log = LoggerFactory.getLogger(RolController.class);
	
	@Autowired
	private MessageSource mensajes;
	
    @Autowired
    RolService rolesService;
    
    @GetMapping("/test")
    public  ResponseEntity<String> test() {
    	return new ResponseEntity<String>(ClubPadelUtil.getString(mensajes,"language.test"), HttpStatus.OK);
    }    
    
    @GetMapping("")
    @JsonView(View.Basic.class)
    public List<Rol> list() {
    	log.debug("List");
        return rolesService.listAll();
    }

    @GetMapping("/{id}")
    @JsonView(View.Extended.class)
    public ResponseEntity<Rol> get(@PathVariable Integer id) {
        try {
            Rol rol = rolesService.getRoles(id);
            return new ResponseEntity<Rol>(rol, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Rol>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/")
    @JsonView(View.Basic.class)
    public Rol add(@RequestBody Rol rol) {
    	try {
    		return rolesService.saveRoles(rol);
    	} catch (Exception e) {
    		throw new ClubPadelException(HttpStatus.NOT_FOUND, ExceptionErrorDetail.EXCEPTION_UNEXPECTED, e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    @JsonView(View.Basic.class)
    public ResponseEntity<?> update(@RequestBody Rol rol, @PathVariable Integer id) {
        try {
            rol.setId(id);
            rolesService.saveRoles(rol);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        rolesService.deleteRoles(id);
    }

	public MessageSource getMensajes() {
		return mensajes;
	}

	public void setMensajes(MessageSource mensajes) {
		this.mensajes = mensajes;
	}
    
}
