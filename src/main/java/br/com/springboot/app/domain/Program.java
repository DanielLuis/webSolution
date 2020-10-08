package br.com.springboot.app.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe Program.
 */

@Entity
@Table(name = "TB_PROGRAM")
public class Program implements Serializable {

	@Transient
	private static final long serialVersionUID = 8184977682804322708L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

	@ManyToOne
    @JoinColumn(name="channel_id")
	private Channel channel;
	
	
    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
   
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;



    public Program() {
    	super();
	}
    
    public Program( Long id) {
    	super();
    	this.id = id;
	}

    public Program(Channel channel, String imageUrl, String description,LocalDate start_date,LocalDate end_date) {
    	super();
    	this.channel = channel;
    	this.imageUrl = imageUrl;
    	this.description = description;
    	this.start_date = start_date;
    	this.end_date = end_date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Program sms = (Program) o;

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
        return "Program{" + "id=" + id + ", channel='" + channel +  ", imageUrl='" + imageUrl +  ", description='" + description +  "'}'";
    }
}
