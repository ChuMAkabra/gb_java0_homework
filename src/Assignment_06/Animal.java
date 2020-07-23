package Assignment_06;

import java.util.Random;

public abstract class Animal {

    protected float runLimit;
    protected float jumpLimit;
    protected String species;

    /**
     * Определение ограничения (высоты или дистанции) для данной особи
     * @param def   -базовое ограничение для данного вида животного
     * @param shift -разброс в ограничении для данного вида животного (+-shift)
     * @return      -ограничение для данной особи
     */
    protected float defineLimit (float def, float shift) {
        Random random = new Random ();
        return def + shift * (random.nextFloat() * 2.0F - 1); // def + shift * [-1;1)
    }

    public void run (float meters) {
        System.out.println(((meters > 0 && meters <= runLimit) ? "пробежал " : "не пробежал ") +
                meters + "м");
    }

    public void jump (float meters) {
        System.out.println(((meters > 0 && meters <= jumpLimit) ? "допрыгнул" : "не допрыгнул")  +
                " до " + meters + "м");
    }

    public String getSpecies() {
        return species;
    }

    public float getRunLimit() {
        return runLimit;
    }

    public float getJumpLimit() {
        return jumpLimit;
    }
}
