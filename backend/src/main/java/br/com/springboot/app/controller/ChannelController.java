package br.com.springboot.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.app.domain.Channel;
import br.com.springboot.app.service.ChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping(value = "channels")
@Tag(name = "channel", description = "the Channel API")
public class ChannelController {
	@Autowired
	private ChannelService channelService;
	

	@Operation(summary = "Find Channels", description = "Api method to find all Channels", tags = { "channel" })
	@RequestMapping(value = "/" , method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Channel> getChannels() {
		return channelService.findAll();
	}
	
	@Operation(summary = "Create Channels", description = "Api method for create one Channel", tags = { "channel" })
	@RequestMapping(value = "/create" , method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Channel create(@RequestBody Channel channel) {
		return channelService.save(channel);
	}
	

}
