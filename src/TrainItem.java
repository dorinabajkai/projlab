
import javafx.geometry.Pos;

import java.util.*;

/**
 * vonatelemet-mozdonyt és kocsit-megvalósító osztály
 */
public class TrainItem extends Empty {
    /**
     * a vonatelemek azonosítójának generálást segíti elő
     */
    private static int idCounter = 0;

    /**
     * a vonatelemhez tartozó azonosító
     */
    private String id = "";

    /**
     * a vonatelemhez tartozó aktuális pályaelem, amelyiken éppen áll
     */
    private BoardItem place;

    /**
     * a vonatelem színe
     * kocsi esetén egy valódi szín
     * mozdony esetén null
     */
    private String color;

    /**
     * a vonat, melynek a vonatelem része
     */
    private Train train;

    /**
     * megadja, hogy a vonatelem alagútban van vagy nincs
     * true -  alagútban van
     * false - nincs alagútban
     */
    private boolean inTunnel;

    /**
     * vonatelem láthatóságát jelzi
     * true -  a vonatelem látható
     * false -  a vonatelem nem látható
     */
    private boolean visibility;

    private Position position;


    /**
     * vonatelem konstruktora, létrehozza a vonatelemet a paraméterként kapott színnel
     * @param color a vonatelemhez tartozó szín
     * @param isEmpty a vonatelem üressége
     */

    public TrainItem(String color, boolean isEmpty) {
        this.color = color;
        this.isEmpty = isEmpty;
        this.idCounter++;
        this.id = "ti" + idCounter;
    }

    /**
     * a vonatelem helyének lekérdezése
     * @return a pályaelem, amelyen a vonatelem éppen tartózkodik
     */
    public BoardItem getBoardItem() {
        return this.place;
    }

    /**
     * beállítja a vonatelem helyét
     * @param bi a helynek beállítandó pályaelem
     */
    public void setBoardItem(BoardItem bi) {
        this.place = bi;
    }

    /**
     * utasok leszálllítása/vonatelem eltűntetése amennyiben a vonatelem színe megegyezik az állomáséval
     * @param color az állomás színe
     */
    public void checkColor(String color) {
        if (this.color.equals(color) == true) {
            if (this.isEmpty()) {
                Station actualBoardItem = (Station) this.place;
                if (actualBoardItem.getPassengers()) {
                    this.setEmpty(false);
                    actualBoardItem.setPassengers(false);
                    System.out.println("-" + this.id + " " + this.color  + " get on at station");
                }
            } else if (this.train.isFirstNotEmptyWagon(this)){
                this.setEmpty(true);
                System.out.println("-" + this.id + " " + this.color + " get off at station");
            }
        }
    }

    /**
     * vonatelem alagútban levőségének beállítása
     * true -  a vonatelem alagútban van
     * false -  a vonatelem nincs alagútban
     * @param b az alagútban levőség értéke
     */
    public void setInTunnel(boolean b) {
        this.inTunnel = b;
    }

    /**
     * a vonatelem alagútban levőségének lekérdezése
     * @return a vonatelem alagútban levőségének értéke
     */
    public boolean getInTunnel() {
        return this.inTunnel;
    }

    /**
     * vonatelem láthatóságának lekérdezése
     * @return a vonatelem láthatósága
     */
    public boolean getVisibility() {
        return this.visibility;
    }

    /**
     * vonatelem láthatóságának beállítása
     * @param b a vonatelemnek beállítandó
     */
    public void setVisibility(boolean b) {
        this.visibility = b;
    }

    /**
     * vonatelemhez tartozó vonat beállítása
     * @param t a vonat, amihez a vonatelem tartozik
     */
    public void setTrain(Train t) { this.train = t;}

    /**
     * vonatelemek azonosítószámának visszaállítása
     */
    public static void resetIDCounter() {
        idCounter = 0;
    }

    /**
     * vonatelem azonosítóját adja meg
     * @return a vonatelem azonosítója
     */
    public String getID(){
        return this.id;
    }

    /**
     * információt szolgáltat a vonatelemről
     * @return a vonatelemmel kapcsolatos információk
     */
    public String getInfo(){
        String returnString = this.color;
        if (this.isEmpty()) {
            returnString += " empty";
        }
        else{
            returnString += " !empty";
        }
        if (this.place != null) {
            returnString += " on " + place.getID();
        }
        else {
            returnString = "";
        }
        return returnString;
    }

    /**
     * a vonatelem azonosítóját adja meg
     * @return a vonatelem azonosítója
     */
    public String getPlaceID() {
        return this.place.getID();
    }

    /**
     * a vonatelem színét adja meg
     * @return a vonatelem színe
     */
    public String getColor() {
        return this.color;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position p){
        position = p;
    }

}