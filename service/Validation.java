package Project.service;

import Project.model.Room;

import java.util.List;


public interface Validation  {

    boolean validate(String userName, String country, boolean RoomsReserved);
}
