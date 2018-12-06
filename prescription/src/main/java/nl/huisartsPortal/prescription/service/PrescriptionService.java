package nl.huisartsPortal.prescription.service;

import nl.huisartsPortal.prescription.exceptions.DataNotFoundException;
import nl.huisartsPortal.prescription.model.Doctor;
import nl.huisartsPortal.prescription.model.Medication;
import nl.huisartsPortal.prescription.model.Patient;
import nl.huisartsPortal.prescription.model.Prescription;
import nl.huisartsPortal.prescription.repository.DoctorDao;
import nl.huisartsPortal.prescription.repository.MedicationDao;
import nl.huisartsPortal.prescription.repository.PatientDao;
import nl.huisartsPortal.prescription.repository.PrescriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionDao prescriptionDao;

    @Autowired
    private MedicationDao medicationDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PatientDao patientDao;

    public Prescription getPrescription(Long prescriptionId) {
        Prescription prescriptionEntity = prescriptionDao.getPrescriptionByPrescriptionId(prescriptionId);
        if (prescriptionEntity == null) {
            throw new DataNotFoundException("prescription with Prescription Code " + prescriptionId + " not found");
        }
        return prescriptionEntity;
    }

    public List<Prescription> getAll() {
        List<Prescription> prescription = prescriptionDao.findAll();
        for (Prescription pres : prescription) {
            if (pres == null) {
                throw new DataNotFoundException("Empty");
            }
        }
        return prescription;
    }

    public Prescription createPrescription(Prescription prescription) {
        Prescription prescriptionStored = prescriptionDao.getPrescriptionByPrescriptionId(prescription.getPrescriptionId());
        if (prescriptionStored != null) throw new RuntimeException("prescription already exist");

        Doctor doctorStored = doctorDao.findByBsnNumber(prescription.getDoctor().getBsnNumber());
        if (doctorStored != null) prescription.setDoctor(doctorStored);

        Patient patientStored = patientDao.getPatientByBsnNumber(prescription.getPatient().getBsnNumber());
        if (patientStored != null) prescription.setPatient(patientStored);

        List<Medication> medicationSet = new ArrayList<>();
        for (Medication pre : prescription.getMedications()) {
            Medication medicationStored = medicationDao.getMedicationByName(pre.getName());
            medicationSet.add(medicationStored);
            if (medicationStored != null) {
                prescription.setMedications(medicationSet);
            }
        }
       return prescriptionDao.save(prescription);
    }

    public void deletePrescription(Long id) {
        Prescription prescription = prescriptionDao.getOne(id);
        prescriptionDao.delete(prescription);
    }

    public void updatePrescription(Prescription prescription, Long prescriptionId) {
        Prescription prescriptionStored = prescriptionDao.getPrescriptionByPrescriptionId(prescriptionId);
        if (prescriptionStored == null) throw new RuntimeException("prescription with " + prescriptionId + "not exist");

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
        if (patientStored == null) throw new RuntimeException("patient with " + bsnNumber + "not exist");
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
