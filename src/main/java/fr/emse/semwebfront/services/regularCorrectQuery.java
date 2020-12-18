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

import fr.emse.semwebfront.controllers.gitHubUpload;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.*;

public class regularCorrectQuery {

    public static void sparqlTransform(String sparqlData, String fileName) throws IOException, GitAPIException {

        String line;
        Integer i = 0;
        String input = "src/main/resources" + sparqlData + ".txt";
        PrintWriter out = new PrintWriter("src/main/resources/static/json/" + fileName, "UTF-8");
        out.println("{" + "\n" + "\"type\": \"FeatureCollection\"," + "\n" + "\"features\": [");
        try {
            BufferedReader bufferedReaderPlus = new BufferedReader(new FileReader(input));
            while ((line = bufferedReaderPlus.readLine()) != null) {
                i++;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(input));
            while ((line = bufferedReader.readLine()) != null) {
                i--;
                line = line.replaceAll("http://example/base/", "");
                line = line.replaceAll("#@lat\":\"", "\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[");
                line = line.replaceAll("#@long\":\"", ", ");
                if (i > 0) {
                    line = line.replaceAll("#", "]},\"id\":\"ak020frm4jci\"},");
                } else {
                    line = line.replaceAll("#", "]},\"id\":\"ak020frm4jci\"}");
                }
                line = line.replaceAll("@", "{\"type\":\"Feature\",\"properties\":{\"");
                out.println(line);
            }
            out.println("]}");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gitHubUpload upload = new gitHubUpload();
        upload.gitHubUpload("src/main/resources/static/json/" + fileName, fileName);
    }
}
