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
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.model.util.GraphUtil;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.repository.config.RepositoryConfig;
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

public class createRepo {


    public createRepo() throws IOException {
        RepositoryManager repositoryManager = new RemoteRepositoryManager(SERVER_URL);
        repositoryManager.init();
        TreeModel graph = new TreeModel();
        InputStream config = globalValues.class.getResourceAsStream(REPO_DEFAULTS);
        RDFParser rdfParser = Rio.createParser(RDFFormat.TURTLE);
        rdfParser.setRDFHandler(new StatementCollector(graph));
        rdfParser.parse(config, RepositoryConfigSchema.NAMESPACE);
        config.close();
        Resource repositoryNode = GraphUtil.getUniqueSubject(graph, RDF.TYPE, RepositoryConfigSchema.REPOSITORY);
        RepositoryConfig repositoryConfig = RepositoryConfig.create(graph, repositoryNode);
        repositoryManager.removeRepository(REPO_NAME);
        repositoryManager.addRepositoryConfig(repositoryConfig);
    }
}
