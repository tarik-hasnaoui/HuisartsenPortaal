package nl.huisartsPortal.prescription.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
public class Medication implements Serializable {

    private static final long serialVersionUID = 1598430021607613043L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Enter the user first name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "medications")
    private List<Prescription> prescriptions;

    public Medication() {
    }

    public Medication(String name, List<Prescription> prescriptions) {
        this.name = name;
        this.prescriptions = prescriptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}

