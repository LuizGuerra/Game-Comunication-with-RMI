// import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.Random;

public class Player extends UnicastRemoteObject implements PlayerInterface {
    /**
     *
     */
    private static final long serialVersionUID = 70855187886381799L;
    // @Serial private static final long serialVersionUID = 32112312312313L;
    private final Integer BONUS_PROBABILITY = 3; // 3%
    private Random random;
    private int id;

    public static boolean start = false;

    public Player() throws RemoteException {
        random = new Random();
    }

    public int getID() throws RemoteException {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void start() throws RemoteException {
        System.out.println("Player @" + id + " started");
        start = true;
    }
    
    public void bonus() throws RemoteException {
        int calculatedProbability = random.nextInt(101); 
        if(calculatedProbability < BONUS_PROBABILITY) {
            System.out.println("Player @" + id + " received a bonus");
        }
    }

    public void verify() throws RemoteException {
        System.out.println("Player @" + id + " is connected.");
    }
}
