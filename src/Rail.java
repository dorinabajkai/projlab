
import java.util.*;

/**
 * a sín
 */
public class Rail extends BoardItem implements IRemoveNeighbour {

    /**
     * a sínek azonosítójának generálást segíti elő
     */
    private static int idCounter = 0;

    /**
     * a virtuális sínek generálást segíti elő
     */
    private static int virtualIdCounter = 0;

    /**
     * @param virtual létrehozza a sínelemet, és az átadott virtual paraméter értékének megfelelően állítja
     *                be az isVIrtual változót
     */
    public Rail(boolean virtual) {
        super(virtual);
        if (virtual) {
            this.virtualIdCounter++;
            id = "vr" + virtualIdCounter;
        }
        else {
            this.idCounter++;
            id = "r" + idCounter;
            System.out.println("-new boarditem " + id);
        }

    }

    /**
     * @return visszaadja a következő elérhető BoardItem-et, ha nem talál iylet, nullal tér vissza
     */
    @Override
    public BoardItem getNextStep() {
        for(BoardItem bi: neighbours){
            if(bi.isAvailable())
                return bi;
        }
        return null;
    }

    /**
     * visszaadja, hogy az adott sínelem virtuális-e
     * @return true virtuális
     * @return false nem virtuális
     */
    public boolean isVirtual() {
        return isVirtual;
    }

    /**
     * a szomszédsági listából eltávolítja az átadott pályaelemet
     * @param bi az eltávolítandó pályaelem
     */
    public void removeNeighbour(BoardItem bi) {
        neighbours.remove(bi);
    }

    /**
     * sínek, virtuális sínek azonosítószámának visszaállítása
     */
    public static void resetIDCounter() {
        idCounter = 0;
        virtualIdCounter = 0;
    }
}