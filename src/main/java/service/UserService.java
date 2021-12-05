package service;

import domain.User;
import repository.Repository;

public class UserService {
    private Repository<Long, User> repo;

    public UserService(Repository<Long, User> repo) {this.repo = repo;}

    public User addUser(User user) {
        User task = repo.save(user);

        return task;
    }

    public void deleteUser(User user) {
        repo.delete(user);
    }

    public Iterable<User> getAll() { return repo.findAll(); }

    public User updateUser(User user) {
        User task = repo.update(user);

        return task;
    }
}
