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

    public Player() throws RemoteException { }

    public int getID() throws RemoteException {
        return this.id;
    }

    public void start() throws RemoteException {
        System.out.println("Player " + id + "começou");
        start = true;
    }
    
    public void bonus() throws RemoteException {
        System.out.println("Player Bonus 01");
        int calculatedProbability = random.nextInt(101); 
        System.out.println("Player Bonus 02");// Because 0% and 100% are valid numbers
        if(BONUS_PROBABILITY < calculatedProbability) {
            System.out.println("Player @" + id + " received a bonus");
        }
        System.out.println("Player Bonus 03");
    }

    public void verify() throws RemoteException {
        System.out.println("Jogador @" + id + " está conectado.");
    }

    public static long randomTime() {
        return 0;
    }
}
