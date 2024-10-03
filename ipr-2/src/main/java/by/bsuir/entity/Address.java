package by.bsuir.entity;

public class Address {
    private String region;
    private String city;
    private String street;
    private String building;
    private String zip;

    public Address(String region, String city, String street, String building, String zip) {
        this.region = region;
        this.city = city;
        this.street = street;
        this.building = building;
        this.zip = zip;
    }

    public Address() {}

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
