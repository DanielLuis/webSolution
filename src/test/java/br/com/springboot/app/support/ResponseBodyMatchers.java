package br.com.springboot.app.support;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseBodyMatchers {
	  private ObjectMapper objectMapper = new ObjectMapper();

	  public <T> ResultMatcher containsObjectAsJson(
	      Object expectedObject, 
	      Class<T> targetClass) {
	    return mvcResult -> {
	      String json = mvcResult.getResponse().getContentAsString();
	      T actualObject = objectMapper.readValue(json, targetClass);
	      assertThat(actualObject).isEqualToComparingFieldByField(expectedObject);
	    };
	  }
	  
	  public ResultMatcher containsError(String expectedMessage) {
		    return mvcResult -> {
		      String json = mvcResult.getResponse().getContentAsString();
		      MessageError errorResult = objectMapper.readValue(json, MessageError.class);
		      List<String> messages = errorResult.getMessages().stream()
		              .filter(message -> message.equals(expectedMessage))
		              .collect(Collectors.toList());

		      assertThat(messages)
		              .hasSize(1)
		              .withFailMessage("expecting exactly 1 error message '%s'",expectedMessage);
		    };
		  }
	  
	  static ResponseBodyMatchers responseBody(){
	    return new ResponseBodyMatchers();
	  }
	  
	}