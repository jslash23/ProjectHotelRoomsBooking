package Project.controller;

import Project.model.Filter;
import Project.model.Room;
import Project.service.RoomService;

import java.util.ArrayList;

public class RoomController {

    private  static RoomService roomService = new RoomService();//
    //for admins
    public void addRoom(Room room, String path){

    }
  public static ArrayList<Room> findRooms (Filter filter, String rooms, String reqRooms) throws Exception{
        //return userService.registerUser(user);//тут должна быть логика
        return  roomService.findRooms(filter, rooms, reqRooms);
    }
}
