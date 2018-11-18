package nl.huisartsPortal.prescription.repository;

import nl.huisartsPortal.prescription.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tarik on 5-11-2018.
 */
public interface DoctorDao extends JpaRepository<Doctor, Long> {
    Doctor findByPolisNumber(String polisNumber);
}
