package domain;

import java.time.LocalDate;

public class FriendRequest extends Entity<Long>{

    private User user1;
    private User user2;
    private String status;
    private LocalDate date;

    public FriendRequest(User u, User u1,String status)
    {
        this.user1 = u;
        this.user2 = u1;
        this.date = LocalDate.now();
        this.status=status;
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

    public String getStatus(){return this.status;}

    public void setUser1(User u)
    {
        this.user1 = u;
    }

    public void setUser2(User u)
    {
        this.user2 = u;
    }

    public void setDate(LocalDate date) { this.date = date; }

    public void setStatus(String status){this.status=status;}

    @Override
    public String toString(){return "You have a friend request from "+this.user1+"; Status:"+this.status;}
}
