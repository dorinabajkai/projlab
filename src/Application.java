import java.io.File;
import java.util.*;

/**
 * a program belépési pontját tartalmazó osztály
 */
public class Application {
    /**
     * a program belépési pontja, a menürendszert valósítja meg
     * @param args a parancssoron keresztül kapott argumentumok tömbje
     */
    public static void main(String[] args) {
        Board b = new Board();
        boolean start = false;
        ArrayList<String> boardItemNames = new ArrayList<String>();
        boardItemNames.add("Cross");
        boardItemNames.add("Rail");
        boardItemNames.add("SpecialPlace");
        boardItemNames.add("Station");
        boardItemNames.add("Switch");
        ArrayList<String> colorList = new ArrayList<String>();
        colorList.add("red");
        colorList.add("green");
        colorList.add("blue");
        colorList.add("grey");
        ArrayList<String> logicalValueList = new ArrayList<String>();
        logicalValueList.add("true");
        logicalValueList.add("false");
        ArrayList<String> emptyList = new ArrayList<String>();
        emptyList.add("empty");
        emptyList.add("!empty");
        int idCounter = 1;
        ArrayList<String> testNames = new ArrayList<String>();
        testNames.add("initAndStart");
        testNames.add("stepTrain");
        testNames.add("stepTrainOnSwitch");
        testNames.add("managePortal");
        testNames.add("goThroughTunnel");
        testNames.add("stepOnStationGetOff");
        testNames.add("stepOnStationNoGetOff");
        testNames.add("stepOnStationFirstIsCoaly");
        testNames.add("stepOnStationDisappear");
        testNames.add("stepOnStationGetOn");
        testNames.add("collision");

        Scanner scSystemIn = new Scanner(System.in);
        Scanner scFile = null;

        while(true){
            String testName = scSystemIn.nextLine();
            if (testName.equals("exit")){
               System.exit(0);
            }
            else if (testNames.contains(testName)) {
                String path = "src\\" + testName + ".txt";
                File theFile = new File(path);
                try {
                    scFile = new Scanner(theFile);
                }
                catch (Exception e){
                    //TODO
                }
                while(scFile.hasNext()) {
                    String[] inputCommand = scFile.nextLine().split(" ");
                    switch (inputCommand[0]) {
                        case "newGame":
                            System.out.println("-newGame");
                            b.clearBoard();
                            b = new Board();
                            start = false;
                            break;
                        case "start":
                            System.out.println("-start");
                            start = true;
                            break;
                        case "connectBoardItems":
                            if (start == false) {
                                if (inputCommand.length == 3) {
                                    if (b.validBoardItemID(inputCommand[1]) && b.validBoardItemID(inputCommand[2])) {
                                        BoardItem bi1 = b.getBoardItem(inputCommand[1]);
                                        BoardItem bi2 = b.getBoardItem(inputCommand[2]);
                                        bi1.addNeighbour(bi2);
                                    }
                                    else {
                                        System.out.println("-Invalid syntax!");
                                    }
                                }
                                else {
                                    System.out.println("-Invalid syntax!");
                                }
                            }
                            else {
                                System.out.println("-The game has started!");
                            }
                            break;
                        case "createTrain":
                            if (inputCommand.length >= 4 && inputCommand.length % 2 == 0) {
                                if (b.validBoardItemID(inputCommand[1])) {
                                    BoardItem place = b.getBoardItem(inputCommand[1]);
                                    ArrayList<TrainItem> tis = new ArrayList<TrainItem>();
                                    boolean canCreateTrain = true;
                                    for (int i = 2; i < inputCommand.length; i+=2) {
                                        if (colorList.contains(inputCommand[i]) && emptyList.contains(inputCommand[i+1])) {
                                            boolean isEmpty = inputCommand[i+1].equals("empty");
                                            TrainItem ti = new TrainItem(inputCommand[i],isEmpty);
                                            tis.add(ti);
                                        }
                                        else {
                                            System.out.println("-Invalid syntax!");
                                            canCreateTrain = false;
                                            break;
                                        }
                                    }
                                    if (canCreateTrain) {
                                        Train train = new Train(place,tis);
                                        b.addTrain(train);
                                    }
                                }
                                else{
                                    System.out.println("-Invalid syntax!");
                                }
                            }
                            else {
                                System.out.println("-Invalid syntax!");
                            }
                            break;
                        case "changeSwitch":
                            if (inputCommand.length == 2) {
                                if (b.validBoardItemID(inputCommand[1]) && inputCommand[1].contains("sw")){
                                    Switch sw = (Switch) b.getBoardItem(inputCommand[1]);

                                    sw.changeSwitchOutput();
                                }
                                else {
                                    System.out.println("-Invalid syntax!");
                                }
                            }
                            else {
                                System.out.println("-Invalid syntax!");
                            }
                            break;
                        case "stepTrain":
                            if (inputCommand.length == 2) {
                                if (b.validTrainID(inputCommand[1])) {
                                    Train t = b.getTrain(inputCommand[1]);
                                    boolean successfullStep = t.step();
                                    if (!successfullStep) {
                                        System.out.println("-collision " + t.getID());
                                    }
                                }
                                else{
                                    System.out.println("-Invalid syntax!");
                                }
                            }
                            else {
                                System.out.println("-Invalid syntax!");
                            }
                            break;
                        case "managePortal":
                            if (inputCommand.length == 2) {
                                if (b.validBoardItemID(inputCommand[1]) && inputCommand[1].contains("sp")) {
                                    SpecialPlace sp = (SpecialPlace) b.getBoardItem(inputCommand[1]);
                                    Tunnel tunnel = b.getTunnel();
                                    tunnel.managePortals(sp);
                                }
                                else {
                                    System.out.println("-Invalid syntax!");
                                }
                            }
                            else {
                                System.out.println("-Invalid syntax!");
                            }
                            break;
                        case "listSpecialPlaces":
                            idCounter = 1;
                            SpecialPlace sp = null;
                            while ((sp = (SpecialPlace)b.getBoardItem("sp" + idCounter)) != null) {
                                System.out.println("\t-" + sp.getID() + " " + sp.getInfo());
                                idCounter++;
                            }
                            break;
                        case "listSwitches":
                            idCounter = 1;
                            Switch sw = null;
                            while ((sw = (Switch)b.getBoardItem("sw" + idCounter)) != null) {
                                System.out.println("\t-" + sw.getID() + " " + sw.getInfo());
                                idCounter++;
                            }
                            break;
                        case "listTrains":
                            idCounter = 1;
                            Train t = null;
                            while ((t = b.getTrain("t" + idCounter)) != null) {
                                System.out.println("\t-" + t.getID());
                                idCounter++;
                            }
                            break;
                        case "listBoardItems":
                            b.listBoardItems();
                            break;
                        case "createBoardItem":
                            if (start == false) {
                                if (inputCommand.length > 1) {
                                    if (boardItemNames.contains(inputCommand[1])) {
                                        switch (inputCommand[1]) {
                                            case "Cross":
                                                b.addBoardItem(new Cross());
                                                break;
                                            case "Rail":
                                                b.addBoardItem(new Rail(false));
                                                break;
                                            case "SpecialPlace":
                                                b.addBoardItem(new SpecialPlace(false));
                                                break;
                                            case "Station":
                                                if (inputCommand.length == 4) {
                                                    if (colorList.contains(inputCommand[2]) && logicalValueList.contains(inputCommand[3])) {
                                                        boolean passengers = inputCommand[3].equals("true");
                                                        b.addBoardItem(new Station(false, inputCommand[2], passengers));
                                                    } else {
                                                        System.out.println("-Invalid syntax!");
                                                    }
                                                } else {
                                                    System.out.println("-Invalid syntax!");
                                                }
                                                break;
                                            case "Switch":
                                                b.addBoardItem(new Switch(false));
                                                break;
                                            default:
                                                System.out.println("-Invalid syntax!");
                                        }
                                    } else {
                                        System.out.println("-Invalid syntax!");
                                    }
                                } else {
                                    System.out.println("-Invalid syntax!");
                                }
                            }
                            else {
                                System.out.println("-The game has started!");
                            }
                            break;
                        default:
                            System.out.println("-Invalid syntax!");
                    }
                }
            }
            else {
                System.out.println("-Invalid syntax!");
            }
        }
    }
}