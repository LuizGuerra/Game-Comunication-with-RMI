import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameInterface extends Remote {
    public int register() throws RemoteException;
    public int play(int id) throws RemoteException;
    public int quit(int id) throws RemoteException;
    public int end(int id) throws RemoteException;
}
