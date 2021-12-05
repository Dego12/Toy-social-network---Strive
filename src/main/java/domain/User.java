package domain;

public class User extends Entity<Long>{
    private String firstName;
    private String lastName;

    public User(String fn, String ln)
    {
        this.firstName = fn;
        this.lastName = ln;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String fn) { this.firstName = fn; }

    public String getLastName() { return lastName; }

    public void setLastName(String ln) { this.lastName = ln; }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }

}
