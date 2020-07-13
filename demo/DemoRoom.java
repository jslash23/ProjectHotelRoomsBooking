package Project.demo;

import Project.controller.RoomController;
import Project.model.Filter;
import Project.model.Hotel;
import Project.model.Room;
import Project.model.UserType;

import java.util.ArrayList;


public class DemoRoom {
    public static void main(String[] args) throws Exception {
        Filter newf = new Filter(0,0,false,false,
                " "," "," ");
        Hotel kievPlaza = new Hotel(0,"KievPlaza","Ukraine","Kiev","Hreshatik");
        ArrayList<Hotel>  hotels = new ArrayList<>();
        hotels.add(kievPlaza);
        Room room1 = new Room(0,1,500, true, true, "02.05.2020",
                kievPlaza);
        long roomId = 46;

        /*  private long id;
        private String name;
        private String country;
        private String city;
        private  String street;*/


        String roomsPath = "C:/Users/Пользователь/Desktop/Project/roomsDb.txt";
        String reqRooms = "C:/Users/Пользователь/Desktop/Project/requestRooms.txt";

        System.out.println("Rooms with you parameters:  " + RoomController.findRooms(newf, roomsPath, reqRooms));

        //Нужно добавить нашу сущность room1 в репозиторий roomsPath
        RoomController.addRoom(room1, roomsPath, UserType.ADMIN);
        //RoomController.deleteRoom(roomId, roomsPath, UserType.ADMIN);
        //RoomController.addRoom(room1, roomsPath, UserType.USER);
        //RoomController.deleteRoom(roomId, roomsPath, UserType.USER);
    }
}
