package br.com.stefanini.games.stefaninigamesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.stefanini.games.stefaninigamesapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value="select distinct usuario "
			+ " FROM Usuario usuario "
			+ " WHERE usuario.id = :idUsuario ")
	public Usuario findById(@Param("idUsuario") Long idUsuario);

	@Query(value="select distinct usuario "
			+ " FROM Usuario usuario "
			+ " WHERE usuario.nomeRede LIKE :username ")
	public Usuario findByUsername(@Param("username") String username);

}
