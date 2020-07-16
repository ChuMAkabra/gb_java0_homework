package Assignment_06;

public class Dog extends Animal implements SwimmingAnimals {
    private float swimLimit;

    public Dog () {
        this.species = "Пёс";
        this.runLimit  = defineLimit(500F, 100F);
        this.jumpLimit = defineLimit(0.5F, 0.2F);
        this.swimLimit = defineLimit(10F, 5F);
    }

    @Override
    public void swim(float meters) {
        System.out.println(((meters > 0 && meters <= swimLimit) ? "проплыл " : "не проплыл ") +
                        meters + "м");
    }

    public float getSwimLimit() {
        return swimLimit;
    }

}
