package resources;

import java.util.ArrayList;
import java.util.List;

import config.Config;

public class UsersList implements Config<User> {
    public List<User> users;

    public UsersList() {
        users = new ArrayList<>();
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public List<User> list() {
        return users;
    }

    @Override
    public User get(String id) {
        for (User user : users) {
            if (user.id.equals(id))
                return user;
        }
        return null;
    }

    @Override
    public void delete(String id) {
        User toRemove = get(id);
        users.remove(toRemove);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            result.append(i < users.size() - 1 ? users.get(i) + "\n" : users.get(i) + "");
        }
        if (result.length() == 0) {
            result.append("No users there!");
        }
        return result.toString();
    }
}
