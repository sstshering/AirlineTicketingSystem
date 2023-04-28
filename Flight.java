import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * This class is Flight class and this class has it own function 
 * This class stores the data from the flightschedule.csv 
 * @author Garab K Dorji, Daniel Lucio, Sonam S Tshering
 */
public class Flight {
	//variables
  	private String ID;
	private int flightNum;
	private String orgAirport;
	private String orgCode;
	private String desAirport;
	private String desCode;
	private String departureDate;
	private String departureTime;
	private String arrivalDate;
	private String arrivalTime;
	private String duration;
	private String  distance;
	private String timeZoneDifference;
	private int firstClassSeats;
	private int businessClassSeats;
	private int mainCabinSeats;
	private int totalSeats;
	private String flightType;
	private int surcharge;
	private boolean foodServed;
	private int routeCost;
	private int minerPoints;
	private double firstClassPrice;
	private double businessClassPrice;
	private double mainCabinPrice;
    private double fcProfit = 0;
	private double bcProfit = 0;
	private double expProfit = 0;
	private double mcProfit = 0;
	private double totProfit = 0;
	private double totDiscounts = 0;
    private double totTax = 0;
    private double totMaFee = 0;
	private double totSecurity = 0;
	private String destinationAirportState;
    private String orgAirportCity;
	private String orgAirportCountry;
	private double originAirportFee;
	private String destinationAirportCity;
    private String desAirportCountry;
    private double destinationAirportFee;
	private boolean destinationAirportLounge;
    private String orgAirportState;
	private boolean orgAirportLounge;
	private ArrayList<Booking> books = new ArrayList<>();
	private double seniorDiscount =0;

	public Flight(){}


	/**
	 * It a constructor and created a new flight class when created
	 * @param ID
	 * @param flightNum
	 * @param orgAirport
	 * @param orgCode
	 * @param desAirport
	 * @param desCode
	 * @param departureDate
	 * @param departureTime
	 * @param duration
	 * @param distance
	 * @param timeZoneDifference
	 * @param arrivalDate
	 * @param arrivalTime
	 * @param flightType
	 * @param surcharge
	 * @param foodServed
	 * @param routeCost
	 * @param minerPoints
	 * @param totalSeats
	 * @param firstClassSeats
	 * @param businessClassSeats
	 * @param mainCabinSeats
	 * @param firstClassPrice
	 * @param businessClassPrice
	 * @param mainCabinPrice
	 * @param destinationAirportState
	 * @param orgAirportCity
	 * @param orgAirportCountry
	 * @param originAirportFee
	 * @param destinationAirportCity
	 * @param desAirportCountry
	 * @param destinationAirportFee
	 * @param destinationAirportLounge
	 * @param orgAirportState
	 * @param orgAirportLounge
	 */
	public Flight(String ID, int flightNum, String orgAirport, String orgCode, String desAirport,
    String desCode,String departureDate,String departureTime, String duration,String distance, String timeZoneDifference,
    String arrivalDate,String arrivalTime, String flightType, int surcharge, boolean foodServed, int routeCost, 
    int minerPoints,  int totalSeats, int firstClassSeats,int businessClassSeats,int mainCabinSeats, Double firstClassPrice,
    Double businessClassPrice,Double mainCabinPrice, String destinationAirportState, String orgAirportCity, String orgAirportCountry, 
    float originAirportFee,String destinationAirportCity,String desAirportCountry, float destinationAirportFee, boolean destinationAirportLounge,
    String orgAirportState, boolean orgAirportLounge){

		this.ID = ID;
		this.flightNum = flightNum;
		this.orgAirport = orgAirport;
		this.orgCode = orgCode;
		this.desAirport = desAirport;
		this.desCode = desCode;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.duration = duration;
		this.distance = distance;
		this.timeZoneDifference = timeZoneDifference;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.firstClassSeats = firstClassSeats;
		this.businessClassSeats = businessClassSeats;
		this.mainCabinSeats = mainCabinSeats;
		this.totalSeats = totalSeats;
		this.flightType = flightType;
		this.surcharge = surcharge;
		this.foodServed = foodServed;
		this.routeCost = routeCost;
		this.minerPoints = minerPoints;
		this.firstClassPrice = firstClassPrice;
		this.businessClassPrice = businessClassPrice;
		this.mainCabinPrice = mainCabinPrice;
		this.destinationAirportState = destinationAirportState;
		this.orgAirportCity = orgAirportCity;
		this.orgAirportCountry = orgAirportCountry;
		this.originAirportFee = originAirportFee;
		this.destinationAirportCity = destinationAirportCity;
		this.desAirportCountry = desAirportCountry;
		this.destinationAirportFee = destinationAirportFee;
		this.destinationAirportLounge = destinationAirportLounge;
		this.orgAirportState = orgAirportState;
		this.orgAirportLounge = orgAirportLounge;
	}
	
