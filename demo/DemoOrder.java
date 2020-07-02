package Project.demo;
import Project.controller.OrderController;
import Project.model.User;
import Project.model.UserType;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

public class DemoOrder {
    public static void main(String[] args)  throws  Exception{
        String orderPath = "C:/Users/Пользователь/Desktop/Project/OrderDb.txt";
        long roomId = 1020;
        long userId = 5003;
        String dateFrom = "2018, Month.AUGUST";
        String dateTo = "2020, Month.SEPTEMBER";// 15, 12,45
       //OrderController.bookRoom(roomId,userId,dateFrom,dateTo, orderPath, UserType.ADMIN) ;
        long roomId1 = 1040;
        long userId1 = 5007;
        String dateFrom1 = "2021, Month.AUGUST";
        String dateTo1 = "2022, Month.SEPTEMBER";// 15, 12,45
        //OrderController.bookRoom(roomId1,userId1,dateFrom1,dateTo1, orderPath, UserType.ADMIN);
     OrderController.cancelReservation(roomId, userId, orderPath, UserType.ADMIN);
    }
}
