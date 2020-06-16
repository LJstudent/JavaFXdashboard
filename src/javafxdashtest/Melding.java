/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdashtest;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Leon
 */
public class Melding {
    private final StringProperty Status;
    private final StringProperty Datum;
    private final StringProperty Melding;
    
    
     public Melding(String Status, String Datum, String Melding) {
        this.Status = new SimpleStringProperty(Status);
        this.Datum = new SimpleStringProperty(Datum);
        this.Melding = new SimpleStringProperty(Melding);
    }
     
    public String getStatus() {
        return Status.get();
    }

    public void setSatus(String Status) {
        this.Status.set(Status);
    }

    public String getDatum() {
        return Datum.get();
    }

    public void setDatum(String Datum) {
        this.Datum.set(Datum);
    }

    public String getMelding() {
        return Melding.get();
    }

    public void setMelding(String Melding) {
        this.Melding.set(Melding);
    }
    
     
    public StringProperty StatusProperty() {
        return Status;
    }

    public StringProperty DatumProperty() {
        return Datum;
    }

    public StringProperty MeldingProperty() {
        return Melding;
    }

  
     
     
    
}
