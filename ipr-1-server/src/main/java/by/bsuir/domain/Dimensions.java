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
     * Пустой конструктор класса "Габаритные размеры"
     */
    public Dimensions() {
    }

    /**
     * Метод для задания ширины
     * @param width Ширина
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    /**
     * Метод для задания высоты
     * @param height Высота
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * Метод для задания глубины
     * @param depth Глубина
     */
    public void setDepth(Double depth) {
        this.depth = depth;
    }

    /**
     * Метод для задания веса
     * @param weight вес
     */
    public void setWeight(Double weight) {
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
