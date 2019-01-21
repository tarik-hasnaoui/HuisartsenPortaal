package nl.huisartsportal.prescription.service;

import nl.huisartsportal.prescription.exceptions.DataNotFoundException;
import nl.huisartsportal.prescription.model.Doctor;
import nl.huisartsportal.prescription.model.Medication;
import nl.huisartsportal.prescription.model.Patient;
import nl.huisartsportal.prescription.model.Prescription;
import nl.huisartsportal.prescription.repository.DoctorDao;
import nl.huisartsportal.prescription.repository.MedicationDao;
import nl.huisartsportal.prescription.repository.PatientDao;
import nl.huisartsportal.prescription.repository.PrescriptionDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PrescriptionServiceTest {

    @Mock
    private PrescriptionDao prescriptionDao;

    @Mock
    private PatientDao patientDao;

    @Mock
    private DoctorDao doctorDao;

    @Mock
    private MedicationDao medicationDao;

    @InjectMocks
    private PrescriptionService prescriptionService;

    private Prescription prescriptionEntity;
    private Doctor doctorEntity;
    private Patient patientEntity;
    private Medication medicationsEntity;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doctorEntity = new Doctor("nl123456", "thomas", "Verhaar", null);
        patientEntity = new Patient("NL1002", "tarik", "has", null);
        medicationsEntity = new Medication("paracetomole", null);
        prescriptionEntity = new Prescription(LocalDate.of(2018, 2, 10), patientEntity, Arrays.asList(medicationsEntity), doctorEntity);
    }

    @Test
    public void testGetPrescription() {
        when(prescriptionDao.getPrescriptionByPrescriptionId(anyLong())).thenReturn(prescriptionEntity);
        Prescription actualPrescription = prescriptionService.getPrescription(1L);

        assertNotNull(actualPrescription);
        assertEquals("NL1002", actualPrescription.getPatient().getBsnNumber());
    }

    @Test(expected = DataNotFoundException.class)
    public void testGetPrescription_DataNotFoundException() {
        when(prescriptionDao.getPrescriptionByPrescriptionId(anyLong())).thenReturn(null);
        prescriptionService.getPrescription(1L);
    }

    @Test
    public void testGetAll() {
        when(prescriptionDao.findAll()).thenReturn(Arrays.asList(prescriptionEntity));
        List<Prescription> actualPrescription = prescriptionService.getAll();

        assertNotNull(actualPrescription);
        assertEquals("NL1002", actualPrescription.get(0).getPatient().getBsnNumber());
    }

    @Test(expected = DataNotFoundException.class)
    public void testGetAll_DataNotFoundException() {
        when(prescriptionDao.findAll()).thenReturn(null);
        prescriptionService.getPrescription(1L);
    }

    @Test
    public void createPrescription() {
        when(doctorDao.findByBsnNumber(anyString())).thenReturn(null);
        when(patientDao.getPatientByBsnNumber(anyString())).thenReturn(null);
        when(medicationDao.getMedicationByName(anyString())).thenReturn(null);
        when(prescriptionDao.getPrescriptionByPrescriptionId(anyLong())).thenReturn(null);

        when(prescriptionDao.save(any(Prescription.class))).thenReturn(prescriptionEntity);
        Prescription actualPrescription = prescriptionService.createPrescription(prescriptionEntity);

        assertNotNull(actualPrescription);
        assertEquals(prescriptionEntity.getDoctor().getFirstName(), actualPrescription.getDoctor().getFirstName());

    }

    @Test
    public void deletePrescription() {
    }

    @Test
    public void updatePrescription() {
    }

    @Test
    public void getPatientPrescriptions() {
    }

    @Test
    public void getPatientPrescription() {
    }
}