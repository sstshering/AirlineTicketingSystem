import java.io.FileWriter;
import java.io.File;
/**
 * this class handles writing file log
 * @author Garab K Dorji, Daniel Lucio, Sonam S Tshering
 */
public class AirLog{
    private FileWriter author;
    private static AirLog obj;
    /**
     * constructor
     */
    private AirLog() {
    }
    /**
     * 
     * @return new airlog 
     */
    public static AirLog getInstance(){
        if(obj == null){
            obj = new AirLog();
        }
        return obj;
    }
    /**
     * 
     * @return returns filewrite
     */
    public FileWriter getAuthor() {return author;}
    /**
     * sets new log file
     */
    public void setUpLog(){
        File fileName = new File("Log.txt"); 
        fileName.delete(); 
    }
    /**
     * flights purchased by user
     * @param Book
     */
    public void makeBook(Booking Book){
        try{
            author = new FileWriter("Log.txt", true);
            loggedUser user = loggedUser.getInstance();
            if(user.getUserType() == 1){
                author.write(user.getE().getFName() + " " + user.getE().getLName() + " purchased "+ Book.getNumTickets() +" tickets to flight "+ Book.getRelFlight().getFlightNum() + "\n");
            }else{
                author.write(user.getC().getFName() + " " + user.getC().getLName() + " purchased "+ Book.getNumTickets() +" tickets to flight "+ Book.getRelFlight().getFlightNum() + "\n");
            }
            author.close();
        }catch(Exception e){}
    }
    /**
     * tickets cancelled by user
     * @param con
     */
    public void logBCancel(int con){
        try{
            author = new FileWriter("Log.txt", true);
            loggedUser user = loggedUser.getInstance();
            if(user.getUserType() == 1){
                author.write(user.getE().getFName() + " " + user.getE().getLName() + " canceled their booking at confirmation no. " + con + "\n");
            }else{
                author.write(user.getC().getFName() + " " + user.getC().getLName() + " canceled their booking at confirmation no. " + con + "\n");
            }
            author.close();
        }catch(Exception e){}

    }
    /**
     * flights cancelled
     * @param fn
     */
    public void logFcancel(int fn){
        try{
            author = new FileWriter("Log.txt", true);
            loggedUser user = loggedUser.getInstance();
            author.write(user.getE().getFName() + " " + user.getE().getLName() + " canceled flight"+ fn);
            author.close();
        }catch(Exception e){}

    }
    /**
     * updates flight changes
     * @param field
     * @param fIn
     * @param change
     */
    public void logFchange(int field, Flight fIn, String change){
        try{
            author = new FileWriter("Log.txt", true);
                 if(field ==  1) {author.write("Fligth Id: " + fIn.getID() + " Updated Origin Airport with this value: " + change + " \n"); }
            else if(field ==  2) {author.write("Fligth Id: " + fIn.getID() + " Updated Origin Code with this value: " + change + " \n"); }
            else if(field ==  3) {author.write("Fligth Id: " + fIn.getID() + " Updated Destination Airport with this value: " + change + " \n"); }
            else if(field ==  4) {author.write("Fligth Id: " + fIn.getID() + " Updated Destination Code with this value: " + change + " \n"); }
            else if(field ==  5) {author.write("Fligth Id: " + fIn.getID() + " Updated Departure Date with this value: " + change + " \n"); }
            else if(field ==  6) {author.write("Fligth Id: " + fIn.getID() + " Updated Departure time with this value: " + change + " \n"); }
            else if(field ==  7) {author.write("Fligth Id: " + fIn.getID() + " Updated Arrival Date with this value: " + change + " \n"); }
            else if(field ==  8) {author.write("Fligth Id: " + fIn.getID() + " Updated Arrival time with this value: " + change + " \n"); }
            else if(field ==  9) {author.write("Fligth Id: " + fIn.getID() + " Updated Flight Duration with this value: " + change + " \n"); }
            else if(field == 10) {author.write("Fligth Id: " + fIn.getID() + " Updated Flight Distance with this value: " + change + " \n"); }
            else if(field == 11) {author.write("Fligth Id: " + fIn.getID() + " Updated Time Zone Difference with this value: " + change + " \n"); }
            else if(field == 12) {author.write("Fligth Id: " + fIn.getID() + " Updated First Class Price with this value: " + change + " \n"); }
            else if(field == 13) {author.write("Fligth Id: " + fIn.getID() + " Updated Buisness class price with this value: " + change + " \n"); }
            else if(field == 14) {author.write("Fligth Id: " + fIn.getID() + " Updated Main cabin price with this value: " + change + " \n"); }
            else if(field == 15) {author.write("Fligth Id: " + fIn.getID() + " Updated Amount of First class seats with this value: " + change + " \n"); }
            else if(field == 16) {author.write("Fligth Id: " + fIn.getID() + " Updated Amount of Buisness class seats with this value: " + change + " \n"); }
            else if(field == 17) {author.write("Fligth Id: " + fIn.getID() + " Updated Amount of main cabin seats with this value: " + change + " \n"); }
            else if(field == 18) {author.write("Fligth Id: " + fIn.getID()+ " Updated Amount of Total seats with this value: " + change + " \n"); }
             
            author.close();

        }catch(Exception e){}

    }
    
}
