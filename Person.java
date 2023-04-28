/**
 * This class is an abstract class that is extended by the customer class and employee class
 * @author Garab Dorji, Daniel Lucio, Sonam S Tshering
 */
public  abstract class Person {
    private String fName;
    private String lName; 
    private int ID;
    private String DOB;
    private String role;

    /**
     * This constructoer is called when this class is called
     * @param ID
     * @param fName
     * @param lName
     * @param DOB
     * @param role
     */
    public Person(int ID, String fName, String lName, String DOB, String role){
        this.ID = ID;
        this.fName = fName;
        this.lName = lName;
        this.DOB = DOB;
        this.role = role;
    }
    /**
     * 
     * @return string first name 
     */
    public String getFName(){
        return this.fName;
    }
    /**
     * 
     * @return string last name
     */
    public String getLName(){
        return this.lName;
    }
    /**
     * 
     * @return int ID 
     */
    public int getID(){
        return this.ID;
    }
    /**
     * 
     * @return string DOB 
     */
    public String getDOB(){
        return this.DOB;
    }
    /**
     * 
     * @return string role of the user: customer or employee
     */
    public String getRole(){
        return this.role;
    }
    
}
