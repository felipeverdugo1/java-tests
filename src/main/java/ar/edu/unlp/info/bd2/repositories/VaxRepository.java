package ar.edu.unlp.info.bd2.repositories;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.info.bd2.model.Centre;
import ar.edu.unlp.info.bd2.model.Nurse;
import ar.edu.unlp.info.bd2.model.Patient;
import ar.edu.unlp.info.bd2.model.Shot;
import ar.edu.unlp.info.bd2.model.ShotCertificate;
import ar.edu.unlp.info.bd2.model.Staff;
import ar.edu.unlp.info.bd2.model.SupportStaff;
import ar.edu.unlp.info.bd2.model.VaccinationSchedule;
import ar.edu.unlp.info.bd2.model.Vaccine;


@Repository
public class VaxRepository {

	@Autowired
	private SessionFactory sessionFactory;
	

	public VaxRepository() {

	}

	
	public Serializable create (Object aux) throws VaxException {
		try {			
			Serializable serializableAux = sessionFactory.getCurrentSession().save(aux);
			System.out.println(aux.toString());
			return serializableAux;
		}
		catch(ConstraintViolationException cve){
			System.out.println(cve.getMessage());
			throw new VaxException("Constraint Violation");	
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			throw new VaxException("Otro error");			
		}
	}
	
	
	public void update(Object aux) {		
		this.sessionFactory.getCurrentSession().update(aux);
		System.out.println(aux.toString());
	}
	
	
	
//==================Meteodos Paciente===========================================================================================
	
	public Patient getPatientById(Long id) {	
		
		return (Patient) this.sessionFactory.getCurrentSession().createQuery("from Patient where id = :id").setParameter("id", id).getSingleResult();
	}
	
	
	public Optional<Patient> getPatientByEmail(String email)throws VaxException{	
		return this.sessionFactory.getCurrentSession().createQuery("from Patient pa where pa.email='" + email + "'").uniqueResultOptional(); 
	}
	
	
//==================Meteodos Vaccine==============================================================================================
	
	public Vaccine getVaccineById(Long id) {
		return (Vaccine) this.sessionFactory.getCurrentSession().createQuery("from Vaccine where id = :id").setParameter("id", id).getSingleResult();
	}
	
	public Optional<Vaccine> getVaccineByName(String name) throws VaxException{	
		// Esta consulta es mejor solo retornando el nombre, supongo
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		List<Vaccine> lista = session.createQuery("from Vaccine").getResultList();
//		session.getTransaction().commit();
//		session.close();
//		return lista;
		
		return this.sessionFactory.getCurrentSession().createQuery("from Vaccine va where va.name='" + name + "'").uniqueResultOptional();		
	}
	
	
//==================Meteodos Centre=============================================================================================	
	
	public Centre getCentreById(Long id) {
		return (Centre) this.sessionFactory.getCurrentSession().createQuery("from Centre where id = :id").setParameter("id", id).getSingleResult();
	}
	
	public Optional<Centre> getCentreByName(String name) throws VaxException{

		return this.sessionFactory.getCurrentSession().createQuery("from Centre ce where ce.name='" + name + "'").uniqueResultOptional();
	}
	

//==================Meteodos Nurse=============================================================================================		
	
	public Nurse getNurseById(Long id) {
		return (Nurse) this.sessionFactory.getCurrentSession().createQuery("from Nurse where id = :id").setParameter("id", id).getSingleResult();
	}
	
//==================Meteodos SupportStaff======================================================================================	
	
	public SupportStaff getSupportStaffById(Long id) {
		return (SupportStaff) this.sessionFactory.getCurrentSession().createQuery("from SupportStaff where id = :id").setParameter("id", id).getSingleResult();
	}
	
	
	public Optional<SupportStaff> getSupportStaffByDni(String dni) {

		return this.sessionFactory.getCurrentSession().createQuery("from SupportStaff ss where ss.dni='" + dni + "'").uniqueResultOptional();
	}
	
	
	
//==================Meteodos VaccinationSchedule==============================================================================
	
	public VaccinationSchedule getVaccinationScheduleById(Long id) {
		return (VaccinationSchedule) this.sessionFactory.getCurrentSession().createQuery("from VaccinationSchedule where id = :id").setParameter("id", id).getSingleResult();
	}
	
	
//==================Meteodos Shot==============================================================================================	
	
	public Shot getShotById(Long id) {
		return (Shot) this.sessionFactory.getCurrentSession().createQuery("from Shot where id = :id").setParameter("id", id).getSingleResult();
	}
	



//==================Meteodos ShotCerificate==============================================================================================


	public Optional<ShotCertificate> getShotCertificateBySerialNumber(int serial_number) {
		
		return this.sessionFactory.getCurrentSession().createQuery("from ShotCertificate ss where ss.serialNumber=':serialNumber'").setParameter("serialNumber",serial_number).uniqueResultOptional();
	}


//==========================================================================================================================================
//==========================================================================================================================================	
	
	public List<Patient> getAllPatients() {
		return this.sessionFactory.getCurrentSession().createQuery("from Patient").list();
	}


	public List<Nurse> getNurseWithMoreThanNYearsExperience(int years) {
		return this.sessionFactory.getCurrentSession().createQuery("from Nurse n where n.experience > :years").setParameter("years",years).list();
	}


	
	public List<Centre> getCentresTopNStaff(int n) {
		String query = "Select c from Centre c group by c.id order by size(c.staffs) desc";
		return this.sessionFactory.getCurrentSession().createQuery(query).setMaxResults(n).getResultList();
	}


	public Centre getTopShotCentre() {
		String query = "Select s.centre from Shot s group by s.centre order by count(s.centre) desc";
		return (Centre) this.sessionFactory.getCurrentSession().createQuery(query).setMaxResults(1).uniqueResult();
	}


	public List<Nurse> getNurseNotShot() {
		String query = "from Nurse where id not in (select distinct nurse from Shot)";
		return this.sessionFactory.getCurrentSession().createQuery(query).list();
	}


	
	public String getLessEmployeesSupportStaffArea() {
		String query = "select s.area from SupportStaff s group by s.area order by count(s) asc";
		return (String) this.sessionFactory.getCurrentSession().createQuery(query).setMaxResults(1).uniqueResult();
	}


	public List<Staff> getStaffWithName(String name) {
		return this.sessionFactory.getCurrentSession().createQuery("from Staff where nombre_completo like :name ")
				.setParameter("name", "%" + name + "%" ).getResultList();
	}


	public List<Vaccine> getUnappliedVaccines() {
		String query = "from Vaccine where id not in ( select distinct vaccine from Shot ) ";
		return this.sessionFactory.getCurrentSession().createQuery(query).list();
	}


	public List<ShotCertificate> getShotCertificatesBetweenDates(Date startDate, Date endDate) {
		return this.sessionFactory.getCurrentSession()
				.createQuery("FROM ShotCertificate WHERE id in (select shotCertificate from Shot where date BETWEEN :stDate AND :edDate )")
				.setParameter("stDate", startDate)
				.setParameter("edDate", endDate)
				.list();
	}
	

	

	


	

	
}
