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

import org.openrdf.model.Model;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.Rio;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvToTtl {

    private static Model ttlModel = new LinkedHashModel();

    public CsvToTtl() {
    }

    public static void CsvToTtl(String csvFile, String ttlFile, String finalName) throws IOException {

        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(ttlFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        doTTL(csvFile);
        RDFWriter writer = Rio.createWriter(RDFFormat.TURTLE, out);
        try {
            writer.startRDF();
            writer.handleNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
            writer.handleNamespace("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
            writer.handleNamespace("geo", "http://www.opengis.net/ont/geosparql#");
            writer.handleNamespace("foaf", "http://xmlns.com/foaf/0.1/");
            writer.handleNamespace("skos", "http://www.w3.org/2004/02/skos/core#");
            writer.handleNamespace("xsd", "http://www.w3.org/2001/XMLSchema#");
            writer.handleNamespace("wd", "http://www.wikidata.org/entity/");
            ttlModel.forEach(writer::handleStatement);
            writer.endRDF();
        } catch (RDFHandlerException e) {
        }
        regularCorrectData correct = new regularCorrectData();
        correct.regularCorrects(ttlFile, finalName);
    }

    private static void doTTL(String csvFile) {

        String line;
        String delimiter = ",";
        List<String> alphabetList = new ArrayList<String>(Arrays.asList("a", "rdfs:lable", "geo:lat", "geo:long"));
        List<String> listBase = new ArrayList<String>(Arrays.asList("\"code UAI\"", "nom", "\"longitude (X)\"", "\"latitude (Y)\""));
        String[] alphabet = alphabetList.toArray(new String[0]);

        try {
            String filename = csvFile;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            line = bufferedReader.readLine();
            String[] attributes = listBase.toArray(new String[0]);
            while ((line = bufferedReader.readLine()) != null) {
                String[] tempocsv = line.split(delimiter);
                String[] csvinput = new String[attributes.length + 1];
                System.out.println("LINE: " + tempocsv[0]);
                if (tempocsv[0] != null && tempocsv[0].length() > 0) {
                    if (tempocsv[3] != null && tempocsv[3].length() > 0 && tempocsv[20] != null && tempocsv[20].length() > 0 && tempocsv[21] != null && tempocsv[21].length() > 0) {
                        System.arraycopy(tempocsv, 0, csvinput, 0, 1);
                        csvinput[1] = "geo:SpatialThing";
                        System.arraycopy(tempocsv, 3, csvinput, 2, 1);
                        System.arraycopy(tempocsv, 20, csvinput, 3, 2);
                        System.out.print("CSVINPUT: ");
                        for (int i = 0; i < 5; i++) {
                            System.out.print(csvinput[i] + " ");
                        }
                        System.out.println("");
                        System.out.print("ALPHABET: ");
                        for (int i = 0; i < 4; i++) {
                            System.out.print(alphabet[i] + " ");
                        }
                        System.out.println("");
                        ttlPart doc = new ttlPart(alphabet, csvinput);
                        ttlModel.addAll(doc.getModel());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String current = System.getProperty("user.dir");
        System.out.println("Current working directory in Java : " + current);
        CsvToTtl test = new CsvToTtl();
        test.CsvToTtl("src/main/resources/static/csv/gares-tgv.csv", "src/main/resources/static/ttl/gares-tgv3.ttl", "gares-tgv3final.ttl");
    }
}
