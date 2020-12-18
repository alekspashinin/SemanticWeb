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

import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;

import java.io.IOException;

import static fr.emse.semwebfront.globalValues.SERVER_URL;

public class deleteRepo {


    public deleteRepo() throws IOException {
        RepositoryManager repositoryManager = new RemoteRepositoryManager(SERVER_URL);
        repositoryManager.init();
        repositoryManager.removeRepository("Test-Repo");
    }
}
