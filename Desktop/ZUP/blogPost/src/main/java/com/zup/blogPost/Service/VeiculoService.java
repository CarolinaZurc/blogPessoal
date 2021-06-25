package com.zup.blogPost.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.blogPost.Repositories.VeiculosRepository;
import com.zup.blogPost.model.Usuario;
import com.zup.blogPost.model.Veiculos;

@Service
public class VeiculoService {

	@Autowired
	private VeiculosRepository repositoryVeiculos;

	public Optional<Veiculos> cadastrarVeiculo(Veiculos veiculos) {
		Optional<Veiculos> placaExistente = repositoryVeiculos.findById(veiculos.getId());
		if (placaExistente.isPresent()) {
			return Optional.empty();
		}

		String placa = veiculos.getPlaca();

		char ultimoNumero = placa.charAt(6);

		if (ultimoNumero == 1 || ultimoNumero == 0) {
			veiculos.setDiaRodizio("Segunda-Feira");
		} else if (ultimoNumero == 2 || ultimoNumero == 3) {
			veiculos.setDiaRodizio("Terça-Feira");
		} else if (ultimoNumero == 4 || ultimoNumero == 5) {
			veiculos.setDiaRodizio("Quarta-Feira");
		} else if (ultimoNumero == 6 || ultimoNumero == 7) {
			veiculos.setDiaRodizio("Quinta-Feira");
		} else if (ultimoNumero == 8 || ultimoNumero == 9) {
			veiculos.setDiaRodizio("Sexta-Feira");
		} else
			return Optional.empty();

		return Optional.ofNullable(repositoryVeiculos.save(veiculos));

	}

	public void rodizioAtivo(Usuario usuario) {

		Date dataAtual = new Date();
		Calendar calendario = new GregorianCalendar();
		calendario.setTime(dataAtual);
		String diaSemana = "data";
		int dia = calendario.get(calendario.DAY_OF_WEEK);
		switch (dia) {
		case Calendar.SUNDAY:
			diaSemana = "Domingo";
			break;
		case Calendar.MONDAY:
			diaSemana = "Segunda-Feira";
			break;
		case Calendar.TUESDAY:
			diaSemana = "Terça-Feira";
			break;
		case Calendar.WEDNESDAY:
			diaSemana = "Quarta-Feira";
			break;
		case Calendar.THURSDAY:
			diaSemana = "Quinta-Feira";
			break;
		case Calendar.FRIDAY:
			diaSemana = "Sexta-Feira";
			break;
		case Calendar.SATURDAY:
			diaSemana = "Sábado";
			break;
		}

		List<Veiculos> meusVeiculos = usuario.getMeusVeiculos();

		for (Veiculos veiculo : meusVeiculos) {
			if (veiculo.getDiaRodizio() == diaSemana) {
				veiculo.setRodizioAtivo(true);
			} else {
				veiculo.setRodizioAtivo(false);
			}
		}
	}
}