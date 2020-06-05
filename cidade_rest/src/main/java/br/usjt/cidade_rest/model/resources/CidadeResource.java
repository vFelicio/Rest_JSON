package br.usjt.cidade_rest.model.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.usjt.cidade_rest.model.beans.Cidade;
import br.usjt.cidade_rest.model.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@GetMapping("/lista")
	public List<Cidade> todosAsCidades(){
		return cidadeRepo.findAll();
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
		Cidade c = cidadeRepo.save(cidade);
			URI uri = ServletUriComponentsBuilder.
					fromCurrentServletMapping().path("/{id}").
					buildAndExpand(c.getId()).
					toUri();
			
			return ResponseEntity.created(uri).body(c);
		}
	
	@GetMapping("/{id}")
	public Cidade buscarPeloId(@PathVariable Long id) {
		return cidadeRepo.getOne(id);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Cidade> atualizar(@RequestBody Cidade cidade){
		Cidade c = cidadeRepo.findById(cidade.getId()).get();

		c.setNome(cidade.getNome());
		c.setLatitude(cidade.getLatitude());
		c.setLongitude(cidade.getLongitude());
		c = cidadeRepo.save(c);
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}
	
	@DeleteMapping("/{id}")
	public void excluirPeloId(@PathVariable Long id){
		Cidade c = cidadeRepo.getOne(id);
		cidadeRepo.delete(c);
		
	}

}
