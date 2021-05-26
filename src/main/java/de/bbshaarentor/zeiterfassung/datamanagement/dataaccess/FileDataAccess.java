package de.bbshaarentor.zeiterfassung.datamanagement.dataaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.bbshaarentor.zeiterfassung.User;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektBereichDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ProjektDaten;
import de.bbshaarentor.zeiterfassung.datamanagement.ZeitErfassungsDaten;

public class FileDataAccess implements DataAccess {

    public static final String PROJEKTDATEN_JSON_DATEINAME = "projektdatendatei.json";
    public static final String PROJEKTBEREICHDATEN_JSON_DATEINAME = "ProjektBereichDatendatei.json";
    public static final String ZEITERFASSUNGSDATEN_JSON_DATEINAME = "ZeitErfassungDatei.json";
    public static final String USER_JSON_DATEINAME = "UserDatei.json";

    private final File directory;

    public FileDataAccess(File directory) throws Exception {

        this.directory = directory;
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File projektdatenFile = new File(directory, PROJEKTDATEN_JSON_DATEINAME);
        if (!projektdatenFile.exists()) {
            this.saveProjektDaten(Collections.emptyList());
        }

        File projektbereichdatenFile = new File(directory, PROJEKTBEREICHDATEN_JSON_DATEINAME);
        if (!projektbereichdatenFile.exists()) {
            this.saveProjektBereichDaten(Collections.emptyList());
        }

        File zeiterfassungsdatenFile = new File(directory, ZEITERFASSUNGSDATEN_JSON_DATEINAME);
        if (!zeiterfassungsdatenFile.exists()) {
            this.saveZeitErfassung(Collections.emptyList());
        }

        File userdatenFile = new File(directory, USER_JSON_DATEINAME);
        if (!userdatenFile.exists()) {
            this.saveUser(Collections.emptyList());
        }
    }

    @Override
    public Collection<ProjektDaten> loadProjektDaten() throws Exception {

        Collection<ProjektDaten> datenLaden = new ArrayList<>();

        InputStream inputStream = new FileInputStream(new File(this.directory, PROJEKTDATEN_JSON_DATEINAME));
        JSONArray jsonArray = new JSONArray(new String(inputStream.readAllBytes()));

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            JSONArray projektBereiche = jsonObject.getJSONArray("projektBereicheIds");

            List<Long> ids = new ArrayList<>(projektBereiche.length());
            for (int projektBereichIndex = 0; projektBereichIndex < projektBereiche.length(); projektBereichIndex++) {
                ids.add(projektBereiche.getLong(projektBereichIndex));
            }

            datenLaden.add(new ProjektDaten(jsonObject.getLong("id"), jsonObject.getString("bezeichnung"), ids));
        }

        return datenLaden;
    }

    @Override
    public Collection<ProjektBereichDaten> loadProjektBereichDaten() throws Exception {

        Collection<ProjektBereichDaten> datenLaden = new ArrayList<>();

        InputStream inputStream = new FileInputStream(new File(this.directory, PROJEKTBEREICHDATEN_JSON_DATEINAME));
        JSONArray jsonArray = new JSONArray(new String(inputStream.readAllBytes()));

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            JSONArray projektBereiche = jsonObject.getJSONArray("zeitErfassungenIds");

            List<Long> ids = new ArrayList<>(projektBereiche.length());
            for (int projektBereichIndex = 0; projektBereichIndex < projektBereiche.length(); projektBereichIndex++) {
                ids.add(projektBereiche.getLong(projektBereichIndex));
            }

            datenLaden.add(new ProjektBereichDaten(jsonObject.getLong("id"), jsonObject.getString("bezeichnung"), ids));
        }

        return datenLaden;
    }

    @Override
    public Collection<ZeitErfassungsDaten> loadZeitErfassungen() throws Exception {

        Collection<ZeitErfassungsDaten> datenLaden = new ArrayList<>();

        InputStream inputStream = new FileInputStream(new File(this.directory, ZEITERFASSUNGSDATEN_JSON_DATEINAME));
        JSONArray jsonArray = new JSONArray(new String(inputStream.readAllBytes()));

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            JSONArray projektBereiche = jsonObject.getJSONArray("zeitErfassungenIds");

            datenLaden.add(new ZeitErfassungsDaten(jsonObject.getLong("id"), jsonObject.getString("bezeichnung"), jsonObject.getLong("logZeit"), jsonObject.getLong("startZeit"), jsonObject.getLong("userID")));
        }

        return datenLaden;
    }

    @Override
    public Collection<User> loadUsers() throws Exception {

        Collection<User> datenLaden = new ArrayList<>();

        InputStream inputStream = new FileInputStream(new File(this.directory, USER_JSON_DATEINAME));
        JSONArray jsonArray = new JSONArray(new String(inputStream.readAllBytes()));

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            datenLaden.add(new User(jsonObject.getLong("userId"), jsonObject.getString("name")));
        }

        return datenLaden;
    }

    @Override
    public void saveProjektDaten(Collection<ProjektDaten> projektDatenCollection) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(this.directory, PROJEKTDATEN_JSON_DATEINAME), projektDatenCollection);
    }

    @Override
    public void saveProjektBereichDaten(Collection<ProjektBereichDaten> projektBereichDatenCollection) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(this.directory, PROJEKTBEREICHDATEN_JSON_DATEINAME), projektBereichDatenCollection);
    }

    @Override
    public void saveZeitErfassung(Collection<ZeitErfassungsDaten> zeitErfassungen) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(this.directory, ZEITERFASSUNGSDATEN_JSON_DATEINAME), zeitErfassungen);
    }

    @Override
    public void saveUser(Collection<User> users) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(this.directory, USER_JSON_DATEINAME), users);
    }

}
