package Project.repository;

import Project.model.Filter;
import Project.model.Hotel;
import Project.model.Room;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class RoomRepository {
    public static ArrayList<Room> findRooms = new ArrayList<>();

    private static Filter filter = new Filter(0, 0, false, false, null,
            null, null);


    public static ArrayList<Room> findRooms(Filter filter, String pathRooms, String pathReqRooms) throws Exception {
        //нам нужно пребразовать входные данные в фильтр (в объект Filter)
        //вызовем метод findHotelByCity и найдем гостиницу по городу
        //получив список гостиниц найденных по городу мы их
        // вернем.
        //Теперь если совпадает ай ди отеля который мы нашли с ай ди отеля в Room
        // Сверим наши поля в фильтре с полями  румов
        //если все поля совпадают то вернем комнаты

        Filter filter1 = writeToFilter(readFromFile(pathReqRooms));//Маппинг запроса комнат в объект Фильтр
        System.out.println(filter1);
        //нужно из файла Рум замапить строки в объект Рум
        String hotelsPath = "C:/Users/Пользователь/Desktop/Project/HotelDb.txt";

        //найдем отели по городу
        ArrayList<Hotel> hotelsByCity = HotelRepository.findHotelByCity(filter1.getCity(), hotelsPath);

        //Взять результат вывода всех отелей по имени
        //Сверить у каждый отель по id с каждой строкой комнат по id
        //для этого нам нужно все румы из roomDb.txt замапить в ArrayList<Room>

        //Замапим наши комнаты из файла
        ArrayList<Room> allRooms = writeToRooms(readFromFile(pathRooms));

        //найдем наши комнаты по ай ди
        return findRoomsId(hotelsByCity, allRooms);
    }

    //Получим список комнат которые есть в отелях hotels
    private static ArrayList<Room> findRoomsId(ArrayList<Hotel> hotels, ArrayList<Room> rooms) {
        ArrayList<Room> roomsMap = new ArrayList<>();

        try {
            for (Hotel hotel : hotels) {
                for (Room room : rooms) {
                    Long hotId = hotel.getId();
                    Long roomId = room.getHotel().getId();
                    if (hotId.equals(roomId)) {
                        if (compareRoomsWithFilter(room, filter)) {
                            roomsMap.add(room);
                        }
                    }
                }
                //System.out.println("hotels:  " + hotel);
            }
        } catch (Exception e) {
            System.err.println("Error");
        }
        return roomsMap;
    }

    private static boolean compareRoomsWithFilter(Room room, Filter filter) {


        Integer guests = room.getNumbersOfGuests();
        Double price = room.getPrice();
        boolean breakfast = room.isBreakfastIncluded();
        boolean pets = room.isPetsAllowed();
        String dateAvailable = room.getDateAvableFrom();

        if (guests.equals(filter.getNumbersOfGuests()) && price.equals(filter.getPrice()) &&
                dateAvailable.equals(filter.getDateAvableFrom())) {
            if (breakfast == filter.isBreakfastIncluded() && pets == filter.isPetsAllowed()) {
                return true;
            }
        }

        return false;
    }

    //Маппинг данных из текстового файла requestRooms в объект фильтр


    //тут нужно использовать мап иначе затирается предыдущий объект хостел
    private static StringBuilder readFromFile(String path) throws Exception {//

        StringBuilder res = new StringBuilder();

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


    //Нужно перевести входящий объект в строку  и записать в файл

    private static ArrayList<Room> writeToRooms(StringBuilder contentToWrite) {
        ArrayList<Room> rooms = new ArrayList<>();

        Room room1 = new Room(0, 0, 0, false, false,
                null,new Hotel(0,"","","","") );

        Room room2 = new Room(0, 0, 0, false, false,
                null, new Hotel(0,"","","",""));

        rooms.add(room1);
        rooms.add(room2);

        String convString = contentToWrite.toString();//это входящий объект


        String[] lines = convString.trim().split(",");
        int i = 0;

        for (int j = 0; j < rooms.size() - 1; j++) {

                    rooms.get(j).setId((Integer.parseInt(lines[i])));
            i++;

                    rooms.get(j).setNumbersOfGuests((Integer.parseInt(lines[i].trim())));
            i++;

                    rooms.get(j).setPrice(Double.parseDouble(lines[i].trim()));
            i++;

            //validate fild breakfastIncluded проверка значения false или true
            try {
                if (!lines[i].isEmpty() && lines[i].trim().equals("true")) {
                    rooms.get(j).setBreakfastIncluded(true);
                } else if (!lines[i].isEmpty() && lines[i].trim().equals("false")) {
                    rooms.get(j).setBreakfastIncluded(false);
                }
            } catch (Exception e) {
                System.err.println("Fild breakfastIncluded  contains invalid data");
            }
            i++;

            //validate fild petsAllowed проверка значения false или true
            try {
                if (!lines[i].isEmpty() && lines[i].trim().equals("true")) {
                    rooms.get(j).setPetsAllowed(true);
                } else if (!lines[i].isEmpty() && lines[i].trim().equals("false")) {
                    rooms.get(j).setPetsAllowed(false);
                }
            } catch (Exception e) {
                System.err.println("Fild petsAllowed contains invalid data");
            }
            i++;

                    rooms.get(j).setDateAvableFrom(lines[i].trim());
            i++;

                    rooms.get(j).getHotel().setId(Long.parseLong(lines[i].trim()));
            i++;

        }
        return rooms;
    }

    //Почему программа заменяет файлы в колекции АрейЛист HotelMap?
    private static Filter writeToFilter(StringBuilder contentToWrite) throws Exception {

        String convString = contentToWrite.toString();
        String[] lines = convString.trim().split(",");

        int i = 0;
        //for (int j = 0; j < 2; j++) {

        //validate fild numberOfGuests проверка на целые числа
        try {
            if (!lines[i].isEmpty()) {
                filter.setNumbersOfGuests(Integer.parseInt(lines[i]));
            }
        } catch (Exception e) {
            System.err.println("Fild numberOfGuest contains invalid data");
        }
        i++;

        //validate fild price проверка на целые числа и числа с плавающей точкой
        try {//"^[\\-?\\d+(\\.\\d{0,})?\n ]+$"
            if (!lines[i].isEmpty()) {
                filter.setPrice(Double.parseDouble(lines[i]));
            }
        } catch (Exception e) {
            System.err.println("Fild price contains invalid data");
        }
        i++;

        //validate fild breakfastIncluded проверка значения false или true
        try {
            if (!lines[i].isEmpty() && lines[i].trim().equals("true")) {
                filter.setBreakfastIncluded(true);
            } else if (!lines[i].isEmpty() && lines[i].trim().equals("false")) {
                filter.setBreakfastIncluded(false);
            }
        } catch (Exception e) {
            System.err.println("Fild breakfastIncluded  contains invalid data");
        }
        i++;

        //validate fild petsAllowed проверка значения false или true
        try {
            if (!lines[i].isEmpty() && lines[i].trim().equals("true")) {
                filter.setPetsAllowed(true);
            } else if (!lines[i].isEmpty() && lines[i].trim().equals("false")) {
                filter.setPetsAllowed(false);
            }
        } catch (Exception e) {
            System.err.println("Fild petsAllowed contains invalid data");
        }
        i++;

        //validate fild dateAvailableFrom проверка значения даты Дата в формате DD/MM/YYYY:/  HH:MM:SS !!!!!!!!!!!!!!!!!!!!
        try {
            if (!lines[i].isEmpty()) {

                filter.setDateAvableFrom(lines[i].trim());
            }
        } catch (Exception e) {
            System.err.println("Fild dateAvailableFrom contains invalid data");
        }
        i++;


        //validate fild country проверка на целые Английские буквы
        try {
            if (!lines[i].isEmpty() && lines[i].trim().matches("^[\\D]+$")) {
                filter.setCountry(lines[i].trim());
            }
        } catch (Exception e) {
            System.err.println("Fild numberOfGuest contains invalid data");
        }
        i++;

        //validate fild city проверка на целые Английские буквы    matches("^[a-zA-Z]+$")*/)
        try {//{
            if (!lines[i].isEmpty() && lines[i].trim().matches("^[\\D]+$")) {
                filter.setCity(lines[i].trim());
            }
        } catch (Exception e) {
            System.err.println("Fild city contains invalid data");
        }
        return filter;
    }
}
