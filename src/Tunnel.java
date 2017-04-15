import java.util.*;

/**
 *
 */
public class Tunnel {
    /**
     * Ebben az attribútumban tárolódnak a virtuális sínek
     */
    private ArrayList<Rail> virtualRails;

    /**
     * Ebben az attribútumban tárolja a Tunnel az alagútszájakat.
     */
    private ArrayList<SpecialPlace> portals;

    /**
     * Konstruktor
     */
    public Tunnel(){
        virtualRails = new ArrayList<Rail>();
        portals = new ArrayList<SpecialPlace>();
    }

    /** Virtuális síneket hoz létre a két meglévő alagútszáj között
     * és beállítja a szomszédságokat is.
     */
    private void createVirtualRails() {
        Rail r1 = new Rail(true);
        virtualRails.add(r1);

        r1.addNeighbour(portals.get(0));
        r1.addNeighbour(portals.get(1));
        portals.get(0).addNeighbour(r1);
        portals.get(1).addNeighbour(r1);
    }

    /** Az alagútszájakat kezeli. Ha még egy alagútszáj sincs, csak hozzáadja a kapott SpecialPlace-t.
     * Ha már van egy, és a kapott SpecialPlace nem egyezik meg a már tárolttal, létrehozza a virtuális síneket,
     * kimenetet generál az alagútnak. Ha már van egy SpecialPlace és újból azt kapja paraméternek, eltávolítja azt.
     * Ha már van két SpecialPlace és olyat kap paraméternek, amit már tartalmaz, eltávolítja a virtuális síneket,
     * megszünteti az alagutat.
     * @param sp a kapott különleges hely
     */
    public void managePortals(SpecialPlace sp) {
        if(portals.size() == 0)
        {
            portals.add(sp);
            System.out.println("-" + sp.getID() + " create portal");
        }
        else if(portals.size() == 1 && portals.contains(sp) == false)
        {
            portals.add(sp);
            System.out.println("-" + sp.getID() + " create portal");

            createVirtualRails();

            portals.get(0).setTunnelExists(true);
            portals.get(1).setTunnelExists(true);
            System.out.println("-" + portals.get(0).getID()+ " " + portals.get(1).getID() + " create tunnel");
        }
        else if(portals.size() == 1 && portals.contains(sp) == true)
        {
            portals.remove(sp);
            System.out.println("-" + sp.getID() + " destroy portal");
        }
        else if(portals.size() == 2 && portals.contains(sp) == true)
        {
            portals.get(0).removeNeighbour(virtualRails.get(0));
            portals.get(1).removeNeighbour(virtualRails.get(0));
            virtualRails.get(0).removeNeighbour(portals.get(0));
            virtualRails.get(0).removeNeighbour(portals.get(1));

            portals.get(0).setTunnelExists(false);
            portals.get(1).setTunnelExists(false);


            System.out.println("-" + sp.getID() + " destroy portal");
            System.out.println("-" + portals.get(0).getID() + " " + portals.get(1).getID() + " destroy tunnel");
            portals.remove(sp);
            virtualRails.clear();
        }
    }
}