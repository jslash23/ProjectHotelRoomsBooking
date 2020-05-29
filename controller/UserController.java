package Project.controller;


import Project.model.User;
import Project.service.UserService;

public class UserController {
    private static UserService userService = new UserService();//зависимость

   public static void registerUser(User user, String userPath) throws Exception{

        userService.registerUser(user, userPath);//тут должна быть логика
    }
}


