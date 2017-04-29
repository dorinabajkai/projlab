import java.util.ArrayList;

/**
 * absztrakt osztály a pályán lévő elemekhez
 */
public abstract class BoardItem implements IClickEvent{

    /**
     * pályaelem azonosítója
     */
    protected String id = "";
    /**
     * a pályaelem szomszédai
     */
    protected ArrayList<BoardItem> neighbours;

    /**
     * jelzi, hogy ráléphet-e a vonat
     * true - ráléphet
     * false -  nem léphet rá (már áll rajta valami)
     */
    protected boolean isAvailable = true;

    /**
     * jelzi, hogy a pályaelem virtuális-e
     * true - virtuális
     * false - nem virtuális
     */
    protected boolean isVirtual;

    /**
     * a pályaelemen álló vonatelemet tárolja
     */
    protected TrainItem content;


    protected Position position;

    /**
     * a pályaelem konstruktora, paraméterként megkapja, hogy virtuális-e.
     * @param virtual a virtuálisságot jelző attribútum
     */

    public BoardItem(boolean virtual) {
        neighbours = new ArrayList<BoardItem>();
        this.isVirtual = virtual;
    }

    /**
     * hozzáadja a szomszédokhoz a paraméterként kapott pályaelemet
     * @param bi a szomszédnak beállítandó pályaelem
     */
    public void addNeighbour(BoardItem bi) {
        neighbours.add(bi);
        if (this.isVirtual() == false && bi.isVirtual() == false) {
            System.out.println("-" + id + " has new neighbour " + bi.getID());
        }
    }

    /**
     * absztrakt függvény, minden pályaelem a szomszédai alapján ennek segítségével adja vissza a következő lépés helyét
     */
    public abstract BoardItem getNextStep();

    /**
     * az elérhetőség eldöntésére szolgál
     * @return az elérhetőséget jelző attribútum értéke
     */
    public boolean isAvailable() {
        return this.isAvailable;
    }

    /**
     * az elérhetőség beállítására szolgál
     * @param availability az elérhetőséget jelző attribútum
     */
    public void setAvailable(boolean availability) {
        this.isAvailable = availability;
    }

    /**
     * beállítja a paraméterként kapott vonatelemet, mint rajta álló elem
     * @param ti a beállítandó vonatelem
     */
    public void setContent(TrainItem ti) {
        this.content = ti;
    }

    /**
     * a virtuálisságot vizsgálja
     * @return a virtuálisságot jelző attribútum értéke
     */
    public boolean isVirtual() {
        return this.isVirtual;
    }

    /**
     * megadja a pályaelem azonosítóját
     * @return az azonosító
     */
    public String getID(){
        return id;
    }


    public void draw(){

    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position p){
        position = p;
    }

    @Override
    public void action(){

    }

}