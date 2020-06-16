
package javafxdashtest;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class FXMLDashboardController implements Initializable {
    
    
    @FXML
    NumberAxis xAxis;
    @FXML
    CategoryAxis yAxis;

    @FXML
    BarChart<String, Number> chart;
    
    @FXML
    public TableView<Melding> table;
    public TableColumn<Melding, String> tbStatus;
    public TableColumn<Melding, String> tbDatum;
    public TableColumn<Melding, String> tbMelding;
    
    @FXML
    public Label pvProductionMonth;
    public Label advies;
    
    @FXML
    public ComboBox combobox;
    
    @FXML
    DatePicker fromDate, toDate;
    
    
    private final StringProperty index = new SimpleStringProperty();

    public void setIndex(String index){
        this.index.set(index);
    }
    public String getIndex(){
        return index.get();
    }
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        
        String value = combobox.getValue().toString();
     
        if(fromDate.getValue() == null || toDate.getValue() == null) {
            System.out.println("Hallo");
        } else {
            
       String strFromDate = fromDate.getValue().toString();
       String strToDate = toDate.getValue().toString();
        
        Calculator calc = new Calculator(getIndex(),strFromDate, strToDate);    
            
            
        // bar chart
        xAxis.setLabel("Energy in KWh");
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("forecast");   
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Omvormer opbrengst");
            
        chart.getData().clear();
        chart.layout();
        
            if(!value.equals("uren")) {

            //List
            List<Item> list;
            List<Item> list2;

            list = calc.meterCalculations(value);
            list2 = calc.inverterCalculations(value);




            for(Item item : list){
                series1.getData().add(new XYChart.Data(item.getTime().toString(), item.getForecast()));
                //System.out.println(item.getTime().toString() +" - "+ item.getForecast());

            }

             for(Item item : list2){
                series2.getData().add(new XYChart.Data(item.getTime().toString(), item.getForecast()));
                //System.out.println(item.getTime().toString() +" - "+ item.getForecast());

            }

              chart.getData().addAll(series1,series2);  


            } else {
                invertersData invertersdata = new invertersData("1", strFromDate, strToDate); 
                
                ArrayList<inverterData> lijst;
                
                lijst = invertersdata.returnInvertersData();
                

                for(inverterData data : lijst){
                series2.getData().add(new XYChart.Data(data.getTime(), data.getPv_production()));
                //System.out.println(item.getTime().toString() +" - "+ item.getForecast());

                }

                chart.getData().add(series2); 
                
                
            }    
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         index.addListener((ob,n,n1)->{
            Calculator calc = new Calculator(n1,"","");
        
            // set combobox
            combobox.getItems().addAll(
            "uren",
            "day",
            "week",
            "month",
            "year"
            );
            combobox.getSelectionModel().select(1);

            //monthly
            pvProductionMonth.setText(calc.monthlyproduction());


            //advies
            advies.setText(calc.PerformanceRatio());


            // bar chart
            xAxis.setLabel("Energy in KWh");

            XYChart.Series series1 = new XYChart.Series();
            series1.setName("forecast");   

            XYChart.Series series2 = new XYChart.Series();
            series2.setName("Omvormer opbrengst");

            //List
            List<Item> list = new ArrayList<>();
            List<Item> list2 = new ArrayList<>();

            list = calc.meterCalculations("day");
            list2 = calc.inverterCalculations("day");


            for(Item item : list){
                //System.out.println(item.getTime().toString() +" - "+ item.getForecast());
                series1.getData().add(new XYChart.Data(item.getTime().toString(), item.getForecast()));
            }

             for(Item item : list2){
                //System.out.println(item.getTime().toString() +" - "+ item.getForecast());
                series2.getData().add(new XYChart.Data(item.getTime().toString(), item.getForecast()));
            }


            chart.getData().addAll(series1,series2);

            // melding
            tbStatus.setCellValueFactory(new PropertyValueFactory<Melding, String>("Status"));
            tbDatum.setCellValueFactory(new PropertyValueFactory<Melding, String>("Datum"));
            tbMelding.setCellValueFactory(new PropertyValueFactory<Melding, String>("Melding"));

            //melding list
            table.getItems().setAll(calc.parseMeldingList());

         });
        
    } 
}
