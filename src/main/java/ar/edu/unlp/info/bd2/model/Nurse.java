package ar.edu.unlp.info.bd2.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Nurse")
public class Nurse extends Staff{
		
	@Column(name = "experiencia")
	private Integer experience;
	
	
	public Nurse(String dni, String fullName, Integer experience) {
		super(dni,fullName);
		this.experience = experience;
	}
	
	public Nurse() {
		super();
	}

	public Integer getExperience() {
		return experience;
	}




	
	

}
