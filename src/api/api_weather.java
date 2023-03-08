package api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.*;
import org.json.simple.parser.JSONParser;


public class api_weather {

    public static void main(String[] args) {

      

        try {
            //Public API for checking weather in our local area:
           

            URL url = new URL("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/craiova?unitGroup=metric&key=CDQ3GXE7M9M3FLQEQXRCDWAJ2&contentType=json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
               
                
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();
                String output = informationString.toString().replace(",", "\n");
                //String country = informationString.indexOf("address");
                System.out.println(output);
               

                JSONObject jsonObj = new JSONObject();
                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(informationString.toString());

                //Get the required object from the above created object
                JSONObject obj = (JSONObject) data_obj.get("latitude");

                //Get the required data using its key
                System.out.println(obj);
                
              

               
               
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	

}