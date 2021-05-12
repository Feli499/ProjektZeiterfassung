package de.bbshaarentor.zeiterfassung;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {


    @Test
    public void testGetter(){

        String herbertString = "Herbert";
        int userId = 1;
        User user = new User(userId, herbertString);

        Assertions.assertEquals(herbertString, user.getName());
        Assertions.assertEquals(userId, user.getId());
    }
}
