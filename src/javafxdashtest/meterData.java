/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdashtest;

/**
 *
 * @author Leon
 */
public class meterData {
    private final int ean_nummer;
    private final String time;
    private final int temperature;
    private final int solar_radiation;


public meterData( int ean_nummer, String time, int temperature, int solar_radiation) {
    this.ean_nummer = ean_nummer;
    this.time = time;
    this.temperature = temperature;
    this.solar_radiation = solar_radiation;
  }

    public int getEan_nummer() {
        return ean_nummer;
    }

    public String getTime() {
        return time;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getSolar_radiation() {
        return solar_radiation;
    }
@Override
  public String toString() {
    return String.format( "ean_nummer: %d  time: %s temperature: %s solar_radiation: %s", ean_nummer, time, temperature, solar_radiation);
  }
}