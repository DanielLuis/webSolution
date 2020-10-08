package br.com.springboot.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.app.domain.Channel;


@Repository
public interface ChannelRepository extends CrudRepository<Channel, Long> {
	public List<Channel> findAll();


}
