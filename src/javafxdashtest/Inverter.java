
package javafxdashtest;


public class Inverter {
    private final int ean_nummer;
    private final int solar_panels;
    private final int watt_per_solar_panel;
    private final int performance_ratio;
    
  
  public Inverter( int ean_nummer, int solar_panels, int watt_per_solar_panel, int performance_ratio) {
    this.ean_nummer = ean_nummer;
    this.solar_panels = solar_panels;
    this.watt_per_solar_panel = watt_per_solar_panel;
    this.performance_ratio = performance_ratio;
  }

    public int getEan_nummer() {
        return ean_nummer;
    }

    public int getSolar_panels() {
        return solar_panels;
    }

    public int getWatt_per_solar_panel() {
        return watt_per_solar_panel;
    }

    public int getPerformance_ratio() {
        return performance_ratio;
    }
 
  @Override
  public String toString() {
    return String.format( "ean_nummer: %d  solar_panels: %d watt_per_solar_panel: %d performance_ratio: %d ", ean_nummer, solar_panels, watt_per_solar_panel, performance_ratio);
  }
}
