package nl.huisartsPortal.prescription.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Prescription implements Serializable {

    private static final long serialVersionUID = 5447640720244978040L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prescriptionId;

    public Prescription() {
    }

    public Prescription(Patient patient, List<Medication> medications, Doctor doctor) {
        this.patient = patient;
        this.medications = medications;
        this.doctor = doctor;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "prescription_medication", joinColumns = @JoinColumn(name = "prescription_id", referencedColumnName = "prescriptionId"), inverseJoinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"))
    private List<Medication> medications;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
}
