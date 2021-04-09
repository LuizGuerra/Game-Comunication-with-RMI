import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote {
    public int getID() throws RemoteException;
    public void start() throws RemoteException;
    public void bonus() throws RemoteException;
    public void verify() throws RemoteException;
}
