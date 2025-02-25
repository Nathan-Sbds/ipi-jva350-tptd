package com.ipi.jva350;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.service.SalarieAideADomicileService;
import com.ipi.jva350.exception.SalarieException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

public class SalarieAideADomicileServiceTest {

    @Test
    public void testAjouteConge() throws SalarieException {
        SalarieAideADomicileService service = mock(SalarieAideADomicileService.class);
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        LocalDate jourDebut = LocalDate.of(2023, 7, 1);
        LocalDate jourFin = LocalDate.of(2023, 7, 10);

        doNothing().when(service).ajouteConge(salarie, jourDebut, jourFin);
        service.ajouteConge(salarie, jourDebut, jourFin);
        verify(service).ajouteConge(salarie, jourDebut, jourFin);
    }
}