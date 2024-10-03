package by.bsuir.entity;

import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private String description;
    private Address address;
    private Double rating;
    private List<Room> roomList;
    private boolean availableToBook;
    private String imagePath;

    public Hotel(int id, String name, String description,
                 Address address, Double rating, List<Room> roomList,
                 boolean availableToBook, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.roomList = roomList;
        this.availableToBook = availableToBook;
        this.imagePath = imagePath;
    }

    public Hotel() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public boolean isAvailableToBook() {
        return availableToBook;
    }

    public void setAvailableToBook(boolean availableToBook) {
        this.availableToBook = availableToBook;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address=" + address +
                ", rating=" + rating +
                ", roomList=" + roomList +
                ", availableToBook=" + availableToBook +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
