package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.Collection;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "centres")
public class Centre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;
	
	@Column(name = "nombre", nullable = false)
	private String name;
	
	@ManyToMany()
	@JoinTable(name="centres_staffs",
			   joinColumns = @JoinColumn(name = "centres_id"), 
			   inverseJoinColumns = @JoinColumn(name = "staffs_id")
	)
	private Collection<Staff> staffs = new ArrayList<Staff>();
	

	public Centre() {
	}
	
	public Centre(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Collection<Staff> getStaffs() {
		return staffs;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStaffs(Collection<Staff> staffs) {
		this.staffs = staffs;
	}
	
	public void addStaff(Staff staff) {
		this.staffs.add(staff);
		staff.addCentre(this);
	}
	
//	public void addSavedStaff(Staff staff) {
//		this.staffs.add(staff);
//	}

}
