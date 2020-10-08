package br.com.springboot.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.app.domain.Channel;
import br.com.springboot.app.service.ChannelService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "channels")
public class ChannelController {
	@Autowired
	private ChannelService channelService;
	

	@RequestMapping(value = "/" , method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Channel> getChannels() {
		return channelService.findAll();
	}
	
	@RequestMapping(value = "/create" , method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Channel create(@RequestBody Channel channel) {
		return channelService.save(channel);
	}
	

}
