package by.tms.springboot.service;

import by.tms.springboot.entity.User;
import by.tms.springboot.storage.InMemoryUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private InMemoryUserStorage storage;

    public UserService(InMemoryUserStorage storage) {
        this.storage = storage;
    }

    public void save (User user) {
        storage.save(user);
    }

    public User existByUsername(String username) {
        return storage.existByUsername(username);
    }

}
