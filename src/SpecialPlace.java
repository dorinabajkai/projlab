
import java.util.*;

/**
 * a különleges helyeket megvalósító osztály
 */
public class SpecialPlace extends BoardItem implements IRemoveNeighbour, IClickEvent {

    /**
     * különleges helyek azonosítójának generálását segíti elő
     */
    private static int idCounter = 0;

    /**
     * jelzi, hogy része-e felépített alagútnak
     * true - része alagútnak
     * false - nem része alagútnak
     */
    private boolean tunnelExists;

    /**
     * a különleges hely után következő pályaelem, amennyiben a különleges hely része felépített alagútnak
     */
    private BoardItem output;

    /**
     * különleges hely konstruktora, mely paraméterként megkapja, hogy virtuális-e
     * generálja a különleges hely kimenetét
     * @param virtual a virtuálisságot jelző attribútum
     */
    public SpecialPlace(boolean virtual) {
        super(virtual);
        this.idCounter++;
        id = "sp" + idCounter;
        System.out.println("-new boarditem " + id);
    }

    /**
     * a szomszédok ellenőrzésével megadja a róla elérhető szomszédot, mint következő lépési helyet
     * @return a következő lépési hely
     */
    @Override
    public BoardItem getNextStep() {
        if(tunnelExists == false) {
            for (BoardItem neighbour: neighbours) {
                if (neighbour.isAvailable()) {
                    return neighbour;
                }
            }
        }
        else {
            if (content.getInTunnel() == false) {
                for (BoardItem neighbour: neighbours) {
                    if (neighbour.isVirtual() == true && neighbour.isAvailable() == true) {
                        content.setInTunnel(true);
                        content.setVisibility(false);
                        return neighbour;
                    }
                }
            }
            else {
                if (output.isAvailable() == true){
                    content.setInTunnel(false);
                    return output;
                }
            }
        }
        return null;
    }

    /**
     * felépített alagút részének jelzésére szolgáló attribútum beállítása
     * true - része felépített alagútnak
     * false - nem része felépített alagútnak
     * @param b az érték, mely jelzi, hogy a különleges hely része-e felépített alagútnak
     */
    public void setTunnelExists(boolean b) {
        this.tunnelExists = b;
    }

    /**
     * különleges hely kimenetének generálása
     */
    @Override
    public void addNeighbour(BoardItem bi) {
        super.addNeighbour(bi);
        if (neighbours.size() == 1) {
            this.output = bi;
        }
    }

    /**
     * eltávolítja a paraméterként kapott pályaelemet a szomszédai közül
     * @param bi az eltávolítandó pályaelem
     */
    @Override
    public void removeNeighbour(BoardItem bi) {
        neighbours.remove(bi);
    }

    /**
     * a különleges helyen alagút létezését jelzi
     * @return true étezik alagút
     * @return false nem létezik alagút
     */
    public boolean getTunnelExists() {
        return this.tunnelExists;
    }

    /**
     * információt szolgáltat a különleges helyről
     * @return a különleges hellyel kapcsolatos információk
     */
    public String getInfo() {
        return "output " + this.output.getID();
    }

    /**
     * különleges helyek azonosítószámának visszaállítása
     */
    public static void resetIDCounter() {
        idCounter = 0;
    }

    @Override
    //TODO
    public void action(){

    }
}