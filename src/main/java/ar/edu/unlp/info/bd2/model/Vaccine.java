package ar.edu.unlp.info.bd2.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vaccines")
public class Vaccine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;
	
	@Column(name = "nombre", nullable = false, unique = true)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "vaccines")
	private List<VaccinationSchedule> vaccines_sheduler = new ArrayList<VaccinationSchedule>();
	
	
	public Vaccine() {
	
	}
	
	public Vaccine(String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VaccinationSchedule> getVaccines_shedules() {
		return vaccines_sheduler;
	}

	public void setVaccines_shedules(List<VaccinationSchedule> vaccines_shedules) {
		this.vaccines_sheduler = vaccines_shedules;
	}

	@Override
	public String toString() {
		return "Vaccine [id=" + id + ", name=" + name + "]";
	}


	public void addShedule(VaccinationSchedule shedule) {
		this.vaccines_sheduler.add(shedule);
	}



	
}
