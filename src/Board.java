
import javax.naming.ldap.Control;
import javax.swing.*;
import java.util.*;

/**
 * a pálya reprezentációja
 */
public class Board {
    /**
     *  a pályán levő pályaelemek
     */
    private ArrayList<BoardItem> boardItems;


    /**
     *  A pályán lévő vonatok
     */
    private ArrayList<Train> trains;

    /**
     * az alagút
     */
    private Tunnel tunnel;





    /**
     * a konstruktor
     * indítványozza a pályán levő pályaelemek inicializálását
     */

    public Board()
    {
        boardItems = new ArrayList<BoardItem>();
        trains = new ArrayList<Train>();
        tunnel = new Tunnel();

        initBoardItems();

        JFrame frame = new Window("Szoftver projekt laboratórium 2017, Hard_Llamas");
        frame.setVisible(true);
    }

    /**
     * A pályát inicializáló függvény. Létrehozza a síneket, különleges helyeket, váltókat,
     *  állomásokat, beállítja a szomszédságokat.
     */
    private void initBoardItems() {


    }

    /**
     *Feltölti a pályát a paraméterként átadott BoardItem lista elemeivel, és létrehozza a szomszédságokat
     */
    public void setBoardItems(ArrayList<BoardItem> bis){
        boardItems.clear();
        if(bis.size()>1)
            bis.get(0).addNeighbour(bis.get(1));
        else {
            boardItems.add(bis.get(0));
            return;
        }
        int i;
        for(i=1;i<bis.size()-1;i++){
            bis.get(i).addNeighbour(bis.get(i-1));
            bis.get(i).addNeighbour(bis.get(i+1));
        }
        bis.get(i).addNeighbour(bis.get(i-1));
        boardItems.clear();
        for(BoardItem bi:bis){
            boardItems.add(bi);
        }
    }
    /**
     * Meghívja a vonatok step() függvényét.
     * @return true, ha minden vonat step()-je true, false egyébként
     */
    public boolean step() {
        for(int i = 0; i < trains.size(); i++)
        {
            boolean successfullStep = trains.get(i).step();

            if(!successfullStep) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ellenőrzi, van-e még vonat a pályán
     * @return false, ha már nincs, egyébként true
     */
    public boolean trainExists() {
        return trains.size() == 0;
    }

    /**
     * pályaelem hozzáadása a pályához
     * @param bi a hozzáadandó pályaelem
     */
    public void addBoardItem(BoardItem bi){
        this.boardItems.add(bi);
    }

    /**
     * vonat hozzáadása a pályához
     * @param train a hozzáadandó vonat
     */
    public void addTrain(Train train) {
        this.trains.add(train);
    }

    /**
     * ellenőrzi, hogy léteik-e adott azonosítójú pályaelem
     * @param id az ellenőrizendő azonosító
     * @return true létezik az adott azonosítójú pályaelem
     * @return false nem létezik az adott azonosítójú pályaelem
     */
    public boolean validBoardItemID(String id){
        for (BoardItem bi: boardItems) {
            if (bi.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ellenőrzi, hogy létezik-e adott azonosítójú vonat
     * @param id az ellenőrizendő azonosító
     * @return true létezik az adott azonosítójú vonat
     * @return false nem létezik az adott azonosítójú vonat
     */
    public boolean validTrainID(String id){
        for (Train t: trains) {
            if (t.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * visszaadja az adott azonosítójú pályaelemet, ha létezik
     * @param id a visszaadandó pályaelem azonosítója
     * @return az adott azonosítójú pályaelem
     */
    //TODO
    public BoardItem getBoardItem(String id) {




        return null;
    }

    /**
     * visszaadja az adott azonosítójú vonatot, ha létezik
     * @param id a visszaadandó vonat azonosítója
     * @return az adott azonosítójú vonat
     */
    public Train getTrain(String id) {
        for (Train t: trains) {
            if (t.getID().equals(id)) {
                return t;
            }
        }
        return null;
    }

    /**
     * visszaadja az alagutat
     * @return az alagút
     */
    public Tunnel getTunnel(){
        return this.tunnel;
    }

    /**
     * pályaelemek listázására szolgál
     */
    public void listBoardItems(){
        for (BoardItem bi: boardItems) {
            System.out.println("\t-" + bi.getID());
        }
    }

    /**
     * pálya törlésére szolgál
     */
    public static void clearBoard() {
        Cross.resetIDCounter();
        Rail.resetIDCounter();
        SpecialPlace.resetIDCounter();
        Station.resetIDCounter();
        Switch.resetIDCounter();
        Train.resetIDCounter();
    }
}