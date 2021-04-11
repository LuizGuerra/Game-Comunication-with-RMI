import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class GameServer {
    static final int PORT = 10991;
    static final String ROOT_URL = ":10991/GameServer";
    static final String PLAYER_URL = "Client";

    // static Game gameClass = null;
    // static Game game = null;
    // static Player player = null;
    
    public static void main (String[] args) {
        if	(args.length != 2)  {
            System.out.println("Usage: java GameServer <ip do servidor> <numero de jogadores>");
			System.exit(1);
		}
        System.out.println("Max number of players in game: " + args[1]);
        int maxPlayers = Integer.parseInt(args[1]);
        try {
            // System.setProperty(key, value);
            System.setProperty("java.rmi.server.hostname", args[0]);
            LocateRegistry.createRegistry(PORT);
            System.out.println("RMI registry ready");
        } catch (RemoteException remoteException) {
            System.out.println("RMI registry already running");
            remoteException.printStackTrace();
        }
        
        try {
            String game = "rmi://" + args[0] + ROOT_URL;
            Naming.rebind(game, new Game());
            System.out.println("Game server is ready");
        } catch (Exception e) {
            System.out.println("Game server failed");
            e.printStackTrace();
        }
        
        while (true) {
            if (Game.connections == maxPlayers) {
                Game.playersRMI.forEach((conId, path) -> {
                    try {
                        System.out.println("print do path: " + path);
                        PlayerInterface client = (PlayerInterface) Naming.lookup(path);
                        client.start();
                    } catch (Exception e) {
                        System.out.println("Falha na inicialização");
                        e.printStackTrace();
                    }

                });
            }
            try {
                Game.playersRMI.forEach((conId, path) -> {
                    try {
                        PlayerInterface client = (PlayerInterface) Naming.lookup(path);
                        client.verify();
                    } catch (Exception e) {
                        System.out.println("Falha na conexão");
                        e.printStackTrace();
                    }
                });

                Thread.sleep(3000); // ??
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
		// connections deve ser uma variavel da classe Game
		// que sera incrementada pelo metodo registry
        
        // while (true) {
        //     if (connections == maxPlayers)) {      
        //     }
        // }
        
        // try {
        //     Naming.rebind(PLAYER_URL, new Player());
        //     System.out.println("Player server is ready");
        // } catch (Exception e) {
        //     System.out.println("Player server failed");
        //     e.printStackTrace();
        // }
}
