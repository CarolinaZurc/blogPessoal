package com.zup.blogPost.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "veiculos")
public class Veiculos extends EntityModel<Veiculos> {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "A marca do veículo deve ser preenchida!")
	private String marca;

	@NotBlank(message = "O modelo do veículo deve ser preenchido!")
	private String modelo;

	@NotBlank(message = "O ano do veículo deve ser preenchido!")
	private String ano;

	private long valor;

	@Column(unique = true)
	@NotBlank(message = "A placa deve ser preenchida!")
	private String placa;

	private String diaRodizio;

	private boolean rodizioAtivo;

	@ManyToOne
	@JsonIgnoreProperties("meusVeiculos")
	private Usuario usuario;

	public Veiculos() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getDiaRodizio() {
		return diaRodizio;
	}

	public void setDiaRodizio(String diaRodizio) {
		this.diaRodizio = diaRodizio;
	}

	public boolean isRodizioAtivo() {
		return rodizioAtivo;
	}

	public void setRodizioAtivo(boolean rodizioAtivo) {
		this.rodizioAtivo = rodizioAtivo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
