package Project.repository;

import Project.model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class OrderRepository {
    public static void bookRoom(long roomId, long userId, String dateFrom, String dateTo, String order) throws InterruptedException {
        bookRooms(roomId, userId, dateFrom, dateTo, order);

    }

    private static void bookRooms(long roomId, long userId, String dateFrom, String dateTo, String order) {


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(order))) {

            bufferedWriter.append(Long.toString(roomId));
            bufferedWriter.append(",");
            bufferedWriter.append(Long.toString(userId));
            bufferedWriter.append(",");
            bufferedWriter.append(dateFrom);
            bufferedWriter.append(",");
            bufferedWriter.append(dateTo);
            System.out.println("all writen");
        } catch (IOException e) {
            System.err.println("Can't write to file");
        }
    }


    public static void cancelReservation(long roomId, long userId, String order) throws Exception {
        cancelReserv(roomId, userId, order);
    }

    private static void cancelReserv(long roomId, long userId, String order) throws Exception {
        //найдем наши комнаты для отмены брони, удалим заказ
        //замапим данные из файла ОrderDb.txt
        //в них найдем комнаты, пользователя и если есть удалим заказ этот заказ из файла orderDb.txt

        //1.Считаем строку из текстового файла нашей БД (ОrderDb.txt)
        //2.Замапим данные в объект Order
        //3.Сравним полученные данные с входными данными (long roomId, long userId,)
        //если они сходятся то удалим нужную строку из БД
        //иначе выведем эксепшн такого заказа нет
        //

        writeToOrder(readFromFile(order), roomId, userId, order);
    }

    private static StringBuffer readFromFile(String path) throws Exception {//

        StringBuffer res = new StringBuffer();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                res.append(line);//тут возможно не целый файл а записана какая то ерунда это нужно проверить
                res.append("\n");
            }
        } catch (IOException e) {
            System.err.println("Reading from file " + path + " failed");
        }
        return res;
    }

    private static void writeToOrder(StringBuffer contentToWrite, long roomId, long userId, String orderPath) throws Exception {
        //Нужно записать  данные в OrderDb.txt
        ArrayList<Order> orders = new ArrayList<>();
        Order order1 = new Order(0, new User(0, "", "", "", UserType.USER, false),
                new Room(0, 0, 0, false, false, "",
                        new Hotel(0, "", "", "", "")), "", "", 0);

        Order order2 = new Order(0, new User(0, "", "", "", UserType.USER, false),
                new Room(0, 0, 0, false, false, "",
                        new Hotel(0, "", "", "", "")), "", "", 0);

        Order order3 = new Order(0, new User(0, "", "", "", UserType.USER, false),
                new Room(0, 0, 0, false, false, "",
                        new Hotel(0, "", "", "", "")), "", "", 0);


        StringBuffer res = new StringBuffer();
        try (BufferedWriter br = new BufferedWriter(new FileWriter(orderPath,false))) {

            String convString = contentToWrite.toString();
            String[] lines = convString.trim().split(",");
            int i = 0;

            for (int j = 0; j < orders.size() - 1; j++) {

                //validate fild id проверка на целые числа
                try {
                    if (!lines[i].isEmpty() && lines[i].trim().equals(roomId)) {
                        i++;
                    }
                    if (!lines[i].isEmpty() && lines[i].trim().equals(userId)) {


                        res.append("");
                        // deleteOrder(order/*roomId*/);
                    }


                } catch (Exception e) {
                    System.err.println("There is no such order ");
                }
                i++;

            }
        } catch (IOException e) {
            System.err.println("Can't write to file");
        }
    }

    private static void deleteOrder(String order) {
        //сотрем наш заказ

    }
}







