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




    public User updateUser(User user, Password password) {
        User task = repoU.update(user);
        Password t2 = repoP.update(password);
        return task;
    }
}
