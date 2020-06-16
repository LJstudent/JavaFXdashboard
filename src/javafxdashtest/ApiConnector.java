package javafxdashtest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ApiConnector {

    private final String JSONURL = "http://lcocalhost/api/meters";
    private final String JSONURL_inverter_info = "http://lcocalhost/api/inverters";
    private final String JSONURL_sensor_data = "http://lcocalhost/api/meters/sensordata/";
    private final String JSONURL_inverter_data = "http://lcocalhost/api/meters/inverterdata/";
    private final String JSONURL_inverter_production_month = "http://lcocalhost/api/meters/inverterdata/month/";
    private final String JSONURL_sensor_data_specific = "http://lcocalhost/api/meters/sensordata/";
    private final String JSONURL_inverter_data_specific = "http://lcocalhost/api/meters/inverterdata/";
    
    JSONArray jo = new JSONArray();


    public ApiConnector() {
    }
    
    public ArrayList<Meter> SensorInfoConnection() {
        
        ArrayList<Meter> lijst = new ArrayList<Meter>();
        
        JSONArray ja = new JSONArray();

        try {
            ja = readUrl(JSONURL);
      

            for (Object o : ja) {
                JSONObject jsonobject = (JSONObject) o;      
                String ean_nummer = checkValue(jsonobject, "ean_nummer");
                int EanNummer = Integer.parseInt(ean_nummer);
                String postal_code = checkValue(jsonobject, "postal_code");
                String created_at = checkValue(jsonobject, "created_at");
               
                Meter item = new Meter(EanNummer, postal_code, created_at);
                lijst.add(item);
                
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return lijst;
    }
    public ArrayList<Inverter> InverterInfoConnection() {
        
        ArrayList<Inverter> lijst = new ArrayList<Inverter>();
        
        JSONArray ja = new JSONArray();

        try {
            ja = readUrl(JSONURL_inverter_info);
      

            for (Object o : ja) {
                JSONObject jsonobject = (JSONObject) o;      
                String ean_nummer = checkValue(jsonobject, "ean_nummer");
                String solar_panels = checkValue(jsonobject, "solar_panels");
                String watt_per_solar_panel = checkValue(jsonobject, "watt_per_solar_panel");
                String performance_ratio = checkValue(jsonobject, "performance_ratio");
                
                int EanNummer = Integer.parseInt(ean_nummer);
                int SolarPanels = Integer.parseInt(solar_panels);
                int WattPerSolarPanel = Integer.parseInt(watt_per_solar_panel);
                int PerformanceRatio = Integer.parseInt(performance_ratio);
               
                Inverter item = new Inverter(EanNummer, SolarPanels, WattPerSolarPanel,PerformanceRatio);
                lijst.add(item);
                
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return lijst;
    }
    
    public ArrayList<meterData> DataConnection(String eanNummer) {
        
        ArrayList<meterData> lijst = new ArrayList<meterData>();
        
        JSONArray ja = new JSONArray();

        try {
            ja = readUrl(JSONURL_sensor_data + eanNummer);
      

            for (Object o : ja) {
                JSONObject jsonobject = (JSONObject) o;      
                String ean_nummer = checkValue(jsonobject, "ean_nummer");
                String time = checkValue(jsonobject, "time");
                String temperature = checkValue(jsonobject, "temperature");
                String solar_radiation = checkValue(jsonobject, "solar_radiation");
               
                int EanNummer = Integer.parseInt(ean_nummer);
                int Temperature = Integer.parseInt(temperature);
                int SolarRadiation = Integer.parseInt(solar_radiation);
                
                meterData item = new meterData(EanNummer, time, Temperature, SolarRadiation);
                lijst.add(item);
                
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return lijst;
    }
    
    public ArrayList<inverterData> DataConnectionInverter(String eanNummer) {
        
        ArrayList<inverterData> lijst = new ArrayList<inverterData>();
        
        JSONArray ja = new JSONArray();

        try {
            ja = readUrl(JSONURL_inverter_data + eanNummer);
      

            for (Object o : ja) {
                JSONObject jsonobject = (JSONObject) o;      
                String ean_nummer = checkValue(jsonobject, "ean_nummer");
                String time = checkValue(jsonobject, "time");
                String pv_productie = checkValue(jsonobject, "pv_productie");
                
               
                int EanNummer = Integer.parseInt(ean_nummer);
                double PvProductie = Double.parseDouble(pv_productie);
         
                
                inverterData item = new inverterData(EanNummer, time, PvProductie);
                lijst.add(item);
                
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return lijst;
    }
    public String DataConnectionInverterMonthProduction (String eanNummer) {
       JSONArray ja = new JSONArray();
       String production_monthly = "";

        try {
            ja = readUrl(JSONURL_inverter_production_month + eanNummer);
      

            for (Object o : ja) {
                JSONObject jsonobject = (JSONObject) o;      
                String pv_productie_month = checkValue(jsonobject, "SUM(pv_productie)");
               
                
                production_monthly = pv_productie_month;
        
                
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return production_monthly; 
    
    }
     public ArrayList<inverterData> DataConnectionInverterSpecificPeriod(String eanNummer, String startdate, String enddate) {
        
        ArrayList<inverterData> lijst = new ArrayList<inverterData>();
        
        JSONArray ja = new JSONArray();

        try {
            ja = readUrl( JSONURL_inverter_data_specific + eanNummer + "/" +startdate+ "/" + enddate);
      

            for (Object o : ja) {
                JSONObject jsonobject = (JSONObject) o;      
                String ean_nummer = checkValue(jsonobject, "ean_nummer");
                String time = checkValue(jsonobject, "time");
                String pv_productie = checkValue(jsonobject, "pv_productie");
                
               
                int EanNummer = Integer.parseInt(ean_nummer);
                double PvProductie = Double.parseDouble(pv_productie);
         
                
                inverterData item = new inverterData(EanNummer, time, PvProductie);
                lijst.add(item);
                
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return lijst;
    }
     
    public ArrayList<meterData> DataConnectionSpecificPeriod(String eanNummer, String startdate, String enddate) {
        
        ArrayList<meterData> lijst = new ArrayList<meterData>();
        
        JSONArray ja = new JSONArray();

        try {
            ja = readUrl( JSONURL_sensor_data_specific + eanNummer + "/" +startdate+ "/" + enddate);
            
      

            for (Object o : ja) {
                JSONObject jsonobject = (JSONObject) o;      
                String ean_nummer = checkValue(jsonobject, "ean_nummer");
                String time = checkValue(jsonobject, "time");
                String temperature = checkValue(jsonobject, "temperature");
                String solar_radiation = checkValue(jsonobject, "solar_radiation");
               
                int EanNummer = Integer.parseInt(ean_nummer);
                int Temperature = Integer.parseInt(temperature);
                int SolarRadiation = Integer.parseInt(solar_radiation);
                
                meterData item = new meterData(EanNummer, time, Temperature, SolarRadiation);
                lijst.add(item);
                
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return lijst;
    } 
    
    

      private String checkValue(JSONObject jo, String attribute) {
        String waarde = "";
        try {
            if (jo.get(attribute).toString() != null) {
                waarde = jo.get(attribute).toString();
            }
        } catch (Exception e) {
        }
        return waarde;
    }

    private JSONArray readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        StringBuffer data = new StringBuffer();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/xml");
            BufferedReader br
                    = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            line = br.readLine();
            while(line != null){
                data.append(line);
                line = br.readLine();
            }
                    
            connection.disconnect();
            jo = (JSONArray) JSONValue.parseWithException(data.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jo;
    }
}
