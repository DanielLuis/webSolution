package br.com.springboot.app.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ChannelServiceTest {
   
	@Autowired
	private ChannelService channelService;
	
//	private SMS getSMS(){
//		return new SMS("999922234","999233424","Mensagem",LocalDate.now());
//	}
	
//	private SMS saveSms(){
//		return smsService.save(getSMS());
//	}
//	
	@Test
	public void testSend() {
//		MensagemDTO mensagemDTO = smsService.send(getSMS());
//		
//		assertEquals(mensagemDTO.getCodigo(),201); 
		 Assert.fail();
	}


	@Test
	public void testFindAll() {
//		saveSms();
//		List<SMS> listaSms = smsService.findAll();
//		assertTrue(listaSms.size() > 0);
		
		 Assert.fail();
	}
	

	@Test
	public void testSave() {
//		assertNotNull(saveSms().getId()); 
		  Assert.fail();
	}
	
	
	
	



}
