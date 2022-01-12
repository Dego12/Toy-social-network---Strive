package UI;

import domain.*;
import service.FriendRequestService;
import service.FriendshipService;
import service.MessageService;
import service.UserService;

import java.time.LocalDateTime;
import java.util.*;

public class UI {
    private UserService us;
    private FriendshipService fs;
    private MessageService ms;
    private FriendRequestService frs;

    public UI(UserService us, FriendshipService fs, MessageService ms, FriendRequestService frs){
        this.us = us;
        this.fs = fs;
        this.ms = ms;
        this.frs=frs;
    }


    public FriendshipService getFriendshipService(){return fs;}

    public User createAccount()
    {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String Name = sc.nextLine();
        String[] flName = Name.split(" ");
        String firstName = flName[0];
        String lastName = flName[1];
        String password = sc.nextLine();
        User user = new User(firstName, lastName);
        Password p = new Password(user, password);
        user.setId(Long.valueOf(rand.nextInt(20)));
        System.out.println(user.getId());
        this.us.addUser(user, p);
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
        for (User u: us.getAllU())
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

        for (User usr: us.getAllU())
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
        us.getAllU().forEach(System.out::println);
    }


    public void showFriends(User user)
    {
        List<Friendship> friendships = new ArrayList<Friendship>();
        for (Friendship fr : this.fs.getAll()) {
                friendships.add(fr);
        }
        friendships.stream()
                .filter(x -> {
                    return (x.getUser1().getFirstName().equals(user.getFirstName()) && x.getUser1().getLastName().equals(user.getLastName()));
                })
                .forEach(x->{System.out.println(x.getUser2() + " since " + x.getDate());});
        friendships.stream()
                .filter(x -> {
                    return (x.getUser2().getFirstName().equals(user.getFirstName()) && x.getUser2().getLastName().equals(user.getLastName()));
                })
                .forEach(x -> {System.out.println(x.getUser1() + " since " + x.getDate());});
    }

    public void showFriendRequests(User user){
        for(FriendRequest fr:this.frs.getAll()){
            if (fr.getUser2().getFirstName().equals(user.getFirstName()) && fr.getUser2().getLastName().equals(user.getLastName())){
                System.out.println(fr);
            }
        }
    }

    public void deleteUser(User user) {
        for (Password p: this.us.getAllP())
        {
            if (user.getFirstName().equals(p.getUser().getFirstName()) && user.getLastName().equals(p.getUser().getLastName()))
                us.deleteUser(user, p);
        }
    }

    public void messenger(User user)
    {
        boolean running = true;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of the friends you'd like to send a message to");
        String friend = sc.nextLine();
        String[] friends = friend.split(", ");
        List<User> to = new LinkedList<User>();
        for (String f : friends) {
            String[] fr = f.split(" ");
            for (User u : this.us.getAllU()) {
                if (u.getFirstName().equals(fr[0]) && u.getLastName().equals(fr[1])) {
                    to.add(u);
                }
            }
        }
        List<Message> messages = new ArrayList<>();
        for (Message m: this.ms.getAll())
        {
            messages.add(m);
        }
        messages.sort(Comparator.comparing(Message::getData));
        for (String f : friends)
        {
            String[] fr = f.split(" ");
            for (Message m: messages)
            {
                for (User u: m.getTo())
                {
                    if (u.getFirstName().equals(fr[0]) && u.getLastName().equals(fr[1]) && ((m.getFrom().getFirstName().equals(user.getFirstName())) && (m.getFrom().getLastName().equals(user.getLastName()))))
                    {
                        System.out.println(m);
                    }
                    if (m.getFrom().getFirstName().equals(fr[0]) && m.getFrom().getLastName().equals(fr[1]) && (u.getFirstName().equals(user.getFirstName()) && u.getLastName().equals(user.getLastName())))
                    {
                        System.out.println(m);
                    }
                }
            }
        }
        System.out.println("Enter 'x' to exit!");
        while (running) {
            String message = sc.nextLine();
            if (message.equals("x")) {
                running = false;
                break;
            }
            LocalDateTime date = LocalDateTime.now();
            Message msg = new Message(user, to, message, date);
            ms.addMessage(msg);
            System.out.println(msg);
        }
    }


    public void showFriendsWithDate(User user)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the month(ex: 01,03,10) ");
        String luna=sc.nextLine();
        List<Friendship> friendships = new ArrayList<Friendship>();
        for (Friendship fr : this.fs.getAll()) {
            friendships.add(fr);
        }
            friendships.stream()
                    .filter(x -> {
                        return (x.getDate().toString().split("-")[1].equals(luna) && (x.getUser1().getFirstName().equals(user.getFirstName()) && x.getUser1().getLastName().equals(user.getLastName())));
                    })
                    .forEach(x->{System.out.println(x.getUser2() + " since " + x.getDate());});
            friendships.stream()
                    .filter(x -> {
                        return (x.getDate().toString().split("-")[1].equals(luna) && (x.getUser2().getFirstName().equals(user.getFirstName()) && x.getUser2().getLastName().equals(user.getLastName())));
                    })
                    .forEach(x -> {System.out.println(x.getUser1() + " since " + x.getDate());});
        }

    public void addFriendRequest(User user){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter the name of the user you'd like to be friends with");
        String Name = sc.nextLine();
        String[] flName = Name.split(" ");
        String firstName = flName[0];
        String lastName = flName[1];

        for (User usr: us.getAllU())
        {
            if (usr.getFirstName().equals(firstName) && usr.getLastName().equals(lastName)) {
                FriendRequest friendrequest= new FriendRequest(user,usr,"Pending");
                friendrequest.setId((long)rand.nextInt(20));
                this.frs.addFriendRequest(friendrequest);
                System.out.println("Friend request sent!");

            }
        }
    }

    public void manageFriendRequests(User user){
        for(FriendRequest fr:this.frs.getAll()){
            if (fr.getUser2().getFirstName().equals(user.getFirstName()) && fr.getUser2().getLastName().equals(user.getLastName()) && fr.getStatus().equals("Pending")){
                System.out.println("Do you accept "+fr.getUser1()+"'s friend request?(Accept/Decline)");
                Scanner sc = new Scanner(System.in);
                String answer = sc.nextLine();
                if(answer.equals("Accept")){
                    fr.setStatus("Accepted");
                    this.frs.updateFriendRequest(fr);
                    Friendship friendship=new Friendship(fr.getUser1(),fr.getUser2());
                    this.fs.addFriendship(friendship);
                }
                else if(answer.equals("Decline")){
                    fr.setStatus("Declined");
                    this.frs.updateFriendRequest(fr);
                }
            }
        }
    }

    public void menu()
    {
        System.out.println("Choose your option:");
        System.out.println("1.Friend requests");
        System.out.println("2.Manage friend requests");
        System.out.println("3.Add friend");
        System.out.println("4.Delete friend");
        System.out.println("5.Show all users");
        System.out.println("6.Show all friends");
        System.out.println("7.Show all friends added in a specific month");
        System.out.println("8.Delete User");
        System.out.println("9.Messenger");
        System.out.println("10.Log out");
    }


}
