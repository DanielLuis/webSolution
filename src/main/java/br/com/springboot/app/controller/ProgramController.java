package br.com.springboot.app.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.app.domain.Program;
import br.com.springboot.app.service.ProgramService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping(value = "programs")
public class ProgramController {
	@Autowired
	private ProgramService programsService;

	@RequestMapping(value = "/" , method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Program> getPrograms() {
		return programsService.findAll();
	}
	
	@RequestMapping(value = "/create" , method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Program save(@RequestBody Program Programs) {
		return programsService.save(Programs);
	}
	@RequestMapping(value = "/findByChannel/{id}" , method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Program> findByChannel(@PathVariable Long channelId) {
		return programsService.findByChannel(channelId);
	}
	@RequestMapping(value = "/findById" , method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Program> findById(@PathVariable Long id) {
		return programsService.findById(id);
	}
	@RequestMapping(value = "/delete" , method= RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Long id) {
		programsService.delete(id);
	}
	

}
