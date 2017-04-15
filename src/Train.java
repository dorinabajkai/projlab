import java.util.*;
import java.util.Random;
/**
 * a vonat, mely tárolja a mozdonyt, kocsikat, szeneskocsikat
 */
public class Train extends Empty {

    /**
     * a vonatelemek azonosítójának generálást segíti elő
     */
    private static int idCounter = 0;

    /**
     * a vonatelemhez tartozó azonosító
     */
    private String id = "";

    /**
     * megadja, hogy a vonat alagútban van vagy nincs
     * true -  alagútban van
     * false - nincs alagútban
     */
    private boolean inTunnel = false;

    /**
     * A vonat mozdonyát, és kocsijait tároló lista
     */
    private ArrayList<TrainItem> trainItems;

    /**
     * @param bi a mozdony és a kocsik létrehozása véletlen színnel ellátva, és a mozdony elhelyezése a paraméterként
     *           átadott BoardItemen
     */
    public Train(BoardItem bi, ArrayList<TrainItem> trainItems) {
        trainItems.add(0,new TrainItem("",false));
        this.trainItems = trainItems;

        for (TrainItem ti: trainItems) {
            ti.setTrain(this);
        }

        this.trainItems.get(0).setBoardItem(bi);
        bi.setContent(this.trainItems.get(0));
        bi.setAvailable(false);

        TrainItem.resetIDCounter();
        this.idCounter++;
        this.id = "t" + this.idCounter;
        System.out.println("-new train " + id);
    }

    /**
     * @return lépteti a mozdonyt, majd utánalépteti a kocsikat. Ha a mozdony nem tud hova lépni, akkor ütközés történt,
     * emiatt false-szal tér vissza, egyébként true-val.
     * Léptetés közben ellenőrzi a kocsik isDestroyable változóját, és ha szükséges, eltávolítja az adott kocsit a
     * trainItems listából.
     */
    public boolean step() {
        if(!isEmpty){
            BoardItem previousBoardItem = trainItems.get(0).getBoardItem();
            BoardItem newBoardItem = previousBoardItem.getNextStep();
            if(newBoardItem == null)
                return false;
            trainItems.get(0).setBoardItem(newBoardItem);
            newBoardItem.setContent(trainItems.get(0));
            newBoardItem.setAvailable(false);

            for(int i=1;i<trainItems.size();i++) {
                    if(previousBoardItem!=null) {
                        BoardItem newItem = previousBoardItem;
                        previousBoardItem = trainItems.get(i).getBoardItem();
                        trainItems.get(i).setBoardItem(newItem);
                        newItem.setContent(trainItems.get(i));
                    }
            }
            if(previousBoardItem!=null)
                previousBoardItem.setAvailable(true);
        } else {
            System.out.println("-train disappeared " + this.id);
        }
        if (!isEmpty) {
            System.out.println("-" + this.id);
            System.out.println("\te on " + this.trainItems.get(0).getPlaceID());
            for (int i = 1; i < trainItems.size(); ++i) {
                if (trainItems.get(i).getInfo().length() > 0) {
                    System.out.println("\t" + trainItems.get(i).getID() + " " + trainItems.get(i).getInfo());
                }
            }
        }

        int numberOfEmptyWagons = 0;
        for (TrainItem ti: trainItems) {
            if (ti.isEmpty() == true || ti.getColor().equals("grey") || ti.getColor().equals("")) {
                numberOfEmptyWagons++;
            }
        }

        if (numberOfEmptyWagons == trainItems.size()) {
            setEmpty(true);
        }

        if(this.inTunnel == false) {
            for (TrainItem ti: trainItems) {
                if (ti.getInTunnel() == true) {
                    this.inTunnel = true;
                    System.out.println("-" + this.id + " tunnel in");
                    break;
                }
            }
        }
        else {
            int numberOfTrainItemsInTunnel = 0;
            for (TrainItem ti: trainItems) {
                if (ti.getInTunnel() == true) {
                    numberOfTrainItemsInTunnel++;
                }
            }
            if (numberOfTrainItemsInTunnel == 0) {
                this.inTunnel = false;
                System.out.println("-" + this.id + " tunnel out");
            }
        }

        return true;
    }

    /**
     * @param ti TrainItem, amiről tudni szeretnénk, hogy az első kocsi-e
     * @return logikai érték, ami megadja, hogy a paraméterként átadott TrainItem az első kocsija-e a vonatnak
     */
    public boolean isFirstNotEmptyWagon(TrainItem ti) {
        int indexOfFirstNotEmptyWagon = -1;
        int indexOfTrainItem = trainItems.indexOf(ti);
        for (int i = 1; i <trainItems.size(); ++i) {
            if (trainItems.get(i).isEmpty() == false && trainItems.get(i).getColor().equals("grey") == false) {
                indexOfFirstNotEmptyWagon = i;
                break;
            }
        }
        return indexOfFirstNotEmptyWagon == indexOfTrainItem;
    }

    /**
     * a vonat azonosítóját adja meg
     * @return a vonat azonosítója
     */
    public String getID(){
        return this.id;
    }

    /**
     * vonatok azonosítószámának visszaállítása
     */
    public static void resetIDCounter() {
        idCounter = 0;
    }
}