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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "vaccinationSchedules")
public class VaccinationSchedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;
	
	@ManyToMany()
	@JoinTable(name="schedules_vaccines",
			   joinColumns = @JoinColumn(name = "shedules_id"), 
			   inverseJoinColumns = @JoinColumn(name = "vaccines_id")
	)
	@OrderColumn(name = "orden_vaccines")
    private List<Vaccine> vaccines;
	
	

	public VaccinationSchedule() {		
		this.vaccines = new ArrayList<Vaccine>();
	}

	public void addVaccine(Vaccine vaccine) {
		this.vaccines.add(vaccine);
		vaccine.addShedule(this);
	}

	public Long getId() {
		return id;
	}
	
	public List<Vaccine> getVaccines() {
		return this.vaccines;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}
	
	
	
	
	
}
