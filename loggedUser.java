/**
 * * singleton class for currently logged in user
 * @author Garab K Dorji, Daniel Lucio, Sonam S Tshering
 */
public class loggedUser{
    private Customer C;
    private Employee E;
    private int userType;
    private static loggedUser obj;
    /**
     * 
     * @return current logged in user
     */
    public static loggedUser getInstance(){
        if(obj == null){
            obj = new loggedUser();
        }
        return obj;
    }
     /**
     * 
     * @return current Customer
     */
    public Customer getC(){
        return C;
    }
    /**
     * set current customer 
     * @param Cin
     */
    public void setC(Customer Cin){
        this.C = Cin;
    }
    /**
     * 
     * @return current Employee
     */
    public Employee getE(){
        return E;
    }
    /**
     * sets current employee
     * @param Ein
     */
    public void setE(Employee Ein){
        this.E = Ein;
    }
    /**
     * 
     * @return int user type of currently logged in user
     */
    public int getUserType() {
        return userType;
    }
    /**
     * sets user type that is currently logged in
     * @param userType
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    
}
