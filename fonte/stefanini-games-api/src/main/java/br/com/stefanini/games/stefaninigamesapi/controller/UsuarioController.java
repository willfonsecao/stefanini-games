package br.com.stefanini.games.stefaninigamesapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.stefanini.games.stefaninigamesapi.exception.rest.UnprocessableEntityException;
import br.com.stefanini.games.stefaninigamesapi.model.Usuario;
import br.com.stefanini.games.stefaninigamesapi.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> findAll(){
		return usuarioService.findAll();
	}

	@GetMapping(path = "{id}")
	public Usuario findById(@PathVariable Long id){
		return usuarioService.findById(id);
	}

	@GetMapping(path = "username/{username}")
	public Usuario findByUsername(@PathVariable String username){
		return usuarioService.findByUsername(username);
	}
	
	@GetMapping(path = "/administradores")
	public List<Usuario> getAdms(){
		return usuarioService.getAdms();
	}
	
	@PostMapping
	public Usuario save(@RequestBody Usuario usuario){
		return usuarioService.save(usuario);
	}

	@PostMapping(path = "/adm/{username}")
	public Usuario saveAdm(@PathVariable("username") String username){
		return usuarioService.saveAdm(username);
	}
	
	@PostMapping(path = "/photo")
	public void savePhoto(@RequestParam("file") MultipartFile file) {
		String username = file.getOriginalFilename();
		usuarioService.savedPhoto(username,getBytes(file));
	}
	
	@DeleteMapping(path = "/adm/{username}")
	public void removerAdm(@PathVariable String username){
		usuarioService.removerAdm(username);
	}
	
	private byte[] getBytes(MultipartFile file) {
		List<String> erros = new ArrayList<>();
		try {
			byte[] bytes = file != null ? file.getBytes() : null;
			return bytes;
		} catch (IOException e) {
			erros.add("Não foi possível salvar sua foto. Tente novamente mais tarde.");
			throw new UnprocessableEntityException(erros);
		}

	}

}
