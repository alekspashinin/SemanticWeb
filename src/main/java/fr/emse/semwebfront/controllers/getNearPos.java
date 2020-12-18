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

import java.io.*;

@Setter
@Getter
public class getNearPos {

    static String[] perso = new String[2];
    float dstns;
    String nearlat = null;
    String nearlong = null;
    String name;

    public void getNearPosResult(String file) throws FileNotFoundException, IOException {

        perso = getPerso();
        int i = 1;
        String line = "";
        String cvsSplitBy = ",";
        String csvFile = file;
        dstns = 10000;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                line = line.replaceAll("#@lat\":\"http://example/base/", ",");
                line = line.replaceAll("@name\":\"http://example/base/", "");
                line = line.replaceAll("#@long\":\"http://example/base/", ",");
                line = line.replaceAll("#", "");
                System.out.println(line);
                String[] data = line.split(cvsSplitBy);

                if (i > 1) { //this is to skip first line of csv file that is not our values
                    String dic = distance(perso[0], perso[1], data[1], data[2]);
                    if (dstns > Float.parseFloat(dic)) { //this "if" stores the nearest station
                        dstns = Float.parseFloat(dic);
                        name = data[0];
                        nearlat = data[1];
                        nearlong = data[2];
                    }
                }
                i++;
            }
        }
        System.out.print(nearlat + " " + nearlong + " " + dstns);
    }

    static String distance(String lat1, String long1, String lat2, String long2) {
        float radlat1 = (float) (Math.PI * Double.parseDouble(lat1) / 180);
        float radlat2 = (float) (Math.PI * Double.parseDouble(lat2) / 180);
        float theta = (float) (Double.parseDouble(long1) - Double.parseDouble(long2));
        float radtheta = (float) (Math.PI * theta / 180);
        float dist = (float) (Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta));
        dist = (float) Math.acos(dist);
        dist = (float) (dist * 180 / Math.PI);
        dist = (float) (dist * 60 * 1.1515);
        dist = (float) (dist * 1.609344);
        String dstnce = Float.toString(dist);
        return dstnce;
    }

    public static String[] getPerso() throws IOException {

        final File persoFile = new File("tempoDB/perso.json");
        BufferedReader br = new BufferedReader(new FileReader(persoFile));
        String line = br.readLine();
        String[] data = line.split(" ");
        perso[0] = data[0];
        perso[1] = data[1];
        return perso;
    }

}
