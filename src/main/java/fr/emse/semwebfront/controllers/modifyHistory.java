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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class modifyHistory {

    static Integer historyId = 1;

    public static void addToHistory(String querySparql) {
        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader("tempoDB/history.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("History");

            JSONObject query = new JSONObject();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            query.put("ID", historyId);
            query.put("Query", querySparql);
            query.put("Time", dateFormat.format(date));

            array.add(query);


            FileWriter file = new FileWriter("tempoDB/history.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();

            historyId++;

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteHistory() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader("tempoDB/history.json"));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray array = (JSONArray) jsonObject.get("History");
        array.clear();
        FileWriter file = new FileWriter("tempoDB/history.json");
        file.write(jsonObject.toJSONString());
        file.flush();
        file.close();
    }
}

