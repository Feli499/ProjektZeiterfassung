package de.bbshaarentor.zeiterfassung;
//import static org.assertj.core.api.Assertions.*;
import java.io.File;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//import com.fasterxml.jackson.databind.ObjectMapper;

import de.bbshaarentor.zeiterfassung.datamanagement.ProjektDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.dataaccess.FileDataAccess;

public class UserTest {


    @Test
    public void testGetter(){

        String herbertString = "Herbert";
        int userId = 1;
        User user = new User(userId, herbertString);

        Assertions.assertEquals(herbertString, user.getName());
        Assertions.assertEquals(userId, user.getId());
       
   
        
        
       //Create pfad 
       ProjektDaten projektdatenobj = new ProjektDaten(2, "hasdgfsdgsdhahhahaha", null);
       String pathname = "D:\\ZeitErfassungsProjekt";
       FileDataAccess fileDataAccess = new FileDataAccess( new File (pathname)); 
      
       //Save ProjektDaten
       Collection<ProjektDaten> projektDaten = new ArrayList<ProjektDaten>();
       projektDaten.add(projektdatenobj);
       fileDataAccess.saveProjektDaten(projektDaten);
       
       //Save User
       Collection<User> userss = new ArrayList<User>();
	   userss.add(user);
	   fileDataAccess.saveUser(userss);
       
       
      
	   
	   
	   
	   Collection<ProjektDaten> loadProjektDaten = fileDataAccess.loadProjektDaten();
	for (ProjektDaten projektDaten2 : loadProjektDaten) {
		System.out.println(projektDaten2.getId());
	//	Assertions.assertEquals( projektDaten2.getId(),2);
	}
	  
      //        projektdatenobj.getId();
//        projektdatenobj.getBezeichnung();
//        projektdatenobj.getProjektBereicheIds();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//      	  mapper.writeValue( new File("D:\\myJson12.json"), projektdatenobj);
//      	 
//        }catch (IOException e) {  
//            e.printStackTrace(); 
//            	
//       }
  }
}
