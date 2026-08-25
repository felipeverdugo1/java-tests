package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "staff_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "staffs")
public abstract class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;
	
	@Column(nullable = false)
	private String dni;
	
	@Column(name = "nombre_completo")
	private String fullName;
	
	
	@ManyToMany(mappedBy = "staffs")
	private Collection<Centre> centres = new ArrayList<Centre>();	
	
	
	public Staff(String dni, String fullName) {
		super();
		this.dni = dni;
		this.fullName = fullName;
	}
	
	public Staff() {
		
	}
	
	public String getDni() {
		return dni;
	}


	public String getFullName() {
		return fullName;
	}
	
	
	public Collection<Centre> getCentres() {
		// TODO Auto-generated method stub
		return centres;
	}
	
	public void addCentre(Centre centre) {
		this.centres.add(centre);	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setCentres(Collection<Centre> centres) {
		this.centres = centres;
	}
	
	
//	protected void addSavedCentre(Centre centre) {
//		this.centres.add(centre);	
//  }
	
	
	
}
