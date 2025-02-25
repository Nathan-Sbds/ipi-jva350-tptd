package com.ipi.jva350;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SalarieAideADomicileRepositoryTest {

    @Autowired
    private SalarieAideADomicileRepository repository;

    @Test
    public void testFindByNom() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("John Doe");
        SalarieAideADomicile found = repository.findByNom("John Doe");
        assertNotNull(found);
        assertEquals("John Doe", found.getNom());
    }

    @Test
    public void testPartCongesPrisTotauxAnneeNMoins1() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setCongesPayesAcquisAnneeNMoins1(20);
        salarie.setCongesPayesPrisAnneeNMoins1(10);
        repository.save(salarie);
        Double part = repository.partCongesPrisTotauxAnneeNMoins1();
        assertEquals(0.5, part);
    }
}