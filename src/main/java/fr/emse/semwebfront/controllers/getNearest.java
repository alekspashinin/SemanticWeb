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

import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class getNearest {

    //static String[][] hospitals = new String[10000][10000];
    static String[][] museums = new String[10000][10000];
    //static String[][] postOffices = new String[10000][10000];
    static String[][] universities = new String[10000][10000];
    static Float[][] tempData = new Float[10000][10000];
    static Double[] tempResult = new Double[10000];
    static Integer[] perso = new Integer[2];
    static String[][] finalResult = new String[10000][10000];
    private String[] id = new String[100];

    public static String[][] getNearest() throws IOException, ParseException {
        //hospitals = getStructData("src/main/resources/static/json/Hospitals.txt", hospitals);
        museums = getStructData("src/main/resources/static/json/Museums.txt", museums);
        //postOffices = getStructData("src/main/resources/static/json/PostOffices.txt", postOffices);
        universities = getStructData("src/main/resources/static/json/University.txt", universities);
        perso = getPerso();

        //Double[] resHospital = subTraction(hospitals);
        Double[] resMuseums = subTraction(museums);
        //Double[] resPostOffices = subTraction(postOffices);
        Double[] resUniversities = subTraction(universities);

        //int winnerHospital = finalTarget(resHospital);
        int winnerMuseums = finalTarget(resMuseums);
        //int winnerPostOffices = finalTarget(resPostOffices);
        int winnerUniversities = finalTarget(resUniversities);

        //finalResult[0][0] = hospitals[winnerHospital][0];
        //finalResult[0][1] = hospitals[winnerHospital][1];
        //finalResult[0][2] = hospitals[winnerHospital][2];
        finalResult[1][0] = museums[winnerMuseums][0];
        finalResult[1][1] = museums[winnerMuseums][1];
        finalResult[1][2] = museums[winnerMuseums][2];
        //finalResult[2][0] = postOffices[winnerPostOffices][0];
        //finalResult[2][1] = postOffices[winnerPostOffices][1];
        //finalResult[2][2] = postOffices[winnerPostOffices][2];
        finalResult[3][0] = universities[winnerUniversities][0];
        finalResult[3][1] = universities[winnerUniversities][1];
        finalResult[3][2] = universities[winnerUniversities][2];

        return finalResult;
    }

    public static Integer finalTarget(Double[] input) {
        Double res = 0.0;
        int finalI = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] < res) {
                res = input[i];
                finalI = i;
            }
        }
        return finalI;
    }

    public static String[][] getStructData(String input, String[][] res) throws IOException, ParseException {

        String line = "";
        String fileSplitBy = ",";
        String csvFile = input;
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null && line.length() > 0) {
                System.out.println(line);
                line = line.replaceAll("#@lat\":\"http://example/base/", ",");
                line = line.replaceAll("@name\":\"http://example/base/", "");
                line = line.replaceAll("#@long\":\"http://example/base/", ",");
                line = line.replaceAll("#", "");
                System.out.println(line);
                String[] data = line.split(fileSplitBy);
                res[i][0] = data[0];
                res[i][1] = data[1];
                res[i][2] = data[2];
                i++;
            }
            return res;
        }
    }


    public static Integer[] getPerso() throws IOException {

        final File persoFile = new File("tempoDB/perso.json");
        BufferedReader br = new BufferedReader(new FileReader(persoFile));
        String line = br.readLine();
        String[] data = line.split(" ");
        perso[0] = Integer.parseInt(data[0]);
        perso[1] = Integer.parseInt(data[1]);
        return perso;
    }

    public static Double[] subTraction(String[][] input) {

        int i;

        for (i = 0; i < input.length; i++) {
            tempData[i][1] = Float.parseFloat(input[i][1]) - perso[0];
            tempData[i][2] = Float.parseFloat(input[i][2]) - perso[1];
            double mathRes = Math.pow(tempData[i][1], 2) + Math.pow(tempData[i][2], 2);
            tempResult[i] = Math.sqrt(mathRes);
        }
        return tempResult;

    }

    public static void main(String[] args) throws IOException, ParseException {
        String[][] gogo = getNearest();
        //System.out.println("NEAREST HOSPITAL: " +gogo[0][0] +" lat:" +gogo[0][1] +" lon:" +gogo[0][2]);
        System.out.println("NEAREST MUSEUM: " + gogo[1][0] + " lat:" + gogo[1][1] + " lon:" + gogo[1][2]);
        //System.out.println("NEAREST POST: " +gogo[2][0] +" lat:" +gogo[2][1] +" lon:" +gogo[2][2]);
        System.out.println("NEAREST UNIVERSITY: " + gogo[3][0] + " lat:" + gogo[3][1] + " lon:" + gogo[3][2]);
    }

}
