/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdashtest;

import java.util.ArrayList;

public class invertersData {
    private ArrayList<inverterData> lijst;
    ApiConnector conn = new ApiConnector();
    

public invertersData(String ean_nummer, String startdate, String enddate) {
    if(startdate.equals("") && enddate.equals(""))
     lijst = conn.DataConnectionInverter(ean_nummer);
    else {
     lijst = conn.DataConnectionInverterSpecificPeriod(ean_nummer, startdate, enddate);
    }
  }

public void invertersDataSpecificPeriod(String ean_nummer, String startdate, String enddate) {
    lijst = conn.DataConnectionInverterSpecificPeriod(ean_nummer, startdate, enddate);
}
  
  public void voegtoe( inverterData data ) {
    lijst.add( data );
  }
  
  public void print() {
    for( inverterData data : lijst ) {
      System.out.println( data );
    }
  }
public ArrayList<inverterData> returnInvertersData(){
      return lijst;
  }  
}
