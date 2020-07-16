package Assignment_06;

public class Main {
    public static void main(String[] args) {
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
        dog1.swim(10);
        dog2.swim(11);
    }
}
