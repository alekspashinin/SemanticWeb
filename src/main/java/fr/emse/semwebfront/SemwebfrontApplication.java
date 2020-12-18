/**
 *
 *                      UJM * EMSE
 *
 *            Hamed RAHIMI * Aleksei PASHININ
 *
 *                 Semantic Web Project
 *
 */

package fr.emse.semwebfront;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static fr.emse.semwebfront.globalValues.*;

import java.io.IOException;

@SpringBootApplication
public class SemwebfrontApplication {

    public static void main(String[] args) throws IOException, GitAPIException, ParseException {

        SpringApplication.run(SemwebfrontApplication.class, args);
        System.out.println("Starting Web App...");
        //preLoadData loadData = new preLoadData(DOWNLOAD);
        //preLoadData transformData = new preLoadData(CSV_TO_TTL);
        //preLoadData createRepo = new preLoadData(CREATE_REPO);
        //preLoadData uploadToDB = new preLoadData(UPLOAD_TO_GRAPHDB);
        //preLoadData getSparqlData = new preLoadData(GET_SPARQL_DATA);
        //preLoadData sparqlToJson = new preLoadData(SPARQL_TO_JSON);
        //preLoadData meteo = new preLoadData(GET_METEO);
        System.out.println("Loading Data is finished!");
    }

}
