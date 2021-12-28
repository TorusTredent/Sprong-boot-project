package by.tms.springboot.storage;

import by.tms.springboot.entity.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class InMemoryUserStorage {

    private static LinkedList<User> users = new LinkedList<>();


    public void save(User user) {
        users.addFirst(user);
    }

    public User existByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().contains(username))
                .findFirst().orElse(null);
    }
}
