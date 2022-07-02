package com.company;

import com.company.controller.UserController;
import com.company.model.User;
import com.company.service.CommandReader;
import com.company.service.ComandType;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/vetclinic";
    static final String USER = "postgres";
    static final String PASS = "postgres";


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a login: ");
        String login = in.nextLine();
        System.out.print("Input a password: ");
        String password = in.nextLine();

        UserController userController = new UserController();
        Optional<User> user = userController.find(login, password);

        if (user.isPresent()) {
            CommandReader commandReader = new CommandReader(in);
            while (true) {
                System.out.print("Input \"Help\" for list of operation.\nInput a operation: ");
                try {
                    ComandType comandType = ComandType.valueOf(in.nextLine());
                    if (commandReader.getCommand(comandType) == 0) {
                        return;
                    }
                } catch (IllegalArgumentException illegalArgumentException) {
                    System.out.println("Не удалось опознать команду: " + illegalArgumentException.getLocalizedMessage());
                } catch (Exception exception) {
                    System.out.println("Error: " + exception.getLocalizedMessage());
                }
            }
        } else {
            System.out.print("401 Unauthorized");
        }
    }
}
