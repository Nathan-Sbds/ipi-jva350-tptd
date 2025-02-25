package com.ipi.jva350;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SalarieAideADomicileTest {

    @Test
    void testConstructor() {
        String nom = "John Doe";
        LocalDate moisDebutContrat = LocalDate.of(2022, 1, 1);
        LocalDate moisEnCours = LocalDate.of(2023, 7, 1);
        double joursTravaillesAnneeN = 10;
        double congesPayesAcquisAnneeN = 5;
        double joursTravaillesAnneeNMoins1 = 20;
        double congesPayesAcquisAnneeNMoins1 = 10;
        double congesPayesPrisAnneeNMoins1 = 5;

        SalarieAideADomicile salarie = new SalarieAideADomicile(nom, moisDebutContrat, moisEnCours,
                joursTravaillesAnneeN, congesPayesAcquisAnneeN, joursTravaillesAnneeNMoins1,
                congesPayesAcquisAnneeNMoins1, congesPayesPrisAnneeNMoins1);

        assertEquals(nom, salarie.getNom());
        assertEquals(moisDebutContrat, salarie.getMoisDebutContrat());
        assertEquals(moisEnCours, salarie.getMoisEnCours());
        assertEquals(joursTravaillesAnneeN, salarie.getJoursTravaillesAnneeN());
        assertEquals(congesPayesAcquisAnneeN, salarie.getCongesPayesAcquisAnneeN());
        assertEquals(joursTravaillesAnneeNMoins1, salarie.getJoursTravaillesAnneeNMoins1());
        assertEquals(congesPayesAcquisAnneeNMoins1, salarie.getCongesPayesAcquisAnneeNMoins1());
        assertEquals(congesPayesPrisAnneeNMoins1, salarie.getCongesPayesPrisAnneeNMoins1());

        assertEquals(0, salarie.getCongesPayesPris().size());

        //tests all the setter
        salarie.setNom("Jane Doe");
        salarie.setMoisDebutContrat(LocalDate.of(2022, 2, 1));
        salarie.setMoisEnCours(LocalDate.of(2023, 8, 1));
        salarie.setJoursTravaillesAnneeN(15);
        salarie.setCongesPayesAcquisAnneeN(10);
        salarie.setJoursTravaillesAnneeNMoins1(25);
        salarie.setCongesPayesAcquisAnneeNMoins1(15);
        salarie.setCongesPayesPrisAnneeNMoins1(10);

        assertEquals("Jane Doe", salarie.getNom());
        assertEquals(LocalDate.of(2022, 2, 1), salarie.getMoisDebutContrat());
        assertEquals(LocalDate.of(2023, 8, 1), salarie.getMoisEnCours());
        assertEquals(15, salarie.getJoursTravaillesAnneeN());
        assertEquals(10, salarie.getCongesPayesAcquisAnneeN());
        assertEquals(25, salarie.getJoursTravaillesAnneeNMoins1());
        assertEquals(15, salarie.getCongesPayesAcquisAnneeNMoins1());
        assertEquals(10, salarie.getCongesPayesPrisAnneeNMoins1());
    }

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

    @Test
    void testEstJourOuvrable() {
        LocalDate jour = LocalDate.of(2023, 7, 1);
        assertTrue(SalarieAideADomicile.estJourOuvrable(jour));

        jour = LocalDate.of(2023, 7, 2);
        assertFalse(SalarieAideADomicile.estJourOuvrable(jour));
    }
}