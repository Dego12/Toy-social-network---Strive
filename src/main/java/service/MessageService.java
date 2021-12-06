package service;

import domain.Message;
import domain.ReplyMessage;
import domain.User;
import repository.Repository;

import java.time.LocalDateTime;
import java.util.List;

public class MessageService {
    private Repository<Long, Message> repo;

    public MessageService(Repository<Long, Message> repo) {this.repo = repo;}

    public Message addMessage(Message message)
    {
        Message message1 = repo.save(message);

        return message1;
    }

    public void deleteMessage(Message message) { repo.delete(message); }

    public Iterable<Message> getAll() { return repo.findAll(); }

    public Message sendMessage(User from, List<User> to, String message)
    {
        LocalDateTime date = LocalDateTime.now();
        Message send = new Message(from, to, message, date);
        this.addMessage(send);
        return send;
    }
}
