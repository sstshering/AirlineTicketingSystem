import java.util.HashMap;
/**
 * CSVFile interface
 */
public interface CSVFile {
    public void readDetails(String filePath);
    
    HashMap<Integer, Flight> flightMap();

    HashMap<String, Customer> customerMap();
   
    HashMap<String, Employee> employeeMap();
   
    HashMap<String, Airport> airportMap();
}
