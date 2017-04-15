/**
 * absztrakt osztály az üresség jelzésére
 */
public abstract class Empty {
    /**
     * az ürességet jelzi
     * true - üres
     * false - nem üres
     */
    protected boolean isEmpty = false;

    /**
     * az üresség eldöntésére szolgál
     * @return az ürességet jelző attribútum értéke
     */
    public boolean isEmpty() {
        return this.isEmpty;
    }

    /**
     * az üresség beállítására szolgál
     */
    public void setEmpty(boolean empty) {
        this.isEmpty = empty;
    }
}