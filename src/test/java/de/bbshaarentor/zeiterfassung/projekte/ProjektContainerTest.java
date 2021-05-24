package de.bbshaarentor.zeiterfassung.projekte;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.bbshaarentor.zeiterfassung.datamanagement.dataaccess.DataAccess;

public class ProjektContainerTest {

    /*
     * TODO: Die Tests müssen noch die korrekte Interaktion mit dem ProjektContainer Abbilden.
     */
    @Test
    public void testCreateAndAddProjekt() {

        DataAccess dataAccess = Mockito.mock(DataAccess.class);
        ProjektContainer projektContainer = new ProjektContainer(dataAccess);

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

        DataAccess dataAccess = Mockito.mock(DataAccess.class);
        ProjektContainer projektContainer = new ProjektContainer(dataAccess);

        int id = 10;

        Assertions.assertTrue(projektContainer.isIdVerfuegbar(id));
        projektContainer.addProjekt(new Projekt(id, "Test"));
        Assertions.assertFalse(projektContainer.isIdVerfuegbar(id));

        Assertions.assertThrows(IllegalArgumentException.class, () -> projektContainer.addProjekt(new Projekt(id, "Test2")));
    }
}
