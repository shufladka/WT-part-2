package by.bsuir.domain;

public enum OperationType {
    CREATION("Создание"),
    UPDATE("Обновление");

    private String name;

    OperationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
