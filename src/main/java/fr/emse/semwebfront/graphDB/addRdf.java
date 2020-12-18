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

import fr.emse.semwebfront.globalValues;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.config.RepositoryConfigSchema;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;

import java.io.IOException;
import java.io.InputStream;

import static fr.emse.semwebfront.globalValues.*;

public class addRdf {
    public static void addRdfTurtle(String ttl) throws IOException {
        RepositoryManager repositoryManager = new RemoteRepositoryManager(SERVER_URL);
        repositoryManager.init();
        TreeModel graph = new TreeModel();
        InputStream config = globalValues.class.getResourceAsStream(REPO_DEFAULTS);
        RDFParser rdfParser = Rio.createParser(RDFFormat.TURTLE);
        rdfParser.setRDFHandler(new StatementCollector(graph));
        rdfParser.parse(config, RepositoryConfigSchema.NAMESPACE);
        config.close();
        Repository repository = repositoryManager.getRepository(REPO_NAME);
        RepositoryConnection repositoryConnection = repository.getConnection();
        InputStream str1 = globalValues.class.getResourceAsStream(ttl);
        repositoryConnection.add(str1, "urn:base", RDFFormat.TURTLE);
    }

    public void addRdfJson(String ttl, String repo) throws IOException {
        RepositoryManager repositoryManager = new RemoteRepositoryManager(SERVER_URL);
        repositoryManager.init();
        TreeModel graph = new TreeModel();
        InputStream config = globalValues.class.getResourceAsStream(REPO_DEFAULTS);
        RDFParser rdfParser = Rio.createParser(RDFFormat.TURTLE);
        rdfParser.setRDFHandler(new StatementCollector(graph));
        rdfParser.parse(config, RepositoryConfigSchema.NAMESPACE);
        config.close();
        Repository repository = repositoryManager.getRepository(REPO_NAME);
        RepositoryConnection repositoryConnection = repository.getConnection();
        InputStream str1 = globalValues.class.getResourceAsStream(REPO_WEATHER_DATA);
        repositoryConnection.add(str1, "urn:base", RDFFormat.JSONLD);
    }

}
