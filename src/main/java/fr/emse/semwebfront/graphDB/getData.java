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
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.query.*;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.config.RepositoryConfigSchema;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import static fr.emse.semwebfront.globalValues.*;

public class getData {

    public static void getData(String query, String file) throws IOException {

        final File resultfile1 = new File("src/main/resources" + file + ".txt");
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

        String testQuery = query;

        TupleQuery tupleQuery = repositoryConnection.prepareTupleQuery(QueryLanguage.SPARQL, testQuery);
        TupleQueryResult tupleQueryResult = tupleQuery.evaluate();
        try {
            FileWriter filewriter = new FileWriter(resultfile1);
            while (tupleQueryResult.hasNext()) {
                BindingSet bindingSet = tupleQueryResult.next();
                for (Binding binding : bindingSet) {
                    String name = binding.getName();
                    Value value = binding.getValue();
                    filewriter.write("@" + name + "\":\"" + value + "#");
                }
                filewriter.write("\n");
            }
            filewriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        repositoryConnection.close();
        repository.shutDown();
        repositoryManager.shutDown();
    }
}
