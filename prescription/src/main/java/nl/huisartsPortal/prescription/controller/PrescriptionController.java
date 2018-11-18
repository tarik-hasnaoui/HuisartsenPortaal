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

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping("/admin/addPrescription")
    public void addPrescription(@Valid @RequestBody Prescription prescription) {
        prescriptionService.createPrescription(prescription);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping("/admin/getPrescription/{prescriptionId}")
    public Prescription getPrescription(@PathVariable Long prescriptionId) {
        return prescriptionService.getPrescription(prescriptionId);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping("/admin/getAllPrescription")
    public List<Prescription> getPrescriptions() {
        return prescriptionService.getAll();
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @DeleteMapping("/admin/deletePrescription/{prescriptionId}")
    public void deletePrescription(@PathVariable Long prescriptionId) {
        prescriptionService.deletePrescription(prescriptionId);
    }

}
