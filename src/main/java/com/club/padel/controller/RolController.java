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
    	log.info("Entrando en el metodo de test");
    	return new ResponseEntity<String>(ClubPadelUtil.getString(mensajes,"language.test"), HttpStatus.OK);
    }    
    
    @GetMapping("")
    public List<Rol> list() {
    	log.info("List");
    	List<Rol> list = rolesService.listAll();
    	return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> get(@PathVariable Long id) {
        Rol rol = rolesService.getRoles(id);
        return new ResponseEntity<Rol>(rol, HttpStatus.OK);
    }
    
    @PostMapping("/")
    public Rol add(@RequestBody RolRequest rolRequest) throws ClubPadelException {
    	try {
    		Rol rol = new Rol();
    		rol.setNombre(rolRequest.getNombre());
    		rol.setDiasAntelacion(rolRequest.getDiasAntelacion());
    		return rolesService.saveRoles(rol);
    	} catch (Exception e) {
    		throw new ClubPadelException(HttpStatus.INTERNAL_SERVER_ERROR,ExceptionErrorDetail.EXCEPTION_UNEXPECTED, e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody RolRequest rolRequest, @PathVariable Long id) {
    	Rol rol = new Rol();
    	
    	rol.setNombre(rolRequest.getNombre());
    	rol.setDiasAntelacion(rolRequest.getDiasAntelacion());
        rol.setId(id);
        rolesService.saveRoles(rol);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rolesService.deleteRoles(id);
    }

	public MessageSource getMensajes() {
		return mensajes;
	}

	public void setMensajes(MessageSource mensajes) {
		this.mensajes = mensajes;
	}
    
}

class RolRequest {
    private String nombre;
    private Integer diasAntelacion;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getDiasAntelacion() {
		return diasAntelacion;
	}
	public void setDiasAntelacion(Integer diasAntelacion) {
		this.diasAntelacion = diasAntelacion;
	}
    
}
