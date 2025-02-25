package com.ipi.jva350;

import com.ipi.jva350.model.Entreprise;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class EntrepriseTest {

    @Test
    public void testEstDansPlage() {
        LocalDate date = LocalDate.of(2023, 7, 15);
        LocalDate debut = LocalDate.of(2023, 7, 1);
        LocalDate fin = LocalDate.of(2023, 7, 31);
        assertTrue(Entreprise.estDansPlage(date, debut, fin));

        date = LocalDate.of(2023, 8, 1);
        assertFalse(Entreprise.estDansPlage(date, debut, fin));
    }
    @Test
    public void testEstJourFerie() {
        LocalDate jour = LocalDate.of(2023, 1, 1);
        assertTrue(Entreprise.estJourFerie(jour));

        jour = LocalDate.of(2023, 1, 2);
        assertFalse(Entreprise.estJourFerie(jour));
    }
}