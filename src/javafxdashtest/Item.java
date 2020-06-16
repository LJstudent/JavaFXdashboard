/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdashtest;

import java.time.LocalDate;

/**
 *
 * @author Leon
 */
public class Item {
    private final LocalDate time;
    private final double forecast;
    
    public Item( LocalDate time, double forecast) {
    this.time = time;
    this.forecast = forecast;
  }

    public LocalDate getTime() {
        return time;
    }

    public double getForecast() {
        return forecast;
    }

    @Override
    public String toString() {
        return String.format( "%d", forecast);
    }
    
    
}
