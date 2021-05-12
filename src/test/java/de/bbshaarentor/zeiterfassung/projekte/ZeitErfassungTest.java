package de.bbshaarentor.zeiterfassung.projekte;

import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.bbshaarentor.zeiterfassung.User;

public class ZeitErfassungTest {

    @Test
    public void testCompareTo() {

        int abstand = 10000;

        User user = Mockito.mock(User.class);

        ZeitErfassung zeitErfassung1 = new ZeitErfassung(1, "", System.currentTimeMillis(), System.currentTimeMillis(), System.currentTimeMillis() + 1000, user);
        ZeitErfassung zeitErfassung2 = new ZeitErfassung(2, "", System.currentTimeMillis() - abstand, System.currentTimeMillis() - abstand, System.currentTimeMillis() - 9000, user);
        ZeitErfassung zeitErfassung3 = new ZeitErfassung(3, "", System.currentTimeMillis() + abstand, System.currentTimeMillis() + abstand, System.currentTimeMillis() + 11000, user);

        Assertions.assertEquals(-abstand, zeitErfassung1.compareTo(zeitErfassung2));
        Assertions.assertEquals(abstand, zeitErfassung1.compareTo(zeitErfassung3));

        Assertions.assertEquals(abstand, zeitErfassung2.compareTo(zeitErfassung1));
        Assertions.assertEquals(abstand * 2, zeitErfassung2.compareTo(zeitErfassung3));

        Assertions.assertEquals(-abstand, zeitErfassung3.compareTo(zeitErfassung1));
        Assertions.assertEquals(abstand * -2, zeitErfassung3.compareTo(zeitErfassung2));

        /* Hier Testen wir die Sortierung in einer TreeSet, auch wenn die Test oben die Sortierung zwar schon bestätigen, aber kost ja nichts :P */
        TreeSet<ZeitErfassung> treeSet = new TreeSet<>();
        treeSet.add(zeitErfassung1);
        treeSet.add(zeitErfassung2);
        treeSet.add(zeitErfassung3);

        /* Wir erwarten den neusten zuerst */
        String expectedIdOrder = "312";
        String actualIdOrder = "";

        for (ZeitErfassung z : treeSet) {
            actualIdOrder = String.format("%s%d", actualIdOrder, z.getId());
        }

        Assertions.assertEquals(expectedIdOrder, actualIdOrder);
    }
}
