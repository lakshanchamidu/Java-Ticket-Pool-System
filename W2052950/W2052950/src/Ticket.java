import java.math.BigDecimal;

public class Ticket {
    private int ticketId;
    private String eventName;
    private int ticketPrice;

    public Ticket(int ticketId, String eventName, int ticketPrice) {
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    public int getTicketId() {
        return this.ticketId;
    }

    public String getEventName() {
        return this.eventName;
    }

    public int getTicketPrice() {
        return this.ticketPrice;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String toString() {
        int var10000 = this.ticketId;
        return "Ticket{ticketId=" + var10000 + ", eventName=" + this.eventName + "',ticketPrice=" + String.valueOf(this.ticketPrice) + "}";
    }
}
