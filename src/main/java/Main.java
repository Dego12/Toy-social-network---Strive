import UI.UI;
import domain.Friendship;
import domain.User;
import repository.Repository;
import repository.database.FriendshipDbRepository;
import repository.database.UserDbRepository;
import repository.file.FriendshipFileRepository;
import repository.file.UserFileRepository;
import service.FriendshipService;
import service.UserService;
import validators.FriendshipValidator;
import validators.UserValidator;
import validators.ValidationException;

public class Main {

    public static void main(String[] args) {
            String fileName = "data/users.csv";
            String fileName2 = "data/friendships.csv";
            Repository<Long, User> userFileRepository = new UserFileRepository(fileName, new UserValidator());
            Repository<Long, Friendship> friendshipFileRepository = new FriendshipFileRepository(fileName2, new FriendshipValidator());
            Repository<Long, User> userDbRepository = new UserDbRepository("jdbc:postgresql://localhost:5432/social", "postgres", "010203010203", new UserValidator());
            Repository<Long, Friendship> friendshipDbRepository = new FriendshipDbRepository("jdbc:postgresql://localhost:5432/social", "postgres", "010203010203", new FriendshipValidator());
            UserService us = new UserService(userDbRepository);
            FriendshipService fs = new FriendshipService(friendshipDbRepository);
            UI ui = new UI(us, fs);
            Console console = new Console(ui);
            console.run_console();
    }
}
