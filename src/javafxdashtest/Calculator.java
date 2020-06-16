
package javafxdashtest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Calculator {
    ApiConnector conn = new ApiConnector();
    
    private ArrayList<meterData> lijst;
    private ArrayList<inverterData> lijst2;
    
    static final Map<String, TemporalAdjuster> ADJUSTERS = new HashMap<>();
    
    Inverters inv = new Inverters();
    
    private String ean_nummer_class = "";
    private String startdate_class = "";
    private String enddate_class = "";
    private int solar_panels;
    private int watt_per_panel;
    private int performance;
    
    private double total_system;
    private double forecast;
    
    
    public Calculator(String ean_nummer, String startdate, String enddate) {  
         ean_nummer_class = ean_nummer;
         startdate_class = startdate;
         enddate_class = enddate;
         
    }
    //Calculations
    public List<Item> meterCalculations(String value) {
        
        metersData metersdata = new metersData( ean_nummer_class,startdate_class, enddate_class);
        
        lijst = metersdata.returnMetersData();
        
        // inverter info
        Inverter inverter = inv.returninfo(1);
        solar_panels = inverter.getSolar_panels();
        watt_per_panel = inverter.getWatt_per_solar_panel();
        performance = inverter.getPerformance_ratio();
        
        total_system = watt_per_panel * solar_panels;
        
        
        //List
        List<Item> list = new ArrayList<>();
       
        for( meterData data : lijst ) {
            
            // convert 
            String conv_localdate_substring = data.getTime().substring(0, 10);
            LocalDate conv_localdate = LocalDate.parse(conv_localdate_substring);
            // System.out.println(conv_localdate);
            
            // calculation
            double temperature = Double.valueOf(data.getTemperature());
            temperature = ((25-temperature)/3)/100;
            if(data.getSolar_radiation() != 0) {
                double percentage = Double.valueOf(data.getSolar_radiation());
                percentage = (percentage/1000) + temperature;
                forecast = (total_system * percentage)/1000;
                
                list.add(new Item(conv_localdate,forecast));
                
            } else {
                forecast = 0;
                list.add(new Item(conv_localdate,forecast));
            }    
        }
        //day
        if (!value.trim().isEmpty()){
            AdjustView(list, value);
        }
        
        return list;
    }
    
    public List<Item> inverterCalculations(String value) {
        
        invertersData data2 = new invertersData(ean_nummer_class,startdate_class, enddate_class); 
        
        lijst2 = data2.returnInvertersData();
        
        List<Item> list = new ArrayList<>();
        
        for( inverterData data : lijst2) {
            // convert 
            String conv_localdate_substring = data.getTime().substring(0, 10);
            LocalDate conv_localdate = LocalDate.parse(conv_localdate_substring);
            // System.out.println(conv_localdate);
                     
            list.add(new Item(conv_localdate,data.getPv_production()));
        }
        //day
        if (!value.trim().isEmpty()){
            AdjustView(list, value);
        }
        
        return list;
        
    }
    
    //monthly production
    public String monthlyproduction() {
        String pv_production = "";
            if (!ean_nummer_class.trim().isEmpty()){
            pv_production = conn.DataConnectionInverterMonthProduction(ean_nummer_class);
                } else {
                pv_production = "";
            }
        return pv_production;
    }
    
     public List<Melding> parseMeldingList(){
        List<Melding> ints = new ArrayList<>();
        
        List<Item> list = new ArrayList<>();
        List<Item> list2 = new ArrayList<>();
        
        list = meterCalculations("day");
        list2 = inverterCalculations("day");
        
        for(int i = 0; i< list.size(); i++){
        Item str1 = list.get(i);
        Item str2 = list2.get(i);
        //do stuff
        double vergelijking_per = str2.getForecast() / str1.getForecast();
        
        if(vergelijking_per > 0.2 && vergelijking_per <0.9) {
            ints.add(new Melding("medium", str1.getTime().toString(), "Lage opbrengst ten opzichte van forecast"));
            //System.out.println("medium");
        }
        if(vergelijking_per <= 0.2) { 
           ints.add(new Melding("not ok", str1.getTime().toString(), "Geen opbrengst"));
           // System.out.println("not ok");
        }
        
        }
        
        return ints;
    }
     
     public String PerformanceRatio() {
         String performanceadvies = "";
        
        List<Item> list = new ArrayList<>();
        List<Item> list2 = new ArrayList<>();
         
        list = meterCalculations("day");
        list2 = inverterCalculations("day");
        
        double forecast_per = 0.0;
        for (Item item : list) {
            forecast_per += item.getForecast();
        }
        
        double pv_production_per = 0.0;
        for (Item item : list2) {
            pv_production_per += item.getForecast();
        }
         
       double performance_ratio_calc = pv_production_per / forecast_per;
       
       if(performance_ratio_calc >= 0.9){
           performanceadvies = "Uw systeem presteert goed!";
       }
       if(performance_ratio_calc >=0.7 && performance_ratio_calc <0.9) {
          performanceadvies = "Uw systeem presteert gemiddeld";
       }
       if(performance_ratio_calc <0.7) {
          performanceadvies = "Uw systeem presteert slecht!";
       }    
       return performanceadvies;
     }
    
    
    
    public List<Item> AdjustView(List<Item> list, String value) {
        
        //test reasons
        ADJUSTERS.put("day", TemporalAdjusters.ofDateAdjuster(d -> d)); // identity
        ADJUSTERS.put("week", TemporalAdjusters.previousOrSame(DayOfWeek.of(1)));
        ADJUSTERS.put("month", TemporalAdjusters.firstDayOfMonth());
        ADJUSTERS.put("year", TemporalAdjusters.firstDayOfYear());
        /*
        Map<LocalDate, List<Item>> result = list.stream().collect(
                Collectors.groupingBy(item -> item.getTime().with(ADJUSTERS.get("day"))
                ));
        
        for (LocalDate key : result.keySet())
            System.out.println(key + " - " + result.get(key));
        */
       
        
        Map<LocalDate, Double> result = list.stream().collect(
                Collectors.groupingBy(item -> item.getTime().with(ADJUSTERS.get(value)), Collectors.summingDouble(Item::getForecast)));  
        
        list.clear();
        
        for (LocalDate key : result.keySet())
            list.add(new Item(key,result.get(key)));
     
        Collections.sort(list, (a,b)->a.getTime().compareTo(b.getTime()));
        
        return list;
    }
    
    
}
