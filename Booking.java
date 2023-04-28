/**
 * @author Garab K Dorji, Daniel Lucio, Sonam S Tshering
 * @version 1.0
 */
public class Booking {
    private int numTickets;
    private Flight relFlight;
    private Customer relCustomer;
    private Employee relEmployee;
    private double pricePer;
    private double totPrice;
    private int ticklvl;
    private int confirmation;
    private double preTax;
    /**
     * When there is no parameter passed this constructor is called
     */
    public Booking(){}
    /**
     * When customer is passsed as a parameter this constructor is called
     * @param numTickets number of tickets bought
     * @param relFlight related flight of the ticket
     * @param relCustomer related customer
     * @param pricePer   price per ticket
     * @param totPrice  total price
     * @param ticklvl  seat type 
     * @param confirmation confrimation number 
     */
    public Booking(int numTickets, Flight relFlight, Customer relCustomer, double pricePer, double totPrice, double preTax, int ticklvl, int confirmation) {
        this.numTickets = numTickets;
        this.relFlight = relFlight;
        this.relCustomer = relCustomer;
        this.pricePer = pricePer;
        this.totPrice = totPrice;
        this.preTax= preTax;
        this.ticklvl = ticklvl;
        this.confirmation = confirmation;
    }
    /**
     * When employee is passed as a parameter this constructor is called
     * @param numTickets number of tickets bought
     * @param relFlight related flight of the ticket
     * @param relEmployee related employee
     * @param pricePer   price per ticket
     * @param totPrice  total price
     * @param ticklvl  seat type 
     * @param confirmation confrimation number 
     */
    public Booking(int numTickets, Flight relFlight, Employee relEmployee, int pricePer, double totPrice, double preTax, int ticklvl, int confirmation) {
        this.numTickets = numTickets;
        this.relFlight = relFlight;
        this.relEmployee = relEmployee;
        this.pricePer = pricePer;
        this.totPrice = totPrice;
        this.ticklvl = ticklvl;
        this.confirmation = confirmation;
    }
    //------------------------------------getters and setters--------------------------------------
    /**
     * gets number of tickets
     * @return number of tickets
     */
    public int getNumTickets(){return numTickets;}
    /**
     * sets the number of tickets
     * @param numTickets 
     */
    public void setNumTickets(int numTickets){this.numTickets = numTickets;}
    /**
     * gets the related flight object
     * @return flight object bought by the customer or employee
     */
    public Flight getRelFlight() {return relFlight;}
    /**
     * sets the related flight object
     * @param relFlight flight bought by the customer/employee
     */
    public void setRelFlight(Flight relFlight){this.relFlight = relFlight;}
    /**
     * gets the related customer object
     * @return the objects of the customer
     */
    public Customer getRelCustomer(){return relCustomer;}
    /**
     * sets the related customer of the flight
     * @param relCustomer customer object
     */
    public void setRelCustomer(Customer relCustomer){this.relCustomer = relCustomer;}
    /**
     * gets the related customer of the flight
     * @return customer object
     */
    public Employee getRelEmployee(){return relEmployee;}
    /**
     * gets the relaed employee of the fight
     * @param relEmployee employee object
     */
    public void setRelEmployee(Employee relEmployee){this.relEmployee = relEmployee;}
    /**
     * gets the price per seat of the flight
     * @return price per seat 
     */
    public double getPricePer(){return pricePer;}
    /**
     * sets the price per seat of the flight
     * @param pricePer price per seat of the flight
     */
    public void setPricePer(int pricePer){this.pricePer = pricePer;}
    /**
     * gets the total price of the flight
     * @return total price
     */
    public double getTotPrice(){return totPrice;}
    /**
     * sets the total price of the flight
     * @param totPrice total price
     */
    public void setTotPrice(double totPrice){this.totPrice = totPrice;}
    /**
     * gets the ticket level of the flight
     * @return seat type
     */
    public int getTicklvl(){return ticklvl;}
    /**
     * sets the ticket level of the flight
     * @param ticklvl seat type
     */
    public void setTicklvl(int ticklvl){this.ticklvl = ticklvl;}
    /**
     * gets the confirmation code for the ticket
     * @return confirmation code
     */
    public int getConfirmation(){return confirmation;}
    /**
     * sets the confirmation code for the ticket
     * @param confirmation confrimation code
     */
    public void setConfirmation(int confirmation){this.confirmation = confirmation;}
    /**
     * sets pre tax
     * @param preTax
     */

    public void setPreTax(double preTax){this.preTax = preTax;}
    /**
     * 
     * @return double pretax
     */
    public double getPreTax(){return this.preTax;}
    
    //------------------------------------------------------------------------------------------------
    /**
     * printing the list of the ticket
     */
    public void printBook(){
        System.out.println(
            "Number of Tickets: "  + this.numTickets + "\n" +
            "Price per Ticket:  "  + this.pricePer + "\n" +
            "Ticket Class:      "  + this.ticklvl + "\n" +
            "Confirmation No.:  "  + this.confirmation + "\n"
        );
    }
    /**
     * Sets zero to all the values if the ticket is cancelled by the customer/employee
     */
    public void zeroOut(){
        this.numTickets = 0;
        this.pricePer = 0;
        this.totPrice = 0;
    }

}
