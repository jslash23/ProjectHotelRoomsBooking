package Project.service;

import Project.model.UserType;
import Project.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.Date;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();

    public static void bookRoom(long roomId, long userId, String dateFrom, String dateTo, String order, UserType usType) throws Exception {


            if (usType.equals(UserType.ADMIN)) {
                OrderRepository.bookRoom(roomId, userId, dateFrom, dateTo, order);
            }
        else  {
            System.err.println("You havn't rights for modify repository");
        }
    }

    public static void cancelReservation(long roomId, long userId, String order, UserType usType) throws Exception {


            if (usType.equals(UserType.ADMIN)) {
                OrderRepository.cancelReservation(roomId, userId, order);
            }
        else  {
            System.err.println("You havn't rights for modify repository");
        }
    }


    private static boolean ValidateroomId(long roomId) {
        return true;
    }

    private static boolean ValidateuserId(long userId) {
        return true;
    }
}
