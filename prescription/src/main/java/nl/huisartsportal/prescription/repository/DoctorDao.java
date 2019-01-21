package nl.huisartsportal.prescription.repository;

import nl.huisartsportal.prescription.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDao extends JpaRepository<Doctor, Long> {
    public Doctor findByBsnNumber(String bsnNumber);
}
