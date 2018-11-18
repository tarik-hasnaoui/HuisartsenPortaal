package nl.huisartsPortal.prescription.service;

import nl.huisartsPortal.prescription.exceptions.DataNotFoundException;
import nl.huisartsPortal.prescription.model.Doctor;
import nl.huisartsPortal.prescription.model.Patient;
import nl.huisartsPortal.prescription.repository.DoctorDao;
import nl.huisartsPortal.prescription.repository.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PatientDao patientDao;

    public Patient getPatient(Long id) {
        Patient patientEntity = patientDao.getPatientById(id);
        if (patientEntity == null) {
            throw new DataNotFoundException("patient with id " + id + " not found");
        }
        return patientEntity;
    }

    public List<Patient> getAll() {
        return patientDao.findAll();
    }

    public Patient createPatient(Patient patient) {
        Patient patientStored = patientDao.findByFirstName(patient.getFirstName());
        if (patientStored != null) throw new RuntimeException("patient already exist");

        Doctor doctorStored = doctorDao.findByPolisNumber(patient.getDoctor().getPolisNumber());
        if (doctorStored != null) patient.setDoctor(doctorStored);
        Patient patientSave = patientDao.save(patient);
        return patientSave;
    }

    public void updatePatient(Patient patient, Long id) {
        Patient existPatient = patientDao.getPatientById(id);

        if (existPatient == null) {
            throw new RuntimeException("user with" + id + "not exist");
        }
        Long doctorId = null;
        if (existPatient.getDoctor() != null) {
            doctorId = existPatient.getDoctor().getId();
        }

        patient.setId(existPatient.getId());
        if (doctorId != null) {
            patient.getDoctor().setId(doctorId);
        }
        patientDao.save(patient);

    }

    public void deletePatient(Long id) {
        Patient patient = patientDao.getOne(id);
        patientDao.delete(patient);
    }
}

