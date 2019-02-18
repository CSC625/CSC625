package net.csc625.checkin.models.pojos;

/**
 * Created by sristic on 2/19/18.
 */

public class Ticket {

    private int ticketID;
    private String name;
    private String description;
    private String priority;
    private User user;

    public Ticket() {

    }

    public Ticket(String name, String description, String priority, User user) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.user = user;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", user=" + user +
                '}';
    }
}
