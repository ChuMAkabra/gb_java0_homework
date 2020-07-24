package Assignment_07;

public class Plate {
    private int food;

    public Plate(int food) {
        addFood(food);
    }

    public Plate() {
        this(0);
    }

    public void addFood(int food) {
        if (food >= 0) {
            this.food += food;
            System.out.println("Добавлено еды: " + food);
        }
        else System.out.println("Неужели вы хотите отобрать корм у котеек? " +
                "Кол-во корма останется неизменным");
    }

    public void decreaseFood(int foodCount) {
        this.food -= foodCount;
    }

    public int getFood() {
        return food;
    }

    public void info() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Plate{" +
                "food=" + food +
                '}';
    }
}