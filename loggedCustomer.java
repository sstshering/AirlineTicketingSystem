/**
 * singleton class for currently logged in customer
 * @author Garab K Dorji, Daniel Lucio, Sonam S Tshering
 */
public class loggedCustomer{
    private Customer C;
    private static loggedCustomer obj;
    
    private loggedCustomer(){}
    /**
     * 
     * @return current Customer logged in
     */
    public static loggedCustomer getInstance(){
        if(obj == null){
            obj = new loggedCustomer();
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
}
