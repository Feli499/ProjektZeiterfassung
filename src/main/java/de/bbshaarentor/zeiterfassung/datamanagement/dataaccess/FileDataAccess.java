package de.bbshaarentor.zeiterfassung.datamanagement.dataaccess;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.bbshaarentor.zeiterfassung.User;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektBereichDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektDaten;
import de.bbshaarentor.zeiterfassung.projekte.ZeitErfassung;

public class FileDataAccess implements DataAccess {

    private final File directory;

    public FileDataAccess(File directory) {

        this.directory = directory;
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @Override
    public Collection<ProjektDaten> loadProjektDaten() {
        Collection<ProjektDaten> datenLaden = new ArrayList<ProjektDaten>();

        // ObjectMapper mapper = new ObjectMapper();
        try {
            JSONParser parser = new JSONParser();
            // Use JSONObject for simple JSON and JSONArray for array of JSON.
            ProjektDaten data = (ProjektDaten) parser.parse(new FileReader(this.directory + "\\george2.json"));// path to the JSON file.

            datenLaden.add(data);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return datenLaden;
    }

    @Override
    public Collection<ProjektBereichDaten> loadProjektBereichDaten() {
        return null;
    }

    @Override
    public Collection<ZeitErfassung> loadZeitErfassungen() {
        return null;
    }

    @Override
    public Collection<User> loadUsers() {
        return null;
    }

    @Override
    public void saveProjektDaten(Collection<ProjektDaten> projektDaten) {

        for (ProjektDaten projektDaten2 : projektDaten) {
            // new ProjektDaten(projektDaten2.getId(),projektDaten2.getBezeichnung(),projektDaten2.getProjektBereicheIds());

            ObjectMapper mapper = new ObjectMapper();
            try {

                mapper.writeValue(new File(this.directory + "\\projektdatendatei.json"), projektDaten2);

            } catch (IOException e) {
                e.printStackTrace();

            }
        }

    }

    @Override
    public void saveProjektBereichDaten(Collection<ProjektBereichDaten> projektBereichDaten) {
        for (ProjektBereichDaten projektBereichDaten2 : projektBereichDaten) {
            new ProjektBereichDaten(projektBereichDaten2.getId(), projektBereichDaten2.getBezeichnung(), projektBereichDaten2.getZeitErfassungenIds());

            ObjectMapper mapper = new ObjectMapper();
            try {

                mapper.writeValue(new File(this.directory + "\\ProjektBereichDatendatei.json"), projektBereichDaten2);

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public void saveZeitErfassung(Collection<ZeitErfassung> zeitErfassungen) {
        for (ZeitErfassung ZeitErfassung2 : zeitErfassungen) {
            new ZeitErfassung(ZeitErfassung2.getId(), ZeitErfassung2.getKommentar(), ZeitErfassung2.getLogZeit(), ZeitErfassung2.getStartZeit(), ZeitErfassung2.getBenutzer());

            ObjectMapper mapper = new ObjectMapper();
            try {

                mapper.writeValue(new File(this.directory + "\\ZeitErfassungDatei.json"), ZeitErfassung2);

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public void saveUser(Collection<User> users) {

        for (User user2 : users) {
            new User(user2.getId(), user2.getName());

            ObjectMapper mapper = new ObjectMapper();
            try {

                mapper.writeValue(new File(this.directory + "\\UserDatei.json"), user2);

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

}
