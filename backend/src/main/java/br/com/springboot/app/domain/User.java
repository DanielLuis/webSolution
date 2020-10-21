package br.com.springboot.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "TB_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	
	@NotEmpty(message = "{error.user.name.required}")
	@Column(name = "name")
    private String name;
	
	@Email(message = "{error.user.email.valid}")
	@NotEmpty(message = "{error.user.email.required}")
	@Column(name = "email")
    private String email;
	
	@Length(max = 1, message = "{error.user.status.information}")
	@NotEmpty(message = "{error.user.status.required}")
	@Column(name = "status")
	private String status;
    
    
}
