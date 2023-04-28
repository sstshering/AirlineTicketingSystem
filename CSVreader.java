import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;

/**
* @author Garab K Dorji, Daniel Lucio, Sonam S Tshering
 * @version 1.0
 *          This CSV reader class reads the data from the csvs files; flight and
 *          customer, and put it into a hashmap before returning it.
 */

public class CSVreader implements CSVFile {
    // declare hashmap
    private HashMap<Integer, String[]> FlightInter = new HashMap<>(); 
    private final HashMap<Integer, Flight> flightMap = new HashMap<>();
    private HashMap<Integer, String[]> customerInter = new HashMap<>(); 
    private final HashMap<String, Customer> customerMap = new HashMap<>();
    private final HashMap<String, Airport> airportMap = new HashMap<>();
    private final HashMap<String, Employee> employeeMap = new HashMap<>();
    private Scanner scribe1; 
    private Scanner scribe2; 
    
    /**
     * 
     * @param filePath  the filepath for reading the file
     * @param flightArr
     */
    public void flightDetails(String fileName){
        try{
            this.scribe1 = new Scanner(new File(fileName));
            int idIdx = 0;
            int flightNumIdx = 0;
            int originPortIdx = 0;
            int originCodeIdx = 0;
            int destIdx = 0;
            int destCodeIdx = 0;
            int departDateIdx = 0;
            int departTimeIdx = 0;
            int durationIdx = 0;
            int distanceIdx = 0;
            int timeZoneIdx = 0;
            int arriveDateIdx = 0;
            int arriveTimeIdx = 0;
            int isInternationalIdx = 0;
            int surchargeIdx = 0;
            int foodServiceIdx = 0;
            int routeCostIdx = 0;
            int minerPointsIdx = 0;
            int totSeatsIdx = 0;
            int firstClassIdx = 0;
            int buisnessIdx = 0;
            int mainCIdx = 0;
            int fcPriceIdx = 0;
            int BsPriceIdx = 0;
            int mainPriceIdx = 0;
            int originCityIdx = 0;
            int originStateIdx = 0;
            int originCountryIdx = 0;
            int originFeeIdx = 0;
            int originLoungeIdx = 0;
            int destCityIdx = 0;
            int destStateIdx = 0;
            int destCountryIdx = 0;
            int destFeeIdx = 0;
            int destLoungeIdx = 0;

            int header = 0; //flag to move past header in csv
            int FlightID = 1;     

            scribe1.useDelimiter(",");   
            while (scribe1.hasNextLine()){ 
                if(header > 0){
                    String[] TempArr = scribe1.nextLine().split(",");  
                    FlightInter.put(FlightID, TempArr);
                    FlightID++;
                }else{
                    String[] TempArr = scribe1.nextLine().split(","); 

                    for(int i = 0; i < TempArr.length; i++){
                        switch(TempArr[i]){
                            case "ID":
                                idIdx = i;
                                break;
                            case "Flight Number":
                                flightNumIdx = i;
                                break;
                            case "Origin Airport":
                                originPortIdx = i;
                                break;
                            case "Origin Code":
                                originCodeIdx = i;
                                break;
                            case "Destination Airport":
                                destIdx = i;
                                break;
                            case "Destination Code":
                                destCodeIdx = i;
                                break;
                            case "Departing Date":
                                departDateIdx = i;
                                break;
                            case "Departing Time":
                                departTimeIdx = i;
                                break;
                            case "Duration":
                                durationIdx = i;
                                break;
                            case "Distance":
                                distanceIdx = i;
                                break;
                            case "Time Zone Difference":
                                timeZoneIdx = i;
                                break;
                            case "Arrival Date":
                                arriveDateIdx = i;
                                break;
                            case "Arrival Time":
                                arriveTimeIdx = i;
                                break;
                            case "Type":
                                isInternationalIdx = i;
                                break;
                            case "Surcharge":
                                surchargeIdx = i;
                                break;
                            case "Food Served":
                                foodServiceIdx = i;
                                break;
                            case "Route Cost":
                                routeCostIdx = i;
                                break;
                            case "Miner Points":
                                minerPointsIdx = i;
                                break;
                            case "Total Seats":
                                totSeatsIdx = i;
                                break;
                            case "First Class Seats":
                                firstClassIdx = i;
                                break;
                            case "Business Class Seats":
                                buisnessIdx = i;
                                break;
                            case "Main Cabin Seats":
                                mainCIdx = i;
                                break;
                            case "First Class Price":
                                fcPriceIdx = i;
                                break;
                            case "Business Class Price":
                                BsPriceIdx = i;
                                break;
                            case "Main Cabin Price":
                                mainPriceIdx = i;
                                break;
                            case "Origin Airport City":
                                originCityIdx = i;
                                break;
                            case "Origin Airport State":
                                originStateIdx = i;
                                break;
                            case "Origin Airport Country":
                                originCountryIdx = i;
                                break;
                            case "Origin Airport Fee":
                                originFeeIdx = i;
                                break;
                            case "Origin Airport Lounge":
                                originLoungeIdx = i;
                                break;
                            case "Destination Airport City":
                                destCityIdx = i;
                                break;
                            case "Destination Airport State":
                                destStateIdx = i;
                                break;
                            case "Destination Airport Country":
                                destCountryIdx = i;
                                break;
                            case "Destination Airport Fee":
                                destFeeIdx = i;
                                break;
                            case "Destination Airport Lounge":
                                destLoungeIdx = i;
                                break;
                            default:
                                System.out.println(TempArr[i] +" not found at index " + i);
                                break;
                        }
                    }
                    header ++;  
                }
            }

            scribe1.close();
            
            Set fIds = FlightInter.keySet();

            Iterator it = fIds.iterator();

            while(it.hasNext()){
                int k = (int)it.next();
                Flight tempFlight = new Flight(FlightInter.get(k)[idIdx], Integer.parseInt(FlightInter.get(k)[flightNumIdx]), 
                FlightInter.get(k)[originPortIdx], FlightInter.get(k)[originCodeIdx], FlightInter.get(k)[destIdx], FlightInter.get(k)[destCodeIdx], FlightInter.get(k)[departDateIdx],
                FlightInter.get(k)[departTimeIdx], FlightInter.get(k)[durationIdx], FlightInter.get(k)[distanceIdx], FlightInter.get(k)[timeZoneIdx],FlightInter.get(k)[arriveDateIdx], 
                FlightInter.get(k)[arriveTimeIdx], FlightInter.get(k)[isInternationalIdx], Integer.parseInt(FlightInter.get(k)[surchargeIdx]),
                Boolean.parseBoolean(FlightInter.get(k)[foodServiceIdx]), Integer.parseInt(FlightInter.get(k)[routeCostIdx]), Integer.parseInt(FlightInter.get(k)[minerPointsIdx]), 
                Integer.parseInt(FlightInter.get(k)[totSeatsIdx]), Integer.parseInt(FlightInter.get(k)[firstClassIdx]), Integer.parseInt(FlightInter.get(k)[buisnessIdx]), 
                Integer.parseInt(FlightInter.get(k)[mainCIdx]), Double.parseDouble(FlightInter.get(k)[fcPriceIdx]), Double.parseDouble(FlightInter.get(k)[BsPriceIdx]), 
                Double.parseDouble(FlightInter.get(k)[mainPriceIdx]),FlightInter.get(k)[destStateIdx],FlightInter.get(k)[originCityIdx], FlightInter.get(k)[originCountryIdx], Float.parseFloat(FlightInter.get(k)[originFeeIdx]),
                FlightInter.get(k)[destCityIdx], FlightInter.get(k)[destCountryIdx], 
                Float.parseFloat(FlightInter.get(k)[destFeeIdx]), Boolean.parseBoolean(FlightInter.get(k)[destLoungeIdx]),FlightInter.get(k)[originStateIdx],Boolean.parseBoolean(FlightInter.get(k)[originLoungeIdx]));

                flightMap.put(Integer.parseInt(FlightInter.get(k)[flightNumIdx]), tempFlight);

                Airport tempPort = new Airport(FlightInter.get(k)[originPortIdx], FlightInter.get(k)[originCodeIdx], FlightInter.get(k)[originCityIdx], FlightInter.get(k)[originStateIdx], FlightInter.get(k)[originCountryIdx], Double.parseDouble(FlightInter.get(k)[originFeeIdx]),
                Boolean.parseBoolean(FlightInter.get(k)[originLoungeIdx]));

                if (airportMap.containsKey(FlightInter.get(k)[originCodeIdx]) == false){
                    airportMap.put(FlightInter.get(k)[originCodeIdx], tempPort);
                }
                airportMap.get(FlightInter.get(k)[originCodeIdx]).addFlight(Integer.parseInt(FlightInter.get(k)[flightNumIdx]), tempFlight);
            } 

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    
    /**
    * 
    * @param fileName 
    */
    public void customerDetails(String fileName){
        try{
            this.scribe2 = new Scanner(new File(fileName));

            int idIdx = 0;
            int firstNameIdx = 0;
            int lastNameIdx = 0;
            int DOBIdx = 0;
            int roleIdx = 0;
            int balanceIdx = 0;
            int ticketsPurchasedIdx = 0;
            int memberIdx = 0;
            int userNameIdx = 0;
            int passwordIdx = 0;

            int header = 0; 
            int customerID = 1;      

            scribe2.useDelimiter(",");  

            while (scribe2.hasNextLine()){ 
                if(header > 0){
                    String[] TempArr = scribe2.nextLine().split(",");  
                    customerInter.put(customerID, TempArr);
                    customerID++;
                }else{
                    String[] TempArr = scribe2.nextLine().split(","); 

                    for(int i = 0; i < TempArr.length; i++){
                        switch(TempArr[i]){
                            case "ID":
                                idIdx = i;
                                break;
                            case "First Name":
                                firstNameIdx = i;
                                break;
                            case "Last Name":
                                lastNameIdx = i;
                                break;
                            case "DOB":
                                DOBIdx = i;
                                break;
                            case "Role":
                                roleIdx = i;
                                break;
                            case "Money Available":
                                balanceIdx = i;
                                break;
                            case "Flights Purchased":
                                ticketsPurchasedIdx = i;
                                break;
                            case "MinerAirlines Membership":
                                memberIdx = i;
                                break;
                            case "Username":
                                userNameIdx = i;
                                break;
                            case "Password":
                                passwordIdx = i;
                                break;
                            default:
                                System.out.println("not found at index" + i);
                        }
                    }
                    header ++;  
                }
            }
            
            scribe2.close();

            Set cIds = customerInter.keySet();

            Iterator it2 = cIds.iterator();

            while(it2.hasNext()){
                int k = (int)it2.next();
                if(customerInter.get(k)[roleIdx].toLowerCase().equals("employee")){
                    Employee tempEmployee = new Employee(Integer.parseInt(customerInter.get(k)[idIdx]), customerInter.get(k)[firstNameIdx], 
                    customerInter.get(k)[lastNameIdx], customerInter.get(k)[DOBIdx], customerInter.get(k)[roleIdx], Double.parseDouble(customerInter.get(k)[balanceIdx]), 
                    Integer.parseInt(customerInter.get(k)[ticketsPurchasedIdx]), Boolean.parseBoolean(customerInter.get(k)[memberIdx]), customerInter.get(k)[userNameIdx], customerInter.get(k)[passwordIdx]);

                    employeeMap.put(customerInter.get(k)[firstNameIdx] += " " + customerInter.get(k)[lastNameIdx], tempEmployee);
                    
                }else{
                    Customer tempCustomer = new Customer(Integer.parseInt(customerInter.get(k)[idIdx]), customerInter.get(k)[firstNameIdx], 
                    customerInter.get(k)[lastNameIdx], customerInter.get(k)[DOBIdx], customerInter.get(k)[roleIdx], Double.parseDouble(customerInter.get(k)[balanceIdx]), 
                    Integer.parseInt(customerInter.get(k)[ticketsPurchasedIdx]), Boolean.parseBoolean(customerInter.get(k)[memberIdx]), customerInter.get(k)[userNameIdx], customerInter.get(k)[passwordIdx]);

                    customerMap.put(customerInter.get(k)[firstNameIdx] += " " + customerInter.get(k)[lastNameIdx], tempCustomer);
                }
            } 

        }catch(Exception e){}
    }

     /**
     * returns flight map
     */
    public HashMap<Integer, Flight> flightMap() {
        return flightMap;
    }

    /**
     * returns customer map
     */
    public HashMap<String, Customer> customerMap() {
        return customerMap;
    }
     /**
     * returns employee map
     */
    public HashMap<String, Employee> employeeMap() {
        return employeeMap;
    }
     /**
      * returns airport map
      */
    public HashMap<String, Airport> airportMap() {
        return airportMap;
    }

    @Override
    public void readDetails(String filePath) {
        throw new UnsupportedOperationException("Unimplemented method 'readDetails'");
    }

}
