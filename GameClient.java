import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Random;
import java.util.Timer;

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

        try {
            System.setProperty("java.rmi.server.hostname", args[1]);
            LocateRegistry.createRegistry(Integer.parseInt(args[2]));
            System.out.println("RMI registry ready");
        } catch (RemoteException remoteException) {
            System.out.println("RMI registry already running");
            remoteException.printStackTrace();
        }

        Integer userID = -1;
        Player player = null;
        try {
            player = new Player();
            String client = "rmi://" + args[1] + ":" + args[2] + "/" + PLAYER_URL;
            Naming.rebind(client, player);
            System.out.println("Player RMI is ready");
        } catch (Exception e) {
            System.out.println("Player RMI failed");
            e.printStackTrace();
        }

        String serverIP = args[0];
        String serverConnection = "rmi://" + serverIP + ROOT_URL;
        
        GameInterface server = null;

        
        try {
            System.out.println("Tentando conectar com server..");
            server = (GameInterface) Naming.lookup(serverConnection);
        } catch (Exception e) {
            System.out.println("Erro na conexao..");
            e.printStackTrace();
        }

        Timer timer = new Timer();
        System.out.println("Player: " + player);

        while (true) {
            try {
                timer.schedule(new GameTimerTask(player), 30000);
                int i = 0;
                if (userID == -1) {
                    userID = server.register(Integer.parseInt(args[2]));
                }
                if (Player.start) {
                    while (i < 20) {
                        if (!Game.endClient) {
                            System.out.println("Player @" + userID + " is thinking.");
                            randomInterval();
                            if(Math.random() < 0.05) {
                                server.quit(userID);
                                i = 21;
                                break;
                            }
                            server.play(userID);
                            player.bonus();
                            i++;
                        }
                    }
                    server.end(userID);
                    System.out.println("Fim de jogo");
                    timer.cancel();
                    return;
                }
                Thread.sleep(10000);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
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
