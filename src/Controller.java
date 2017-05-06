import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * a vezérlés megvalósítása
 */
public class Controller implements Runnable{
    /**
     * a következő vonat létrehozásáig hátralévő idő
     */
    private int timeCounter;

    /**
     * a pályán még létrejövendő vonatok száma
     */
    private int trainCounter;

    /**
     * a pálya referenciája
     */
    private Board board;

    /**
     * szintektől függően tárolja, hogy milyen időközönként jönnek létre vonatok
     */
    private int createTrainFrequency;

    /**
     * az összesen létrejövő vonatok száma
     */
    private int maxTrains;

    /**
     * @param b a board tagváltozó értéke
     */
    public Controller(Board b) {
        this.board = b;
        this.createTrainFrequency = 30;
        this.maxTrains = 5;
        this.trainCounter = this.maxTrains;
        this.timeCounter = this.createTrainFrequency;
    }

    /**
     * a szál indításakor meghívandó függvény
     */
    public void start(){}

    /**
     * A szál futásakor ismétlődően meghívódó függvény.
     * Meghívja a Board léptető függvényét, valamint ha a timeCounter 0-hoz ér, és a trainCounter még nem érte el a
     * 0-t, akkor a timeCountert visszaállítja a createTrainFrequency értékére, a trainCountert csökkenti 1-gyel,
     * és a Board-dal létrehozat egy új vonatot
     */
    public void run() {
        if (timeCounter!=0) {
            timeCounter--;
        }
        else {
            if (trainCounter>0) {
                timeCounter = createTrainFrequency;
                trainCounter--;
                //board.createTrain();
            }
        }
        if (!board.step()) {
            gameOver();
        }
    }

    /**
     * a játék végét menedzseli
     */
    private void gameOver() {}
}