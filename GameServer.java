import java.rmi.Naming;
import java.rmi.RemoteException;

public class GameServer {
    static final int PORT = 1099;
    static final String ROOT_URL = "GameServer"
    public static void main (String[] args) {
		if	(args.length != 1)  {
			System.out.println("Uso: java GameServer <numero de jogadores>");
			System.exit(1);
		}
        try {
            java.rmi.registry.LocateRegistry.createRegistry(PORT);
            System.out.println("RMI registry ready");
        } catch (RemoteException remoteException) {
            System.out.println("RMI registry already running");
            remoteException.printStackTrace();
        }
        try {
            Naming.rebind(ROOT_URL, )
            System.out.println("Server is ready");
        } catch (Exception e) {
            System.out.println("Server failed");
            e.printStackTrace();
        }
    }
}