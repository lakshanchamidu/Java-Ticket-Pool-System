public class Customer implements Runnable{
    public final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private final int  count;

    public Customer(TicketPool ticketPool, int RetrievalRate,int count) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = RetrievalRate;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i= 0; i < count; i++){

        }
    }
}
