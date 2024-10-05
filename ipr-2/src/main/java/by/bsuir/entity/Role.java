package by.bsuir.entity;

public enum Role {
    USER("Пользователь"),
    ADMIN("Администратор");

    private String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
