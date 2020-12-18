/**
 *
 *                      UJM * EMSE
 *
 *            Hamed RAHIMI * Aleksei PASHININ
 *
 *                 Semantic Web Project
 *
 */

package fr.emse.semwebfront.controllers;

import java.io.*;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import static fr.emse.semwebfront.globalValues.REMOTE_URL;
import static fr.emse.semwebfront.globalValues.PASS;
import static fr.emse.semwebfront.globalValues.USER;

public class gitHubUpload {

    FileInputStream instream = null;
    FileOutputStream outstream = null;

    public void gitHubUpload(String inFile, String outFile ) throws IOException, GitAPIException{

        File localPath = File.createTempFile("GitHub", "");
        if(!localPath.delete()) {
            throw new IOException("Error of delete this folder>>> " + localPath);
        }
        try (Git git = Git.cloneRepository().setURI(REMOTE_URL)
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(USER, PASS))
                .setDirectory(localPath).call()) {

            Repository repository = git.getRepository();
            File theDir = new File(repository.getDirectory().getParent(), "remote");
            theDir.mkdir();
            File jsonFile = new File(theDir, outFile);
            jsonFile.createNewFile();
            git.add().addFilepattern(".").call();
            git.commit().setMessage("JSON commit").call();

            try{
                instream = new FileInputStream(inFile);
                outstream = new FileOutputStream(jsonFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = instream.read(buffer)) > 0){
                    outstream.write(buffer, 0, length);
                }

                instream.close();
                outstream.close();
            }catch(IOException ioe) {
                ioe.printStackTrace();
            }

            git.commit().setAll(true).setMessage("Commit changes to all files").call();
            git.add().addFilepattern("*").call();
            RevCommit result = git.commit().setMessage("initial commit").call();
            git.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider(USER, PASS)).call();

        }
    }
}


