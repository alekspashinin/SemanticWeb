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

import fr.emse.semwebfront.controllers.modifyHistory;
import fr.emse.semwebfront.graphDB.addRdf;
import fr.emse.semwebfront.graphDB.climaCell;
import fr.emse.semwebfront.graphDB.createRepo;
import fr.emse.semwebfront.graphDB.getData;
import fr.emse.semwebfront.services.CsvToTtl;
import fr.emse.semwebfront.services.downloadData;
import fr.emse.semwebfront.services.regularCorrectQuery;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static fr.emse.semwebfront.globalValues.*;
import static fr.emse.semwebfront.queries.*;

@Getter
@Setter
public class preLoadData {

    public preLoadData(String arg) throws IOException, GitAPIException, ParseException {
        System.out.println("Loading PreloadData Values...");

        // -------- MAIN declaration -------- //
        // -------- URL declaration -------- //
        String urlVeloLyon = "https://www.data.gouv.fr/fr/datasets/r/d02cd196-ba9d-47f4-bbad-6ae0a9787d1b";
        String urlVeloMontpellier = "https://www.data.gouv.fr/fr/datasets/r/d02cd196-ba9d-47f4-bbad-6ae0a9787d1b";
        String urlVeloRenne = "https://www.data.gouv.fr/fr/datasets/r/d02cd196-ba9d-47f4-bbad-6ae0a9787d1b";
        String urlVeloSaintEtienne = "https://www.data.gouv.fr/fr/datasets/r/d02cd196-ba9d-47f4-bbad-6ae0a9787d1b";
        String urlVeloStrasbourg = "https://www.data.gouv.fr/fr/datasets/r/d02cd196-ba9d-47f4-bbad-6ae0a9787d1b";
        String urlHospitals = "https://www.data.gouv.fr/fr/datasets/r/c41a1919-fbd7-4d19-a8df-1719bef8b14a";
        String urlMuseums = "https://www.data.gouv.fr/fr/datasets/r/d02cd196-ba9d-47f4-bbad-6ae0a9787d1b";
        String urlPostOffices = "https://www.data.gouv.fr/fr/datasets/r/d02cd196-ba9d-47f4-bbad-6ae0a9787d1b";
        String urlSNCF = "https://www.data.gouv.fr/fr/datasets/r/d02cd196-ba9d-47f4-bbad-6ae0a9787d1b";
        String urlTGV = "https://www.data.gouv.fr/fr/datasets/r/939bfbb2-e5de-4085-894a-61eb6c74312d";
        String urlUniversity = "https://www.data.gouv.fr/fr/datasets/r/dae4b3d6-e35a-4686-aaf9-03060a5ff21a";

        // -------- CVS files declaration -------- //
        String csvVeloLyon = "/static/csv/VeloLyon.csv";
        String csvVeloMontpellier = "/static/csv/VeloMontpellier.csv";
        String csvVeloRenne = "/static/csv/VeloRenne.csv";
        String csvVeloSaintEtienne = "/static/csv/VeloSaintEtienne.csv";
        String csvVeloStrasbourg = "/static/csv/VeloStrasbourg.csv";
        String csvHospitals = "/static/csv/Hospitals.csv";
        String csvMuseums = "/static/csv/Museums.csv";
        String csvPostOffices = "/static/csv/PostOffices.csv";
        String csvSNCF = "/static/csv/SNCF.csv";
        String csvTGV = "/static/csv/TGV.csv";
        String csvUniversity = "/static/csv/University.csv";

        // -------- TTL files declaration -------- //
        String ttlVeloLyon = "/static/ttl/VeloLyon.ttl";
        String ttlVeloMontpellier = "/static/ttl/VeloMontpellier.ttl";
        String ttlVeloRenne = "/static/ttl/VeloRenne.ttl";
        String ttlVeloSaintEtienne = "/static/ttl/VeloSaintEtienne.ttl";
        String ttlVeloStrasbourg = "/static/ttl/VeloStrasbourg.ttl";
        String ttlHospitals = "/static/ttl/Hospitals.ttl";
        String ttlMuseums = "/static/ttl/Museums.ttl";
        String ttlPostOffices = "/static/ttl/PostOffices.ttl";
        String ttlSNCF = "/static/ttl/SNCF.ttl";
        String ttlTGV = "/static/ttl/TGV.ttl";
        String ttlUniversity = "/static/ttl/University.ttl";

        // -------- TTL files to send to GraphDB -------- //
        String ttlVeloLyonFinal = "/static/ttl-final/VeloLyonFinal.ttl";
        String ttlVeloMontpellierFinal = "/static/ttl-final/VeloMontpellierFinal.ttl";
        String ttlVeloRennesFinal = "/static/ttl-final/VeloRennesFinal.ttl";
        String ttlVeloSaintEtienneFinal = "/static/ttl-final/VeloSaintEtienneFinal.ttl";
        String ttlVeloStrasbourgFinal = "/static/ttl-final/VeloStrasbourgFinal.ttl";
        String ttlHospitalsFinal = "/static/ttl-final/HospitalsFinal.ttl";
        String ttlMuseumsFinal = "/static/ttl-final/MuseumsFinal.ttl";
        String ttlPostOfficesFinal = "/static/ttl-final/PostOfficesFinal.ttl";
        String ttlSNCFFinal = "/static/ttl-final/SNCFFinal.ttl";
        String ttlTGVFinal = "/static/ttl-final/TGVFinal.ttl";
        String ttlUniversityFinal = "/static/ttl-final/UniversitiesFinal.ttl";

        // -------- JSON files to send to Web App -------- //
        String jsonVeloLyon = "/static/json/VeloLyon";
        String jsonVeloMontpellier = "/static/json/VeloMontpellier";
        String jsonVeloRennes = "/static/json/VeloRennes";
        String jsonVeloSaintEtienne = "/static/json/VeloSaintEtienne";
        String jsonVeloStrasbourg = "/static/json/VeloStrasbourg";
        String jsonHospitals = "/static/json/Hospitals";
        String jsonMuseums = "/static/json/Museums";
        String jsonPostOffices = "/static/json/PostOffices";
        String jsonSNCF = "/static/json/SNCF";
        String jsonTGV = "/static/json/TGV";
        String jsonUniversity = "/static/json/University";

        switch (arg) {
            case DOWNLOAD:
                System.out.println("Downloading Data...");
                try {
                    downloadData.downloadData(urlVeloLyon, "/static/csv/VeloLyon.csv");
                    downloadData.downloadData(urlVeloMontpellier, "/static/csv/VeloMontpellier.csv");
                    downloadData.downloadData(urlVeloRenne, "/static/csv/VeloRenne.csv");
                    downloadData.downloadData(urlVeloSaintEtienne, "/static/csv/VeloSaintEtienne.csv");
                    downloadData.downloadData(urlVeloStrasbourg, "/static/csv/VeloStrasbourg.csv");
                    downloadData.downloadData(urlHospitals, "/static/csv/Hospitals.csv");
                    downloadData.downloadData(urlMuseums, "/static/csv/Museums.csv");
                    downloadData.downloadData(urlPostOffices, "/static/csv/PostOffices.csv");
                    downloadData.downloadData(urlSNCF, "/static/csv/SNCF.csv");
                    downloadData.downloadData(urlTGV, "/static/csv/TGV.csv");
                    downloadData.downloadData(urlUniversity, "/static/csv/University.csv");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case CSV_TO_TTL:
                System.out.println("Transform CSV to TTL...");
                // -------- Transform CSV to TTL -------- //
                CsvToTtl.CsvToTtl(csvVeloLyon,ttlVeloLyon, ttlVeloLyonFinal);
                CsvToTtl.CsvToTtl(csvVeloMontpellier,ttlVeloMontpellier, ttlVeloMontpellierFinal);
                CsvToTtl.CsvToTtl(csvVeloRenne,ttlVeloRenne, ttlVeloRennesFinal);
                CsvToTtl.CsvToTtl(csvVeloSaintEtienne,ttlVeloSaintEtienne, ttlVeloSaintEtienneFinal);
                CsvToTtl.CsvToTtl(csvVeloStrasbourg,ttlVeloStrasbourg, ttlVeloStrasbourgFinal);
                CsvToTtl.CsvToTtl(csvHospitals,ttlHospitals, ttlHospitalsFinal);
                CsvToTtl.CsvToTtl(csvMuseums,ttlMuseums, ttlMuseumsFinal);
                CsvToTtl.CsvToTtl(csvPostOffices,ttlPostOffices, ttlPostOfficesFinal);
                CsvToTtl.CsvToTtl(csvSNCF,ttlSNCF, ttlSNCFFinal);
                CsvToTtl.CsvToTtl(csvTGV,ttlTGV, ttlTGVFinal);
                CsvToTtl.CsvToTtl(csvUniversity, ttlUniversity, ttlUniversityFinal);
                break;

            case CREATE_REPO:
                System.out.println("Creating Repo...");
                createRepo initRepo = new createRepo();
                break;

            case UPLOAD_TO_GRAPHDB:
                System.out.println("Uploading TTL Data to New Repo...");
                addRdf.addRdfTurtle(ttlVeloLyonFinal);
                addRdf.addRdfTurtle(ttlVeloMontpellierFinal);
                addRdf.addRdfTurtle(ttlVeloRennesFinal);
                addRdf.addRdfTurtle(ttlVeloSaintEtienneFinal);
                addRdf.addRdfTurtle(ttlVeloStrasbourgFinal);
                addRdf.addRdfTurtle(ttlHospitalsFinal);
                addRdf.addRdfTurtle(ttlMuseumsFinal);
                addRdf.addRdfTurtle(ttlPostOfficesFinal);
                addRdf.addRdfTurtle(ttlSNCFFinal);
                addRdf.addRdfTurtle(ttlTGVFinal);
                addRdf.addRdfTurtle(ttlUniversityFinal);
                break;

            case GET_SPARQL_DATA:
                System.out.println("Extract Data By SPARQL Queries...");

                getData.getData(GET_VELO_LYON_DATA, jsonVeloLyon);
                modifyHistory.addToHistory(GET_VELO_LYON_DATA);
                getData.getData(GET_VELO_MONTPELLIER_DATA, jsonVeloMontpellier);
                modifyHistory.addToHistory(GET_VELO_MONTPELLIER_DATA);
                getData.getData(GET_VELO_RENNES_DATA, jsonVeloRennes);
                modifyHistory.addToHistory(GET_VELO_RENNES_DATA);
                getData.getData(GET_VELO_SAINT_ETIENNE_DATA, jsonVeloSaintEtienne);
                modifyHistory.addToHistory(GET_VELO_SAINT_ETIENNE_DATA);
                getData.getData(GET_VELO_STRASBOURG_DATA, jsonVeloStrasbourg);
                modifyHistory.addToHistory(GET_VELO_STRASBOURG_DATA);
                getData.getData(GET_TGV_DATA, jsonTGV);
                modifyHistory.addToHistory(GET_TGV_DATA);
                getData.getData(GET_SNCF_DATA, jsonSNCF);
                modifyHistory.addToHistory(GET_SNCF_DATA);
                getData.getData(GET_HOSPITAL_DATA, jsonHospitals);
                modifyHistory.addToHistory(GET_HOSPITAL_DATA);
                getData.getData(GET_MUSEUM_DATA, jsonMuseums);
                modifyHistory.addToHistory(GET_MUSEUM_DATA);
                getData.getData(GET_UNIVERSITY_DATA, jsonUniversity);
                modifyHistory.addToHistory(GET_UNIVERSITY_DATA);
                getData.getData(GET_POST_OFFICE_DATA, jsonPostOffices);
                modifyHistory.addToHistory(GET_POST_OFFICE_DATA);
                break;

            case SPARQL_TO_JSON:
                System.out.println("Transform Data and send to Web App...");
                regularCorrectQuery.sparqlTransform(jsonTGV, "jsonTGV.geojson");
                regularCorrectQuery.sparqlTransform(jsonSNCF, "jsonSNCF.geojson");
                regularCorrectQuery.sparqlTransform(jsonMuseums, "jsonMuseums.geojson");
                regularCorrectQuery.sparqlTransform(jsonUniversity, "jsonUniversity.geojson");
                regularCorrectQuery.sparqlTransform(jsonPostOffices, "jsonPostOffices.geojson");
                regularCorrectQuery.sparqlTransform(jsonVeloLyon, "jsonVeloLyon.json");
                regularCorrectQuery.sparqlTransform(jsonVeloMontpellier, "jsonVeloMontpellier.json");
                regularCorrectQuery.sparqlTransform(jsonVeloRennes, "jsonVeloRennes.json");
                regularCorrectQuery.sparqlTransform(jsonVeloSaintEtienne, "jsonVeloSaintEtienne.json");
                regularCorrectQuery.sparqlTransform(jsonVeloStrasbourg, "jsonVeloStrasbourg.json");
                regularCorrectQuery.sparqlTransform(jsonHospitals, "jsonHospitals.geojson");
                break;

            case GET_METEO:
                climaCell.getMeteo();
                break;


        }
    }
}
