package Assignment_07;

public class Plate {
    private int food;
    private final int volume;
    private boolean isFull = false;

    public Plate(int volume) {
        this.volume=volume; // не стал проверять на положительные числа (+ по дефолту тарелка пуста)
    }

    /**
     * Метод добавления еды в миску.
     * @param food кол-во еды, которое хотим добавить
     * @return кол-во еды, не попавшее в миску
     */
    public int addFood(int food) {
        int leftToFill = volume - this.food; // сколько осталось до наполнения миски
        if (food >= 0) {
            int toBeAdded = Math.min(leftToFill, food);
            this.food += toBeAdded;
            if (this.food == this.volume) isFull = true;
            System.out.println("Добавлено еды: " + toBeAdded);
            return food - toBeAdded;
        }
        else {
            System.out.println("Неужели вы хотите отобрать корм у котеек? " +
                    "Кол-во корма останется неизменным");
            return food;
        }
    }

    public void decreaseFood(int foodCount) {
        this.food -= foodCount; // не стал добавлять проверок на положительные foodCount
        isFull = false;
    }

    public boolean isFull() {
        return isFull;
    }

    public int getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Тарелка с едой " + food + "/" + volume;
    }
}