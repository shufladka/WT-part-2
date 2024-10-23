package by.bsuir.domain;

/**
 * Перечисления "Операция"
 */
public enum Operation {

    /**
     * Тип операции "Загрузка"
     */
    LOADING("LOADING", "LOADED"),

    /**
     * Тип операции "Разгрузка"
     */
    UNLOADING("UNLOADING", "UNLOADED"),;

    /**
     * Имя
     */
    private String name;

    /**
     * Имя завершения операции
     */
    private String finishName;

    /**
     * Конструктор перечисления "Операция"
     * @param name Имя операции
     * @param finishName Имя завершения операции
     */
    Operation(String name, String finishName) {
        this.name = name;
        this.finishName = finishName;
    }

    /**
     * Метод для получения имени операции
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для получения имени завершения операции
     * @return String
     */
    public String getFinishName() {
        return finishName;
    }
}
