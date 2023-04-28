/**
 * CSVfactory class handles differnt types of CSV files.
 */
public class CSVfactory {
    /**
     * 
     * @param fileType
     * @return instance of a flight/customer file accordingly
     */
    public static CSVFile createCSVFile(String fileType) {
        if (fileType.equalsIgnoreCase("Flight")) {
            return new FlightFile();
        } else if (fileType.equalsIgnoreCase("Customer")) {
            return new CustomerFile();
        } else {
            return null;
        }
        
    }

}
