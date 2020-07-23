package Assignment_06;

/**
 * Что лучше?
 * 1) Статическое поле animals и createAnimals(); runJumpSwim()?
 * 2) локальная переменная animals и Animal[] animals= createAnimals(); runJumpSwim(animals)?
 */
public class Main {
    private static Animal[] animals;

    public static void main(String[] args) {
        createAnimals();
        runJumpSwim();
    }

    public static void createAnimals() {
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        animals = new Animal[] {dog1, dog2, cat1, cat2};
    }

    public static void runJumpSwim() {

        for (Animal animal: animals) {
            System.out.printf("%s с лимитом в %.2fм\n", animal.getSpecies(), animal.getRunLimit());
            animal.run(500);
            animal.run(200);
            System.out.printf("%s с лимитом в %.2fм\n", animal.getSpecies(), animal.getJumpLimit());
            animal.jump(0.5F);
            animal.jump(2.01F);

            // поскольку плавать умеют только собаки, выполним проверку
            if (animal instanceof Dog) {
                // решил попробовать вот такие приведения типов на практике
                System.out.printf("%s с лимитом в %.2fм\n",
                        animal.getSpecies(), ((Dog) animal).getSwimLimit());
                ((Dog)animal).swim(10);
                ((Dog)animal).swim(11);
            }
        }
    }
}
