package com.zup.blogPost.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zup.blogPost.model.Usuario;
import com.zup.blogPost.model.Veiculos;

@Repository
public interface VeiculosRepository extends JpaRepository<Veiculos, Long> {

	public Optional<Veiculos> findById(long id);

	public Optional<List<Veiculos>> findByUsuario(Usuario usuario);

}
