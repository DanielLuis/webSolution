package br.com.springboot.app.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.springboot.app.domain.Channel;
import br.com.springboot.app.repository.ChannelRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class ChannelService 
{
	
	@Autowired
	private ChannelRepository channelRepository;
	
	public List<Channel> findAll(){
		return channelRepository.findAll();
	}
	

	@Transactional(propagation=Propagation.REQUIRED)
	public Channel save(Channel channel) {
		return channelRepository.save(channel);
	}
	
}
