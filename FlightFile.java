import java.util.HashMap;
/**
 * this class implements the CSVFile interface to read flight csv file.
 */
public class FlightFile implements CSVFile {
    private CSVreader csvReader = new CSVreader();
    /**
     * calls flightDetails method from CSVreader
     */
    @Override
    public void readDetails(String fileName) {
        csvReader.flightDetails(fileName);
    }

    @Override
    public HashMap<Integer, Flight> flightMap() {
        return csvReader.flightMap();
    }

    @Override
    public HashMap<String, Customer> customerMap() {
        return csvReader.customerMap();
    }
    @Override
    public HashMap<String, Employee> employeeMap() {
        return csvReader.employeeMap();
    }
    @Override
    public HashMap<String, Airport> airportMap() {
        return csvReader.airportMap();
    }

}