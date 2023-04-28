import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
/**
 * This class is a customer class and it has it's own fucntion that handles certain functionalitie related to the customer 
 * 
 * @author Garab K Dorji, Daniel Lucio, Sonam S Tshering
 */
public class Customer extends Person{
    //variable
    private double moneyAvailable;
    private boolean membership;
    private String userName; 
    private String password;
    private String role;
    private int ticketsPurchased;
    private int flightsPurchased;
    private HashMap<Integer, Booking> books = new HashMap<>();
    private double memberSavedMoney;

    /**
     * It is a constructor and creates a new class customer class whenever this class is called 
     * @param ID
     * @param fName
     * @param lName
     * @param DOB
     * @param role
     * @param moneyAvailable
     * @param flightsPurchased
     * @param membership
     * @param userName
     * @param password
     */

    public Customer(int ID, String fName, String lName, String DOB , String role, double moneyAvailable, 
    int  flightsPurchased,  boolean membership, String userName, String password){
        super(ID, fName, lName, DOB, role);
        this.moneyAvailable = moneyAvailable;
        this.membership = membership;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.flightsPurchased = flightsPurchased;
    }
    //getters and setters methods
    /**
     * @return hashmap books that contains all the flights booked by the customer
     */
    public HashMap<Integer, Booking> getBooks() {return books;}
    /**
     * sets flights booked by the customer to the hashmap books
     * @param books
     */
    public void setBooks(HashMap<Integer, Booking> books) {this.books = books;}
    /**
     * 
     * @return double money saved by customer who have memberships
     */
    public double getMemberSaved(){return this.memberSavedMoney;}
    /**
     * sets amount of money saved by the customer who has the membership
     * @param memberSavedMoney
     */
    public void setMemberSaved(double memberSavedMoney){this.memberSavedMoney = memberSavedMoney;}
    /**
     *  
     * @return int which is number a flight purchased by the customer
     */
    public int getflightsPurchased(){return this.flightsPurchased;}
    /**
     * @return double which is money avilable of a customer
     */
    public double getMoneyAvailable() {return this.moneyAvailable;}

    /**
     * @return boolean which checks if the cutomer has membershipt or not
     */
    public boolean isMembership() {return this.membership;}
    /**
     * get the name of the customer
     * @return the name
     */
    public String getUserName() {return this.userName;}
    /**
     * sets the password  for the customer
     * @param password the customer's password
     */
    public void setPassword(String password) {this.password = password;}
    /**
     * gets the password of the customer
     * @return the password 
     */
    public String getPassword(){return this.password;}
    /**
     * gets the number of ticket bought by the customer
     * @return the number Ticket
     */
    public int getTicket() {return this.ticketsPurchased;}

    /**
     * set's the number of ticket bought by the customer in the current transanctions
     * @param ticket cutomer's ticket
     */
    public void setTicket(int ticket) {this.ticketsPurchased= ticket;}

    /**
     * set's customer money avaiable
     * @param moneyAvail 
     */
    public void setMoneyAvailable(double moneyAvailable) {this.moneyAvailable = moneyAvailable;}

    /**
     * sets the membership of the customer
     * @param membership
     */
    public void setMembership(boolean membership) {this.membership = membership;}

    /**
     * set the usname of the customer
     * @param userName
     */
    public void setUserName(String userName) {this.userName = userName;}
    /**
     * Sets number of flight purchased by the customer
     * @param flightsPurchased
     */
    public void setflightsPurchased(int flightsPurchased){this.flightsPurchased = flightsPurchased;}
    
    public void getCustomerList(){
        System.out.println("Name: " + getFName() + " " + getLName());
        System.out.println("Money Available: " + getMoneyAvailable());
        System.out.println("Membership: " + isMembership());
    }
    //---------------------------------------methods--------------------------------------------
    
    /**
     * prints the tickets booked by the customer
     */
    public void seeBooks(){
        if(this.books.size() == 0){
            System.out.println("Currently no flights Booked");
        }else{
            Set bookKeys = books.keySet();
            Iterator it = bookKeys.iterator();

            while(it.hasNext()){
                int k = (int)it.next();
                books.get(k).printBook();
            }
        }
    }
    /**
     * prints out the money available on the customer
     */
    public void seeBalance(){
        System.out.println("Your Current Balance: " + this.moneyAvailable + "\n");
    }
    /**
     * calculates the money lost
     * @param loss
     */
    public void deBalance(double loss){
        this.moneyAvailable -= loss;
    }
    /**
     * calcualtes the money gained
     * @param gain
     */
    public void incBalance(double gain){
        this.moneyAvailable += gain;
    }
    /**
     * calculates tickets purchased
     * @param gain
     */
    public void incPurchased(int gain){
        this.ticketsPurchased += gain;
    }
    /**
     * adds the tickets booked to the haahmap books
     * @param confirmationIn
     * @param bookIn
     */
    public void addBooking(int confirmationIn, Booking bookIn){
        books.put(confirmationIn, bookIn);
    }
    /**
     * calculates the amount of money saved by customers who have the membership
     * @param gain
     */
    public void incMemberSaved(double gain){
        this.memberSavedMoney += gain;
    }

    /**
     * 
     * @return int age in years
     */
    public int calculateAge(){  
        String dob = this.getDOB();
        String [] line = dob.split("/");
        String x = "";

        if(line[0].length() != 2){
            x = "0"+line[0];
            line[0] = x;
        }
        if (line[1].length() != 2){
            x = "0"+line[1];
            line[1] = x;
        }
        dob = line[0]+"/"+line[1]+"/"+line[2];
        

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
        //convert String to LocalDate
        LocalDate localDateDOB = LocalDate.parse(dob, formatter);   

        String dueDate = "04/25/2023";
        LocalDate localDatedue = LocalDate.parse(dueDate, formatter);
        //calculates the amount of time between two dates and returns the years  
        return Period.between(localDateDOB, localDatedue).getYears(); 
    } 
}
