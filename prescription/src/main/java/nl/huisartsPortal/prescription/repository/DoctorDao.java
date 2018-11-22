package nl.huisartsPortal.prescription.repository;

import nl.huisartsPortal.prescription.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDao extends JpaRepository<Doctor, Long> {
    Doctor findByBsnNumber(String bsnNumber);
}
