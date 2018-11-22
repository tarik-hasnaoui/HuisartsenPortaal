package nl.huisartsPortal.prescription.repository;


import nl.huisartsPortal.prescription.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionDao extends JpaRepository<Prescription, Long> {
    Prescription getPrescriptionByPrescriptionId(Long prescriptionId);
}
