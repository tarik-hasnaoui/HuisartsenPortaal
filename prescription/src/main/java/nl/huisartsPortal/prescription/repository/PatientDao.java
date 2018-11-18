package nl.huisartsPortal.prescription.repository;

import nl.huisartsPortal.prescription.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Long> {
    Patient getPatientByBsnNumber(String bsnNumber);
}
