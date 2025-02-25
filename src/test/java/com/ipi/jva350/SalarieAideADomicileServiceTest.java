package com.ipi.jva350;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SalarieAideADomicileServiceTest {

    private SalarieAideADomicileRepository repository;
    private SalarieAideADomicileService service;

    @BeforeEach
    void setUp() {
        repository = mock(SalarieAideADomicileRepository.class);
        service = new SalarieAideADomicileService(repository);
    }

    @Test
    void testCreerSalarieAideADomicile() throws SalarieException {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("John Doe");

        when(repository.findByNom("John Doe")).thenReturn(null);

        service.creerSalarieAideADomicile(salarie);

        verify(repository).save(salarie);
    }

    @Test
    void testCreerSalarieAideADomicileThrowsExceptionIfNameExists() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("John Doe");

        when(repository.findByNom("John Doe")).thenReturn(salarie);

        assertThrows(SalarieException.class, () -> service.creerSalarieAideADomicile(salarie));
    }

    @Test
    void testCreerSalarieAideADomicileThrowsExceptionIfIdProvided() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setId(1L);
        salarie.setNom("John Doe");

        assertThrows(SalarieException.class, () -> service.creerSalarieAideADomicile(salarie));
    }

    @Test
    void testCalculeLimiteEntrepriseCongesPermis() {
        LocalDate moisEnCours = LocalDate.of(2023, 7, 1);
        double congesPayesAcquisAnneeNMoins1 = 20.0;
        LocalDate moisDebutContrat = LocalDate.of(2020, 1, 1);
        LocalDate premierJourDeConge = LocalDate.of(2023, 7, 1);
        LocalDate dernierJourDeConge = LocalDate.of(2023, 7, 10);

        when(repository.partCongesPrisTotauxAnneeNMoins1()).thenReturn(0.5);

        long limite = service.calculeLimiteEntrepriseCongesPermis(moisEnCours, congesPayesAcquisAnneeNMoins1,
                moisDebutContrat, premierJourDeConge, dernierJourDeConge);

        assertEquals(15, limite);
    }

    @Test
    void testAjouteConge() {
        SalarieAideADomicile salarie = mock(SalarieAideADomicile.class);
        LocalDate jourDebut = LocalDate.of(2023, 7, 1);
        LocalDate jourFin = LocalDate.of(2023, 7, 10);

        when(salarie.aLegalementDroitADesCongesPayes()).thenReturn(true);
        when(salarie.calculeJoursDeCongeDecomptesPourPlage(jourDebut, jourFin))
                .thenReturn(new LinkedHashSet<>(Set.of(jourDebut, jourFin)));
        when(salarie.getMoisEnCours()).thenReturn(LocalDate.of(2023, 7, 1));
        when(salarie.getCongesPayesAcquisAnneeNMoins1()).thenReturn(20.0);
        when(salarie.getMoisDebutContrat()).thenReturn(LocalDate.of(2020, 1, 1));
        when(salarie.getCongesPayesRestantAnneeNMoins1()).thenReturn(20.0);

        verify(salarie).setCongesPayesPrisAnneeNMoins1(50);
        verify(repository).save(salarie);
    }

    @Test
    void testClotureMois() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setMoisEnCours(LocalDate.of(2023, 5, 1));
        service.clotureMois(salarie, 20);

        assertEquals(LocalDate.of(2023, 6, 1), salarie.getMoisEnCours());
    }

    @Test
    void testClotureAnnee() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setMoisEnCours(LocalDate.of(2023, 6, 1));
        salarie.setJoursTravaillesAnneeN(10);
        salarie.setCongesPayesAcquisAnneeN(5);
        salarie.setCongesPayesPris(new LinkedHashSet<>(Set.of(LocalDate.of(2023, 6, 1))));

        service.clotureAnnee(salarie);

        assertEquals(10, salarie.getJoursTravaillesAnneeNMoins1());
        assertEquals(5, salarie.getCongesPayesAcquisAnneeNMoins1());
        assertEquals(0, salarie.getJoursTravaillesAnneeN());
        assertEquals(0, salarie.getCongesPayesAcquisAnneeN());
        assertFalse(salarie.getCongesPayesPris().isEmpty());
        verify(repository).save(salarie);
    }
}