package domain;

public class User extends Entity<Long>{
    public String firstName;
    private String lastName;

    public User(String fn, String ln)
    {
        this.firstName = fn;
        this.lastName = ln;
    }

    public String getFirstName() { return this.firstName; }

    public void setFirstName(String fn) { this.firstName = fn; }

    public String getLastName() { return lastName; }

    public void setLastName(String ln) { this.lastName = ln; }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }

}
