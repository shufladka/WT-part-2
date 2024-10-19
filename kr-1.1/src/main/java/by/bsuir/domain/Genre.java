package by.bsuir.domain;

/**
 * Перечисление "Жанр"
 */
public enum Genre {
    DETECTIVE("Детектив"),
    ROMANCE("Роман"),
    SCIENCE_FICTION("Научная фантастика"),
    FANTASY("Фэнтези"),
    COMEDY("Комедия"),
    HISTORICAL("Историческая");

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
