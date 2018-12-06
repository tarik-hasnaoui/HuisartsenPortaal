package nl.huisartsPortal.prescription.repository;


import nl.huisartsPortal.prescription.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionDao extends JpaRepository<Prescription, Long> {
    public Prescription getPrescriptionByPrescriptionId(Long prescriptionId);
}
