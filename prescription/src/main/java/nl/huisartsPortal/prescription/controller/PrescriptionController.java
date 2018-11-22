package nl.huisartsPortal.prescription.controller;

import nl.huisartsPortal.prescription.model.Prescription;
import nl.huisartsPortal.prescription.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/doctor/addPrescription")
    public void addPrescription(@Valid @RequestBody Prescription prescription) {
        prescriptionService.createPrescription(prescription);
    }

    @GetMapping("/doctor/getPrescription/{prescriptionId}")
    public Prescription getPrescription(@PathVariable Long prescriptionId) {
        return prescriptionService.getPrescription(prescriptionId);
    }

    @GetMapping("/doctor/getAllPrescription")
    public List<Prescription> getPrescriptions() {
        return prescriptionService.getAll();
    }

    @DeleteMapping("/doctor/deletePrescription/{prescriptionId}")
    public void deletePrescription(@PathVariable Long prescriptionId) {
        prescriptionService.deletePrescription(prescriptionId);
    }

}
