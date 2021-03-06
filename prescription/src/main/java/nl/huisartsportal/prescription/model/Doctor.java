package nl.huisartsportal.prescription.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
public class Doctor implements Serializable {

    private static final long serialVersionUID = 7945509928538708187L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String bsnNumber;

    @NotBlank(message = "Enter the user first name")
    private String firstName;

    @NotBlank(message = "Enter the user last name")
    private String lastName;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    public Doctor() {

    }

    public Doctor(String bsnNumber, String firstName, String lastName,
                  List<Prescription> prescriptions) {
        this.bsnNumber = bsnNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.prescriptions = prescriptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBsnNumber() {
        return bsnNumber;
    }

    public void setBsnNumber(String bsnNumber) {
        this.bsnNumber = bsnNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
