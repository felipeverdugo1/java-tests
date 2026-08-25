package ar.edu.unlp.info.bd2.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;
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
import ar.edu.unlp.info.bd2.repositories.VaxException;
import ar.edu.unlp.info.bd2.repositories.VaxRepository;

@Service
public class VaxServiceImpl implements VaxService{


	private VaxRepository repository;
	
	public VaxServiceImpl() {
		
	}

	public VaxServiceImpl(VaxRepository repository) {
		this.repository = repository;
	}
	
	public VaxRepository getRepository() {
		return repository;
	}
	
	
	

//==================Meteodos Patient=======================
	
	@Transactional
	public Patient createPatient(String email, String fullname, String password, Date dayOfBirth) throws VaxException{
		Patient patient = new Patient(email, fullname, password, dayOfBirth);
		Serializable serializablePatient = this.repository.create(patient);
		
		return this.repository.getPatientById((Long) serializablePatient);
	}
	
	public Optional<Patient> getPatientByEmail(String email) throws VaxException  {
		return this.repository.getPatientByEmail(email);
	}
	
	


//==================Meteodos Vaccine=======================
	
	@Transactional
	public Vaccine createVaccine(String name) throws VaxException {
		Vaccine vaccine = new Vaccine(name);
		Serializable serializableVacinne = this.repository.create(vaccine);
		
		return this.repository.getVaccineById((Long) serializableVacinne);
	}
	
	public Optional<Vaccine> getVaccineByName(String name) throws VaxException{
		return this.repository.getVaccineByName(name);
	}
	

	
	
	
	
//==================Meteodos Centre=======================	
	
	@Transactional
	public Centre createCentre(String name) throws VaxException  {			
		Centre centre = new Centre(name);
		Serializable serializableCentre = this.repository.create(centre);
		
		return this.repository.getCentreById((Long) serializableCentre);
	}
	
	public Optional<Centre> getCentreByName(String name) throws VaxException  {			
		return this.repository.getCentreByName(name);
	}
	
	
	@Override
	@Transactional
	public Centre updateCentre(Centre centre) throws VaxException{		
		this.repository.update(centre);
		return this.repository.getCentreById(centre.getId());
	}
	

	
	
	
	
//==================Meteodos Nurse=======================	

	@Override
	@Transactional
	public Nurse createNurse(String dni, String fullName, Integer experience) throws VaxException  {
		Nurse nurse = new Nurse(dni, fullName, experience);
		Serializable serializableCentre = this.repository.create(nurse);
		
		return this.repository.getNurseById((Long) serializableCentre);	
	}
	

	
	
	
	
//==================Meteodos SupportStaff=======================

	@Override
	@Transactional
	public SupportStaff createSupportStaff(String dni, String fullName, String area) throws VaxException  {
		SupportStaff supportStaff = new SupportStaff(dni, fullName, area);
		Serializable serializableCentre = this.repository.create(supportStaff);
		
		return this.repository.getSupportStaffById((Long) serializableCentre);
	}
	
	@Override
	public Optional<SupportStaff> getSupportStaffByDni(String dni) {
		return this.repository.getSupportStaffByDni(dni);
	}
	
	@Override
	@Transactional
	public SupportStaff updateSupportStaff(SupportStaff staff) throws VaxException {
		this.repository.update(staff);
		return this.repository.getSupportStaffById(staff.getId());
	}
	
	
	
	
//==================Meteodos VaccinationSchedule=======================
	
	@Transactional
	public VaccinationSchedule createVaccinationSchedule() throws VaxException {
		VaccinationSchedule vaccinationSchedule = new VaccinationSchedule();
		Serializable serializableCentre = this.repository.create(vaccinationSchedule);
		
		return this.repository.getVaccinationScheduleById((Long) serializableCentre);
	}
	
	
	public VaccinationSchedule getVaccinationScheduleById(Long id) {
		
		return this.repository.getVaccinationScheduleById(id);
	}
	
	@Transactional
	public void updateVaccinationSchedule(VaccinationSchedule vaccinationshedule) {
		this.repository.update(vaccinationshedule);
	}

	
	
	
	
//==================Meteodos Shot=======================		


	@Override
	@Transactional
	public Shot createShot(Patient patient, Vaccine vaccine, Date date, Centre centre, Nurse nurse) throws VaxException {
		Shot shot = new Shot(patient, vaccine, date, centre, nurse);
		patient.addShot(shot);
		
		Serializable serializableCentre = this.repository.create(shot);
		
		return this.repository.getShotById((Long) serializableCentre);	
		
	}
	
	public Optional<ShotCertificate> getShotCertificateBySerialNumber(int serial_number) {
		return this.repository.getShotCertificateBySerialNumber(serial_number);
	}
	
	
//========================================================================================================================================
//========================================================================================================================================	
	
	

	@Override
	public List<Patient> getAllPatients() {
		return this.repository.getAllPatients();
	}

	@Override
	public List<Nurse> getNurseWithMoreThanNYearsExperience(int years) {
		return this.repository.getNurseWithMoreThanNYearsExperience(years);
	}

	@Override
	public List<Centre> getCentresTopNStaff(int n) {
		return this.repository.getCentresTopNStaff(n);
	}

	@Override
	public Centre getTopShotCentre() {
		return this.repository.getTopShotCentre();
	}

	@Override
	public List<Nurse> getNurseNotShot() {
		return this.repository.getNurseNotShot();
	}

	@Override
	public String getLessEmployeesSupportStaffArea() {
		return this.repository.getLessEmployeesSupportStaffArea();
	}

	@Override
	public List<Staff> getStaffWithName(String name) {
		return this.repository.getStaffWithName(name);
	}

	@Override
	public List<Vaccine> getUnappliedVaccines() {
		return this.repository.getUnappliedVaccines();
	}

	@Override
	public List<ShotCertificate> getShotCertificatesBetweenDates(Date startDate, Date endDate) {
		return this.repository.getShotCertificatesBetweenDates(startDate, endDate);
	}

	

	
	


}
