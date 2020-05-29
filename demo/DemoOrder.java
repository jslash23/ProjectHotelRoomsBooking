package Project.demo;
import Project.controller.OrderController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

public class DemoOrder {
    public static void main(String[] args)  throws  Exception{
        String order = "C:/Users/Пользователь/Desktop/Project/OrderDb.txt";
        long roomId = 1020;
        long userId = 5003;
        String dateFrom = "2020, Month.AUGUST, 15, 12,45";
        String dateTo = "2020, Month.SEPTEMBER, 15, 12,45";
        OrderController.bookRoom(roomId,userId,dateFrom,dateTo, order) ;


        OrderController.cancelReservation(roomId,  userId, order);

    }
}
