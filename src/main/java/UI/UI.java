package UI;

import domain.Friendship;
import domain.User;
import service.FriendshipService;
import service.UserService;
import java.util.Random;

import java.util.Scanner;

public class UI {
    private UserService us;
    private FriendshipService fs;

    public UI(UserService us, FriendshipService fs){
        this.us = us;
        this.fs = fs;
    }

    public User createAccount()
    {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String Name = sc.nextLine();
        String[] flName = Name.split(" ");
        String firstName = flName[0];
        String lastName = flName[1];
        User user = new User(firstName, lastName);
        user.setId(Long.valueOf(rand.nextInt(20)));
        this.us.addUser(user);
        return user;
    }

    public User Login()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String Name = sc.nextLine();
        String[] flName = Name.split(" ");
        String firstName = flName[0];
        String lastName = flName[1];
        for (User u: us.getAll())
            if (u.getFirstName().equals(firstName) && u.getLastName().equals(lastName))
            {
                User user = u;
                return user;
            }
        return null;
    }

    public void addFriend(User user)
    {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter the name of the user you'd like to be friends with");
        String Name = sc.nextLine();
        String[] flName = Name.split(" ");
        String firstName = flName[0];
        String lastName = flName[1];

        for (User usr: us.getAll())
        {
            if (usr.getFirstName().equals(firstName) && usr.getLastName().equals(lastName)) {
                Friendship fr = new Friendship(user, usr);
                fr.setId((long)rand.nextInt(20));
                this.fs.addFriendship(fr);
            }
        }
    }

    public void deleteFriend(User user)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the friend you'd like to delete");
        String Name = sc.nextLine();
        String[] flName = Name.split(" ");
        String firstName = flName[0];
        String lastName = flName[1];

        for (Friendship fr: fs.getAll())
        {
            if (fr.getUser1().getFirstName().equals(user.getFirstName()) && fr.getUser1().getLastName().equals(user.getLastName()) && fr.getUser2().getFirstName().equals(firstName) && fr.getUser2().getLastName().equals(lastName))
            {
                fs.deleteFriendship(fr);
            }
            if (fr.getUser1().getFirstName().equals(firstName) && fr.getUser1().getLastName().equals(lastName) && fr.getUser2().getFirstName().equals(user.getFirstName()) && fr.getUser2().getLastName().equals(user.getLastName()))
            {
                fs.deleteFriendship(fr);
            }
        }
    }

    public void showUsers()
    {
        us.getAll().forEach(System.out::println);
    }


    public void showFriends(User user)
    {
            for (Friendship fr : this.fs.getAll()) {
                if (fr.getUser1().getFirstName().equals(user.getFirstName()) && fr.getUser1().getLastName().equals(user.getLastName()))
                {
                    System.out.println(fr.getUser2() + " since " + fr.getDate());
                }
                if (fr.getUser2().getFirstName().equals(user.getFirstName()) && fr.getUser2().getLastName().equals(user.getLastName()))
                {
                    System.out.println(fr.getUser1() + " since " + fr.getDate());
                }
            }
    }

    public void deleteUser(User user) {
        us.deleteUser(user);
    }

    public void menu()
    {
        System.out.println("Choose your option:");
        System.out.println("1.Add friend");
        System.out.println("2.Delete friend");
        System.out.println("3.Show all users");
        System.out.println("4.Show all friends");
        System.out.println("5.Delete User");
        System.out.println("6.Log out");
    }

    public FriendshipService getFriendshipService()
    {
        return this.fs;
    }

}