/** this class inherits the customer class and all if its functionalities
 * @author Garab K Dorji, Daniel Lucio, Sonam S Tshering
 */
public class Employee extends Customer{
    /**
     * constructor
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
    public Employee(int ID, String fName, String lName, String DOB , String role, double moneyAvailable, 
    int flightsPurchased, boolean membership, String userName, String password){

        super(ID,fName,lName,DOB,role,moneyAvailable,flightsPurchased,membership,userName,password);
        
    }
}
    