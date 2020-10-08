package br.com.springboot.app.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.springboot.app.domain.Channel;
import br.com.springboot.app.domain.Program;
import br.com.springboot.app.repository.ProgramRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class ProgramService 
{
	
	@Autowired
	private ProgramRepository programRepository;
	
	public List<Program> findAll(){
		return programRepository.findAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Optional<Program> findById(Long id) {
		return programRepository.findById(id);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Program> findByChannel(Long id) {
		Channel channel = new Channel(id);
		return programRepository.findByChannel(channel);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Long id) {
		Program program = new Program(id);
		programRepository.delete(program);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Program save(Program program) {
		return programRepository.save(program);
	}
	
}
