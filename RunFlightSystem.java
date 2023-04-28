import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Main class where we run the system from
 * 
 * @version 1.0
 * @author Garab K Dorji, Daniel Lucio, Sonam S Tshering
 */
public class RunFlightSystem {
    // --------------------------------------------------------------------------------main
    // caller/menu
    // system-------------------------------------------------------------------------------------
    /**
     * Main method that navigates in between different displays and functions
     * 
     * @param args argument taken into main
     */
    public static void main(String[] args) {
        try {
            AirLog log = AirLog.getInstance();
            log.setUpLog();
            Terminal menu = new Terminal();

            // create instance of CSVreader
            CSVFile flightFile = CSVfactory.createCSVFile("Flight");
            flightFile.readDetails("FlightSchedule.csv");
            CSVFile customerFile = CSVfactory.createCSVFile("Customer");
            customerFile.readDetails("CustomerList.csv");

            HashMap<Integer, Flight> flightMap = flightFile.flightMap();
            HashMap<String, Customer> customerMap = customerFile.customerMap();
            HashMap<String, Airport> portMap = flightFile.airportMap();
            HashMap<String, Employee> employeeMap = customerFile.employeeMap();

            int run1 = 1;
            while (run1 != 0) {
                int next1 = menu.askUser();
                if (next1 == 1) {
                    int run2 = 1;
                    while (run2 != 0) {
                        int next2 = menu.customerLogin(customerMap);
                        if (next2 == 1) {
                            int run3 = 1;
                            while (run3 != 0) {
                                int next3 = menu.cActions();
                                if (next3 == 1) {
                                    int next4 = menu.bookTicket(flightMap, portMap);
                                    if (next4 == 0) {
                                        continue;
                                    } else {
                                        System.out.println("--Invalid input.--\n");
                                    }
                                } else if (next3 == 2) {
                                    int next4 = menu.cancelBook(flightMap);
                                    if (next4 == 0) {
                                        continue;
                                    } else {
                                        System.out.println("--Invalid input.--\n");
                                    }
                                } else if (next3 == 3) {
                                    loggedUser operator = loggedUser.getInstance();
                                    operator.getC().seeBooks();
                                } else if (next3 == 4) {
                                    loggedUser operator = loggedUser.getInstance();
                                    operator.getC().seeBalance();
                                } else if (next3 == 0) {
                                    run3--;
                                    run2--;
                                } else {
                                    System.out.println("--Invalid input.--\n");
                                }
                            }
                        } else if (next2 == 0) {
                            run2--;
                            run1--;
                        } else if (next2 == -1) {
                            System.out.println("--Invalid login.--\n");
                        }
                    }

                } else if (next1 == 2) {
                    int run2 = 1;
                    while (run2 != 0) {
                        int next2 = menu.employeeLogin(employeeMap);
                        if (next2 == 1) {
                            int run3 = 1;
                            while (run3 != 0) {
                                int next3 = menu.eActions();
                                if (next3 == 1) {
                                    int next4 = menu.bookTicket(flightMap, portMap);
                                    if (next4 == 0) {
                                        continue;
                                    } else {
                                        System.out.println("--Invalid input.--\n");
                                    }
                                } else if (next3 == 2) {
                                    int next4 = menu.employeeInquireChange(flightMap);
                                    if (next4 == 0) {
                                        continue;
                                    } else {
                                        System.out.println("--Invalid input.--\n");
                                    }

                                } else if (next3 == 3) {
                                    int next4 = menu.employeeInquireInfo(flightMap);
                                    if (next4 == 0) {
                                        continue;
                                    } else {
                                        System.out.println("--Invalid input.--\n");
                                    }
                                } else if (next3 == 4) {
                                    int next4 = menu.cancelFlight(flightMap);
                                    if (next4 == 0) {
                                        continue;
                                    } else {
                                        System.out.println("--Invalid input.--\n");
                                    }
                                } else if (next3 == 5) {
                                    int next4 = menu.seePort(portMap);
                                    if (next4 == 0) {
                                        continue;
                                    } else {
                                        System.out.println("--Invalid input.--\n");
                                    }
                                } else if (next3 == 6) {
                                    int next4 = menu.automation(customerMap, flightMap, portMap, employeeMap);
                                    if (next4 == 0) {
                                        continue;
                                    } else {
                                        System.out.println("--Invalid input.--\n");
                                    }
                                } else if (next3 == 7) {
                                    int next4 = menu.ticketSummaryCustomer(customerMap);
                                    if (next4 == 0) {
                                        continue;
                                    } else {
                                        System.out.println("--Invalid input.--\n");
                                    }
                                } else if (next3 == 8) {
                                    int next4 = menu.ticketSummaryEmployee(employeeMap);
                                    if (next4 == 0) {
                                        continue;
                                    } else {
                                        System.out.println("--Invalid input.--\n");
                                    }
                                } else if (next3 == 0) {
                                    run3--;
                                    run2--;
                                } else {
                                    System.out.println("--Invalid input.--\n");
                                }
                            }
                        } else if (next2 == 0) {
                            run2--;
                            run1--;
                        } else if (next2 == -1) {
                            System.out.println("--Invalid login.--\n");
                        }
                    }

                } else if (next1 == 0) {
                    run1--;
                } else {
                    worngInput(next1);
                    // System.out.println("--Invalid input.--\n");
                }
            }

            // AirLog log = AirLog.getInstance();
            // log.close

            newCSVs(customerMap, flightMap);

            System.out.println("Bye.\n");

        } catch (MyException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
        }
    }

