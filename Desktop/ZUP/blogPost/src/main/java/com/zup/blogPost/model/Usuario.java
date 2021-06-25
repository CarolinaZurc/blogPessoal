package com.zup.blogPost.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuario")
public class Usuario extends EntityModel<Usuario> {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 3, max = 50)
	@NotBlank(message = "O nome deve ser preenchido!")
	private String nome;

	@NotBlank(message = "A data de nascimento deve ser preenchida!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private String nascimento;

	@Column(unique = true)
	@NotBlank(message = "O E-mail deve ser preenchido!")
	@Email
	private String email;

	@Column(unique = true)
	@NotNull(message = "O CPF deve ser preenchido!")
	@CPF
	private long cpf;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("usuario")
	private List<Veiculos> meusVeiculos = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public List<Veiculos> getMeusVeiculos() {
		return meusVeiculos;
	}

	public void setMeusVeiculos(List<Veiculos> meusVeiculos) {
		this.meusVeiculos = meusVeiculos;
	}

}
