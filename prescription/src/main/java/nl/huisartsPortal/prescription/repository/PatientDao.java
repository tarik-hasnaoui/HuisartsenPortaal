package nl.huisartsPortal.prescription.repository;

import nl.huisartsPortal.prescription.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tarik on 5-11-2018.
 */
public interface PatientDao extends JpaRepository<Patient, Long> {

    Patient findByFirstName(String voorName);
    Patient getPatientById(Long id);
}
