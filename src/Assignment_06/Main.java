package Assignment_06;

public class Main {

    public static void main(String[] args) {
        runJumpSwim();
    }

    public static void runJumpSwim() {
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Animal[] animals = new Animal[] {dog1, dog2, cat1, cat2};

        for (Animal animal: animals) {
            System.out.printf("%s с лимитом в %.2fм\n", animal.getSpecies(), animal.getRunLimit());
            animal.run(500);
            animal.run(200);
            System.out.printf("%s с лимитом в %.2fм\n", animal.getSpecies(), animal.getJumpLimit());
            animal.jump(0.5F);
            animal.jump(2.01F);
        }

        // поскольку плавать умеют только собаки, выполним проверку отдельно

        Dog[] dogs = new Dog[] {dog1, dog2};
        for (Dog dog : dogs) {
            System.out.printf("%s с лимитом в %.2fм\n", dog.getSpecies(), dog.getSwimLimit());
            dog.swim(10);
            dog.swim(11);
        }
    }
}
