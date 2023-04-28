import java.util.HashMap;
/**
 * this class implements the CSVFile interface to read customer csv file.
 */
public class CustomerFile implements CSVFile {
    private CSVreader csvReader = new CSVreader();
    /**
     * calls customerDetails method from CSVreader
     */
    @Override
    public void readDetails(String fileName) {
        csvReader.customerDetails(fileName);
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