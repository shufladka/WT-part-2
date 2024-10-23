package by.bsuir.domain;

/**
 * Перечисления "Операция"
 */
public enum Operation {

    /**
     * Тип операции "Загрузка"
     */
    LOADING("Загрузка"),

    /**
     * Тип операции "Разгрузка"
     */
    UNLOADING("Разгрузка");

    /**
     * Имя
     */
    private String name;

    /**
     * Конструктор перечисления "Операция"
     * @param name Имя операции
     */
    Operation(String name) {
        this.name = name;
    }

    /**
     * Метод для получения имени операции
     * @return String
     */
    public String getName() {
        return name;
    }
}
