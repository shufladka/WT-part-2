package by.bsuir.domain;

public enum Role {
    USER("Пользователь"),
    ADMIN("Администратор");

    private final String russianRoleName;

    Role(String russianRoleName) {
        this.russianRoleName = russianRoleName;
    }

    public String getRussianRoleName() {
        return russianRoleName;
    }
}
