import java.io.*;
import java.util.*;

public class TicketPool {
    private final List<String> tickets = Collections.synchronizedList(new ArrayList<>());
    private  String jsonFile = "tickets.json";
    private int ticketReleaseRate;
    private Vendor vendor;
    private int totalTickets;
    private int customerRetrievalRate;
    private final Queue<String> ticketQueue;
    private int maxTicketCapacity;
    private int ticketsSold;
    private int remainingTicketsToAdd;

    public TicketPool() {
        this.ticketQueue = new LinkedList<String>();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            try{
                System.out.print("Enter the amount of total ticket: ");
                this.totalTickets = scanner.nextInt();
                if (totalTickets <= 0){
                    System.out.println("Wrong Input! Please enter the correct input.");
                }
                else{
                    break;
                }
            } catch (Exception e) {
                System.out.println("Wrong Input! Please enter the correct input.");
                scanner.nextLine();
            }
        }
        while(true) {
            try{
                System.out.print("Enter the ticket release rate: ");
                this.ticketReleaseRate = scanner.nextInt();
                if (ticketReleaseRate <= 0){
                    System.out.println("Wrong Input! Please enter the correct input.");
                }
                else{
                    break;
                }
            } catch (Exception e) {
                System.out.println("Wrong Input! Please enter the correct input.");
                scanner.nextLine();
            }
        }
        while(true){
            try {
                System.out.print("Enter the customer retrieval rate: ");
                this.customerRetrievalRate = scanner.nextInt();
                if (customerRetrievalRate <= 0){
                    System.out.println("Wrong Input! Please enter the correct input.");
                }
                else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Wrong Input! Please enter the correct input.");
                scanner.nextLine();
            }
        }
        while(true) {
            try {
                System.out.print("Enter the maximum ticket capacity: ");
                this.maxTicketCapacity = scanner.nextInt();
                if (maxTicketCapacity <= 0) {
                    System.out.println("Wrong Input! Please enter the correct input.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.print("Wrong Input! Please enter the correct input.");
                scanner.nextLine();
            }
        }
        String total = String.valueOf(totalTickets);
        String release = String.valueOf(ticketReleaseRate);
        String retrieval = String.valueOf(customerRetrievalRate);
        String max = String.valueOf(maxTicketCapacity);
        tickets.add("Total Tickets: " + total);
        tickets.add("Ticket Release Rate: "+release);
        tickets.add("Customer Retrieval rate: "+retrieval);
        tickets.add("Maximum Ticket Capacity: " + max);
        System.out.println(tickets);
    }


public synchronized void addTicket(String ticket) {
    while(this.ticketQueue.size() >= this.maxTicketCapacity) {
        try {
            this.wait();
        } catch (InterruptedException var3) {
            throw new RuntimeException("Interrupted while waiting to add ticket.");
        }
    }

    this.ticketQueue.add(ticket);
    --this.remainingTicketsToAdd;
    this.notifyAll();
    PrintStream var10000 = System.out;
    String var10001 = Thread.currentThread().getName();
    var10000.println("Ticket added by - " + var10001 + " - current size is-" + this.ticketQueue.size());
    this.displayRemainingTickets();
}

    private void displayRemainingTickets() {
        int remainingTicketsInSystem = this.totalTickets - this.ticketsSold;
        System.out.println("Remaining Tickets in System: " + remainingTicketsInSystem);
    }

    public synchronized int getPoolSize() {
        return this.ticketQueue.size();
    }

    public synchronized int getRemainingTicketsToAdd() {
        return this.remainingTicketsToAdd;
    }

    public synchronized int getTicketsSold() {
        return this.ticketsSold;
    }

    public List<String> getTickets() {
        return new ArrayList<>(tickets);
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }


}
