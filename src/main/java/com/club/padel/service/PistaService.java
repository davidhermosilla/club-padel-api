package com.club.padel.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.club.padel.model.Pista;
import com.club.padel.repository.PistaRepository;
@Service
@Transactional
public class PistaService {
    @Autowired
    private PistaRepository pistaRepository;
       
    @Autowired
	private MessageSource mensajes;
    
    public List<Pista> listAll() {
        return pistaRepository.findAll();
    }

    public Pista savePistas(Pista pista) {
        return pistaRepository.save(pista);
    }

    public Pista getPistas(Long id) {
        return pistaRepository.findById(id).get();
    }

    public void deletePistas(Long id) {
    	pistaRepository.deleteById(id);
    }
    
	public MessageSource getMensajes() {
		return mensajes;
	}

	public void setMensajes(MessageSource mensajes) {
		this.mensajes = mensajes;
	}


}
