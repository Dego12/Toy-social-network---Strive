package service;

import domain.Password;
import domain.User;
import repository.Repository;

public class UserService {
    private Repository<Long, User> repoU;
    private Repository<Long, Password> repoP;


    public UserService(Repository<Long, User> repo, Repository<Long, Password> repo2) {
        this.repoU = repo;
        this.repoP = repo2;
    }

    public User addUser(User user, Password pass) {
        User task = repoU.save(user);
        Password t2 = repoP.save(pass);


        return task;
    }

    public void deleteUser(User user, Password p) {
        repoU.delete(user);
        repoP.delete(p);
    }

    public Iterable<User> getAllU() {
        return repoU.findAll(); }

    public Iterable<Password> getAllP() {
        return repoP.findAll();
    }

    public User Login(String name, String password)
    {
        String[] flName = name.split(" ");
        String firstName = flName[0];
        String lastName = flName[1];
        for (User u: this.getAllU())
            for (Password p: this.getAllP()) {
                if ((u.getFirstName().equals(firstName) && u.getLastName().equals(lastName)) && (u.getFirstName().equals(p.getUser().getFirstName()) && u.getLastName().equals(p.getUser().getLastName())))
                    if (p.getPassword().equals(password))
                    {
                        User user = u;
                        return user;
                    }
                }
        return null;
    }



    public User updateUser(User user, Password password) {
        User task = repoU.update(user);
        Password t2 = repoP.update(password);
        return task;
    }
}
