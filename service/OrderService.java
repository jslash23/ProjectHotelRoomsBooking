package Project.service;

import Project.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.Date;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();

    public static void bookRoom(long roomId, long userId, String dateFrom, String dateTo, String order) throws Exception {
        //if (ValidateroomId(roomId) && ValidateuserId(userId)) {
            OrderRepository.bookRoom(roomId,userId,dateFrom,dateTo,order);
       // }
        //throw new Exception("this fild can't be null!");

    }
    public static void cancelReservation(long roomId, long userId,String order) throws Exception{
        OrderRepository.cancelReservation ( roomId, userId,order) ;
    }

    private static boolean ValidateroomId(long roomId){
        return true;
    }
    private static boolean ValidateuserId(long userId){
        return true;
    }
}
