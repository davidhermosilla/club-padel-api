package com.club.padel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club.padel.model.Rol;
import com.club.padel.model.Usuario;
import com.club.padel.repository.RolRepository;
import com.club.padel.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    public Usuario crearUsuario(String username, Long rolId) throws Exception {
    	
        if (usuarioRepository.existsByUsername(username)) {
            throw new Exception("El nombre de usuario ya existe");
        }

        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new Exception("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long usuarioId) throws Exception {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new Exception("Usuario no encontrado");
        }
        usuarioRepository.deleteById(usuarioId);
    }

    public Usuario actualizarUsuario(Long usuarioId, String username, Long rolId) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (!usuario.getUsername().equals(username) && usuarioRepository.existsByUsername(username)) {
            throw new Exception("El nombre de usuario ya existe");
        }

        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new Exception("Rol no encontrado"));

        usuario.setUsername(username);
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuario(Long usuarioId) throws Exception {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
    }

	public List<Usuario> listAll() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (Usuario user:usuarioRepository.findAll()) {
			Rol userRol = user.getRol();
			user.setRol(userRol);
			System.out.println(userRol);
			usuarios.add(user);
		}
        return usuarios;
    }
}