    //-----------------------------------------setters-------------------------------------------------------
	/**
	 * sets senior discount
	 * @param seniorDiscount
	 */
	public void setSeniorDiscount(double seniorDiscount){ this.seniorDiscount = seniorDiscount;}
	/**
	 * sets total tax fee
	 * @param totTax
	 */
	public void setTotTax(double totTax) { this.totTax = totTax;}
	/**
	 * sets total membership fee
	 * @param totMaFee
	 */
	public void setTotMaFee(double totMaFee) { this.totMaFee = totMaFee;}
	/**
	 * sets total security fee
	 * @param totSecurity
	 */
	public void setTotSecurity(double totSecurity) {this.totSecurity = totSecurity;}
	/**
	 * sets origin airport lounge
	 * @param orgAirportLounge
	 */
	public void setOrgAirportLounge(boolean orgAirportLounge) {this.orgAirportLounge = orgAirportLounge;}
	/**
	 * sets origin airport fee
	 * @param originAirportFee
	 */
	public void setOriginAirportFee(float originAirportFee) {this.originAirportFee = originAirportFee;}
	/**
	 * sets origin airport city
	 * @param orgAirportCity
	 */
	public void setOrgAirportCity(String orgAirportCity) {this.orgAirportCity = orgAirportCity;}
	/**
	 * sets origin airport country
	 * @param orgAirportCountry
	 */
	public void setOrgAirportCountry(String orgAirportCountry) {this.orgAirportCountry = orgAirportCountry;}
	/**
	 * sets origin airport state
	 * @param orgAirportState
	 */
	public void setOrgAirportState(String orgAirportState) {this.orgAirportState = orgAirportState;}
	/**
	 * sets origin airport
	 * @param orgAirport
	 */
	public void setOrgAirport(String orgAirport) {this.orgAirport = orgAirport;}
	/**
	 * sets origin code
	 * @param orgCode
	 */
    public void setOrgCode(String orgCode) {this.orgCode = orgCode;}
	/**
	 * sets departure date
	 * @param departureDate
	 */
    public void setDepartureDate(String departureDate) {this.departureDate = departureDate;}
	/**
	 * sets departure time
	 * @param departureTime
	 */
	public void setDepartureTime(String departureTime) {this.departureTime = departureTime;}
	/**
	 * sets arrival date;
	 * @param arrivalDate
	 */
	public void setArrivalDate(String arrivalDate) {this.arrivalDate = arrivalDate;}
	/**
	 * sets arrival time
	 * @param arrivalTime
	 */
	public void setArrivalTime(String arrivalTime) {this.arrivalTime = arrivalTime;}
	/**
	 * sets main cabin seats
	 * @param mainCabinSeats
	 */
	public void setMainCabinSeats(int mainCabinSeats) {this.mainCabinSeats = mainCabinSeats;}
	/**
	 * sets frist class seats
	 * @param firstClassSeats
	 */
	public void setFirstClassSeats(int firstClassSeats) {this.firstClassSeats= firstClassSeats;}
	/**
	 * sets business class seats
	 * @param businessClassSeats
	 */
	public void setBusinessClassSeats(int businessClassSeats) {this.businessClassSeats = businessClassSeats;}
	/**
	 * sets route cost
	 * @param routeCost
	 */
	public void setRouteCost(int routeCost) {this.routeCost = routeCost;}
	/**
	 * sets food served
	 * @param foodServed
	 */
	public void setFoodServed(boolean foodServed) {this.foodServed = foodServed;}
	/**
	 * sets flight type
	 * @param flightType
	 */
	public void setFlightType(String flightType) {this.flightType = flightType;}
	/**
	 * sets surcharge
	 * @param surcharge
	 */
	public void setSurcharge(int surcharge) {this.surcharge = surcharge;}
	/**
	 * sets duration
	 * @param duration
	 */
	public void setDuration(String duration){this.duration = duration;}
	/**
	 * sets first class price
	 * @param firstClassPrice
	 */
    public void setFirstClassPrice(double firstClassPrice) {this.firstClassPrice = firstClassPrice;}
	/**
	 * sets business class price
	 * @param businessClassPrice
	 */
	public void setBusinessClassPrice(double businessClassPrice) {this.businessClassPrice = businessClassPrice;}
	/**
	 * sets main cabin price
	 */
    public void setMainCabinPrice(double mainCabinPrice) {this.mainCabinPrice = mainCabinPrice;}
	/**
	 * sets destination airport fee
	 * @param destinationAirportFee
	 */
	public void setDestinationAirportFee(float destinationAirportFee) {this.destinationAirportFee = destinationAirportFee;}
	/**
	 * sets miner points
	 * @param minerPoints
	 */
	public void setMinerPoints(int minerPoints) {this.minerPoints = minerPoints;}
	/**
	 * sets flight number
	 */
	public void setFlightNum(int flightNum) {this.flightNum = flightNum;}
	/**
	 * sets first class profit
	 * @param fcProfit
	 */
	public void setFcProfit(double fcProfit) {this.fcProfit = fcProfit;}
	/**
	 * sets business class profit
	 * @param bcProfit
	 */
	public void setBcProfit(double bcProfit) {this.bcProfit = bcProfit;}
	/**
	 * sets main cabin profit
	 * @param mcProfit
	 */
	public void setMcProfit(double mcProfit) {this.mcProfit = mcProfit;}
	/**
	 * sets total flight profit
	 * @param totProfit
	 */	
	public void setTotProfit(double totProfit) {this.totProfit = totProfit;}
	/**
	 * sets expected flight profit 
	 * @param expProfit
	 */
	public void setExpProfit(double expProfit) {this.expProfit = this.totProfit - this.routeCost;}
	/**
	 * sets destination airport
	 * @param desAirport
	 */
	public void setDestAirport(String desAirport) {this.desAirport = desAirport;}
	/**
	 * sets destination code
	 * @param desCode
	 */
	public void setDesCode(String desCode) {this.desCode = desCode;}
	/**
	 * sets flight distance
	 * @param distance
	 */
	public void setDistance(String distance) {this.distance = distance;}
	/**
	 * sets total seats on flight
	 * @param totalSeats
	 */
	public void setTotalSeats(int totalSeats) {this.totalSeats = totalSeats;}

