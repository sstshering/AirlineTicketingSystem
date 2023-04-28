/**
 * this class handles exceptions
 * @author Garab K Dorji, Daniel Lucio, Sonam S Tshering
 */
public class MyException extends Exception{
    MyException (){

    }
    /**
     * 
     * @param message
     */
    MyException(String message){
        super(message);
    }
}