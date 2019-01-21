package nl.huisartsportal.prescription.service;

import nl.huisartsportal.prescription.error.ErrorMessages;
import nl.huisartsportal.prescription.exceptions.DataNotFoundException;
import nl.huisartsportal.prescription.exceptions.IllegalException;
import nl.huisartsportal.prescription.model.Doctor;
import nl.huisartsportal.prescription.model.Medication;
import nl.huisartsportal.prescription.model.Patient;
import nl.huisartsportal.prescription.model.Prescription;
import nl.huisartsportal.prescription.repository.DoctorDao;
import nl.huisartsportal.prescription.repository.MedicationDao;
import nl.huisartsportal.prescription.repository.PatientDao;
import nl.huisartsportal.prescription.repository.PrescriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class
PrescriptionService {

    @Autowired
    private PrescriptionDao prescriptionDao;

    @Autowired
    private MedicationDao medicationDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PatientDao patientDao;

    public Prescription getPrescription(Long prescriptionId) {
        Prescription prescriptionEntity =
                prescriptionDao.getPrescriptionByPrescriptionId(prescriptionId);
        if (prescriptionEntity == null) {
            throw new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        return prescriptionEntity;
    }

    public List<Prescription> getAll() {
        List<Prescription> prescription = prescriptionDao.findAll();
        for (Prescription pres : prescription) {
            if (pres == null) {
                throw new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
            }
        }
        return prescription;
    }

    public Prescription createPrescription(Prescription prescription) {
        Prescription prescriptionStored =
                prescriptionDao.getPrescriptionByPrescriptionId(prescription.getPrescriptionId());
        if (prescriptionStored != null) {
            throw new IllegalException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }


        Doctor doctorStored =
                doctorDao.findByBsnNumber(prescription.getDoctor().getBsnNumber());
        if (doctorStored != null) prescription.setDoctor(doctorStored);

        Patient patientStored =
                patientDao.getPatientByBsnNumber(prescription.getPatient().getBsnNumber());
        if (patientStored != null) prescription.setPatient(patientStored);

        List<Medication> medicationSet = new ArrayList<>();
        for (Medication pre : prescription.getMedications()) {
            Medication medicationStored =
                    medicationDao.getMedicationByName(pre.getName());
            medicationSet.add(medicationStored);
            if (medicationStored != null) {
                prescription.setMedications(medicationSet);
            }
        }
        return prescriptionDao.save(prescription);
    }

    public void deletePrescription(Long id) {
        Prescription prescriptionEntity =
                prescriptionDao.getPrescriptionByPrescriptionId(id);
        if (prescriptionEntity == null) {
            throw new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        prescriptionDao.delete(prescriptionEntity);
    }

    public void updatePrescription(Prescription prescription,
                                   Long prescriptionId) {
        Prescription prescriptionStored =
                prescriptionDao.getPrescriptionByPrescriptionId(prescriptionId);
        if (prescriptionStored == null)
            throw new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        Long doctorId = null;
        if (prescriptionStored.getDoctor() != null) {
            doctorId = prescriptionStored.getDoctor().getId();
        }
        prescription.setPrescriptionId(prescriptionStored.getPrescriptionId());
        if (doctorId != null) {
            prescription.getDoctor().setId(doctorId);
        }

        Long patientId = null;
        if (prescriptionStored.getPatient() != null) {
            patientId = prescriptionStored.getPatient().getId();
        }

        if (patientId != null) {
            prescription.getPatient().setId(patientId);
        }

        for (Medication pre : prescriptionStored.getMedications()) {
            Long medicationId = null;
            if (pre != null) {
                medicationId = pre.getId();
            }
            if (medicationId != null) {
                pre.setId(medicationId);
            }
        }
        prescriptionDao.save(prescription);
    }

    public List<Prescription> getPatientPrescriptions(String bsnNumber) {

        List<Long> prescriptionId = new ArrayList<>();
        Patient patientStored = patientDao.getPatientByBsnNumber(bsnNumber);
        if (patientStored == null)
            throw new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        for (Prescription pre : patientStored.getPrescriptions()) {
            Long id = pre.getPrescriptionId();
            prescriptionId.add(id);

        }
        return prescriptionDao.findAllById(prescriptionId);
    }

    public Prescription getPatientPrescription(Long prescriptionId) {
        return prescriptionDao.getOne(prescriptionId);
    }
}
