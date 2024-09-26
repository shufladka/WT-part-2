package by.bsuir.domain;

/** Габаритные размеры */
public class Dimensions {
    private Double width;
    private Double height;
    private Double depth;
    private int weight;

    public Dimensions(Double width, Double height, Double depth, int weight) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.weight = weight;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

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
