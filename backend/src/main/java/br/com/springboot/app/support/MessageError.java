package br.com.springboot.app.support;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MessageError implements Serializable{
	private static final long serialVersionUID = 7587947750567867752L;

    private LocalDate timestamp;
    private int statusCode;
    private List<String> messages;

}
