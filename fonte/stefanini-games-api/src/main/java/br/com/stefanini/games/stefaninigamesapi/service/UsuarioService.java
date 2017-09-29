package br.com.stefanini.games.stefaninigamesapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stefanini.games.stefaninigamesapi.exception.rest.NotFoundException;
import br.com.stefanini.games.stefaninigamesapi.model.Usuario;
import br.com.stefanini.games.stefaninigamesapi.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public void saveAdm(String username) {
		Usuario usuario = this.findByUsername(username);
		if(validarUsuario(usuario)){
			usuario.setAdministrador(true);
			usuarioRepository.save(usuario);
		}
	}
	
	private boolean validarUsuario(Usuario usuario){
		if (usuario == null) {
			throw new NotFoundException("Este usuario não existe.");
		}
		return true;
	}
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Usuario findById(Long id){
		return usuarioRepository.findById(id);
	}

	public Usuario findByUsername(String username){
		return usuarioRepository.findByUsername(username);
	}
	
	public void savedPhoto(String username){
		Usuario usuario = usuarioRepository.findByUsername(username);
		usuario.setFoto(true);
		usuarioRepository.save(usuario);
	}
	
}
