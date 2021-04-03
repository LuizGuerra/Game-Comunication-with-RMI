import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Game extends UnicastRemoteObject implements GameInterface {
    @Serial private static final long serialVersionUID = 12312312312313L;

    public Game() throws RemoteException {}

    public int register() throws RemoteException {
        System.out.println("Creating new register");
        return 0;
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