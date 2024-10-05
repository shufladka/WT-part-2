package by.bsuir.mapper;

import by.bsuir.entity.*;
import by.bsuir.mapper.impl.*;

public class RecordMapperSingleton {
    private static RecordMapperSingleton INSTANCE;
    private final RecordMapper<Address> addressRecordMapper = new AddressRecordMapperImpl();
    private final RecordMapper<Hotel> hotelRecordMapper = new HotelRecordMapperImpl();
    private final RecordMapper<Person> personRecordMapper = new PersonRecordMapperImpl();
    private final RecordMapper<Role> roleRecordMapper = new RoleRecordMapperImpl();
    private final RecordMapper<Room> roomRecordMapper = new RoomRecordMapperImpl();

    public static RecordMapperSingleton getInstance()
    {
        if (INSTANCE == null) {
            synchronized (RecordMapperSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RecordMapperSingleton();
                }
            }
        }

        return INSTANCE;
    }

    public RecordMapper<Address> getAddressRecordMapper() {
        return addressRecordMapper;
    }

    public RecordMapper<Hotel> getHotelRecordMapper() {
        return hotelRecordMapper;
    }

    public RecordMapper<Person> getPersonRecordMapper() {
        return personRecordMapper;
    }

    public RecordMapper<Role> getRoleRecordMapper() {
        return roleRecordMapper;
    }

    public RecordMapper<Room> getRoomRecordMapper() {
        return roomRecordMapper;
    }
}
