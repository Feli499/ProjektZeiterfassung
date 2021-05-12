package de.bbshaarentor.zeiterfassung.projekte;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjektContainerTest {

    @Test
    public void testCreateAndAddProjekt() {

        ProjektContainer projektContainer = new ProjektContainer();

        int basisId = 10;
        projektContainer.addProjekt(new Projekt(basisId, "Test"));

        String bezeichnungProjekt2 = "Test2";
        Projekt erstelltesProjekt = projektContainer.createAndAddProjekt(bezeichnungProjekt2);

        Assertions.assertEquals(bezeichnungProjekt2, erstelltesProjekt.getBezeichnung());
        Assertions.assertEquals(basisId + 1, erstelltesProjekt.getId());
        Assertions.assertFalse(projektContainer.isIdVerfuegbar(basisId + 1));
    }

    @Test
    public void testAddProjektMitDoppelterId() {

        ProjektContainer projektContainer = new ProjektContainer();

        int id = 10;

        Assertions.assertTrue(projektContainer.isIdVerfuegbar(id));
        projektContainer.addProjekt(new Projekt(id, "Test"));
        Assertions.assertFalse(projektContainer.isIdVerfuegbar(id));

        Assertions.assertThrows(IllegalArgumentException.class, () -> projektContainer.addProjekt(new Projekt(id, "Test2")));
    }
}
