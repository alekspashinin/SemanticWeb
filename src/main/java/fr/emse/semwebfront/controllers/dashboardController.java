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

import com.maxmind.geoip2.exception.GeoIp2Exception;
import fr.emse.semwebfront.services.geoLocation;
import fr.emse.semwebfront.services.getClientIpAdress;
import fr.emse.semwebfront.services.getIp;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@Controller
public class dashboardController {

    private HttpServletRequest request;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/trains")
    public String trains() {
        return "trains";
    }

    @GetMapping("/bikes")
    public String bikes() {
        return "bikes";
    }

    @GetMapping("/bikes/geo")
    public String bikeGeoDetect(ModelMap modelMap, HttpServletRequest httpServletRequest) throws IOException, GeoIp2Exception {
        getClientIpAdress AdressIp = new getClientIpAdress();
        System.out.println(AdressIp.getIp());
        System.out.println(httpServletRequest.getRemoteAddr());
        geoLocation loc = new geoLocation();
        //NEED TO CHANGE IP AFTER
        getIp res = loc.getLocation(AdressIp.getIp());
        String lanLat = "+res.getLatitude() res.getLongitude()";
        System.out.println("RESULT: " + res.getIpAddress() + " " + res.getCity() + " " + res.getLatitude() + " " + res.getLongitude());
        try {
            FileWriter myWriter = new FileWriter("tempoDB/perso.json");
            myWriter.write(res.getLatitude() + " " + res.getLongitude());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("lon", res.getLongitude());
        modelMap.addAttribute("lat", res.getLatitude());
        return "bikes";
    }

    @GetMapping("/social")
    public String social() {
        return "social";
    }

    @GetMapping("/social/geo")
    public String socialGeoDetect(ModelMap modelMap, HttpServletRequest httpServletRequest) throws IOException, GeoIp2Exception {
        getClientIpAdress AdressIp = new getClientIpAdress();
        String ip = AdressIp.getIp();
        System.out.println(ip);
        System.out.println(httpServletRequest.getRemoteAddr());
        geoLocation loc = new geoLocation();
        //NEED TO CHANGE IP AFTER
        getIp res = loc.getLocation(ip);
        System.out.println("RESULT: " + res.getIpAddress() + " " + res.getCity() + " " + res.getLatitude() + " " + res.getLongitude());
        try {
            FileWriter myWriter = new FileWriter("tempoDB/perso.json");
            myWriter.write(res.getLatitude() + " " + res.getLongitude());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("lon", res.getLongitude());
        modelMap.addAttribute("lat", res.getLatitude());
        System.out.println("NEAREST STRUCTURES: ");
        System.out.println("RESULT: " + res.getIpAddress() + " " + res.getCity() + " " + res.getLatitude() + " " + res.getLongitude());
        return "social";
    }


    @GetMapping("/meteo")
    public String meteo() {
        return "meteo";
    }

    @GetMapping("/history")
    public String history(ModelMap modelMap) throws IOException, ParseException {
        List<String> headers = Arrays.asList("ID", "Query", "Time");
        List<Map<String, Object>> rows = new ArrayList<>();
        jsonReaderHistory idObj = new jsonReaderHistory();
        String[] id = idObj.jsonHistoryId();
        jsonReaderHistory queryObj = new jsonReaderHistory();
        String[] query = queryObj.jsonHistoryQuery();
        jsonReaderHistory timeObj = new jsonReaderHistory();
        String[] time = timeObj.jsonHistoryTime();
        jsonReaderHistory stopObj = new jsonReaderHistory();
        Integer stop = stopObj.getStopList();
        for (Integer i = 0; i < stop; i++) {
            rows.add(Map.of("ID", id[i], "Query", query[i], "Time", time[i]));
        }
        modelMap.addAttribute("headers", headers);
        modelMap.addAttribute("rows", rows);
        return "history";
    }

    @GetMapping("/history/clear")
    public String historyClear() throws IOException, ParseException {
        modifyHistory clear = new modifyHistory();
        clear.deleteHistory();
        return "history";
    }
}
