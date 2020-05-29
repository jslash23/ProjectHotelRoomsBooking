package Project.service;

import Project.model.Filter;
import Project.model.Room;
import Project.repository.RoomRepository;

import java.util.ArrayList;

public class RoomService {
    private RoomRepository roomRepository = new RoomRepository();

    public  ArrayList<Room> findRooms (Filter filter, String rooms, String reqRooms) throws Exception{
   //if ((validateCity(filter)))
        return RoomRepository.findRooms(filter, rooms, reqRooms);
        //throw  new Exception("can't find you city or room!");
    }

private boolean     validateCity (Filter filter) throws Exception {
        //Если поле пустое или не содержит буквы и цифры то кидаем  Эксепшн
        if (filter.getCity().isEmpty() || filter.getCity()== null ||
                !filter.getCity().matches("^[а-яА-ЯёЁa-zA-Z0-9]+$")){
            throw  new Exception("field city contains invalid data");
    }
    return true;
}
}
