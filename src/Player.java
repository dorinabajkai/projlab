/**
 * a játékost megvalósító osztály
 */
public class Player {
    /**
     * A játékos pontszámát tárolja
     */
    private int score;

    /**
     * A játékos neve
     */
    private String name;

    /**
     * A játékos konstruktora, paraméterként megkapja a nevét
     * @param name a játékos neve
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Beállítja a játékos pontszámát
     * @param score a pontszám
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * A játékos nevének a lekérdezésére szolgál
     * @return a játékos neve
     */
    public String getName() {
        return name;
    }
}