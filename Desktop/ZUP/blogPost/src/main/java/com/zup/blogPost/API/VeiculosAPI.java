package com.zup.blogPost.API;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zup.blogPost.model.Veiculos;

@FeignClient(url = "https://parallelum.com.br/fipe/api/v1", name = "tabelaFIPE")
public interface VeiculosAPI {

	@GetMapping("/fipe/api/v1/carros/marcas")
	Veiculos getMarcas();

	@GetMapping("/fipe/api/v1/carros/marcas/{marcaID}")
	Veiculos getModelos(@PathVariable Integer marcaID);

	@GetMapping("/fipe/api/v1/carros/marcas/{marcaID}/modelos/{modeloID}")
	Veiculos getAnos(@PathVariable Integer marcaID, @PathVariable Integer modeloID);

	@GetMapping("/fipe/api/v1/carros/marcas/{marcaID}/modelos/{modeloID}/anos/{anoID}")
	Veiculos getVeiculo(@PathVariable Integer marcaID, @PathVariable Integer modeloID, @PathVariable String anoID);

}
