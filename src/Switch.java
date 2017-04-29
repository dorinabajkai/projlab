import java.util.*;

/**
 * a váltó
 */
public class Switch extends BoardItem implements IClickEvent{

    /**
     * a váltük azonosítójának generálást segíti elő
     */
    private static int idCounter = 0;

    /**
     * A váltó kimenetei
     */
    private ArrayList<BoardItem> outputs;

    /**
     * Az éppen aktív kimenet
     */
    private BoardItem current;

    /**
     * A váltó konstruktora, mely paraméterként megkapja, hogy virtuális-e
     * @param virtual a virtuálisságot jelző attribútum
     */
    public Switch(boolean virtual) {
        super(virtual);
        this.outputs = new ArrayList<BoardItem>();
        this.idCounter++;
        id = "sw" + idCounter;
        System.out.println("-new boarditem " + id);
    }

    /**
     * szomszédok, kimenetek hozzáadása
     * @param bi a szomszédnak beállítandó pályaelem
     */
    @Override
    public void addNeighbour(BoardItem bi) {
        super.addNeighbour(bi);
        if(neighbours.size() > 1) {
            outputs.add(bi);
            if (outputs.size() == 1) {
                this.current = bi;
            }
        }
    }

    /**
     * a szomszédok ellenőrzésével megadja a róla elérhető szomszédot, mint következő lépési helyet
     * @return a következő lépési hely
     */
    @Override
    public BoardItem getNextStep() {
        if(current.isAvailable() == true) {
            return current;
        }
        else {
            return null;
        }
    }

    /**
     *A váltó kimenetének megváltoztatására szolgál
     */
    public void changeSwitchOutput() {
        if (this.outputs.size() == 2) {
            if (this.current.equals(this.outputs.get(0))) {
                this.current = this.outputs.get(1);
            }
            else {
                this.current = this.outputs.get(0);
            }
            System.out.println("-" + this.id + " new output " + this.current.getID());
        }
    }

    /**
     * információt szolgáltat a váltóról
     * @return a váltóval kapcsolatos információk
     */
    public String getInfo() {
        return "current output " + this.current.getID();
    }

    /**
     * vonatelemek azonosítószámának visszaállítása
     */
    public static void resetIDCounter() {
        idCounter = 0;
    }

    //TODO
    @Override
    public void action(){

    }

}