    public static void worngInput(int next) throws MyException {
        if (next == -1) {
            throw new MyException("--Invalid input.--\n");
        }
    }
    // ------------------------------------------------------------------------------------------CSV
    // writer---------------------------------------------------------------------------------------------

    /**
     * Writes the new csvs with the updated data
     * 
     * @param customerData
     * @param flightData
     */

    public static void newCSVs(HashMap<String, Customer> customerData, HashMap<Integer, Flight> flightData) {
        try {

            FileWriter newCustomers = new FileWriter("newCustomersPA6.csv");
            Set fIds = customerData.keySet();
            Iterator it = fIds.iterator();
            newCustomers.write(
                    "ID,First Name, Last Name, DOB, Role, Money Available, Flights Purchased, MinerAir Membership, Username, Password\n");

            while (it.hasNext()) {
                String k = (String) it.next();
                newCustomers.write(customerData.get(k).getID() + ", " + customerData.get(k).getFName() + ", "
                        + customerData.get(k).getLName() + ", " + customerData.get(k).getDOB() + ", " +
                        customerData.get(k).getRole() + ", " + customerData.get(k).getMoneyAvailable() + ", "
                        + customerData.get(k).getflightsPurchased() + ", " + customerData.get(k).isMembership() + ", " +
                        customerData.get(k).getUserName() + ", " + customerData.get(k).getPassword() + "\n");
            }
            newCustomers.close();
        } catch (Exception e) {
        }

        try {
            FileWriter newFlights = new FileWriter("newFlightsPA6.csv");

            Set fIds2 = flightData.keySet();
            Iterator it2 = fIds2.iterator();

            newFlights.write(
                    "ID, Flight Number, Origin Airport, Origin Code, Destination Airport, Destination Code, Departing Date, Departing Time, Duration, Distance, Time Zone Difference, Arrival Date, Arrival Time, Flight Type, Surcharge, Food Served, Route Cost, Miner Points, Total Seats, First Class Seats, Business Class Seats, Main Cabin Seats, First Class Price, Business Class Price, Main Cabin Price, First Class Profit, Buisness Class Profit, Main Cabin Profit, Total Profit, Expexted profit\n");

            while (it2.hasNext()) {
                int k = (int) it2.next();
                newFlights.write(flightData.get(k).getID() + ", " + flightData.get(k).getFlightNum() + ", "
                        + flightData.get(k).getOriginAirport() + ", " +
                        flightData.get(k).getOriginCode() + ", " + flightData.get(k).getDestinationAirport() + ", "
                        + flightData.get(k).getDestinationCode() + ", " + flightData.get(k).getDestinationCode() + ", "
                        +
                        flightData.get(k).getDepartureTime() + ", " + flightData.get(k).getDuration() + ", "
                        + flightData.get(k).getDistance() + ", " + flightData.get(k).getTimeZoneDifference() + ", " +
                        flightData.get(k).getArrivalDate() + ", " + flightData.get(k).getArrivalTime() + ", "
                        + flightData.get(k).getFlightType() + ", " +
                        flightData.get(k).getSurcharge() + ", " + flightData.get(k).isFoodServed() + ", " +
                        flightData.get(k).getRouteCost() + ", " + flightData.get(k).getMinerPoints() + ", "
                        + flightData.get(k).getTotalSeats() + ", " + flightData.get(k).getFirstClassSeats() +
                        ", " + flightData.get(k).getBusinessClassSeats() + ", " + flightData.get(k).getMainCabinSeats()
                        + ", " + flightData.get(k).getFirstClassPrice() + ", "
                        + flightData.get(k).getBusinessClassPrice() + ", " +
                        flightData.get(k).getMainCabinPrice() + flightData.get(k).getFcProfit() + ", "
                        + flightData.get(k).getBcProfit() + ", " + flightData.get(k).getMcProfit() + ", " +
                        flightData.get(k).getTotProfit() + ", " + flightData.get(k).getExpProfit() + ", " + "\n");
            }
            newFlights.close();

        } catch (Exception e) {
        }
    }

    // --------------------------------------------------------------------------------------------end-----------------------------------------------------------------------------------------------------

}
