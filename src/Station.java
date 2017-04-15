/**
 * az állomás
 */
public class Station extends BoardItem {

    /**
     * az állomások azonosítójának generálását segíti elő
     */
    private static int idCounter = 0;

    /**
     *  az állomás színe
     */
    private String color;

    /**
     * az állomáson levő utasok létezését jelzi
     */
    private boolean passengers;

    /** konstruktor
     * @param virtual jelzi, hogy virtuális-e
     * @param color az állomás színét reprezentálja
     * @param passengers az utasok létezését jelzi
     */
    public Station(boolean virtual, String color, boolean passengers) {
        super(virtual);
        this.color = color;
        this.passengers = passengers;
        this.idCounter++;
        id = "st" + idCounter;
        System.out.println("-new boarditem " + id);
    }

    /** a szomszédok ellenőrzésével megadja a róla elérhető szomszédot, mint következő lépési helyet
     * @return a következő lépési hely
     */
    @Override
    public BoardItem getNextStep() {

        for(BoardItem b: neighbours)
        {
            if(b.isAvailable() == true)
                return b;
        }

        return null;
    }

    /**
     * állomáson levő vonatelem beállítása
     * @param ti a beállítandó vonatelem
     */
    @Override
    public void setContent(TrainItem ti){
        this.content = ti;
        ti.checkColor(this.color);
    }

    /**
     * az utazok létezését jelző változó beállítása
     * @param b a passangers változó értéke
     */
    public void setPassengers(boolean b) {
        this.passengers = b;
    }

    /**
     * az állomáson levő utasok létezését adja meg
     * @return
     * true - vannak utasok az állomáson
     * false - nincsenek utasok az állomáson
     */
    public boolean getPassengers(){
        return this.passengers;
    }

    /**
     * állomások azonosítószámának visszaállítása
     */
    public static void resetIDCounter() {
        idCounter = 0;
    }
}