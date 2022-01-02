package Console;

import UI.UI;
import domain.FriendRequest;
import domain.Friendship;
import domain.Message;
import domain.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import repository.Repository;
import repository.database.FriendRequestDbRepository;
import repository.database.FriendshipDbRepository;
import repository.database.MessageDbRepository;
import repository.database.UserDbRepository;
import repository.file.FriendshipFileRepository;
import repository.file.UserFileRepository;
import service.FriendRequestService;
import service.FriendshipService;
import service.MessageService;
import service.UserService;
import validators.FriendshipValidator;
import validators.UserValidator;

import java.io.IOException;

public class Main extends Application{



    @Override
    public void start(Stage stage) throws IOException{

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("console-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 997, 495);
            stage.setTitle("Strive");
            stage.setScene(scene);
            stage.show();
    }



    public static void main(String[] args){
            launch();
            /*
            String fileName = "data/users.csv";
            String fileName2 = "data/friendships.csv";
            Repository<Long, User> userFileRepository = new UserFileRepository(fileName, new UserValidator());
            Repository<Long, Friendship> friendshipFileRepository = new FriendshipFileRepository(fileName2, new FriendshipValidator());
            Repository<Long, User> userDbRepository = new UserDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres", new UserValidator());
            Repository<Long, Friendship> friendshipDbRepository = new FriendshipDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres", new FriendshipValidator());
            Repository<Long, Message> messageDbRepository = new MessageDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres");
            Repository<Long, FriendRequest> friendRequestDbRepository = new FriendRequestDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres");
            UserService us = new UserService(userDbRepository);
            FriendshipService fs = new FriendshipService(friendshipDbRepository);
            MessageService ms = new MessageService(messageDbRepository);
            FriendRequestService frs= new FriendRequestService(friendRequestDbRepository);
            UI ui = new UI(us, fs, ms,frs);
            Console console = new Console(ui);
            console.run_console();
             */

    }
}
