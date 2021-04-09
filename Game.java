import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Game extends UnicastRemoteObject implements GameInterface {
    @Serial private static final long serialVersionUID = 12312312312313L;
    private volatile List<Player> users;
    final int maxNumberOfPlayers;

    public Game(int userListSize) throws RemoteException {
        users = new ArrayList<>(userListSize);
        maxNumberOfPlayers = userListSize;
    }

    public int register() throws RemoteException {
        System.out.println("Creating new register");
        int listSize = users.size();
        if(listSize == maxNumberOfPlayers) {
            throw new RemoteException("Max number of players in game reached.");
        }
        users.add(listSize);
        System.out.println("Created new player ID: @" + listSize);
        return listSize;
    }

    public int play(int id) throws RemoteException {
        return 0;
    }

    public int quit(int id) throws RemoteException {
        return 0;
    }

    public int end(int id) throws RemoteException {
        return 0;
    }

}
