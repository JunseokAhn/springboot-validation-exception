package com.example.springthymeleafvalidation;

import com.example.springthymeleafvalidation.domain.Major;
import com.example.springthymeleafvalidation.domain.User;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Data
public class SingleRepository {
    private Map<String, User> userStore = new HashMap<>();
    private List<Major> majors = new ArrayList<>();
    private int userId = 1;

    @PostConstruct
    public void init() {
        majors.add(new Major("1", "강의1", "교수1"));
        majors.add(new Major("2", "강의2", "교수2"));
        majors.add(new Major("3", "강의3", "교수3"));
        majors.add(new Major("4", "강의4", "교수4"));
        majors.add(new Major("5", "강의5", "교수5"));
    }

    public User save(User user) {
        user.setId("user" + userId++);
        userStore.put(user.getId(), user);
        return userStore.get(user.getId()).clone();
    }

    public List<Major> getMajors() {
        return new ArrayList<>(majors);
    }

    public User find(String userId) {
        User user = userStore.get(userId);
        if (user == null) {
            throw new NoSuchElementException("id not found");
        }
        return user.clone();
    }

    public User findNoException(String userId) {
        User user = userStore.get(userId);
        if (user == null) {
            return null;
        }
        return user.clone();
    }

    public List<User> findAll() {
        return new ArrayList<>(userStore.values());
    }

    public User put(User user) {
        userStore.put(user.getId(), user);
        return find(user.getId()).clone();
    }

    public User remove(String userId) {
        User user = find(userId);
        if (user == null) {
            throw new NoSuchElementException("id not found");
        }
        userStore.remove(userId);
        return user.clone();
    }

}
