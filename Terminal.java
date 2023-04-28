import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Terminal class that holds all the funcions for display and working with data
 * 
 * @version 1.0
 * @author Garab K Dorji, Daniel Lucio, Sonam S Tshering
 */

public class Terminal {

    // -------------------------------------------------------------------------------Menu
    // displays and
    // Functions-------------------------------------------------------------------------------

    private static Scanner tChest = new Scanner(System.in);
    private static loggedUser operator = loggedUser.getInstance();
    private static double minerAirFee = 9.15;
    private static double securityFee = 5.6;

    public Terminal() {
    }

    // ------------------------------------------------------------------------------------Initial
    // Display-----------------------------------------------------------------------------------------

    /**
     * Initial menu asks the user under what type they want to proceed as
     * 
     * @version 1.0
     */

    public int askUser() { // @
        try {
            System.out.println("Hello, Welcome to Miner Air, how would you like to proceed as:\n" +
                    "Customer: 1\n" +
                    "Employee: 2\n" +
                    "Exit    : 0\n");
            int option = tChest.nextInt();

            if (option == 1) {
                operator.setUserType(0);
                return 1;
            } else if (option == 2) {
                operator.setUserType(1);
                return 2;
            } else if (option == 0) {
                return 0;
            } else {
                return -1;
            }

        } catch (Exception e) {
        }
        System.out.println("--Invalid user type--\n");
        return -1;
    }

    // --------------------------------------------------------------------------------------Login
    // Displays-------------------------------------------------------------------------------------------

    /**
     * Function for user to log in as a customer
     * 
     * @version 1.0
     * @param customers
     */

