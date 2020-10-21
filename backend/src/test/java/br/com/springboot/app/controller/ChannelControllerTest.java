package br.com.springboot.app.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.springboot.app.domain.Channel;
import br.com.springboot.app.service.ChannelService;

@RunWith(SpringRunner.class)
@WebMvcTest(ChannelController.class)
public class ChannelControllerTest{
    
	@Autowired
    public MockMvc mockMvc;

    @MockBean
    private ChannelService channelServiceMock;

    @Autowired
    public ObjectMapper objectMapper;

    private static String URL_CHANNEL = "/channel/";

    private static String URL_CHANNEL_CREATE = URL_CHANNEL+"create";

    
//    private static String MSG_ERRO_VALIDADE_VENCIDA = "Validation Error: Channel com validade vencida.";
//    private static String MSG_ERRO_TAMANHO_BODY = "Validation Error: Um Channel pode ter no máximo 160 caracteres.";
    
//    private static String MSG_SUCESSO = "Sms Sent";
    
    public String  obterJson(Channel channel) {
	    String json = null;

		try {
			json = objectMapper.writeValueAsString(channel);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		return json;
	}
    
    @Test
    public void findSmsTest() throws Exception{
    	
    	Channel channel = new Channel();

//    	channel.setBody("Send Message");
//    	channel.setFrom("992345432");
//    	channel.setTo("9998767898");
//
//    	Channel channel2 = new Channel();
//
//    	channel2.setBody("Send Message");
//    	channel2.setFrom("992382432");
//    	channel2.setTo("9998767777");
//    	
//    	List<Channel> listaSms = Arrays.asList(channel,channel2);
//    	when(sMSServiceMock.findAll()).thenReturn(listaSms);
//    	
//    	
//    	this.mockMvc.perform(get(URL_CHANNEL)
//    	.accept(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$[0].body", is("Send Message")))
//        .andExpect(jsonPath("$[0].from", is("992345432")))
//        .andExpect(jsonPath("$[0].to", is("9998767898")))
//        .andExpect(jsonPath("$[1].body", is("Send Message")))
//        .andExpect(jsonPath("$[1].from", is("992382432")))
//        .andExpect(jsonPath("$[1].to", is("9998767777")));
    	
    	
	}

    @Test
    public void sendValidSmsTest() throws Exception{
    	Channel channel = new Channel();

//    	channel.setId(Long.valueOf(100));
//    	channel.setBody("Send Message");
//    	channel.setFrom("Daniel");
//    	channel.setTo("Regina");
//    	
//    	
//    	when(sMSServiceMock.send(channel)).thenReturn(MensagemDTO.sucesso(201, MSG_SUCESSO));
//    	
//    	
//    	this.mockMvc.perform(post(URL_CHANNEL+"send")
//        .contentType(MediaType.APPLICATION_JSON)
//    	.content(obterJson(channel)))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.tipo", is("SUCESSO")))
//        .andExpect(jsonPath("$.codigo", is(201)))
//        .andExpect(jsonPath("$.mensagem", is(MSG_SUCESSO)));
    	
    	
	}
    
    @Test
    public void sendSmsValidDateTest() throws Exception{
    	Channel channel = new Channel();

//    	channel.setId(Long.valueOf(100));
//    	channel.setBody("Send Message");
//    	channel.setFrom("Daniel");
//    	channel.setTo("Regina");
//    	channel.setValidade(LocalDate.now());
//    	
//    	
//    	when(sMSServiceMock.send(channel)).thenReturn(MensagemDTO.sucesso(201, MSG_SUCESSO));
//    	
//    	
//    	this.mockMvc.perform(post(URL_CHANNEL_SEND)
//        .contentType(MediaType.APPLICATION_JSON)
//    	.content(obterJson(channel)))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.tipo", is("SUCESSO")))
//        .andExpect(jsonPath("$.codigo", is(201)))
//        .andExpect(jsonPath("$.mensagem", is(MSG_SUCESSO)));
    	
	}

    @Test
    public void sendSmsInvalidDateTest() throws Exception{
    	Channel channel = new Channel();

//    	channel.setId(Long.valueOf(100));
//    	channel.setBody("Send Message");
//    	channel.setFrom("Daniel");
//    	channel.setTo("Regina");
//    	
//    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//    	channel.setValidade(LocalDate.parse("01/01/2016", formatter));
//    	
//    	
//    	when(sMSServiceMock.send(channel)).thenReturn(MensagemDTO.erro(405, MSG_ERRO_VALIDADE_VENCIDA));
    	
    	
//    	this.mockMvc.perform(post(URL_CHANNEL_SEND)
//        .contentType(MediaType.APPLICATION_JSON)
//    	.content(obterJson(channel)))
//        .andExpect(status().isBadRequest())
//        .andExpect(jsonPath("$.tipo", is("ERRO")))
//        .andExpect(jsonPath("$.codigo", is(405)))
//        .andExpect(jsonPath("$.mensagem", is(MSG_ERRO_VALIDADE_VENCIDA)));
    	
	}

    
    @Test
    public void sendSmsInvalidMessageTest() throws Exception{
    	Channel channel = new Channel();

    	channel.setId(Long.valueOf(100));
//    	channel.setBody("Send Message More 160 chars.........................................................................................................................................");
//    	channel.setFrom("Daniel");
//    	channel.setTo("Regina");
//    	
//    	
//    	when(sMSServiceMock.send(channel)).thenReturn(MensagemDTO.erro(405, MSG_ERRO_TAMANHO_BODY));
//    	
//    	
//    	this.mockMvc.perform(post(URL_CHANNEL_SEND)
//        .contentType(MediaType.APPLICATION_JSON)
//    	.content(obterJson(channel)))
//        .andExpect(status().isBadRequest())
//        .andExpect(jsonPath("$.tipo", is("ERRO")))
//        .andExpect(jsonPath("$.codigo", is(405)))
//        .andExpect(jsonPath("$.mensagem", is(MSG_ERRO_TAMANHO_BODY)));
    	
	}

    
    
    
	

}
