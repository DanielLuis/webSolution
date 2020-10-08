package br.com.springboot.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.springboot.app.domain.Channel;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ChannelRepositoryTest {

	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ChannelRepository channelRepository;

	
	
	@Test
	public void testFindAll() {
//        entityManager.persist(new Channel("999999999","888888888","Mensagem",LocalDate.now()));
//
//        List<Channel> sms = smsRepository.findAll();
//        Assert.assertNotNull(sms);
        
        Assert.fail();
	}

	@Test
	public void testSaves() {
//        entityManager.persist(new Channel("999999999","888888888","Mensagem",LocalDate.now()));

//        Channel sms = smsRepository.findAll().get(0);
//        Assert.assertNotNull(sms.getId());
        Assert.fail();
	}

}
