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
        if (plate.getFood() >= appetite) {
            satiety = true;
            doEat(plate);
        }
        else {

        }

    }

    public void doEat(Plate plate) {
        plate.decreaseFood(appetite);
    }
}