package by.bsuir.entity;

public class Hotel {
    private int id;
    private String name;
    private String description;
    private int addressId;
    private int level;
    private boolean availableToBook;
    private String imagePath;

    public Hotel(int id, String name, String description,
                 int addressId, int level, boolean availableToBook, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.addressId = addressId;
        this.level = level;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
                ", addressId=" + addressId +
                ", rating=" + level +
                ", availableToBook=" + availableToBook +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
