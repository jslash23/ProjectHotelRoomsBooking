package Project.controller;

import Project.model.Filter;
import Project.model.Room;
import Project.model.UserType;
import Project.service.RoomService;

import java.util.ArrayList;

public class RoomController {

    private static RoomService roomService = new RoomService();//

    //methods for admins
    public static void addRoom(Room room, String path, UserType usType) throws Exception {
        roomService.addRoom(room, path, usType);
    }

    public static void deleteRoom(long roomId, String path, UserType usType) throws Exception {
        roomService.deleteRoom(roomId, path, usType);
    }


    public static ArrayList<Room> findRooms(Filter filter, String rooms, String reqRooms) throws Exception {
        //return userService.registerUser(user);//тут должна быть логика
        return roomService.findRooms(filter, rooms, reqRooms);
    }


}
