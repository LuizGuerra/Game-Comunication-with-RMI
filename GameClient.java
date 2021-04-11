import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Random;

public class GameClient {
    // static final int SERVER_PORT = 10991;
    static final String ROOT_URL = ":10991/GameServer";
    static final String PLAYER_URL = "Client";
    
    static Random random = new Random();


    public static void main(String[] args) throws InterruptedException {
		if	(args.length != 3)  {
			System.out.println("Usage: java GameClient <server ip> <client ip> <client port>");
			System.exit(1);
		}
        // final String GAME_PATH = "//" + args[0] + "/" + ROOT_URL;
        // GameInterface gameInterface;
        // Integer userID = -1;
        // PlayerInterface playerInterface;

        try {
            System.setProperty("java.rmi.server.hostname", args[1]);
            LocateRegistry.createRegistry(Integer.parseInt(args[2]));
            System.out.println("RMI registry ready");
        } catch (RemoteException remoteException) {
            System.out.println("RMI registry already running");
            remoteException.printStackTrace();
        }

        Integer userID = -1;

        try {
            String client = "rmi://" + args[1] + ":" + args[2] + "/" + PLAYER_URL;
            Naming.rebind(client, new Player());
            System.out.println("Player RMI is ready");
        } catch (Exception e) {
            System.out.println("Player RMI failed");
            e.printStackTrace();
        }

        String serverIP = args[0];
        String serverConnection = "rmi://" + serverIP + ROOT_URL;
        
        GameInterface server = null;

        // String client = "rmi://" + args[1] + "/" + PLAYER_URL;

        // PlayerInterface clientInterface = (PlayerInterface) Naming.lookup(client);
        
        try {
            System.out.println("Tentando conectar com server..");
            server = (GameInterface) Naming.lookup(serverConnection);
        } catch (Exception e) {
            System.out.println("Erro na conexao..");
            e.printStackTrace();
        }

        while (true) {
            try {
                System.out.println("Chegou no try do while");
                int i = 0;
                if (userID == -1) {
                    System.out.println("Entrou no if");
                    userID = server.register(Integer.parseInt(args[2]));
                }
                if (Player.start) {
                    while (i < 20) {
                        if (!Game.endClient) {
                            server.play(userID);
                            Thread.sleep(Player.randomTime()); //verificar se não está no metodo já o sleep
                            i++;
                        }
                    }
                    server.quit(userID);
                    System.out.println("Fim de jogo");
                    return;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
        
    }
}
    
        // server.registry();
        // player.start();

        // try {
        //     gameInterface = (GameInterface) Naming.lookup(GAME_PATH);
        //     userID = gameInterface.register();
        //     System.out.println("Client @" + userID + " was successfully conected with server!");
        // } catch (Exception e) {
        //     System.out.println("rmi.Game client failed");
        //     e.printStackTrace();
        // }

    //     int numberOfPlays = random.nextInt(50) + 10;
    //     for(int i = 0; i < numberOfPlays; i++) {
    //         playerInterface.play();
    //     }
    // }

    // private static void randomInterval() {
    //     int timeInterval = random.nextInt(700) + 250;
    //     System.out.println("Sleeping for " + timeInterval + " miliseconds");
    //     try {
    //         Thread.sleep(timeInterval);
    //     } catch (InterruptedException interruptedException) {
    //         interruptedException.printStackTrace();
    //     }
    // }
