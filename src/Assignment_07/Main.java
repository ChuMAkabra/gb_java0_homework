package Assignment_07;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Cat cat = new Cat("Мартин", 5);
        Plate plate = new Plate(5);

        plate.info();
        cat.eat(plate);
        Thread.sleep(3000);
        plate.info();
        plate.addFood(3);
        Thread.sleep(3000);
        plate.info();

    }
}
