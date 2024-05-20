package com.club.padel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.club.padel.model.Usuario;
import com.club.padel.service.UsuarioService;

@RestController
@RequestMapping(ClubPadelConstant.APP_PREFIX+"/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ReservaController reservaController;
    
    @GetMapping("")
    public List<UsuarioResponse> obtenerUsuarios() {
    	List<UsuarioResponse> listaUsuarios = new ArrayList<>();
    	for (Usuario usuario:usuarioService.listAll()) {
    		UsuarioResponse usuarioResponse = new UsuarioResponse(usuario);
    		listaUsuarios.add(usuarioResponse);
    	}
        return listaUsuarios;
    }
    
    @GetMapping("/{usuarioId}")
    public UsuarioResponse obtenerUsuario(@PathVariable Long usuarioId) throws ClubPadelException {
            try {
            	Usuario usuario=usuarioService.obtenerUsuario(usuarioId);
            	UsuarioResponse usuarioResponse = new UsuarioResponse(usuario);
				return usuarioResponse;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new ClubPadelException(HttpStatus.NOT_FOUND,ExceptionErrorDetail.NOT_FOUND, e.getMessage());
			}
    }
    
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        try {
            Usuario usuario = usuarioService.crearUsuario(
                    usuarioRequest.getUsername(),
                    usuarioRequest.getRolId()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long usuarioId) {
        try {
            usuarioService.eliminarUsuario(usuarioId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long usuarioId, @RequestBody UsuarioRequest usuarioRequest) {
        try {
            Usuario usuario = usuarioService.actualizarUsuario(
                    usuarioId,
                    usuarioRequest.getUsername(),
                    usuarioRequest.getRolId()
            );
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

class UsuarioRequest {
    private String username;
    private Long rolId;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getRolId() {
		return rolId;
	}
	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}
}

class UsuarioResponse {
	private Long userId;
	
	private String username;
	
	private Long rolId;
	
	private String rolName;
	
	private Integer diasAntelacion;

	public UsuarioResponse(Usuario user) {
		this.userId=user.getId();
		this.username=user.getUsername();
		this.rolId=user.getRol().getId();
		this.rolName=user.getRol().getNombre();
		this.diasAntelacion=user.getRol().getDiasAntelacion();
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getRolId() {
		return rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	public Integer getDiasAntelacion() {
		return diasAntelacion;
	}

	public void setDiasAntelacion(Integer diasAntelacion) {
		this.diasAntelacion = diasAntelacion;
	}
}