	/**
	 * sets total seats on flight
	 * @param timeZoneDifference
	 */
	public void setTimeZoneDifference(String timeZoneDifference) {this.timeZoneDifference = timeZoneDifference;}

	
    //----------------------------------------------getters--------------------------------------------------
	/**
	 * 
	 * @return double senior discount
	 */
	public double getSeniorDiscount(){ return seniorDiscount; }
	/** 
	 * @return double total tax fee
	 */
	public double getTotTax() {return totTax;}
	/**
	 * 
	 * @return double total membershipr fee
	 */
	public double getTotMaFee() {return totMaFee;}
	/**
	 * 
	 * @return double total security fee
	 */
	public double getTotSecurity() { return totSecurity;}
	/** 
	 * @return double first class profit
	 */
	public double getFcProfit() {return fcProfit;}
	/** 
	 * @return double destination airport fee
	 */
	public double getDestinationAirportFee() {return destinationAirportFee;}
	/**
	 * @return String origin airport state
	 */
	public String getOrgAirportState() {return orgAirportState;}
	/**
	 * @return String origin city
	 */
	public String getOrgAirportCity() {return orgAirportCity;}
	/**
	 * @return boolean origin airport lounge
	 */
	public boolean isOrgAirportLounge() {return orgAirportLounge;}
	/**
	 * @return double origin airport fee
	 */
	public double getOriginAirportFee() {return originAirportFee;}
	/**
	 * @return String origin airport country
	 */
	public String getOrgAirportCountry() {return orgAirportCountry;}
	/**
	 * @return String flight ID
	 */
	public String getID(){return this.ID;}
	/** 
	 * @return String origin airport
	 */
	public String getOriginAirport(){return this.orgAirport;}
	/** 
	 * @return String origin code
	 */
	public String getOriginCode() {return this.orgCode;}
	/**
	 * @return String detination airport
	 */
	public String getDestinationAirport() {return this.desAirport;}
	/**
	 * @return String destination code
	 */
	public String getDestinationCode() {return this.desCode;}
	/**
	 * @return String departure date
	 */
	public String getDepartureDate() {return this.departureDate;}
	/**
	 * @return double business class profit
	 */
	public double getBcProfit() {return this.bcProfit;}
	/** 
	 * @return String departure time
	 */
	public String getDepartureTime() {return this.departureTime;}
	/** 
	 * @return double main cabin profit
	 */
	public double getMcProfit() {return mcProfit;}
	/**
	 * @return double total flight profit
	 */
	public double getTotProfit() {return totProfit;}
	/** 
	 * @return String arrival date
	 */
	public String getArrivalDate() {return this.arrivalDate;}
	/**
	 * @return String arrival time
	 */
	public String getArrivalTime() {return this.arrivalTime;}
	/**
	 * @return int flight duration
	 */
	public String getDuration() {return this.duration;}
	/** 
	 * @return int flight distance
	 */
	public String getDistance() {return this.distance;}
	/**
	 * @return int time zone difference
	 */
	public String getTimeZoneDifference() {return this.timeZoneDifference;}
	/**
	*@return int first class seats
	*/
	public int getFirstClassSeats() {return this.firstClassSeats;}
	/**
	 * @return int business class seats
	 */
	public int getBusinessClassSeats() {return this.businessClassSeats;}
	/**
	 * @return int main cabin seats
	 */
	public int getMainCabinSeats() {return this.mainCabinSeats;}
	/**
	 * @return int total seats
	 */
	public int getTotalSeats() {return this.totalSeats;}
	/**
	 * @return String flight type
	 */
	public String getFlightType() {return flightType;}
	/*** 
	 * @return int surcharge
	 */
	public int getSurcharge() {return surcharge;}
	/**
	 * @return double first class price
	 */
    public double getFirstClassPrice() {return firstClassPrice;}
	/**
	 * @return double business class price
	 */
    public double getBusinessClassPrice() {return businessClassPrice;}
	/**
	 * @return double main cabin price
	 */
    public double getMainCabinPrice() {return mainCabinPrice;}
	/**
	 * @return boolean food served on flight
	 */
	public boolean isFoodServed() {return this.foodServed;}
	/** 
	 * @return int route cost
	 */
	public int getRouteCost() {return routeCost;}
	/**
	 * @return int miner points
	 */
	public int getMinerPoints() {return this.minerPoints;}
	/**
	 * 
	 * @return arraylist of bookings
	 */
	public ArrayList<Booking> getBooks() { return books; }
	/**
	 * @return int flight number
	 */
	public int getFlightNum() {return this.flightNum;}
	/**
	 * @return String destination airport
	 */
	public String getDesAirport() {return desAirport;}
	/**
	 * @return String destination code
	 */
	public String getDesCode() {return desCode;}
	/**
	 * @return double expected profit
	 */
	public double getExpProfit() {return expProfit;}
	
