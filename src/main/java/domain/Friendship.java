package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Friendship extends Entity<Long>{
    private User user1;
    private User user2;
    private LocalDate date;

    public Friendship(User u, User u1)
    {
        this.user1 = u;
        this.user2 = u1;
        this.date = LocalDate.now();
    }

    public User getUser1()
    {
        return this.user1;
    }

    public User getUser2()
    {
        return this.user2;
    }

    public LocalDate getDate() { return this.date; }

    public void setUser1(User u)
    {
        this.user1 = u;
    }

    public void setUser2(User u)
    {
        this.user2 = u;
    }

    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString(){
        return this.user1 + "and" + this.user2 + "are friends!";
    }
}
