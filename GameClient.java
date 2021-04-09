import java.rmi.Naming;
import java.util.Random;

public class GameClient {
    static final int PORT = 1099;
    static final String ROOT_URL = "GameServer";
    static final String URL = "GameServer/Player/";
    static Random random = new Random();

    static Player player = null;

    public static void main(String[] args) {
		if	(args.length != 1)  {
			System.out.println("Usage: java GameClient <server ip>");
			System.exit(1);
		}
        final String GAME_PATH = "//" + args[0] + "/" + ROOT_URL;
        GameInterface gameInterface;
        Integer userID = -1;
        PlayerInterface playerInterface;

        try {
            // System.setProperty(key, value);
            java.rmi.registry.LocateRegistry.createRegistry(PORT);
            System.out.println("RMI registry ready");
        } catch (RemoteException remoteException) {
            System.out.println("RMI registry already running");
            remoteException.printStackTrace();
        }

        try {
            player = new Player(maxPlayers);
            Naming.rebind(ROOT_URL, player);
            System.out.println("Player RMI is ready");
        } catch (Exception e) {
            System.out.println("Player RMI failed");
            e.printStackTrace();
        }

        // String serverIP = args[0];
        GameInterface server = null;
        
        try {
            System.out.println("Tentando conectar com server..");
            server = (GameInterface) Naming.lookup(GAME_PATH);
        } catch (Exception e) {
            System.out.println("Erro na conexao..");
            e.printStackTrace();
        }

        server.registry();
        player.start();

        // try {
        //     gameInterface = (GameInterface) Naming.lookup(GAME_PATH);
        //     userID = gameInterface.register();
        //     System.out.println("Client @" + userID + " was successfully conected with server!");
        // } catch (Exception e) {
        //     System.out.println("rmi.Game client failed");
        //     e.printStackTrace();
        // }

        int numberOfPlays = random.nextInt(50) + 10;
        for(int i = 0; i < numberOfPlays; i++) {
            playerInterface.play();
        }
    }

    private static void randomInterval() {
        int timeInterval = random.nextInt(700) + 250;
        System.out.println("Sleeping for " + timeInterval + " miliseconds");
        try {
            Thread.sleep(timeInterval);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
