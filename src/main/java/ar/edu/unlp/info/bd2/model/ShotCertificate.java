package ar.edu.unlp.info.bd2.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "shotCertificates")
public class ShotCertificate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;
	
	@Column(name = "numero_de_serie", nullable = false)
	private long serialNumber;
	//falta validar q no se repita
	
	public ShotCertificate() {
		this.serialNumber = 100 + (int)(Math.random() * ((10000 - 100) + 1)); //valor aleatorio entre 100 y 10000
	}
	

	public long getSerialNumber() {
		return serialNumber;
	}



	
}
