package br.com.springboot.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Classe Channel.
 */
@Entity
@Table(name = "TB_CHANNEL")
public class Channel implements Serializable {

	@Transient
	private static final long serialVersionUID = 8184977682804322708L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private Integer position;

    @Column(name = "category")
    private String category;
    
//    @OneToMany(mappedBy = "channel")
//    private List<Program> programs;

    public Channel() {
    	super();
	}

	public Channel(Long id) {
		super();
		this.id = id;
	}

    public Channel(String name, Integer position, String category) {
    	super();
    	this.name = name;
    	this.position = position;
    	this.category = category;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

//	public List<Program> getPrograms() {
//		return programs;
//	}

//	public void setPrograms(List<Program> programs) {
//		this.programs = programs;
//	}

	public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Channel sms = (Channel) o;

        if (!Objects.equals(id, sms.id))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Channel{" + "id=" + id + ", name='" + name +  ", position='" + position +  ", category='" + category + "'}'";
    }
}
