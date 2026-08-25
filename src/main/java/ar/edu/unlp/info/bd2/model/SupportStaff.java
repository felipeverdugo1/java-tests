package ar.edu.unlp.info.bd2.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("SupportStaff")
public class SupportStaff extends Staff{
	

	
	@Column(name = "area")
	private String area;

	
	public SupportStaff(String dni, String fullName, String area) {
		super(dni,fullName);
		this.area = area;
	}
	
	public SupportStaff() {
		
	}


	public String getArea() {
		return area;
	}





}
