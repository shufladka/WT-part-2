package by.bsuir.mapper;

public final class Attributes {

    // Таблица `addresses`
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String REGION = "region";
    public static final String CITY = "city";
    public static final String STREET = "street";
    public static final String BUILDING = "building";
    public static final String ZIP = "zip";

    // Таблица `roles`
    // public static final String ID = "id";
    // public static final String NAME = "name";

    // Таблица `people`
    // public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String BIRTH_DATE = "birth_date";
    public static final String EMAIL = "email";
    public static final String ROLE_ID = "role_id";

    // Таблица `hotels`
    // public static final String ID = "id";
    // public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String ADDRESS_ID = "address_id";
    public static final String LEVEL = "level";
    public static final String AVAILABLE_TO_BOOK = "available_to_book";
    public static final String IMAGE_PATH = "image_path";

    // Таблица `rooms`
    // public static final String ID = "id";
    // public static final String NAME = "name";
    public static final String CAPACITY = "capacity";
    public static final String FLOOR = "floor";
    public static final String BASIC_PRICE = "basic_price";
    public static final String WEEKEND_PRICE = "weekend_price";
    // public static final String IMAGE_PATH = "image_path";
    public static final String HOTEL_ID = "hotel_id";
    public static final String IS_AVAILABLE = "is_available";

    // Таблица 'orders'
    public static final String PERSON_ID = "person_id";
    public static final String ROOM_ID = "room_id";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
    public static final String CLOSED_AT = "closed_at";
    public static final String STATUS = "status";
}
