package br.com.stefanini.games.stefaninigamesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stefanini.games.stefaninigamesapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
