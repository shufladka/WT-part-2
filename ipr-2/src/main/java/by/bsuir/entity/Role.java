package by.bsuir.entity;

public enum Role {
    USER("Пользователь"),
    ADMIN("Администратор");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
