package nl.huisartsPortal.prescription.service;

import nl.huisartsPortal.prescription.exceptions.DataNotFoundException;
import nl.huisartsPortal.prescription.model.Doctor;
import nl.huisartsPortal.prescription.model.Patient;
import nl.huisartsPortal.prescription.repository.DoctorDao;
import nl.huisartsPortal.prescription.repository.PatientDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by tarik on 6-11-2018.
 */
public class PatientServiceTest {

    @InjectMocks
    PatientService patientService;

    @Mock
    PatientDao patientDao;

    @Mock
    private DoctorDao doctorDao;

    Doctor doctor;
    Patient patientEntity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doctor = new Doctor("nl123456", "thomas", "Verhaar");
        patientEntity = new Patient("NL1002", "tarik", "has", doctor);

    }

    @Test
    public void getOne() throws Exception {
        when(patientDao.getPatientById(anyLong())).thenReturn(patientEntity);

        Patient actualPatient = patientService.getPatient(1l);
        assertNotNull(actualPatient);
        assertEquals("NL1002", actualPatient.getPolisNumber());
    }

    @Test(expected = DataNotFoundException.class)
    public void getOne_DataNotFoundException() {
        when(patientDao.getPatientById(anyLong())).thenReturn(null);
        patientService.getPatient(1l);
    }

    @Test
    public void testCreatePatient() {

        when(patientDao.findByFirstName(anyString())).thenReturn(null);
        when(doctorDao.findByPolisNumber(anyString())).thenReturn(null);
        when(patientDao.save(any(Patient.class))).thenReturn(patientEntity);

        Patient storedPatientDetails = patientService.createPatient(patientEntity);

        assertNotNull(storedPatientDetails);
        assertEquals(patientEntity.getFirstName(), storedPatientDetails.getFirstName());

    }

}