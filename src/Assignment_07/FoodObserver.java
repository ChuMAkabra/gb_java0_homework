package Assignment_07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class FoodObserver {
    private ArrayList <Cat> hungryCats = new ArrayList<>();
    private ArrayList <Plate> plates = new ArrayList<>();
    private int foodLeft;

    // котов и мисок можно добавлять как по одиночке, таки массивом
    public void addCat(Cat cat) {
        hungryCats.add(cat);
    }

    public void addCat(Cat[] cats) {
        Collections.addAll(hungryCats, cats);
    }

    public void addPlate(Plate plate) {
        plates.add(plate);
    }

    public void addPlate(Plate[] plates) {
        Collections.addAll(this.plates, plates);
    }

    public void fillPlates(int food) {
        int notAdded = food;
        for (Plate plate : plates) {
            if (notAdded == 0) break;
            if (plate.isFull()) continue;
            notAdded = plate.addFood(notAdded);
        }
        if (notAdded > 0) System.out.println("Миски полны. В пакетике осталось еды: " + notAdded);
        foodLeft += food - notAdded;
    }

    public void notifyCats() {
        while (foodLeft > 0 && hungryCats.size() > 0) {
            // изучил, каким образом можно без проблем удалять котов из списка
            for (Iterator<Cat> iterator = hungryCats.iterator(); iterator.hasNext();) {
                Cat cat = iterator.next();
                for (Plate plate : plates) {
                    if (plate.getFood() == 0) continue;
                    cat.eat(plate);
                    calculateFoodLeft();
                    if (!cat.isHungry()) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
    }

    private void calculateFoodLeft() {
        int sumFood = 0;
        for (Plate plate : plates) {
            sumFood += plate.getFood();
        }
        // лучше создавать для этого доп.переменную? Или перезаписывать саму foodLeft в цикле?
        foodLeft = sumFood;
    }

    public int getFoodLeft() {
        return foodLeft;
    }

    public void infoPlates() {
        for (Plate plate : plates) {
            System.out.println(plate.toString());
        }
        System.out.printf("В мисках осталось еды: %d\n", getFoodLeft());
    }

    public void infoCats() {
        for (Cat cat: hungryCats) {
            System.out.println(cat.toString());
        }
        System.out.printf("Голодных котов осталось: %d\n", hungryCats.size());
    }
}
