import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.Random;

public class Player extends UnicastRemoteObject implements PlayerInterface {
    @Serial private static final long serialVersionUID = 32112312312313L;
    private final Integer BONUS_PROBABILITY = 3; // 3%
    private Random random;

    public Player() throws RemoteException {
        random = new Random();
    }

    public void start() throws RemoteException {

    }
    
    public void bonus() throws RemoteException {
        int calculatedProbability = random.nextInt(101); // Because 0% and 100% are valid numbers
        if(BONUS_PROBABILITY < calculatedProbability) {
            //TODO: Give probability bonus to the player
        }
    }

    public void verify() throws RemoteException {
        
    }
}