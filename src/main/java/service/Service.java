package service;

import domain.*;
import repository.Repository;
import repository.database.*;
import validators.FriendRequestValidator;
import validators.FriendshipValidator;
import validators.UserValidator;

public class Service {
    private Repository<Long, User> ur = new UserDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres", new UserValidator());
    private Repository<Long, Password> pr = new PasswordDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres");
    private Repository<Long, Friendship> fr = new FriendshipDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres", new FriendshipValidator());
    private Repository<Long, Message> mr = new MessageDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres");
    private Repository<Long, FriendRequest> frr = new FriendRequestDbRepository("jdbc:postgresql://localhost:5432/academic", "postgres", "postgres");

    private UserService us = new UserService(ur, pr);
    private FriendshipService fs = new FriendshipService(fr);
    private MessageService ms = new MessageService(mr);
    private FriendRequestService frs = new FriendRequestService(frr);

    private User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Repository<Long, User> getUr() {
        return ur;
    }

    public Repository<Long, Password> getPr() {
        return pr;
    }

    public Repository<Long, Friendship> getFr() {
        return fr;
    }

    public Repository<Long, Message> getMr() {
        return mr;
    }

    public Repository<Long, FriendRequest> getFrr() {
        return frr;
    }

    public UserService getUs() {
        return us;
    }

    public FriendshipService getFs() {
        return fs;
    }

    public MessageService getMs() {
        return ms;
    }

    public FriendRequestService getFrs() {
        return frs;
    }
}
