package com.zup.blogPost.Control;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.blogPost.Repositories.VeiculosRepository;
import com.zup.blogPost.Service.VeiculoService;
import com.zup.blogPost.model.Usuario;
import com.zup.blogPost.model.Veiculos;

@RestController
@RequestMapping("/blogPost/veiculo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VeiculoController {

	@Autowired
	private VeiculoService service;

	@Autowired
	private VeiculosRepository veiculoRepository;

	@PostMapping("/cadastrar/veiculo")
	public ResponseEntity<?> cadastrarVeiculo(@Valid @RequestBody Veiculos veiculos,
			@PathVariable(value = "id") Long id) {

		Optional<Veiculos> veiculoCriado = service.cadastrarVeiculo(veiculos);

		if (!veiculoCriado.isEmpty()) {
			Veiculos veiculo = veiculoCriado.get();
			@SuppressWarnings("deprecation")
			Link selfLink = new Link("http://localhost:8080/blogPost/veiculo/" + veiculo.getId());
			veiculo.add(selfLink);

			return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Não foi possível cadastrar o veiculo, verifique as informações.");
		}
	}

	@GetMapping("/usuario")
	public ResponseEntity<?> getVeiculoByUsuario(@Valid @RequestBody Usuario usuario) {
		service.rodizioAtivo(usuario);
		Optional<List<Veiculos>> veiculo = veiculoRepository.findByUsuario(usuario);

		if (veiculo.isPresent()) {
			@SuppressWarnings("deprecation")
			Link selfLink = new Link("http://localhost:8080/blogPost/usuario/busca/" + usuario.getCpf());
			usuario.add(selfLink);

			for (Veiculos veiculos : veiculo.get()) {
				@SuppressWarnings("deprecation")
				Link linkVeiculos = new Link("http://localhost:8080/blogPost/veiculo/" + veiculos.getId());
				veiculos.add(linkVeiculos);
			}
			return ResponseEntity.status(200).body(veiculo);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não encontrado no sistema.");
		}
	}

}
