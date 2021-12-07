import UI.UI;
import domain.User;
import java.util.Scanner;

public class Console {
    private UI ui;

    public Console(UI ui)
    {
        this.ui = ui;
    }

    public void run_console()
    {
        boolean logged = false;
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        while (running) {
            System.out.println("1.Log in");
            System.out.println("2.Create account");
            System.out.println("3.Show communities");
            System.out.println("4.Show all users");
            System.out.println("5.Exit");
            int option = sc.nextInt();
            if (option == 1) {
                User user = ui.Login();
                System.out.println("Welcome " + user.getFirstName() + '!');
                logged = true;
                while (logged) {
                    ui.menu();
                    int option2 = sc.nextInt();
                    if (option2 == 1)
                        ui.showFriendRequests(user);
                    if (option2 == 2)
                        ui.manageFriendRequests(user);
                    if (option2 == 3)
                        ui.addFriendRequest(user);
                    if (option2 == 4)
                        ui.deleteFriend(user);
                    if (option2 == 5)
                        ui.showUsers();
                    if (option2 == 6)
                        ui.showFriends(user);
                    if (option2 == 7)
                        ui.showFriendsWithDate(user);
                    if (option2 == 8) {
                        ui.deleteUser(user);
                        logged = false;
                    }
                    if (option2 == 9)
                        ui.messenger(user);
                    if (option2 == 10)
                        logged = false;
                }
            }
            if (option == 2) {
                User user = ui.createAccount();
                System.out.println("Welcome " + user.getFirstName() + '!');
                logged = true;
                while (logged) {
                    ui.menu();
                    int option2 = sc.nextInt();
                    if (option2 == 1)
                        ui.addFriend(user);
                    if (option2 == 2)
                        ui.deleteFriend(user);
                    if (option2 == 3)
                        ui.showUsers();
                    if (option2 == 4)
                        ui.showFriends(user);
                    if (option2 == 5) {
                        ui.deleteUser(user);
                        logged = false;
                    }
                    if (option2 == 6)
                        ui.messenger(user);
                    if (option2 == 7)
                        logged = false;
                }
            }
            if (option == 3)
            {
                Graph communities = new Graph(ui.getFriendshipService());
                communities.edgeMaker();
                communities.printGraph();
            }
            if (option == 4)
            {
                ui.showUsers();
            }
            if (option == 5) {
                running = false;
            }
        }
    }


}
