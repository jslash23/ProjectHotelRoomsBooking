package Project.demo;

import Project.controller.HotelController;
import Project.model.Hotel;
import Project.model.Room;
import Project.model.UserType;

public class DemoHotel {


    public static void main(String[] args) throws Exception {
        Hotel harkovPlaza = new Hotel(0,"HarkovPlaza","Ukraine","Harkov","anystreet");
        long hotelId = 94;

        String pathHotels = "C:/Users/Пользователь/Desktop/Project/HotelDb.txt";

        //HotelController.addHotel(harkovPlaza, pathHotels, UserType.ADMIN );

        HotelController.deleteHotel(hotelId, pathHotels, UserType.ADMIN);
        //HotelController.deleteHotel(hotelId, pathHotels, UserType.USER);
    }
}
