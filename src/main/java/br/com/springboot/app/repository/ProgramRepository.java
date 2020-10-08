package br.com.springboot.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.app.domain.Channel;
import br.com.springboot.app.domain.Program;


@Repository
public interface ProgramRepository extends CrudRepository<Program, Long> {
	public List<Program> findAll();
	public List<Program> findByChannel(Channel channel);
	
	


}
