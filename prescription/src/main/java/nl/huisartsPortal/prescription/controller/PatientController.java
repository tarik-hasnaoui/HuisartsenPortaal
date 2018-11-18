package nl.huisartsPortal.prescription.controller;

import nl.huisartsPortal.prescription.model.Patient;
import nl.huisartsPortal.prescription.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by tarik on 5-11-2018.
 */
@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/user/allPatients")
    //@Secured("ROLE_USER")
    public List<Patient> getPatients() {
        return patientService.getAll();
    }

    @PostMapping("/admin/addPatient")
    public void addPatient(@Valid @RequestBody Patient patient) {
        patientService.createPatient(patient);
    }

    @GetMapping("/admin/getPatient/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return patientService.getPatient(id);
    }

    @PutMapping("/admin/updatePatient/{id}")
    public void updatePatient(@Valid @RequestBody Patient patient, @PathVariable Long id) {
        patientService.updatePatient(patient, id);
    }

    @DeleteMapping("/admin/deletePatient/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}
