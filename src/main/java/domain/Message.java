package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Message extends Entity<Long>{
    private User from;
    private List<User> to;
    private String message;
    private LocalDateTime date;

    public Message(User from, List<User> to, String message, LocalDateTime date)
    {
        this.from = from;
        this.to = to;
        this.message = message;
        this.date = date;
    }

    public User getFrom()
    {
        return this.from;
    }

    public List<User> getTo()
    {
        return this.to;
    }

    public String getMessage()
    {
        return this.message;
    }

    public LocalDateTime getData()
    {
        return this.date;
    }

    public void setTo(User user)
    {
        this.from = user;
    }

    public void setFrom(List<User> users)
    {
        this.to = users;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setData(LocalDateTime date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "From: " + this.from + "to: " + this.to + "at: " + date;
    }
}
