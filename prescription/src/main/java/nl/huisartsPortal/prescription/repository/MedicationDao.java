package nl.huisartsPortal.prescription.repository;

import nl.huisartsPortal.prescription.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationDao extends JpaRepository<Medication, Long> {
    Medication getMedicationByName(String name);
}
