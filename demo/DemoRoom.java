package Project.demo;

import Project.controller.RoomController;
import Project.model.Filter;

import java.io.File;
import java.util.Arrays;

public class DemoRoom {
    public static void main(String[] args) throws Exception {
        Filter newf = new Filter(0,0,false,false,
                " "," "," ");

        String rooms = "C:/Users/Пользователь/Desktop/Project/RoomDb.txt";
        String reqRooms = "C:/Users/Пользователь/Desktop/Project/requestRooms.txt";

        System.out.println("Rooms with you parameters:  " + RoomController.findRooms(newf, rooms, reqRooms));
    }
}
