package com.ipi.jva350;

import com.ipi.jva350.model.Entreprise;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntrepriseTest {

    @Test
    void testEstDansPlage() {
        LocalDate date = LocalDate.of(2023, 7, 15);
        LocalDate debut = LocalDate.of(2023, 7, 1);
        LocalDate fin = LocalDate.of(2023, 7, 31);
        assertTrue(Entreprise.estDansPlage(date, debut, fin));

        date = LocalDate.of(2023, 8, 1);
        assertFalse(Entreprise.estDansPlage(date, debut, fin));
    }

    @Test
    void testEstJourFerie() {
        LocalDate jour = LocalDate.of(2023, 1, 1);
        assertTrue(Entreprise.estJourFerie(jour));
    }

    @Test
    void testBissextile() {
        assertTrue(Entreprise.bissextile(2016));
        assertTrue(Entreprise.bissextile(2020));
        assertTrue(Entreprise.bissextile(2024));

        assertFalse(Entreprise.bissextile(2015));
        assertFalse(Entreprise.bissextile(2017));
        assertFalse(Entreprise.bissextile(2018));

        assertFalse(Entreprise.bissextile(1900));
        assertTrue(Entreprise.bissextile(2000));
    }

    @ParameterizedTest
    @CsvSource({
            "2023-01-01, 0.06666666666666667",
            "2023-02-01, 0.23333333333333334",
            "2023-03-01, 0.4",
            "2023-04-01, 0.4666666666666667",
            "2023-05-01, 0.5333333333333333",
            "2023-06-01, 0.6",
            "2023-07-01, 0.6666666666666666",
            "2023-08-01, 0.7333333333333333",
            "2023-09-01, 0.8",
            "2023-10-01, 0.8666666666666667",
            "2023-11-01, 0.9333333333333333",
            "2023-12-01, 1.0"
    })
    void testProportionPondereeDuMois(String date, double expected) {
        assertEquals(expected, Entreprise.proportionPondereeDuMois(LocalDate.parse(date)));
    }

    @Test
    void testGetPremierJourAnneeDeConges() {
        LocalDate date = LocalDate.of(2023, 7, 1);
        assertEquals(LocalDate.of(2023, 6, 1), Entreprise.getPremierJourAnneeDeConges(date));

        date = LocalDate.of(2023, 5, 1);
        assertEquals(LocalDate.of(2022, 6, 1), Entreprise.getPremierJourAnneeDeConges(date));

        assertNull(Entreprise.getPremierJourAnneeDeConges(null));
    }

    @Test
    void testJoursFeries() {
        LocalDate now = LocalDate.of(2023, 1, 1);
        List<LocalDate> joursFeries = Entreprise.joursFeries(now);
        assertEquals(11, joursFeries.size());
        assertTrue(joursFeries.contains(LocalDate.of(2023, 1, 1)));
        assertTrue(joursFeries.contains(LocalDate.of(2023, 4, 10))); // Lundi de Pâques
        assertTrue(joursFeries.contains(LocalDate.of(2023, 5, 1)));
        assertTrue(joursFeries.contains(LocalDate.of(2023, 5, 8)));
        assertTrue(joursFeries.contains(LocalDate.of(2023, 5, 29))); // Lundi de Pentecôte
        assertTrue(joursFeries.contains(LocalDate.of(2023, 8, 15)));
        assertTrue(joursFeries.contains(LocalDate.of(2023, 11, 1)));
        assertTrue(joursFeries.contains(LocalDate.of(2023, 11, 11)));
        assertTrue(joursFeries.contains(LocalDate.of(2023, 12, 25)));
    }
}