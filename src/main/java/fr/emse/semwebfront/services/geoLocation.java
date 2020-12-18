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

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;


public class geoLocation {
    private DatabaseReader dbReader;

    public geoLocation() throws IOException {
        File database = new File("tempoDB/GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
    }

    public getIp getLocation(String ip) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String cityName = response.getCity().getName();
        String latitude = response.getLocation().getLatitude().toString();
        String longitude = response.getLocation().getLongitude().toString();
        return new getIp(ip, cityName, latitude, longitude);
    }
}
