package br.usjt.cidade_rest.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.cidade_rest.model.beans.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade,Long>{

}
