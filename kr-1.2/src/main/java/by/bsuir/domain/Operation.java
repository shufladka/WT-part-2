package by.bsuir.domain;

/**
 * Перечисления "Операция"
 */
public enum Operation {
    LOADING("Загрузка"),
    UNLOADING("Разгрузка");

    private String name;

    Operation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
