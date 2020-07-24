package Assignment_07;

public class Cat {

    protected final String name;
    private int satiety;
    private final int maxSatiety;
    private boolean hungry = true;

    public Cat(String name, int maxSatiety) {
        this.name = name;
        this.maxSatiety = maxSatiety;
    }

    @Override
    public String toString() {
        return "Кот " +
                "по имени " + name +
                ", сыт на " + satiety +
                "/" + maxSatiety +
                ", " + ((!hungry) ? "не " : "") + "голоден";
    }

    public boolean isHungry() {
        return hungry;
    }

    public void eat (Plate plate) {
        int foodLeft = plate.getFood();
        int hunger = maxSatiety - satiety;
        if (foodLeft >= hunger) {
            plate.decreaseFood(hunger);
            satiety = maxSatiety;
            hungry = false;
        }
        else if (foodLeft > 0) {
            plate.decreaseFood(foodLeft);
            satiety += foodLeft;
        }
    }
}