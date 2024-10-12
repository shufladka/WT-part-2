package by.bsuir.entity;

public class Room {
    private int id;
    private String name;
    private int capacity;
    private int floor;
    private Double basicPrice;
    private Double weekendPrice;
    private String imagePath;
    private Hotel hotel;

    public Room(int id, String name, Integer capacity, Integer floor,
                Double basicPrice, Double weekendPrice, String imagePath, Hotel hotel) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.floor = floor;
        this.basicPrice = basicPrice;
        this.weekendPrice = weekendPrice;
        this.imagePath = imagePath;
        this.hotel = hotel;
    }

    public Room() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Double getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(Double basicPrice) {
        this.basicPrice = basicPrice;
    }

    public Double getWeekendPrice() {
        return weekendPrice;
    }

    public void setWeekendPrice(Double weekendPrice) {
        this.weekendPrice = weekendPrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", floor=" + floor +
                ", basicPrice=" + basicPrice +
                ", weekendPrice=" + weekendPrice +
                ", imagePath='" + imagePath +
                ", hotel=" + hotel +
                '}';
    }
}
