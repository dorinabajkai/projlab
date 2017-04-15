/**
 * szomszéd eltávolítását teszi lehetővé
 */
public interface IRemoveNeighbour {
    /**
     * a paraméterként kapott szomszéd eltávolítása
     * @param bi az eltávolítandó szomszéd
     */
    public void removeNeighbour(BoardItem bi);
}
