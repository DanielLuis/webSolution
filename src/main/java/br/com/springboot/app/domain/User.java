package br.com.springboot.app.domain;

import lombok.*;

import javax.persistence.*;

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
	@NonNull
	@Column(name = "name")
    private String name;
	@NonNull
	@Column(name = "email")
    private String email;
	@NonNull
	@Column(name = "status")
	private String status;
    
    
}
