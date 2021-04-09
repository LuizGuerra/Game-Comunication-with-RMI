import java.rmi.Naming;
import java.rmi.RemoteException;

public class GameServer {
    static final int PORT = 1099;
    static final String ROOT_URL = "GameServer";
    static final String PLAYER_URL = "GameServer/Player";
    private static volatile int connections;

    static Game game = null;
    // static Player player = null;

    public static void main (String[] args) {
		if	(args.length != 1)  {
			System.out.println("Usage: java GameServer <numero de jogadores>");
			System.exit(1);
		}
        System.out.println("Max number of players in game: " + args[0]);
        int maxPlayers = Integer.parseInt(args[0]);
        try {
            // System.setProperty(key, value);
            java.rmi.registry.LocateRegistry.createRegistry(PORT);
            System.out.println("RMI registry ready");
        } catch (RemoteException remoteException) {
            System.out.println("RMI registry already running");
            remoteException.printStackTrace();
        }
        
        try {
            game = new Game(maxPlayers);
            Naming.rebind(ROOT_URL, game);
            System.out.println("Game server is ready");
        } catch (Exception e) {
            System.out.println("Game server failed");
            e.printStackTrace();
        }

        while (true) {
            if (connections == maxPlayers)) {
                
            }
        }
        
        // try {
        //     Naming.rebind(PLAYER_URL, new Player());
        //     System.out.println("Player server is ready");
        // } catch (Exception e) {
        //     System.out.println("Player server failed");
        //     e.printStackTrace();
        // }
    }
}
