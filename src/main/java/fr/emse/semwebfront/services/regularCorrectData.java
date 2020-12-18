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

import java.io.*;

public class regularCorrectData {

    public void regularCorrects(String ttlFile, String fileName) throws IOException {

        String line;
        String input = ttlFile;
        PrintWriter out = new PrintWriter("src/main/resources/static/ttl-final/" + fileName, "UTF-8");
        out.println("@prefix base: <http://example/base/> .");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(input));
            while ((line = bufferedReader.readLine()) != null) {
                // The first line is always the column names/predicates/attributes.
                line = line.replaceAll("<#:#", "");
                line = line.replaceAll("#:#", "");
                line = line.replaceAll("> \"", " ");
                line = line.replaceAll(">\"", ">");
                line = line.replaceAll("<geo:SpatialThing>", "geo:SpatialThing");
                line = line.replaceAll("\"", "");
                line = line.replaceAll("\\\\", "");
                line = line.replaceAll(" ;", ";");
                out.println(line);
                if (line.indexOf("rdfs:lable") > 0) {
                    out.println("    rdfs:SubClassOf wd:Q3918;");
                }
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
