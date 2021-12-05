package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Friendship extends Entity<Long>{
    private User user1;
    private User user2;

    public Friendship(User u, User u1)
    {
        this.user1 = u;
        this.user2 = u1;
    }

    public User getUser1()
    {
        return this.user1;
    }

    public User getUser2()
    {
        return this.user2;
    }

    public void setUser1(User u)
    {
        this.user1 = u;
    }

    public void setUser2(User u)
    {
        this.user2 = u;
    }

    @Override
    public String toString(){
        return this.user1 + "and" + this.user2 + "are friends!";
    }
}
