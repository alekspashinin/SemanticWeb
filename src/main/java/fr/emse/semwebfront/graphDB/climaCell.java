/**
 *
 *                      UJM * EMSE
 *
 *            Hamed RAHIMI * Aleksei PASHININ
 *
 *                 Semantic Web Project
 *
 */

package fr.emse.semwebfront.graphDB;

import fr.emse.semwebfront.controllers.gitHubUpload;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static fr.emse.semwebfront.globalValues.CLIMA_API_KEY;

public class climaCell {

    String lat;
    String lon;
    ArrayList<String> city = new ArrayList<>();
    ArrayList<String> lats = new ArrayList<>();
    ArrayList<String> lons = new ArrayList<>();

    public climaCell(String lat, String lon) {
        System.out.println("LAT :" + lat);
        System.out.println("LON :" + lon);
        this.lat = lat;
        this.lon = lon;
        climaCell.climaCellSpec(lat, lon);
    }

    public climaCell() {
    }

    public static void climaCellDelete() throws IOException {
        File jsonFile = new File("tempoDB/data.json");
        FileWriter fw = new FileWriter(jsonFile);
        PrintWriter pwOb = new PrintWriter(fw, false);
        pwOb.flush();
        pwOb.close();
    }

    public static void climaCellSpec(String lat, String lon) {
        try {
            URL url = new URL("https://api.climacell.co/v3/weather/realtime?lat=" + lat + "&lon=" + lon + "&unit_system=si&fields=temp&apikey=" + CLIMA_API_KEY);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            Writer output;
            output = new BufferedWriter(new FileWriter("tempoDB/data.json", true));
            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                    output.write(inline);
                    output.write("\n");
                }
                output.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getMeteo() throws IOException, GitAPIException {
        climaCell temp = new climaCell();
        temp.climaCellDelete();
        temp.getTemperature();
        gitHubUpload upload = new gitHubUpload();
        upload.gitHubUpload("src/main/resources/static/json/meteoCities.geojson", "meteoCities.geojson");
    }

    public void getTemperature() throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("tempoData/cities.txt"));

        for (String line : lines) {
            String[] fields = line.split(",");
            city.add(fields[0]);
            lats.add(fields[1]);
            lons.add(fields[2]);
        }


        for (int i = 0; i < city.size(); i++) {
            climaCellSpec(lons.get(i), lats.get(i));
        }

        regularMeteo("tempoDB/data.json");


    }

    public void regularMeteo(String inlFile) throws IOException {

        String line;
        Integer i = 0;
        String input = inlFile;
        PrintWriter out = new PrintWriter("src/main/resources/static/json/meteoCities.geojson", "UTF-8");
        out.println("{");
        out.println("\"type\": \"FeatureCollection\",");
        out.println("\"features\": [");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(input));
            while ((line = bufferedReader.readLine()) != null && line.length() > 1) {
                System.out.println(i);
                System.out.println("LENGTH: " + line.length());
                System.out.println("LINE: " + line);
                // The first line is always the column names/predicates/attributes.
                line = line.replaceAll(",\"units\":\"C\"},\"observation_time.*", "},\"geometry\":{\"type\":\"Point\",\"coordinates\":[" + lats.get(i) + ", " + lons.get(i) + "]},\"id\":\"ak020frm4jci\"},");
                line = line.replaceAll(".*value\":", "{\"type\":\"Feature\",\"properties\":{\"name\":\"" + city.get(i) + "\", \"temp\":");
                line = line.replaceAll("1.862801, 50.954468]},\"id\":\"ak020frm4jci\"},", "1.862801, 50.954468]},\"id\":\"ak020frm4jri\"}");
                out.println(line);
                i++;

            }
            out.println("]}");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
