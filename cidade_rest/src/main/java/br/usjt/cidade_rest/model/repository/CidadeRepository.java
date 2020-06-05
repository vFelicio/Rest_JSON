package br.usjt.cidade_rest.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.cidade_rest.model.beans.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade,Long>{

	List<Cidade> findByNomeLike(String nome);
	Cidade findByLatitudeAndLongitude(Double latitude, Double longitude);
	
}
