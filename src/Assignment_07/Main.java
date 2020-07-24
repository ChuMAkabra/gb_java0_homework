package Assignment_07;

public class Main {

    private static FoodObserver obs;

    public static void main(String[] args) {
        initiateVars();
        feedCats();
    }

    public static void initiateVars() {
        obs = new FoodObserver();
        Cat cat1 = new Cat("Мартин", 5);
        Cat cat2 = new Cat("СкоТсезе", 7);
        Cat cat3 = new Cat("Жиробасик", 20);
        Cat[] cats = new Cat[] {cat1, cat2, cat3};

        Plate plate1 = new Plate(5);
        Plate plate2 = new Plate(10);
        Plate plate3 = new Plate(15);
        Plate[] plates = new Plate[] {plate1, plate2, plate3};

        obs.addCat(cats);
        obs.addPlate(plates);
    }

    public static void feedCats() {
        obs.fillPlates(31);
        reportInfo();
        obs.notifyCats();
        reportInfo();
        obs.fillPlates(2);
        obs.notifyCats();
        reportInfo();
    }

    public static void reportInfo() {
        System.out.println();
        obs.infoCats();
        System.out.println();
        obs.infoPlates();
        System.out.println();
    }
}
