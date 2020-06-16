/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdashtest;


public class inverterData {
    private final int ean_nummer;
    private final String time;
    private final double pv_production;
    
public inverterData(int ean_nummer, String time, double pv_production) {
    this.ean_nummer = ean_nummer;
    this.time = time;
    this.pv_production = pv_production;
}

    public int getEan_nummer() {
        return ean_nummer;
    }

    public String getTime() {
        return time;
    }

    public double getPv_production() {
        return pv_production;
    }

   @Override
  public String toString() {
    return String.format( "ean_nummer: %d  time: %s pv productie: %s", ean_nummer, time, pv_production);
  }


    
}
