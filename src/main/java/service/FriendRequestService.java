package service;

import domain.FriendRequest;
import repository.Repository;

public class FriendRequestService {
    private Repository<Long, FriendRequest> repo;

    public FriendRequestService(Repository<Long, FriendRequest> repo) {this.repo = repo;}

    public FriendRequest addFriendRequest(FriendRequest friendrequest)
    {
        FriendRequest fr = this.repo.save(friendrequest);;
        return fr;
    }

    public void deleteFriendRequest(FriendRequest friendrequest)
    {
        repo.delete(friendrequest);
    }

    public Iterable<FriendRequest> getAll()
    {
        return repo.findAll();
    }

    public FriendRequest updateFriendRequest(FriendRequest friendrequest)
    {
        FriendRequest fr = this.repo.update(friendrequest);
        return fr;
    }
}
