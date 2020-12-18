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

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Setter
@Getter
public class downloadData {

    String urlStr;
    String file;

    public downloadData() {
    }

    public static void downloadData(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}
