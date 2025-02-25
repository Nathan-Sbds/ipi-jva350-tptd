package com.ipi.jva350;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SalarieAideADomicileTest {

    @Test
    void testALegalementDroitADesCongesPayes() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(15);
        assertTrue(salarie.aLegalementDroitADesCongesPayes());

        salarie.setJoursTravaillesAnneeNMoins1(5);
        assertFalse(salarie.aLegalementDroitADesCongesPayes());
    }

    @Test
    void testCalculeJoursDeCongeDecomptesPourPlage() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        LocalDate dateDebut = LocalDate.of(2023, 7, 1);
        LocalDate dateFin = LocalDate.of(2023, 7, 10);
        Set<LocalDate> joursDecomptes = salarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);
        assertEquals(7, joursDecomptes.size());
    }
}