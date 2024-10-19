package by.bsuir.domain;

/**
 * Перечисление "Роль"
 */
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
