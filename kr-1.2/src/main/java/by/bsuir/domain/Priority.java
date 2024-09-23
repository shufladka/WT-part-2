package by.bsuir.domain;

public enum Priority {
    HIGH("Высокий"),
    MEDIUM("Средний"),
    LOW("Низкий");

    private String name;

    Priority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
