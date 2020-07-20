package Assignment_07;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Cat cat1 = new Cat("Мартин", 5);
        Cat cat2 = new Cat("СкоТсезе", 7);
        Plate plate = new Plate(6);

        plate.info(); printCat(cat1); printCat(cat2);
        Thread.sleep(2000); System.out.println();
        cat1.eat(plate); printCat(cat1); plate.info();
        Thread.sleep(2000); System.out.println();
        cat2.eat(plate); printCat(cat2); plate.info();
        Thread.sleep(2000); System.out.println();
        plate.addFood(6); plate.info();
        System.out.println();
        cat2.eat(plate); printCat(cat2); plate.info();
        System.out.println();
        plate.addFood(-11);
        plate.info();
    }

    public static void printCat(Cat cat) {
        System.out.printf("Кот %s. Аппетит: %d\n", cat.getName(), cat.getAppetite());
    }
}
