import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;

/**
 * this airport class handles all functionality related to airport
 * @author Daniel Lucio, Sonam Seldon, Garab Dorji
 */

public class Airport {
    //attributes
    private HashMap<Integer, Flight> outFlights = new HashMap<>();
    private String OriginPort;
    private String OriginCode;
    private String originCity;
    private String originState;
    private String originCountry;
    private double originFee;
    private boolean originLounge;
    private double totFees;

    /**
     * constructor
     * @param originPort
     * @param originCode
     * @param originCity
     * @param originState
     * @param originCountry
     * @param originFee
     * @param originLounge
     */
    public Airport(String originPort, String originCode, String originCity, String originState, String originCountry,
                   double originFee, boolean originLounge){

        OriginPort = originPort;
        OriginCode = originCode;
        this.originCity = originCity;
        this.originState = originState;
        this.originCountry = originCountry;
        this.originFee = originFee;
        this.originLounge = originLounge;
    }
    /**
     * 
     * @return flight hashmap
     */
    public HashMap<Integer, Flight> getOutFlights() {
        return outFlights;
    }
    /**
     * sets flight hashma[]
     * @param outFlights
     */
    public void setOutFlights(HashMap<Integer, Flight> outFlights) {
        this.outFlights = outFlights;
    }
    /**
     * 
     * @return String origin airport
     */
    public String getOriginPort() {
        return OriginPort;
    }
    /**
     * sets origin airport
     * @param originPort
     */
    public void setOriginPort(String originPort){OriginPort = originPort;}
    /**
     * 
     * @return String origin code
     */
    public String getOriginCode(){return OriginCode;}
    /**
     * sets origin code 
     * @param originCode
     */
    public void setOriginCode(String originCode){OriginCode = originCode;}
    /**
     * 
     * @return String origin city
     */
    public String getOriginCity(){return originCity;}
    /**
     * sets origin city
     * @param originCity
     */
    public void setOriginCity(String originCity) {this.originCity = originCity;}
    /**
     * 
     * @return String origin state
     */
    public String getOriginState(){return originState;}
    /**
     * sets origin state
     * @param originState
     */
    public void setOriginState(String originState){this.originState = originState;}
    /**
     * 
     * @return String origin country
     */
    public String getOriginCountry(){return originCountry;}
    /**
     * sets origin country
     * @param originCountry
     */
    public void setOriginCountry(String originCountry){this.originCountry = originCountry;}
    /**
     * 
     * @return double origin fee
     */
    public double getOriginFee(){return originFee;}
    /**
     * sets origin fee
     * @param originFee
     */
    public void setOriginFee(double originFee){this.originFee = originFee;}
    /**
     * 
     * @return boolean origin lounge
     */
    public boolean isOriginLounge(){return originLounge;}
    /**
     * sets origin lounge
     * @param originLounge
     */
    public void setOriginLounge(boolean originLounge){this.originLounge = originLounge;}
    /**
     * 
     * @return double total fees
     */
    public double getTotFees() {return totFees;}
    /**
     * sets total fees
     * @param totFees
     */
    public void setTotFees(double totFees) {this.totFees = totFees;}
    /**
     * puts the flight into a hashmap
     * @param fn
     * @param fIn
     */
    public void addFlight(int fn, Flight fIn){
        outFlights.put(fn, fIn);
    }
    /**
     * lists flight available between a given origin code and dest code
     * @param dest
     */
    public void listFlight(String dest){
        if(this.outFlights.size() == 0){
            System.out.println("Currently no flights out");
        }else{
            Set outKeys = outFlights.keySet();
            Iterator it = outKeys.iterator();

            while(it.hasNext()){
                int k = (int)it.next();
                if(outFlights.get(k).getDestinationCode().equals(dest)){
                    outFlights.get(k).printFlightShort();
                }
            }
        }
    }
    /**
     * prints the airport info
     */
    public void printPort(){
        System.out.println("Airport Code             : " + this.OriginCode + "\n" +
                           "Airport Name             : " + this.OriginPort + "\n" +
                           "Airport City             : " + this.originCity + "\n" +
                           "Airport State            : " + this.originState + "\n" +
                           "Airport Country          : " + this.originCountry + "\n" +
                           "Airport Fee              : " + this.originFee + "\n" +
                           "Airport Lounge Status    : " + this.originLounge + "\n" +
                           "Airport Total Fee Charges: " + this.totFees + "\n"
                           );
    }
    /**
     * sets revenue lost
     * @param loss
     */
    public void deTotFees(double loss){
        this.totFees -= loss;
    }
    /**
     * sets revenue gained
     */
    public void incTotFee(double gain){
        this.totFees += gain;
    }  
}
