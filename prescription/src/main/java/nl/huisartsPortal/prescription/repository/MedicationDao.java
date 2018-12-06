package nl.huisartsPortal.prescription.repository;

import nl.huisartsPortal.prescription.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationDao extends JpaRepository<Medication, Long> {
    public Medication getMedicationByName(String name);
}