	//-----------------------------------------------methods---------------------------------------------------
	
	/**
	 * prints a short detail of the flight 
	 */
    public void printFlightShort(){
        System.out.println(
            "Origin Airport     : "  + this.orgAirport + "\n" +
            "Destination Airport: " + this.desAirport + "\n" +
            "Flight Number      : " + this.flightNum + "\n");
    }
	/**
	 * prints all of the flight details
	 */
	public void printFlight(){
		System.out.println("Flight ID                  : " + this.ID);
        System.out.println("Flight Number              : " + this.flightNum);
		System.out.println("Origin Airport             : " + this.orgAirport);
		System.out.println("Origin Code                : " + this.orgCode);
		System.out.println("Destination Airport        : " + this.desAirport);
		System.out.println("Destination Code           : "+this.desCode);
		System.out.println("Depature Date              : " + this.departureDate);
		System.out.println("Depature Time              : "+ this.departureTime);
		System.out.println("Arrival Date               : " + this.arrivalDate );
		System.out.println("Arrival Time               : " + this.arrivalTime);
		System.out.println("Duration                   : " + this.duration);
		System.out.println("Distance                   : " + this.distance);
		System.out.println("Time Zone Difference       : " + this.timeZoneDifference);
		System.out.println("First Class Seats          : "+ this.firstClassSeats);
		System.out.println("Business Class Seats       : "+this.businessClassSeats);
		System.out.println("Main Cabin Seats           : "+this.mainCabinSeats);
		System.out.println("Total Seats                : "+this.totalSeats);
		System.out.println("Flight Type                : " + this.flightType);
		System.out.println("Surcharge                  : " + this.surcharge);
		System.out.println("Food served                : " + this.foodServed);
		System.out.println("routecost                  : " + this.routeCost);
		System.out.println("Miner Points               : " + this.minerPoints);
		System.out.println("Origin Airport City        : " + this.orgAirportCity);
		System.out.println("Destination Airport City   : " + this.destinationAirportCity);
		System.out.println("Origin Airport Country     : " + this.orgAirportCountry);
		System.out.println("Destination Airport Country: " + this.desAirportCountry);
		System.out.println("Origin Airport Fee         : " + this.originAirportFee);
		System.out.println("Destination Airport Fee    : " + this.destinationAirportFee);
		System.out.println("Destination Airport Lounge : " + this.destinationAirportLounge);
		System.out.println("Origin Airport Lounge      : " + this.orgAirportLounge);
	}
	/**
	 * adds a booking to the booking list
	 * @param bookIn
	 */
    public void addBooking(Booking bookIn){
        books.add(bookIn);
    }
	/**
	 * prints out all of the bookings assoiciated with the flight
	 */
    public void seeBooks(){
        for(int i = 0; i < books.size(); i++){
            books.get(i).printBook();
        }
    }
	/**
	 * decrements the number of first class seats
	 * @param loss
	 */
    public void deFirstClass(int loss){
        this.firstClassSeats -= loss;
    }
	/**
	 * decrements the number of buisness class seats
	 * @param loss
	 */
    public void deBuisness(int loss){
        this.businessClassSeats -= loss;
    }
	/**
	 * decrements the number of main cabin seats
	 * @param loss
	 */
    public void deMainC(int loss){
        this.mainCabinSeats -= loss;
    }
	/**
	 * decrements the total profit of the flight
	 * @param loss
	 */
    public void deTotProfit(double loss){
        this.totProfit -= loss;
    }
	/**
	 * decrements the profit from first class seats
	 * @param loss
	 */
    public void deFcProfit(double loss){
        this.fcProfit -= loss;
    }
	/**
	 * decrements the profit from Buisness class seats
	 * @param loss
	 */
    public void deBcProfit(double loss){
        this.bcProfit -= loss;
    }
	/**
	 * decrements the profit from Main cabin seats
	 * @param loss
	 */
    public void deMcProfit(double loss){
        this.mcProfit -= loss;
    }
	/**
	 * decrements the total discouts given
	 * @param loss
	 */
    public void deTotDiscounts(double loss){
        this.totDiscounts -= loss;
    }
	/**
	 * decrements the total taxed on the flight
	 * @param loss
	 */
    public void deTotTax(double loss){
        this.totTax -= loss;
    }
	/**
	 * decrements the profit from miner air fee
	 * @param loss
	 */
    public void deTotMaFee(double loss){
        this.totMaFee -= loss;
    }
	/**
	 * decrements the profit from the security fee
	 * @param loss
	 */
    public void deTotSecurity(double loss){
        this.totSecurity -= loss;
    }
	/**
	 * increments the total profit
	 * @param gain
	 */
    public void incTotProfit(double gain){
        this.totProfit += gain;
    }
	/**
	 * increments the profit from first class seats
	 * @param gain
	 */
    public void incFcProfit(double gain){
        this.fcProfit += gain;
    }
	/**
	 * increments the profit from Buisness class seats
	 * @param gain
	 */
    public void incBcProfit(double gain){
        this.bcProfit+= gain;
    }
	/**
	 * increments the profit from Main cabin seats
	 * @param gain
	 */
    public void incMcProfit(double gain){
        this.mcProfit += gain;
    }
	/**
	 * increments the number of first class seats
	 * @param gain
	 */
    public void incFirstClass(int gain){
        this.firstClassSeats += gain;
    }
	/**
	 * increments the number of buisness class seats
	 * @param gain
	 */
    public void incBuisness(int gain){
        this.businessClassSeats += gain;
    }
	/**
	 * increments the number of main cabin seats
	 * @param gain
	 */
    public void incMainC(int gain){
        this.mainCabinSeats += gain;
    }
	/**
	 * increments the profit from discounts given
	 * @param gain
	 */
    public void incTotDiscounts(double gain){
        this.totDiscounts += gain;
    }
	/**
	 * increments the total taxed on the flight
	 * @param gain
	 */
    public void incTotTax(double gain){
        this.totTax += gain;
    }
	/**
	 * incremets profit from miner air fee
	 * @param gain
	 */
    public void incTotMaFee(double gain){
        this.totMaFee += gain;
    }
	/**
	 * increments profit from security fee
	 * @param gain
	 */
    public void incTotSecurity(double gain){
        this.totSecurity += gain;
    }

	/**
	 * this methods resets the duration of the flight
	 * @throws ParseException
	 */
    public void ResetDuration () throws ParseException {
        String           date1    = this.getDepartureDate();
        String           time1    = this.getDepartureTime();
  
        String           date2    = this.getArrivalDate(); 
        String           time2    = this.getArrivalTime();
  
        String           format   = "MM/dd/yy hh:mm a";  
     
        SimpleDateFormat sdf      = new SimpleDateFormat(format);
      
        Date             dateObj1 = sdf.parse(date1 + " " + time1);
        Date             dateObj2 = sdf.parse(date2 + " " + time2);
     
        long             diff     = dateObj2.getTime() - dateObj1.getTime();

	
        int              diffmin  = (int) ((diff)/ (60 * 1000));
  
        this.setDuration(String.valueOf(diffmin));
    }

}