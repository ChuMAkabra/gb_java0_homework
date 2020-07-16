package Assignment_06;

public class Cat extends Animal {

    public Cat() {
        this.species = "Кот";
        this.runLimit  = defineLimit(200, 50F);
        this.jumpLimit = defineLimit(2F, 0.5F);
    }
}
