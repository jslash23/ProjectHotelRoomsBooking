package Project.controller;

import Project.service.OrderService;

import java.time.LocalDateTime;
import java.util.Date;

public class OrderController {
    private static OrderService orderService = new OrderService();//зависимость

    public static void bookRoom(long roomId, long userId, String dateFrom, String dateTo,String order) throws Exception {
        OrderService.bookRoom(roomId, userId, dateFrom, dateTo,order);
    }

    public static void cancelReservation(long roomId, long userId, String order) throws Exception {
        OrderService.cancelReservation(roomId, userId,order);
    }
}
