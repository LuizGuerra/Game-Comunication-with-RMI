import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.*;

public class GameServer {
    static final int PORT = 10991;
    static final String ROOT_URL = ":10991/GameServer";
    static final String PLAYER_URL = "Client";
    
    public static void main (String[] args) {
        if	(args.length != 2)  {
            System.out.println("Usage: java GameServer <ip do servidor> <numero de jogadores>");
			System.exit(1);
		}
        System.out.println("Max number of players in game: " + args[1]);
        int maxPlayers = Integer.parseInt(args[1]);
        try {
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

        while(Game.connections != maxPlayers) { }
        for(Map.Entry<Integer, String> entry : Game.playersRMI.entrySet()) {
            try {
                PlayerInterface client = (PlayerInterface) Naming.lookup(entry.getValue());
                if(Game.connections == maxPlayers) {
                    client.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
