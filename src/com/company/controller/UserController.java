package com.company.controller;

import com.company.dao.UserDAO;
import com.company.model.User;

import java.util.Optional;

public class UserController {
    public UserController() {
        this.userDAO = new UserDAO();
    }

    UserDAO userDAO;

    public Optional<Integer> add(String login, String password) {
        return userDAO.add(new User(login, password));
    }

    public Optional<User> find(String login, String password) {
        return userDAO.find(login, password);
    }
}
