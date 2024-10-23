package by.bsuir.domain;

/**
 * Класс "Габаритные размеры"
 */
public class Dimensions {

    /**
     * Ширина
     */
    private Double width;

    /**
     * Высота
     */
    private Double height;

    /**
     * Глубина
     */
    private Double depth;

    /**
     * Вес
     */
    private Double weight;

    /**
     * Конструктор класса "Габаритные размеры"
     * @param width Ширина
     * @param height Высота
     * @param depth Глубина
     * @param weight Вес
     */
    public Dimensions(Double width, Double height, Double depth, Double weight) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.weight = weight;
    }

    /**
     * Метод для вывода информации о габаритных размерах в консоль
     * @return String
     */
    @Override
    public String toString() {
        return "Dimensions{" +
                "width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", weight=" + weight +
                '}';
    }
}
