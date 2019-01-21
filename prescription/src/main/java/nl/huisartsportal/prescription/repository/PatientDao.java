package nl.huisartsportal.prescription.repository;

import nl.huisartsportal.prescription.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient, Long> {
    public Patient getPatientByBsnNumber(String bsnNumber);
}
