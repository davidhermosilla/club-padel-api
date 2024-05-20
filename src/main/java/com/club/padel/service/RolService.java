package com.club.padel.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.club.padel.model.Rol;
import com.club.padel.repository.RolRepository;
@Service
@Transactional
public class RolService {
    @Autowired
    private RolRepository rolesRepository;
       
    @Autowired
	private MessageSource mensajes;
    
    public List<Rol> listAll() {
        return rolesRepository.findAll();
    }

    public Rol saveRoles(Rol role) {
        return rolesRepository.save(role);
    }

    public Rol getRoles(Long id) {
        return rolesRepository.findById(id).get();
    }

    public void deleteRoles(Long id) {
        rolesRepository.deleteById(id);
    }
    
	public MessageSource getMensajes() {
		return mensajes;
	}

	public void setMensajes(MessageSource mensajes) {
		this.mensajes = mensajes;
	}


}