    public int customerLogin(HashMap<String, Customer> customers) {
        Scanner tChest2 = new Scanner(System.in);

        System.out.println("First Name: ");
        String firstN = tChest2.nextLine();

        System.out.println("Last Name: ");
        String lastN = tChest2.nextLine();

        String tempKey = firstN + " " + lastN;

        if (customers.containsKey(tempKey)) {
            System.out.println("What is your username: ");
            String tempUsr = tChest2.nextLine();

            System.out.println("What is your password: ");
            String tempPass = tChest2.nextLine();

            if ((customers.get(tempKey).getUserName().equals(tempUsr)) &&
                    (customers.get(tempKey).getPassword().equals(tempPass))) {

                operator.setC(customers.get(tempKey));

                return 1;

            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    // --------------------------------------------------------------------------------------------

    /**
     * Function for user to log in as employee
     * 
     * @version 1.0
     * @param employees
     */

    public int employeeLogin(HashMap<String, Employee> employees) { // @
        Scanner tChest3 = new Scanner(System.in);

        System.out.println("First Name: ");
        String firstN = tChest3.nextLine();

        System.out.println("Last Name: ");
        String lastN = tChest3.nextLine();

        String tempKey = firstN + " " + lastN;

        if (employees.containsKey(tempKey)) {
            System.out.println("What is your username: ");
            String tempUsr = tChest3.nextLine();

            System.out.println("What is your password: ");
            String tempPass = tChest3.nextLine();

            if ((employees.get(tempKey).getUserName().equals(tempUsr)) &&
                    (employees.get(tempKey).getPassword().equals(tempPass))) {

                operator.setE(employees.get(tempKey));

                return 1;

            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    // -----------------------------------------------------------------------------------------Customer
    // Actions-------------------------------------------------------------------------------------

    /**
     * Function that displays and takes input for customer actions
     * 
     * @version 1.0
     */

    public static int cActions() { // method to display menu for user options and return their choice //@
        try {
            Scanner tChest3 = new Scanner(System.in);

            System.out.println("How would you like to procced?\n" +
                    "Book Flight  : Type 1\n" +
                    "Cancel Flight: Type 2\n" +
                    "See Bookings : Type 3\n" +
                    "See Balance  : Type 4\n" +
                    "Log out      : Type 0\n");

            int choice = tChest3.nextInt();
            if (choice < 0 || choice > 4) {
                System.out.println("--Invalid Option.--\n");
                return -1;
            }
            return choice;

        } catch (Exception e) {
        }
        return -1;
    }

    // ----------------------------------------------------------------------------------------Customer
    // book
    // ticket------------------------------------------------------------------------------------

    /**
    * Function for customer and emplyees to book flights
    * @version 1.0
    * @param FlightInfo
    * @param ports
    */

    public static int bookTicket(HashMap<Integer, Flight> FlightInfo, HashMap<String, Airport> ports){ //Method for processing customer purchase
        try{
            Scanner tChest4 = new Scanner(System.in);
            AirLog log = AirLog.getInstance();
            Confirmation confirmation = Confirmation.getInstance();

            System.out.println("Would you like to search by:"+ "\n"+
                                "ID            :type 1"+ "\n"+
                                "Airport Codes :type 2");

            int searchOption = Integer.parseInt(tChest4.nextLine());

            if(searchOption == 2){
                //ask origin
                System.out.println("What is the origin airport code?\n");
                String tempOrigin = tChest4.nextLine();
                //ask dest
                System.out.println("What is the destination airport code?\n");
                String tempDest = tChest4.nextLine();
                ports.get(tempOrigin).listFlight(tempDest);
            }else if(searchOption == 1){
                int hold = 0;
            }else{
                return -1;
            }

            System.out.println("Please Type in your desrired Flight Number: \n");
            int tempFn = tChest4.nextInt();

            if(FlightInfo.containsKey(tempFn)){
                FlightInfo.get(tempFn).printFlight();
            }else{
                System.out.println("Invalid Flight\n");
                return -1;
            }

            tChest4.nextLine();
            System.out.println("Is this your desired flight?\n");
            String decision = tChest4.nextLine();

            if(decision.equals("yes")){

                Flight tempFlight = FlightInfo.get(tempFn);

                double portFee = ports.get(tempFlight.getOriginCode()).getOriginFee();

                ports.get(tempFlight.getOriginCode()).incTotFee(portFee);

                if(tempFlight.getFlightType().equals("International")){
                    System.out.println("There is a "+ tempFlight.getSurcharge() +" dollar surcharge for this flight.\n");
                }

                System.out.println("What type of ticket(s) do you want to buy?\nFirst Class   - 1\nBusiness Class - 2\nMain Cabin    - 3\n");
                int tempClass = tChest4.nextInt();

                System.out.println("How many tickets would you like to purchase (8 max)?\n");
                int tempAmount = tChest4.nextInt();
                double total = 0;
                    //------------------------------------------------------------------------------------
                if(tempClass == 1){
                    if(tempFlight.getFirstClassSeats() < tempAmount){
                        System.out.println("--Sold Out--\n");
                        return 0;
                    }
                    double pricePer = tempFlight.getFirstClassPrice();
                    double savings;
                    double eDiscount = pricePer / 2;
                    double cDiscount = 0;
                    double tax;

                    if(operator.getUserType() == 1){
                        if(operator.getE().isMembership() == true){
                            eDiscount = eDiscount + (pricePer*.05);
                        }
                    }else{
                        if(operator.getC().isMembership() == true){
                            cDiscount = cDiscount + (pricePer*.05);
                        }
                    }

                    if(operator.getUserType() == 1){
                        if(operator.getE().calculateAge() >= 65){
                            eDiscount = eDiscount + (pricePer*.05);
                            tempFlight.setSeniorDiscount(pricePer*0.5);
                        }
                    }else{
                        if(operator.getC().calculateAge() >= 65){
                            cDiscount = cDiscount + (pricePer*.05);
                            tempFlight.setSeniorDiscount(pricePer*0.5);
                        }
                    }

                    if(operator.getUserType() == 1){
                        System.out.println("Price Per ticket: " + ((pricePer * tempAmount) - (eDiscount * tempAmount))+ "\n");
                        total = ((pricePer * tempAmount) - (eDiscount * tempAmount)) + (tempAmount * tempFlight.getSurcharge()) + (tempAmount * securityFee) + minerAirFee + portFee;
                    }else{
                        System.out.println("Price Per ticket: " + ((pricePer * tempAmount) - (cDiscount * tempAmount)) + "\n");
                        total = ((pricePer * tempAmount) - (cDiscount * tempAmount)) + (tempAmount * tempFlight.getSurcharge()) + (tempAmount * securityFee) + minerAirFee + portFee; 
                    }

                    tax = total * .0825;
                    double fTotal = total + tax;
                    
                    if(operator.getUserType() == 1){
                        if(operator.getE().getMoneyAvailable() >= fTotal){
                            tChest4.nextLine();
                            System.out.println("Make purchase?");
                            String purchase = tChest4.nextLine().toLowerCase();
    
                            if(purchase.equals("yes")){
                                tempFlight.deFirstClass(tempAmount);
                                operator.getE().deBalance(fTotal);                                      //First class choice Block
    
    
                                confirmation.increment();
                                Booking addedBook = new Booking(tempAmount, tempFlight, operator.getE(), 
                                tempFlight.getFirstClassPrice(), fTotal, total, tempClass, confirmation.getConfirmation());
    
                                operator.getE().incPurchased(tempAmount);
                                operator.getE().incMemberSaved(eDiscount);
                                tempFlight.incFcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee*tempAmount);
                                tempFlight.incTotDiscounts(eDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                operator.getE().addBooking(confirmation.getConfirmation() ,addedBook);
                                log.makeBook(addedBook);
                            }else{
                                return -1;
                            }
                        }else{
                            System.out.println("--Insufficient Balance--\n");
                            return -1;
                        }

                    }else{
                        if(operator.getC().getMoneyAvailable() >= fTotal){
                            tChest4.nextLine();
                            System.out.println("Make purchase?");
                            String purchase = tChest4.nextLine().toLowerCase();
    
                            if(purchase.equals("yes")){
                                tempFlight.deFirstClass(tempAmount);
                                operator.getC().deBalance(fTotal);                                      //First class choice Block
    
    
                                confirmation.increment();
                                Booking addedBook = new Booking(tempAmount, tempFlight, operator.getC(), 
                                tempFlight.getFirstClassPrice(), fTotal, total, tempClass, confirmation.getConfirmation());
    
                                operator.getC().incPurchased(tempAmount);
                                operator.getC().incMemberSaved(cDiscount);
                                tempFlight.incFcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee*tempAmount);
                                tempFlight.incTotDiscounts(cDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                operator.getC().addBooking(confirmation.getConfirmation() ,addedBook);
                                log.makeBook(addedBook);
                            }else{
                                return -1;
                            }
                        }else{
                            System.out.println("--Insufficient Balance--\n");
                            return -1;
                        }

                    }

                //-------------------------------------------------------------------------------------

                }else if(tempClass == 2){
                    if(tempFlight.getBusinessClassSeats() < tempAmount){
                        System.out.println("--Sold Out--\n");
                        return 0;
                    }
                    
                    double pricePer = tempFlight.getBusinessClassPrice();
                    double savings;
                    double eDiscount = pricePer * .75;
                    double cDiscount = 0;
                    double tax;

                    if(operator.getUserType() == 1){
                        if(operator.getE().isMembership() == true){
                            eDiscount = eDiscount + (pricePer*.05);
                        }
                    }else{
                        if(operator.getC().isMembership() == true){
                            cDiscount = cDiscount + (pricePer*.05);
                        }
                    }

                    if(operator.getUserType() == 1){
                        if(operator.getE().calculateAge() >= 65){
                            eDiscount = eDiscount + (pricePer*.05);
                            tempFlight.setSeniorDiscount(pricePer*0.5);
                        }
                    }else{
                        if(operator.getC().calculateAge() >= 65){
                            cDiscount = cDiscount + (pricePer*.05);
                            tempFlight.setSeniorDiscount(pricePer*0.5); 
                        }
                    }

                    if(operator.getUserType() == 1){
                        System.out.println("Price Per ticket: " + ((pricePer * tempAmount) - (eDiscount * tempAmount))+ "\n");
                        total = ((pricePer * tempAmount) - (eDiscount * tempAmount)) + (tempAmount * tempFlight.getSurcharge()) + (tempAmount * securityFee) + minerAirFee + portFee;
                    }else{
                        System.out.println("Price Per ticket: " + ((pricePer * tempAmount) - (cDiscount * tempAmount)) + "\n");
                        total = ((pricePer * tempAmount) - (cDiscount * tempAmount)) + (tempAmount * tempFlight.getSurcharge()) + (tempAmount * securityFee) + minerAirFee + portFee; 
                    }

                    tax = total * .0825;
                    double fTotal = total + tax;
                    
                    if(operator.getUserType() == 1){
                        if(operator.getE().getMoneyAvailable() >= fTotal){
                            tChest4.nextLine();
                            System.out.println("Make purchase?");
                            String purchase = tChest4.nextLine().toLowerCase();
    
                            if(purchase.equals("yes")){
                                tempFlight.deBuisness(tempAmount);
                                operator.getE().deBalance(fTotal);                                      //Buisness class choice Block
    
    
                                confirmation.increment();
                                Booking addedBook = new Booking(tempAmount, tempFlight, operator.getE(), 
                                tempFlight.getBusinessClassPrice(), fTotal, total, tempClass, confirmation.getConfirmation());
    
                                operator.getE().incPurchased(tempAmount);
                                operator.getE().incMemberSaved(eDiscount);
                                tempFlight.incBcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee*tempAmount);
                                tempFlight.incTotDiscounts(eDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                operator.getE().addBooking(confirmation.getConfirmation() ,addedBook);
                                log.makeBook(addedBook);
                            }else{
                                return -1;
                            }
                        }else{
                            System.out.println("--Insufficient Balance--\n");
                            return -1;
                        }

                    }else{
                        if(operator.getC().getMoneyAvailable() >= fTotal){
                            tChest4.nextLine();
                            System.out.println("Make purchase?");
                            String purchase = tChest4.nextLine().toLowerCase();
    
                            if(purchase.equals("yes")){
                                tempFlight.deBuisness(tempAmount);
                                operator.getC().deBalance(fTotal);                                      //Buisness class choice Block
    
    
                                confirmation.increment();
                                Booking addedBook = new Booking(tempAmount, tempFlight, operator.getC(), 
                                tempFlight.getBusinessClassPrice(), fTotal, total, tempClass, confirmation.getConfirmation());
    
                                operator.getC().incPurchased(tempAmount);
                                operator.getC().incMemberSaved(cDiscount);
                                tempFlight.incBcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee*tempAmount);
                                tempFlight.incTotDiscounts(cDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                operator.getC().addBooking(confirmation.getConfirmation() ,addedBook);
                                log.makeBook(addedBook);
                            }else{
                                return -1;
                            }
                        }else{
                            System.out.println("--Insufficient Balance--\n");
                            return -1;
                        }

                    }

                }else if(tempClass == 3){
                    if(tempFlight.getMainCabinSeats() < tempAmount){
                        System.out.println("--Sold Out--\n");
                        return 0;
                    }
                    
                    double pricePer = tempFlight.getMainCabinPrice();
                    double savings;
                    double eDiscount = pricePer * .75;
                    double cDiscount = 0;
                    double tax;

                    if(operator.getUserType() == 1){
                        if(operator.getE().isMembership() == true){
                            eDiscount = eDiscount + (pricePer*.05);
                        }
                    }else{
                        if(operator.getC().isMembership() == true){
                            cDiscount = cDiscount + (pricePer*.05);
                        }
                    }

                    if(operator.getUserType() == 1){
                        if(operator.getE().calculateAge() >= 65){
                            eDiscount = eDiscount + (pricePer*.05);
                            tempFlight.setSeniorDiscount(pricePer*0.5);
                            
                        }
                    }else{
                        if(operator.getC().calculateAge() >= 65){
                            cDiscount = cDiscount + (pricePer*.05);
                            tempFlight.setSeniorDiscount(pricePer*0.5);
                        }
                    }

                    if(operator.getUserType() == 1){
                        System.out.println("Price Per ticket: " + ((pricePer * tempAmount) - (eDiscount * tempAmount))+ "\n");
                        total = ((pricePer * tempAmount) - (eDiscount * tempAmount)) + (tempAmount * tempFlight.getSurcharge()) + (tempAmount * securityFee) + minerAirFee + portFee;
                    }else{
                        System.out.println("Price Per ticket: " + ((pricePer * tempAmount) - (cDiscount * tempAmount)) + "\n");
                        total = ((pricePer * tempAmount) - (cDiscount * tempAmount)) + (tempAmount * tempFlight.getSurcharge()) + (tempAmount * securityFee) + minerAirFee + portFee; 
                    }

                    tax = total * .0825;
                    double fTotal = total + tax;
                    
                    if(operator.getUserType() == 1){
                        if(operator.getE().getMoneyAvailable() >= fTotal){
                            tChest4.nextLine();
                            System.out.println("Make purchase?");
                            String purchase = tChest4.nextLine().toLowerCase();
    
                            if(purchase.equals("yes")){
                                tempFlight.deMainC(tempAmount);
                                operator.getE().deBalance(fTotal);                                      //Main Cabin choice Block
    
    
                                confirmation.increment();
                                Booking addedBook = new Booking(tempAmount, tempFlight, operator.getE(), 
                                tempFlight.getMainCabinPrice(), fTotal, total, tempClass, confirmation.getConfirmation());
    
                                operator.getE().incPurchased(tempAmount);
                                operator.getE().incMemberSaved(eDiscount);
                                tempFlight.incMcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee*tempAmount);
                                tempFlight.incTotDiscounts(eDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                operator.getE().addBooking(confirmation.getConfirmation() ,addedBook);
                                log.makeBook(addedBook);
                            }else{
                                return -1;
                            }
                        }else{
                            System.out.println("--Insufficient Balance--\n");
                            return -1;
                        }

                    }else{
                        if(operator.getC().getMoneyAvailable() >= fTotal){
                            tChest4.nextLine();
                            System.out.println("Make purchase?");
                            String purchase = tChest4.nextLine().toLowerCase();
    
                            if(purchase.equals("yes")){
                                tempFlight.deMainC(tempAmount);
                                operator.getC().deBalance(fTotal);                                      //Main cabin choice Block
    
    
                                confirmation.increment();
                                Booking addedBook = new Booking(tempAmount, tempFlight, operator.getC(), 
                                tempFlight.getMainCabinPrice(), fTotal, total, tempClass, confirmation.getConfirmation());
    
                                operator.getC().incPurchased(tempAmount);
                                operator.getC().incMemberSaved(cDiscount);
                                tempFlight.incMcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee*tempAmount);
                                tempFlight.incTotDiscounts(cDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                operator.getC().addBooking(confirmation.getConfirmation() ,addedBook);
                                log.makeBook(addedBook);
                            }else{
                                return -1;
                            }
                        }else{
                            System.out.println("--Insufficient Balance--\n");
                            return -1;
                        }

                    }

                //-------------------------------------------------------------------------------------------

            }else{
                System.out.println("--Invalid Class--\n");
                return -1;
            }

        }else{
            return -1;
        }
        return 0;
        
    }catch(Exception e){}
        System.out.println("--Invalid Booking Option--\n");
        return -1;
    }

    // ------------------------------------------------------------------------------------------Customer
    // cancel
    // booking-------------------------------------------------------------------------------

    /**
     * Function for customers and employees to cancel bookings
     * 
     * @version 1.0
     * @param FlightInfo
     */

    public static int cancelBook(HashMap<Integer, Flight> FlightInfo) {
        try {
            Scanner tChest5 = new Scanner(System.in);
            AirLog log = AirLog.getInstance();
            loggedCustomer currCustomer = loggedCustomer.getInstance();
            Confirmation confirmation = Confirmation.getInstance();

            System.out.println("Would you like to see your bookings first?\n");
            String booksDecision = tChest5.nextLine().toLowerCase();

            if (booksDecision.equals("yes")) {
                operator.getC().seeBooks();
            } else if (booksDecision.equals("no")) {

            } else {
                return -1;
            }

            System.out.println("Type in the Confirmation No. of the Booking you would like to cancel: \n");
            int tempCon = tChest5.nextInt();
            if (operator.getUserType() == 1) {
                if (operator.getE().getBooks().containsKey(tempCon)) {

                    if (operator.getE().getBooks().get(tempCon).getTicklvl() == 1) {
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .incFirstClass(operator.getE().getBooks().get(tempCon).getNumTickets());
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deFcProfit(operator.getE().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deTotProfit(operator.getE().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deTotSecurity(operator.getE().getBooks().get(tempCon).getNumTickets() * securityFee);
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deTotTax(operator.getE().getBooks().get(tempCon).getPreTax() * .0825);
                        operator.getE().incBalance(operator.getE().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getE().getBooks().get(tempCon).zeroOut();
                        System.out.println("Booking Canceled");
                        log.logBCancel(tempCon);

                    } else if (operator.getE().getBooks().get(tempCon).getTicklvl() == 2) {
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .incBuisness(operator.getE().getBooks().get(tempCon).getNumTickets());
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deBcProfit(operator.getE().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deTotProfit(operator.getE().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deTotSecurity(operator.getE().getBooks().get(tempCon).getNumTickets() * securityFee);
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deTotTax(operator.getE().getBooks().get(tempCon).getPreTax() * .0825);
                        operator.getE().incBalance(operator.getE().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getE().getBooks().get(tempCon).zeroOut();
                        System.out.println("Booking Canceled");
                        log.logBCancel(tempCon);

                    } else if (operator.getE().getBooks().get(tempCon).getTicklvl() == 3) {
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .incMainC(operator.getE().getBooks().get(tempCon).getNumTickets());
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deMcProfit(operator.getE().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deTotProfit(operator.getE().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deTotSecurity(operator.getE().getBooks().get(tempCon).getNumTickets() * securityFee);
                        operator.getE().getBooks().get(tempCon).getRelFlight()
                                .deTotTax(operator.getE().getBooks().get(tempCon).getPreTax() * .0825);
                        operator.getE().incBalance(operator.getE().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getE().getBooks().get(tempCon).zeroOut();
                        System.out.println("Booking Canceled");
                        log.logBCancel(tempCon);

                    }
                } else {
                    System.out.println("--Confirmation does not exist--\n");
                    return -1;
                }

            } else {
                if (operator.getC().getBooks().containsKey(tempCon)) {

                    if (operator.getC().getBooks().get(tempCon).getTicklvl() == 1) {
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .incFirstClass(operator.getC().getBooks().get(tempCon).getNumTickets());
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deFcProfit(operator.getC().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deTotProfit(operator.getC().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deTotSecurity(operator.getC().getBooks().get(tempCon).getNumTickets() * securityFee);
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deTotTax(operator.getC().getBooks().get(tempCon).getPreTax() * .0825);
                        operator.getC().incBalance(operator.getC().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getC().getBooks().get(tempCon).zeroOut();
                        System.out.println("Booking Canceled");
                        log.logBCancel(tempCon);

                    } else if (operator.getC().getBooks().get(tempCon).getTicklvl() == 2) {
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .incBuisness(operator.getC().getBooks().get(tempCon).getNumTickets());
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deBcProfit(operator.getC().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deTotProfit(operator.getC().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deTotSecurity(operator.getC().getBooks().get(tempCon).getNumTickets() * securityFee);
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deTotTax(operator.getC().getBooks().get(tempCon).getPreTax() * .0825);
                        operator.getC().incBalance(operator.getC().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getC().getBooks().get(tempCon).zeroOut();
                        System.out.println("Booking Canceled");
                        log.logBCancel(tempCon);

                    } else if (operator.getC().getBooks().get(tempCon).getTicklvl() == 3) {
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .incMainC(operator.getC().getBooks().get(tempCon).getNumTickets());
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deMcProfit(operator.getC().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deTotProfit(operator.getC().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deTotSecurity(operator.getC().getBooks().get(tempCon).getNumTickets() * securityFee);
                        operator.getC().getBooks().get(tempCon).getRelFlight()
                                .deTotTax(operator.getC().getBooks().get(tempCon).getPreTax() * .0825);
                        operator.getC().incBalance(operator.getC().getBooks().get(tempCon).getTotPrice() - minerAirFee);
                        operator.getC().getBooks().get(tempCon).zeroOut();
                        System.out.println("Booking Canceled");
                        log.logBCancel(tempCon);

                    }
                } else {
                    System.out.println("--Confirmation does not exist--\n");
                    return -1;
                }
            }

            return 0;

        } catch (Exception e) {
        }
        System.out.println("--Invalid Canceling Option--\n");
        return -1;
    }

    // -------------------------------------------------------------------------------------------------------Employee
    // Actions
    // Display---------------------------------------------------------------------------------------------

    /**
     * Function that displays and takes input for employee actions
     * 
     * @version 1.0
     */

    public static int eActions() {
        try {
            Scanner tChest6 = new Scanner(System.in);

            System.out.println("How would you like to procced?\n" +
                    "Book Flight                   : Type 1\n" +
                    "Inquire Flight Change         : Type 2\n" +
                    "Inquire Flight Info           : Type 3\n" +
                    "Cancel Flight                 : Type 4\n" +
                    "See Airport                   : Type 5\n" +
                    "Automatic purchase            : Type 6\n" +
                    "Generate Customer Summary     : Type 7\n" +
                    "Generate Employee Summary     : Type 8\n" +
                    "Log Out                       : Type 0\n");

            int choice = tChest6.nextInt();
            if (choice < 0 || choice > 8) {
                System.out.println("--Invalid Option.--\n");
                return -1;
            }
            return choice;

        } catch (Exception e) {
        }
        return -1;
    }

    // -------------------------------------------------------------------------------------------------------Ticket
    // Summary------------------------------------------------------------------------------------------

    /**
     * Function to write a summary of all the tickets for a particular customer
     * 
     * @param customers
     * @param employees
     */

    public static int ticketSummaryCustomer(HashMap<String, Customer> customers) {
        try {
            Scanner tChest12 = new Scanner(System.in);

            System.out.println("Please type the customers first name: ");
            String fn = tChest12.nextLine();

            System.out.println("Please type the customers last name: ");
            String ln = tChest12.nextLine();

            String tempCKey = fn + " " + ln;
            String fileName;

            if (customers.containsKey(tempCKey)) {
                fileName = tempCKey + ".txt";
            } else {
                System.out.println("Invalid Customer");
                return 0;
            }

            FileWriter author = new FileWriter(fileName);

            HashMap<Integer, Booking> tempBooks = customers.get(tempCKey).getBooks();

            if (tempBooks.size() == 0) {
                System.out.println("Currently no flights Booked");
                return 0;
            } else {
                Set bookKeys = tempBooks.keySet();
                Iterator it = bookKeys.iterator();

                while (it.hasNext()) {
                    int k = (int) it.next();
                    Flight tempFlight = tempBooks.get(k).getRelFlight();
                    author.write(tempBooks.get(k).getConfirmation() + "\n" +
                            tempFlight.getOriginCode() + "\n" +
                            tempFlight.getOriginAirport() + "\n" +
                            tempFlight.getDestinationCode() + "\n" +
                            tempFlight.getDestinationAirport() + "\n" +
                            tempFlight.getDepartureDate() + "\n" +
                            tempFlight.getDepartureTime() + "\n" +
                            tempFlight.getArrivalDate() + "\n" +
                            tempFlight.getArrivalTime() + "\n" +
                            tempBooks.get(k).getTicklvl() + "\n" +
                            tempBooks.get(k).getNumTickets() + "\n" +
                            tempBooks.get(k).getTotPrice() + "\n");
                }
            }
            author.close();
            return 0;

        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * Function to write a summary of all the tickets for a particular employee
     * 
     * @param employees
     * @return
     */
    public static int ticketSummaryEmployee(HashMap<String, Employee> employees) {
        try {
            Scanner tChest12 = new Scanner(System.in);

            System.out.println("Please type the employee first name: ");
            String fn = tChest12.nextLine();

            System.out.println("Please type the employee last name: ");
            String ln = tChest12.nextLine();

            String tempCKey = fn + " " + ln;
            String fileName;

            if (employees.containsKey(tempCKey)) {
                fileName = tempCKey + ".txt";
            } else {
                System.out.println("Invalid Employee");
                return 0;
            }

            FileWriter author = new FileWriter(fileName);

            HashMap<Integer, Booking> tempBooks = employees.get(tempCKey).getBooks();

            if (tempBooks.size() == 0) {
                System.out.println("Currently no flights Booked");
                return 0;
            } else {
                Set bookKeys = tempBooks.keySet();
                Iterator it = bookKeys.iterator();

                while (it.hasNext()) {
                    int k = (int) it.next();
                    Flight tempFlight = tempBooks.get(k).getRelFlight();
                    author.write(tempBooks.get(k).getConfirmation() + "\n" +
                            tempFlight.getOriginCode() + "\n" +
                            tempFlight.getOriginAirport() + "\n" +
                            tempFlight.getDestinationCode() + "\n" +
                            tempFlight.getDestinationAirport() + "\n" +
                            tempFlight.getDepartureDate() + "\n" +
                            tempFlight.getDepartureTime() + "\n" +
                            tempFlight.getArrivalDate() + "\n" +
                            tempFlight.getArrivalTime() + "\n" +
                            tempBooks.get(k).getTicklvl() + "\n" +
                            tempBooks.get(k).getNumTickets() + "\n" +
                            tempBooks.get(k).getTotPrice() + "\n");
                }
            }
            author.close();
            return 0;

        } catch (Exception e) {
        }
        return -1;
    }

    // --------------------------------------------------------------------------------------------------------------Auto
    // Purchaser--------------------------------------------------------------------------------------------------------------

    /**
     * Function that automates puchases based on an input file
     * 
     * @param customers
     * @param ports
     * @param employees
     */

    public static int automation(HashMap<String, Customer> customers, HashMap<Integer, Flight> FlightInfo,
            HashMap<String, Airport> ports, HashMap<String, Employee> employees) {
        try {
            Scanner tChest11 = new Scanner(System.in);
            AirLog log = AirLog.getInstance();
            Confirmation confirmation = Confirmation.getInstance();
            loggedUser user = loggedUser.getInstance();

            System.out.println("Please type in the file name: \n");
            String fileName = tChest11.nextLine();

            Scanner scribe = new Scanner(new File(fileName));

            int header = 0;
            Employee holdEmp = user.getE();

            scribe.useDelimiter(",");
            while (scribe.hasNextLine()) {
                if (header > 0) {
                    String[] TempArr = scribe.nextLine().split(",");
                    String tempCKey = TempArr[0] + " " + TempArr[1];
                    String tempFId = TempArr[3];
                    String tempOrigin = TempArr[4];
                    String tempDest = TempArr[5];
                    String tempTickType = TempArr[6];
                    int numTicks = Integer.parseInt(TempArr[7]);
                    Employee tempEmp = null;
                    Customer tempCust = null;

                    int roleFlag = 0;

                    if (customers.containsKey(tempCKey)) {
                        tempCust = customers.get(tempCKey);
                        user.setC(tempCust);
                        roleFlag++;
                    } else {
                        tempEmp = employees.get(tempCKey);
                        user.setE(tempEmp);
                    }

                    int tempClass = 0;

                    if (tempTickType.equals("First Class")) {
                        tempClass = 1;

                    } else if (tempTickType.equals("Business Class")) {
                        tempClass = 2;

                    } else if (tempTickType.equals("Main Cabin")) {
                        tempClass = 3;

                    }

                    Airport tempPort = ports.get(tempOrigin);

                    Flight tempFlight = null;

                    HashMap<Integer, Flight> tempOutFlights = tempPort.getOutFlights();

                    if (tempOutFlights.size() == 0) {
                        System.out.println("Currently no flights out");
                    } else {
                        Set outKeys = tempOutFlights.keySet();
                        Iterator it = outKeys.iterator();

                        while (it.hasNext()) {
                            int k = (int) it.next();
                            if (tempOutFlights.get(k).getDestinationCode().equals(tempDest)
                                    && tempOutFlights.get(k).getID().equals(tempFId)) {
                                tempFlight = tempOutFlights.get(k);
                            }
                        }
                    }

                    if (tempFlight == null) {
                        continue;
                    }

                    double portFee = tempPort.getOriginFee();
                    tempPort.incTotFee(portFee);
                    double total = 0;

                    if (tempClass == 1) {
                        if (tempFlight.getFirstClassSeats() < numTicks) {
                            continue;
                        }

                        double pricePer = tempFlight.getFirstClassPrice();
                        double savings;
                        double eDiscount = pricePer / 2;
                        double cDiscount = 0;
                        double tax;

                        if (roleFlag == 0) {
                            if (tempEmp.isMembership() == true) {
                                eDiscount = eDiscount + (pricePer * .05);
                            }
                        } else {
                            if (tempCust.isMembership() == true) {
                                cDiscount = cDiscount + (pricePer * .05);
                            }
                        }

                        if (roleFlag == 0) {
                            total = ((pricePer * numTicks) - (eDiscount * numTicks))
                                    + (numTicks * tempFlight.getSurcharge()) + (numTicks * securityFee) + minerAirFee
                                    + portFee;
                        } else {
                            total = ((pricePer * numTicks) - (cDiscount * numTicks))
                                    + (numTicks * tempFlight.getSurcharge()) + (numTicks * securityFee) + minerAirFee
                                    + portFee;
                        }

                        tax = total * .0825;
                        double fTotal = total + tax;

                        if (roleFlag == 0) {
                            if (tempEmp.getMoneyAvailable() >= fTotal) {
                                tempFlight.deFirstClass(numTicks);
                                tempEmp.deBalance(fTotal);

                                confirmation.increment();
                                Booking addedBook = new Booking(numTicks, tempFlight, tempEmp,
                                        tempFlight.getFirstClassPrice(), fTotal, total, tempClass,
                                        confirmation.getConfirmation());

                                tempEmp.incPurchased(numTicks);
                                tempEmp.incMemberSaved(eDiscount);
                                tempFlight.incFcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee * numTicks);
                                tempFlight.incTotDiscounts(eDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                tempEmp.addBooking(confirmation.getConfirmation(), addedBook);
                                log.makeBook(addedBook);
                            } else {
                                continue;
                            }
                        } else {
                            if (tempCust.getMoneyAvailable() >= fTotal) {
                                tempFlight.deFirstClass(numTicks);
                                tempCust.deBalance(fTotal);

                                confirmation.increment();
                                Booking addedBook = new Booking(numTicks, tempFlight, tempCust,
                                        tempFlight.getFirstClassPrice(), fTotal, total, tempClass,
                                        confirmation.getConfirmation());

                                tempCust.incPurchased(numTicks);
                                tempCust.incMemberSaved(cDiscount);
                                tempFlight.incFcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee * numTicks);
                                tempFlight.incTotDiscounts(cDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                tempCust.addBooking(confirmation.getConfirmation(), addedBook);
                                log.makeBook(addedBook);
                            } else {
                                continue;
                            }
                        }
                    } else if (tempClass == 2) {
                        if (tempFlight.getBusinessClassSeats() < numTicks) {
                            continue;
                        }

                        double pricePer = tempFlight.getBusinessClassPrice();
                        double savings;
                        double eDiscount = pricePer / 2;
                        double cDiscount = 0;
                        double tax;

                        if (roleFlag == 0) {
                            if (tempEmp.isMembership() == true) {
                                eDiscount = eDiscount + (pricePer * .05);
                            }
                        } else {
                            if (tempCust.isMembership() == true) {
                                cDiscount = cDiscount + (pricePer * .05);
                            }
                        }

                        if (roleFlag == 0) {
                            total = ((pricePer * numTicks) - (eDiscount * numTicks))
                                    + (numTicks * tempFlight.getSurcharge()) + (numTicks * securityFee) + minerAirFee
                                    + portFee;
                        } else {
                            total = ((pricePer * numTicks) - (cDiscount * numTicks))
                                    + (numTicks * tempFlight.getSurcharge()) + (numTicks * securityFee) + minerAirFee
                                    + portFee;
                        }

                        tax = total * .0825;
                        double fTotal = total + tax;

                        if (roleFlag == 0) {
                            if (tempEmp.getMoneyAvailable() >= fTotal) {
                                tempFlight.deBuisness(numTicks);
                                tempEmp.deBalance(fTotal);

                                confirmation.increment();
                                Booking addedBook = new Booking(numTicks, tempFlight, tempEmp,
                                        tempFlight.getBusinessClassPrice(), fTotal, total, tempClass,
                                        confirmation.getConfirmation());

                                tempEmp.incPurchased(numTicks);
                                tempEmp.incMemberSaved(eDiscount);
                                tempFlight.incBcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee * numTicks);
                                tempFlight.incTotDiscounts(eDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                tempEmp.addBooking(confirmation.getConfirmation(), addedBook);
                                log.makeBook(addedBook);
                            } else {
                                continue;
                            }
                        } else {
                            if (tempCust.getMoneyAvailable() >= fTotal) {
                                tempFlight.deBuisness(numTicks);
                                tempCust.deBalance(fTotal);

                                confirmation.increment();
                                Booking addedBook = new Booking(numTicks, tempFlight, tempCust,
                                        tempFlight.getBusinessClassPrice(), fTotal, total, tempClass,
                                        confirmation.getConfirmation());

                                tempCust.incPurchased(numTicks);
                                tempCust.incMemberSaved(cDiscount);
                                tempFlight.incBcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee * numTicks);
                                tempFlight.incTotDiscounts(cDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                tempCust.addBooking(confirmation.getConfirmation(), addedBook);
                                log.makeBook(addedBook);
                            } else {
                                continue;
                            }
                        }
                    } else if (tempClass == 3) {
                        if (tempFlight.getMainCabinSeats() < numTicks) {
                            continue;
                        }

                        double pricePer = tempFlight.getMainCabinPrice();
                        double savings;
                        double eDiscount = pricePer / 2;
                        double cDiscount = 0;
                        double tax;

                        if (roleFlag == 0) {
                            if (tempEmp.isMembership() == true) {
                                eDiscount = eDiscount + (pricePer * .05);
                            }
                        } else {
                            if (tempCust.isMembership() == true) {
                                cDiscount = cDiscount + (pricePer * .05);
                            }
                        }

                        if (roleFlag == 0) {
                            total = ((pricePer * numTicks) - (eDiscount * numTicks))
                                    + (numTicks * tempFlight.getSurcharge()) + (numTicks * securityFee) + minerAirFee
                                    + portFee;
                        } else {
                            total = ((pricePer * numTicks) - (cDiscount * numTicks))
                                    + (numTicks * tempFlight.getSurcharge()) + (numTicks * securityFee) + minerAirFee
                                    + portFee;
                        }

                        tax = total * .0825;
                        double fTotal = total + tax;

                        if (roleFlag == 0) {
                            if (tempEmp.getMoneyAvailable() >= fTotal) {
                                tempFlight.deMainC(numTicks);
                                tempEmp.deBalance(fTotal);

                                confirmation.increment();
                                Booking addedBook = new Booking(numTicks, tempFlight, tempEmp,
                                        tempFlight.getMainCabinPrice(), fTotal, total, tempClass,
                                        confirmation.getConfirmation());

                                tempEmp.incPurchased(numTicks);
                                tempEmp.incMemberSaved(eDiscount);
                                tempFlight.incMcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee * numTicks);
                                tempFlight.incTotDiscounts(eDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                tempEmp.addBooking(confirmation.getConfirmation(), addedBook);
                                log.makeBook(addedBook);
                            } else {
                                continue;
                            }
                        } else {
                            if (tempCust.getMoneyAvailable() >= fTotal) {
                                tempFlight.deMainC(numTicks);
                                tempCust.deBalance(fTotal);

                                confirmation.increment();
                                Booking addedBook = new Booking(numTicks, tempFlight, tempCust,
                                        tempFlight.getMainCabinPrice(), fTotal, total, tempClass,
                                        confirmation.getConfirmation());

                                tempCust.incPurchased(numTicks);
                                tempCust.incMemberSaved(cDiscount);
                                tempFlight.incMcProfit(total);
                                tempFlight.incTotProfit(total);
                                tempFlight.incTotMaFee(minerAirFee);
                                tempFlight.incTotSecurity(securityFee * numTicks);
                                tempFlight.incTotDiscounts(cDiscount);
                                tempFlight.incTotTax(tax);
                                tempFlight.addBooking(addedBook);
                                tempCust.addBooking(confirmation.getConfirmation(), addedBook);
                                log.makeBook(addedBook);
                            } else {
                                continue;
                            }
                        }

                    }

                } else {
                    scribe.nextLine();
                    header++;
                }
            }

            user.setE(holdEmp);

        } catch (Exception e) {
        }
        System.out.println("Invalid File");
        return -1;
    }

    // --------------------------------------------------------------------------------------------------Employee
    // get Airport
    // info---------------------------------------------------------------------------------------------------

    /**
     * Lets employee inquire and see an airports information
     * 
     * @param ports
     */

    public static int seePort(HashMap<String, Airport> ports) {
        try {
            Scanner tChest10 = new Scanner(System.in);

            System.out.println("What Airports information would you like to see (input code)?: \n");
            String tempCode = tChest10.nextLine();

            if (ports.containsKey(tempCode)) {
                ports.get(tempCode).printPort();
                return 0;
            } else {
                System.out.println("Not a valid Airport");
                return -1;
            }

        } catch (Exception e) {
        }
        return -1;
    }

    // ------------------------------------------------------------------------------------------------------Employee
    // get flight
    // info-------------------------------------------------------------------------------------------------

    /**
     * Function for employee to inquire info about a flight
     * 
     * @version 1.0
     * @param FlightInfo
     */

    public static int employeeInquireInfo(HashMap<Integer, Flight> FlightInfo) {
        try {
            Scanner tChest7 = new Scanner(System.in);
            Flight fIn = new Flight();
            System.out.println("Type in th Flight number of the flight you are inquiring about: ");
            int tempFn = tChest7.nextInt();

            if (FlightInfo.containsKey(tempFn)) {
                fIn = FlightInfo.get(tempFn);
            } else {
                System.out.println("--Flight does not exist--");
                return -1;
            }

            System.out.println(
                    "Choose A field you would like to see: \n" +
                            " 1 - Origin Airport      \n" +
                            " 2 - Origin Code         \n" +
                            " 3 - Destination Airport \n" +
                            " 4 - Destination Code    \n" +
                            " 5 - Departure Date      \n" +
                            " 6 - Destination Time    \n" +
                            " 7 - Arrival Date   \n" +
                            " 8 - Arrival Time\n" +
                            " 9 - Duration\n" +
                            "10 - Distance\n" +
                            "11 - Time zone difference\n" +
                            "12 - First Class Price\n" +
                            "13 - Buisness Class Price\n" +
                            "14 - Main Cabin Price\n" +
                            "15 - First CLass Seats\n" +
                            "16 - Buisness Class Seats\n" +
                            "17 - Main Cabin Seats\n" +
                            "18 - Total seats\n" +
                            "19 - First Class Profit\n" +
                            "20 - Buisness Class Profit\n" +
                            "21 - Main Cabin Profit\n" +
                            "22 - Total Profit\n" +
                            "23 - Expexted Profit\n" +
                            "24 - Total Miner Airline fee charged\n" +
                            "25 - Total security fee charged\n" +
                            "26 - Total taxed\n" +
                            "0 - Back one Menu       \n");

            int field = tChest7.nextInt();

            if (field == 1) {
                System.out.println("This is the current Origin Airport:       " + fIn.getOriginAirport());
            } else if (field == 2) {
                System.out.println("This is the current Origin Code:          " + fIn.getOriginCode());
            } else if (field == 3) {
                System.out.println("This is the current Destination Airport:  " + fIn.getDestinationAirport());
            } else if (field == 4) {
                System.out.println("This is the current Destination Code:     " + fIn.getDestinationCode());
            } else if (field == 5) {
                System.out.println("This is the current Departure Date:       " + fIn.getDepartureDate());
            } else if (field == 6) {
                System.out.println("This is the current Departure time:       " + fIn.getDepartureTime());
            } else if (field == 7) {
                System.out.println("This is the current Arrival Date:  " + fIn.getArrivalDate());
            } else if (field == 8) {
                System.out.println("This is the current Arrival time:  " + fIn.getArrivalTime());
            } else if (field == 9) {
                System.out.println("This is the current Flight Duration:  " + fIn.getDuration());
            } else if (field == 10) {
                System.out.println("This is the current Flight Distance:  " + fIn.getDistance());
            } else if (field == 11) {
                System.out.println("This is the current Time Zone Difference:  " + fIn.getTimeZoneDifference());
            } else if (field == 12) {
                System.out.println("This is the current First Class Price:    " + fIn.getFirstClassPrice());
            } else if (field == 13) {
                System.out.println("This is the current Buisness class price: " + fIn.getBusinessClassPrice());
            } else if (field == 14) {
                System.out.println("This is the current Main cabin price: " + fIn.getMainCabinPrice());
            } else if (field == 15) {
                System.out.println("This is the current Amount of First class seats: " + fIn.getFirstClassSeats());
            } else if (field == 16) {
                System.out
                        .println("This is the current Amount of Buisness class seats: " + fIn.getBusinessClassSeats());
            } else if (field == 17) {
                System.out.println("This is the current Amount of main cabin seats: " + fIn.getMainCabinSeats());
            } else if (field == 18) {
                System.out.println("This is the current Amount of Total seats: " + fIn.getTotalSeats());
            } else if (field == 19) {
                System.out.println("This is the current First CLass Profit: " + fIn.getFcProfit());
            } else if (field == 20) {
                System.out.println("This is the current Buisness Class Profit: " + fIn.getBcProfit());
            } else if (field == 21) {
                System.out.println("This is the current Main Cabin Profit: " + fIn.getMcProfit());
            } else if (field == 22) {
                System.out.println("This is the current Total Profit: " + fIn.getTotProfit());
            } else if (field == 23) {
                System.out.println("This is the Expected Sold out Flight profit: " + fIn.getExpProfit());
            } else if (field == 24) {
                System.out.println("This is the curren Miner air fee profit: " + fIn.getTotMaFee());
            } else if (field == 25) {
                System.out.println("This is the current security charge total: " + fIn.getTotSecurity());
            } else if (field == 26) {
                System.out.println("This is the current tax total: " + fIn.getTotTax());
            } else if (field == 0) {
                return 0;
            }
            System.out.println(" ");
            return 0;
        } catch (Exception e) {
        }
        System.out.println("--Invalid lookup--\n");
        return -1;
    }

    // --------------------------------------------------------------------------------------------------------employee
    // change flight
    // info-------------------------------------------------------------------------------------------------

    /**
     * Function that alows emloyees to change flight fields
     * 
     * @version 1.0
     * @param FlightInfo
     */

    public static int employeeInquireChange(HashMap<Integer, Flight> FlightInfo) {
        try {
            Scanner tChest8 = new Scanner(System.in);
            AirLog log = AirLog.getInstance();
            Flight fIn = new Flight();
            System.out.println("Type in th Flight number of the flight you are inquiring about: ");
            int tempFn = tChest8.nextInt();

            if (FlightInfo.containsKey(tempFn)) {
                fIn = FlightInfo.get(tempFn);
            } else {
                System.out.println("--Flight does not exist--");
                return -1;
            }

            System.out.println(
                    "Choose A field you would like to change: \n" +
                            " 1 - Origin Airport      \n" +
                            " 2 - Origin Code         \n" +
                            " 3 - Destination Airport \n" +
                            " 4 - Destination Code    \n" +
                            " 5 - Departure Date      \n" +
                            " 6 - Destination Time    \n" +
                            " 7 - Arrival Date   \n" +
                            " 8 - Arrival Time\n" +
                            " 9 - Duration\n" +
                            "10 - Distance\n" +
                            "11 - Time zone difference\n" +
                            "12 - First Class Price\n" +
                            "13 - Buisness Class Price\n" +
                            "14 - Main Cabin Price\n" +
                            "15 - First CLass Seats\n" +
                            "16 - Buisness Class Seats\n" +
                            "17 - Main Cabin Seats\n" +
                            "18 - Total seats\n" +
                            " 0 - Back one Menu       \n");
            int field = Integer.parseInt(tChest8.nextLine());

            if (field == 1) {
                System.out.println("Type in your new Origin Airport:                  ");
                String change = tChest8.nextLine();
                fIn.setOrgAirport(change);
                log.logFchange(field, fIn, change);
            } else if (field == 2) {
                System.out.println("Type in your new Origin Code:                     ");
                String change = tChest8.nextLine();
                fIn.setOrgCode(change);
                log.logFchange(field, fIn, change);
            } else if (field == 3) {
                System.out.println("Type in your new Destination Airport:             ");
                String change = tChest8.nextLine();
                fIn.setDestAirport(change);
                log.logFchange(field, fIn, change);
            } else if (field == 4) {
                System.out.println("Type in your new Destination Code:                ");
                String change = tChest8.nextLine();
                fIn.setDesCode(change);
                log.logFchange(field, fIn, change);
            } else if (field == 5) {
                System.out.println("Type in your new Departure Date:                  ");
                String change = tChest8.nextLine();
                fIn.setDepartureDate(change);
                log.logFchange(field, fIn, change);
                changeDate(fIn);
            } else if (field == 6) {
                System.out.println("Type in your new Departure time:                  ");
                String change = tChest8.nextLine().toUpperCase();
                fIn.setDepartureTime(change);
                log.logFchange(field, fIn, change);
                changeTime(fIn);
            } else if (field == 7) {
                System.out.println("Type in your new Arrival Date:                    ");
                String change = tChest8.nextLine();
                fIn.setArrivalDate(change);
                log.logFchange(field, fIn, change);
            } else if (field == 8) {
                System.out.println("Type in your new Arrival time:                    ");
                String change = tChest8.nextLine();
                fIn.setArrivalTime(change);
                log.logFchange(field, fIn, change);
            } else if (field == 9) {
                System.out.println("Type in your new Flight Duration:                 ");
                String change = tChest8.nextLine();
                fIn.setDuration(change);
                log.logFchange(field, fIn, change);
            } else if (field == 10) {
                System.out.println("Type in your new Flight Distance:                ");
                String change = tChest8.nextLine();
                fIn.setDistance(change);
                log.logFchange(field, fIn, change);
            } else if (field == 11) {
                System.out.println("Type in your new Time Zone Difference:           ");
                String change = tChest8.nextLine();
                fIn.setTimeZoneDifference(change);
                log.logFchange(field, fIn, change);
            } else if (field == 12) {
                System.out.println("Type in your new First Class Price:              ");
                int change = Integer.parseInt(tChest8.nextLine());
                fIn.setFirstClassPrice(change);
                log.logFchange(field, fIn, Integer.toString(change));
            } else if (field == 13) {
                System.out.println("Type in your new Buisness class price:           ");
                int change = Integer.parseInt(tChest8.nextLine());
                fIn.setBusinessClassPrice(field);
                log.logFchange(field, fIn, Integer.toString(change));
            } else if (field == 14) {
                System.out.println("Type in your new Main cabin price:               ");
                int change = Integer.parseInt(tChest8.nextLine());
                fIn.setMainCabinPrice(change);
                log.logFchange(field, fIn, Integer.toString(change));
            } else if (field == 15) {
                System.out.println("Type in your new Amount of First class seats:    ");
                int change = Integer.parseInt(tChest8.nextLine());
                fIn.setFirstClassSeats(change);
                log.logFchange(field, fIn, Integer.toString(change));
            } else if (field == 16) {
                System.out.println("Type in your new Amount of Buisness class seats: ");
                int change = Integer.parseInt(tChest8.nextLine());
                fIn.setBusinessClassSeats(change);
                log.logFchange(field, fIn, Integer.toString(change));
            } else if (field == 17) {
                System.out.println("Type in your new Amount of main cabin seats:     ");
                int change = Integer.parseInt(tChest8.nextLine());
                fIn.setMainCabinSeats(change);
                log.logFchange(field, fIn, Integer.toString(change));
            } else if (field == 18) {
                System.out.println("Type in your new Amount of Total seats:          ");
                int change = Integer.parseInt(tChest8.nextLine());
                fIn.setTotalSeats(change);
                log.logFchange(field, fIn, Integer.toString(change));
            } else if (field == 0) {
                return 0;
            }
            System.out.print(" \n");
            return 0;
        } catch (Exception e) {
        }
        System.out.println("--Invalid change option--");
        return -1;

    }

    // --------------------------------------------------------------------------------------------------------Employee
    // cancel
    // Flight-------------------------------------------------------------------------------------------------------------------

    /**
     * Function that allows employees to cancel a flight
     * 
     * @version 1.0
     * @param FlightInfo
     */

    public static int cancelFlight(HashMap<Integer, Flight> FlightInfo) {
        try {
            Scanner tChest9 = new Scanner(System.in);
            AirLog log = AirLog.getInstance();
            System.out.println("Type in the Flight Number you would like to cancel: \n");
            int tempFn = tChest9.nextInt();

            if (FlightInfo.containsKey(tempFn)) {
                for (int i = 0; i < FlightInfo.get(tempFn).getBooks().size(); i++) {

                    if (FlightInfo.get(tempFn).getBooks().get(i).getTicklvl() == 1) {
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .incFirstClass(FlightInfo.get(tempFn).getBooks().get(i).getNumTickets());
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deFcProfit(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deTotProfit(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());

                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight().deTotMaFee(minerAirFee);
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deTotSecurity(FlightInfo.get(tempFn).getBooks().get(i).getNumTickets() * securityFee);
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deTotTax(FlightInfo.get(tempFn).getBooks().get(i).getPreTax() * .0825);

                        if (FlightInfo.get(tempFn).getBooks().get(i).getRelCustomer() == null) {
                            FlightInfo.get(tempFn).getBooks().get(i).getRelEmployee()
                                    .incBalance(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());
                        } else {
                            FlightInfo.get(tempFn).getBooks().get(i).getRelCustomer()
                                    .incBalance(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());
                        }

                        FlightInfo.get(tempFn).getBooks().get(i).zeroOut();
                        System.out.println("Flight Canceled");
                        log.logFcancel(tempFn);

                    } else if (FlightInfo.get(tempFn).getBooks().get(i).getTicklvl() == 2) {
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .incBuisness(FlightInfo.get(tempFn).getBooks().get(i).getNumTickets());
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deBcProfit(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deTotProfit(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());

                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight().deTotMaFee(minerAirFee);
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deTotSecurity(FlightInfo.get(tempFn).getBooks().get(i).getNumTickets() * securityFee);
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deTotTax(FlightInfo.get(tempFn).getBooks().get(i).getPreTax() * .0825);

                        if (FlightInfo.get(tempFn).getBooks().get(i).getRelCustomer() == null) {
                            FlightInfo.get(tempFn).getBooks().get(i).getRelEmployee()
                                    .incBalance(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());
                        } else {
                            FlightInfo.get(tempFn).getBooks().get(i).getRelCustomer()
                                    .incBalance(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());
                        }

                        FlightInfo.get(tempFn).getBooks().get(i).zeroOut();
                        System.out.println("Flight Canceled");
                        log.logFcancel(tempFn);

                    } else if (FlightInfo.get(tempFn).getBooks().get(i).getTicklvl() == 3) {
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .incMainC(FlightInfo.get(tempFn).getBooks().get(i).getNumTickets());
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deMcProfit(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deTotProfit(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());

                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight().deTotMaFee(minerAirFee);
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deTotSecurity(FlightInfo.get(tempFn).getBooks().get(i).getNumTickets() * securityFee);
                        FlightInfo.get(tempFn).getBooks().get(i).getRelFlight()
                                .deTotTax(FlightInfo.get(tempFn).getBooks().get(i).getPreTax() * .0825);

                        if (FlightInfo.get(tempFn).getBooks().get(i).getRelCustomer() == null) {
                            FlightInfo.get(tempFn).getBooks().get(i).getRelEmployee()
                                    .incBalance(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());
                        } else {
                            FlightInfo.get(tempFn).getBooks().get(i).getRelCustomer()
                                    .incBalance(FlightInfo.get(tempFn).getBooks().get(i).getTotPrice());
                        }

                        FlightInfo.get(tempFn).getBooks().get(i).zeroOut();
                        System.out.println("Flight Canceled");
                        log.logFcancel(tempFn);

                    }
                }

                return 0;

            } else {
                System.out.println("--Flight does not exist--\n");
                return -1;
            }

        } catch (Exception e) {
        }
        return -1;
    }

    // ------------------------------------------------------------------------------------------------employee
    // change
    // time-------------------------------------------------------------------------------------------------------

    /**
     * change departure and arrivaldate
     * 
     * @param fIn
     * @throws ParseException
     */
    public static void changeTime(Flight fIn) throws ParseException { // methods for changing the arrival dat and
                                                                      // arrival time
        String date1 = fIn.getDepartureDate();
        String time1 = fIn.getDepartureTime();

        int iDuration = Integer.parseInt(fIn.getDuration());

        String format = "MM/dd/yy hh:mm a";

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Date dateObj1 = sdf.parse(date1 + " " + time1);

        String[] sArray01 = sdf.format(addMinutesToDate(iDuration, dateObj1)).split(" ");

        fIn.setArrivalTime(sArray01[1] + " " + sArray01[2]);

        fIn.setArrivalDate(sArray01[0]);
    }

    /**
     * change time
     * 
     * @param fIn
     * @throws ParseException
     */
    public static void changeDate(Flight fIn) throws ParseException {
        String date1 = fIn.getDepartureDate();

        fIn.setArrivalDate(date1);
    }

    /**
     * adds minutes to the date, returns new date
     * 
     * @param iMinutes
     * @param dDate
     * @return
     */
    public static Date addMinutesToDate(int iMinutes, Date dDate) {
        long lCurTimeInMs = dDate.getTime();
        Date dNewDate = new Date(lCurTimeInMs + (iMinutes * 60000));
        return dNewDate;
    }

}
