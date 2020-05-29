package Project.repository;

import Project.model.Hotel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HotelRepository {

    //считываем данные,
    //преобразуем данные в объект
    // мапим в объект

    //находим гостиницу по имени
    //возвращаем объект

    public static ArrayList<Hotel> hotelsByName = new ArrayList<>();
    public static ArrayList<Hotel> hotelsByCity = new ArrayList<>();


    public static ArrayList<Hotel> findHotelByName(String name, String path) throws Exception {
          String flag = "n";
        hotelsByName = writeToFile(readFromFile(path), name, flag);

        System.out.println(hotelsByName);
        return hotelsByName;
    }

    public static ArrayList<Hotel> findHotelByCity(String city, String path) throws Exception {
        String flag = "s";

        hotelsByCity = writeToFile(readFromFile(path), city, flag);
        //System.out.println(hotelsByCity)
        return hotelsByCity;
    }


    public static Integer CreateId(Integer min, Integer max) {
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return i;
    }


    //тут нужно использовать мап иначе затирается предыдущий объект хостел
    private static StringBuffer readFromFile(String path) throws Exception {//

        StringBuffer res = new StringBuffer();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                res.append(line);//тут возможно не целый файл а записана какая то ерунда это нужно проверить
                res.append("\n");
            }
            //Уберем добавление пустой строки в последнем шаге цикла  while
            //метод позволяет в Java заменить символы в строке между указанными начальным и конечным индексами
            if (res.length() > 0){
                res.replace(res.length()-1, res.length(), "");
            }
        } catch (IOException e) {
            System.err.println("Reading from file " + path + " failed");
        }
        return res;
    }


    //Почему программа заменяет файлы в колекции АрейЛист HotelMap?
    private static ArrayList<Hotel> writeToFile(StringBuffer contentToWrite, String name, String flag) throws Exception {

        ArrayList<Hotel> hotels = new ArrayList<>();
        Hotel hotel1 = new Hotel(00, "", "", "", "");
        Hotel hotel2 = new Hotel(00, "", "", "", "");
        Hotel hotel3 = new Hotel(00, "", "", "", "");

        hotels.add(hotel1);
       hotels.add(hotel2);
        hotels.add(hotel3);

        ArrayList<Hotel> HotelMap = new ArrayList<>();

        String convString = contentToWrite.toString();
        String[] myList = convString.trim().split(",");
int i = 0;


for (int k = 0; k < 2  ; k++) {//у нас 2-ве записи в таблице значит у нас два пустых отеля

    // validate fild id проверка на целые числа типа long

            hotels.get(k).setId(Long.parseLong(myList[i].trim()));
    i++;

    // validate fild name  проверка на текстовое значение

            hotels.get(k).setName(myList[i]);
    i++;

    // validate fild country проверка на текстовое значение

            hotels.get(k).setCountry(myList[i]);
    i++;

    // validate fild city проверка на текстовое значение

            hotels.get(k).setCity(myList[i]);
    i++;


    // validate fild street проверка на текстовое значение

            hotels.get(k).setStreet(myList[i]);
    i++;
    //hotels.add(hotels.get(k));
}

        for (Hotel hotel : hotels) {
            if (findStringByCityOrName(hotel, name, flag)) {
                HotelMap.add(hotel);
            }
        }
        return HotelMap;

    }


    //делаем из стринга массив стрингов
    //каждый стринг преобразуем в нужное значение


    private static Boolean findStringByCityOrName(Hotel HotelMap, String name, String flag) throws Exception {

        if (flag.equals("s")){
            if (HotelMap.getCity().contains(name)) {
                return true;
            }
        }
        else  if (flag.equals("n")){
            if (HotelMap.getName().contains(name)) {
                return true;
            }
        }
        return false;
    }
}


