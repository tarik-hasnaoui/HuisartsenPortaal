package nl.huisartsPortal.prescription;

import nl.huisartsPortal.prescription.model.Doctor;
import nl.huisartsPortal.prescription.model.Medication;
import nl.huisartsPortal.prescription.model.Patient;
import nl.huisartsPortal.prescription.model.Prescription;
import nl.huisartsPortal.prescription.repository.DoctorDao;
import nl.huisartsPortal.prescription.repository.MedicationDao;
import nl.huisartsPortal.prescription.repository.PatientDao;
import nl.huisartsPortal.prescription.repository.PrescriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.*;


@SpringBootApplication
@EnableDiscoveryClient
public class PrescriptionApplication {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private PrescriptionDao prescriptionDao;

    @Autowired
    private MedicationDao medicationDao;

    public static void main(String[] args) {
        SpringApplication.run(PrescriptionApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {

            Doctor doctor1 = new Doctor("NL9876", "Martijn", "Van Dam", null);
            Doctor doctor2 = new Doctor("NL8765", "Verhaar", "Sana", null);

            Patient patient1 = new Patient("NL100", "Tarik", "Hasnaoui", null);
            Patient patient2 = new Patient("NL200", "Edgar", "Kirchner", null);
            Patient patient3 = new Patient("NL300", "Murat", "Biligri", null);

            Medication medication1 = new Medication("Paracetamol 500MG", null);
            Medication medication2 = new Medication("Atomoxetine 250MG", null);
            Medication medication3 = new Medication("Omeprazol 100MG", null);
            Medication medication4 = new Medication("Pantoprazol 50MG", null);
            Medication medication5 = new Medication("Tramadole 10MG", null);

            Prescription prescription1 = new Prescription(new Date(2018, 2, 10), patient1, Arrays.asList(medication1, medication2), doctor1);
            Prescription prescription2 = new Prescription(new Date(2018, 11, 20), patient2, Arrays.asList(medication3, medication4), doctor1);
            Prescription prescription3 = new Prescription(new Date(2018, 10, 20), patient3, Arrays.asList(medication5), doctor2);
            Prescription prescription4 = new Prescription(new Date(2018, 11, 25), patient1, Arrays.asList(medication3, medication4), doctor1);
            prescriptionDao.saveAll(Arrays.asList(prescription1, prescription2, prescription3, prescription4));


            patient1.setPrescriptions(Arrays.asList(prescription1, prescription4));
            patient2.setPrescriptions(Arrays.asList(prescription2));
            patient3.setPrescriptions(Arrays.asList(prescription3));
            patientDao.saveAll(Arrays.asList(patient1, patient2, patient3));

            doctor1.setPrescriptions(Arrays.asList(prescription1, prescription2, prescription4));
            doctor2.setPrescriptions(Arrays.asList(prescription3));
            doctorDao.saveAll(Arrays.asList(doctor1, doctor2));

            prescription1.getMedications().get(0).setPrescriptions(Arrays.asList(prescription1));
            prescription1.getMedications().get(1).setPrescriptions(Arrays.asList(prescription1));
            prescription2.getMedications().get(0).setPrescriptions(Arrays.asList(prescription2));
            prescription2.getMedications().get(1).setPrescriptions(Arrays.asList(prescription2));
            prescription3.getMedications().get(0).setPrescriptions(Arrays.asList(prescription3));
            prescription4.getMedications().get(0).setPrescriptions(Arrays.asList(prescription4));
            prescription4.getMedications().get(1).setPrescriptions(Arrays.asList(prescription4));
            medicationDao.saveAll(Arrays.asList(medication1, medication2, medication3, medication4, medication5));


        };
    }
}
