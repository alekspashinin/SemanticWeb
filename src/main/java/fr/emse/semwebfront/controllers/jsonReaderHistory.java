/**
 *
 *                      UJM * EMSE
 *
 *            Hamed RAHIMI * Aleksei PASHININ
 *
 *                 Semantic Web Project
 *
 */

package fr.emse.semwebfront.controllers;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

@Getter
@Setter
public class jsonReaderHistory {

    private static Integer stopList;
    JSONParser parser = new JSONParser();
    private String[] id = new String[1000];
    private String[] query = new String[1000];
    private String[] time = new String[1000];

    public static Integer getStopList() {
        return stopList;
    }

    public String[] jsonHistoryId() throws IOException, ParseException {
        Object obj = parser.parse(new FileReader("tempoDB/history.json"));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray array = (JSONArray) jsonObject.get("History");

        int i = 0;
        Iterator<Object> iterator = array.iterator();
        while (iterator.hasNext()) {
            Object it = iterator.next();
            JSONObject data = (JSONObject) it;
            id[i] = String.valueOf(data.get("ID"));
            i++;
        }
        stopList = i;
        return id;
    }

    public String[] jsonHistoryTime() throws IOException, ParseException {
        Object obj = parser.parse(new FileReader("tempoDB/history.json"));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray array = (JSONArray) jsonObject.get("History");

        int i = 0;
        Iterator<Object> iterator = array.iterator();
        while (iterator.hasNext()) {
            Object it = iterator.next();
            JSONObject data = (JSONObject) it;
            time[i] = String.valueOf(data.get("Time"));
            i++;
        }
        stopList = i;
        return time;
    }

    public String[] jsonHistoryQuery() throws IOException, ParseException {
        Object obj = parser.parse(new FileReader("tempoDB/history.json"));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray array = (JSONArray) jsonObject.get("History");

        int i = 0;
        Iterator<Object> iterator = array.iterator();
        while (iterator.hasNext()) {
            Object it = iterator.next();
            JSONObject data = (JSONObject) it;
            query[i] = String.valueOf(data.get("Query"));
            i++;
        }
        stopList = i;
        return query;
    }

    public String[] getIdArray() {
        return id.clone();
    }

    public String[] getQueryArray() {
        return query.clone();
    }

    public String[] getTimeArray() {
        return time.clone();
    }
}

