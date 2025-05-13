public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final String vendorName;
    private final int totalTicket;
    private final int ticketReleaseRate;


    public Vendor(TicketPool pool, String name, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = pool;
        this.vendorName = name;
        this.totalTicket = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }
    @Override
    public void run() {
        for (int i = 1; i <= ticketPool.getMaxTicketCapacity(); i++) {
            String ticket = vendorName + " Ticket: " + i;
            ticketPool.addTicket(ticket);
            try {
                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
