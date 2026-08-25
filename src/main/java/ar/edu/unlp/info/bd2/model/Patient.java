package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;

//import java.util.ArrayList;

import java.util.Date;
import java.util.List;
//import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;





@Entity
@Table(name = "patients")
public class Patient{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;
	
	@Column(name = "correo", nullable = false, unique = true)
	private String email;
	@Column(name = "nombre_completo", nullable = false)
	private String fullName;
	@Column(name = "contraseña", nullable = false)
	private String password;
	@Column(name = "fecha_nacimiento")
	private Date dayOfBirth;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "patient")
	private List<Shot> shots = new ArrayList<Shot>();
	
	
	
	public Patient() {
		
	}

	public  Patient(String email, String fullName, String password,Date dayOfBirth) {
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.dayOfBirth = dayOfBirth;
	}
	
	//====================Geters y Seters==========================

	public long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getFullname() {
		return fullName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public List<Shot> getShots() {
		return shots;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}
	
	public void setShots(List<Shot> shots) {
		this.shots = shots;
	}
	
	public void addShot(Shot shot) {
		this.shots.add(shot);
	}
	
	@Override
	public String toString() {
		return "Patient [id=" + id + ", email=" + email + ", fullName=" + fullName + ", password=" + password
				+ ", dayOfBirth=" + dayOfBirth + "]";
	}

	







	
	

}
