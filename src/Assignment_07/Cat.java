package Assignment_07;

public class Cat {

    protected final String name;
    private int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void eat (Plate plate) {
        int foodLeft = plate.getFood();
        if (foodLeft >= appetite) {
            plate.decreaseFood(appetite);
            satiety = true;
            appetite = 0;
        }
        else if (foodLeft > 0) {
            plate.decreaseFood(plate.getFood());
            appetite -= foodLeft;
        }
    }
}