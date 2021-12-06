package domain;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyMessage extends Message {
    private Message reply;

    public ReplyMessage(User from, List<User> to, String message, LocalDateTime date, Message reply)
    {
        super(from, to, message, date);
        this.reply = reply;
    }

    @Override
    public User getFrom() {
        return super.getFrom();
    }

    @Override
    public List<User> getTo() {
        return super.getTo();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public LocalDateTime getData() {
        return super.getData();
    }

    public Message getReply()
    {
        return this.reply;
    }

    @Override
    public void setTo(User user) {
        super.setTo(user);
    }

    @Override
    public void setFrom(List<User> users) {
        super.setFrom(users);
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }

    @Override
    public void setData(LocalDateTime date) {
        super.setData(date);
    }

    @Override
    public String toString() {
        return "To: " + this.getTo() + "From: " + this.getFrom() + ": " + this.reply + " at: " + this.getData();
    }
}
