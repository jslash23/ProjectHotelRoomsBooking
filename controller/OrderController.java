package Project.controller;

import Project.model.UserType;
import Project.service.OrderService;

import java.time.LocalDateTime;
import java.util.Date;

public class OrderController {
    private static OrderService orderService = new OrderService();//зависимость

    public static void bookRoom(long roomId, long userId, String dateFrom, String dateTo, String order, UserType usType) throws Exception {
        OrderService.bookRoom(roomId, userId, dateFrom, dateTo,order, usType);
    }

    public static void cancelReservation(long roomId, long userId, String order, UserType usType) throws Exception {
        OrderService.cancelReservation(roomId, userId,order, usType);
    }
}
