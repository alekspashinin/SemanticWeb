/**
 *
 *                      UJM * EMSE
 *
 *            Hamed RAHIMI * Aleksei PASHININ
 *
 *                 Semantic Web Project
 *
 */

package fr.emse.semwebfront.services;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvToTtl2 {


    public CsvToTtl2(String csvInput, String result) throws IOException {

        Model model = ModelFactory.createDefaultModel();
        Integer i = 1;
        String line = "";
        String cvsSplitBy = ",";

        Integer[][] hospitals;

        String csvFile = csvInput;
        PrintWriter out = new PrintWriter(result);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                System.out.println("DATA :" + line);
                if (data.length > 7) {
                    model.createResource("ID" + i)
                            .addProperty(model.createProperty("a"), "geo:SpatialThing")
                            .addProperty(model.createProperty("rdfs:label"), data[1])
                            .addProperty(model.createProperty("rdfs:SubClassOf"), "wd:Q13646") //wd:Q13646 is SNCF
                            .addProperty(model.createProperty("geo:lat"), data[3])
                            .addProperty(model.createProperty("geo:long"), data[4]);
                    i++;
                } else {
                    System.out.println("Invalid data!");
                }
            }
            model.createResource("wd:Q13646").addProperty(model.createProperty("rdfs:SubClassOf"), "wd:Q870"); //wd:Q870 is train
            model.write(out, "TURTLE");
        } finally {
            out.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String current = System.getProperty("user.dir");
        System.out.println("Current working directory in Java : " + current);
        CsvToTtl2 test = new CsvToTtl2("src/main/resources/static/csv/gares-tgv.csv", "src/main/resources/static/ttl/gares-tgv3.ttl");
    }
}
