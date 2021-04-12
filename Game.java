// import java.io.Serial;
// import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Game extends UnicastRemoteObject implements GameInterface {
    /**
     *
     */
    private static final long serialVersionUID = 5675296890212610484L;
    // @Serial private static final long serialVersionUID = 12312312312313L;
    // private volatile List<Player> users;

    private static volatile String clientIP;
    public static volatile HashMap<Integer, String> playersRMI = new HashMap<>();
    public static volatile int connections;
    static final String PLAYER_URL = "Client";

    public static boolean endClient = false;
    // final int maxNumberOfPlayers;

    public Game() throws RemoteException {

    }
    
    public int register(int port) throws RemoteException {
        try {
            clientIP = getClientHost();
            System.out.println(getClientHost());
            playersRMI.put(connections, "rmi://" + clientIP + ":" + port + "/" + PLAYER_URL);
        } catch (Exception e) {
            System.out.println("Falha no registro");
            e.printStackTrace();
        }
        connections++;
        return connections - 1;
    }

    public int play(int id) throws RemoteException {
        System.out.println("Player " + id + " played");
        return 1;
    }

    public int quit(int id) throws RemoteException {
        System.out.println("Player @" + id + " has quitted the game");
        return 1;
    }

    public int end(int id) throws RemoteException {
        System.out.println("Player @" + id + "Game over");
        playersRMI.remove(id);
        endClient = true;
        return 0;
    }


}
