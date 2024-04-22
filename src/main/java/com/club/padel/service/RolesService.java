package com.club.padel.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.club.padel.model.Roles;
import com.club.padel.repository.RolesRepository;
@Service
@Transactional
public class RolesService {
    @Autowired
    private RolesRepository rolesRepository;
       
    @Autowired
	private MessageSource mensajes;
    
    public List<Roles> listAll() {
        return rolesRepository.findAll();
    }

    public Roles saveRoles(Roles user) {
        return rolesRepository.save(user);
    }

    public Roles getRoles(Integer id) {
        return rolesRepository.findById(id).get();
    }

    public void deleteRoles(Integer id) {
        rolesRepository.deleteById(id);
    }
    
	public MessageSource getMensajes() {
		return mensajes;
	}

	public void setMensajes(MessageSource mensajes) {
		this.mensajes = mensajes;
	}


}
