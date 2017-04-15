import java.util.HashMap;

/**
 * a kereszteződés
 */
public class Cross extends BoardItem{

    /**
     * a kereszteződések azonosítójának generálását segíti
     */
    private static int idCounter = 0;

    /**
     * a szemköztiségről tárol információt
     */
    private HashMap<BoardItem,BoardItem> opposites;

    /**
     * a kereszteződése konstruktora
     */
    public Cross() {
        super(false);
        this.opposites = new HashMap<BoardItem,BoardItem>();
        this.idCounter++;
        id = "cr" + idCounter;
        System.out.println("-new boarditem " + id);
    }

    /**
     * a kereszteződés szomszoédjainak szemköztiségét állítja be
     * @param bi1 az egyik szemközti pályaelem
     * @param bi2 a másik szemközti pályaelem
     */
    public void setOpposite(BoardItem bi1, BoardItem bi2) {
        this.opposites.put(bi1,bi2);
        this.opposites.put(bi2,bi1);
    }

    /**
     * a szomszédok ellenőrzésével megadja a róla elérhető szomszédot, mint következő lépési helyet
     * @return a következő lépési hely
     */
    @Override
    public BoardItem getNextStep() {
        for (BoardItem neighbour  : this.neighbours) {
            if (neighbour.isAvailable() == false) {
                BoardItem opposite = this.opposites.get(neighbour);
                if (opposite.isAvailable()) {
                    return opposite;
                }
                else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * kereszteződések azonosítószámának visszaállítása
     */
    public static void resetIDCounter() {
        idCounter = 0;
    }
}
