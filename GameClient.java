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
        Player player;
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

        int quittingProbability = 5; // 5% chance of quitting
        
        try {
            System.out.println("Tentando conectar com server..");
            server = (GameInterface) Naming.lookup(serverConnection);
        } catch (Exception e) {
            System.out.println("Erro na conexao..");
            e.printStackTrace();
        }

        Timer timer = new Timer();

        while (true) {
            try {
                timer.schedule(new GameTimerTask(player), 3000);
                int i = 0;
                if (userID == -1) {
                    System.out.println("Entrou no if");
                    userID = server.register(Integer.parseInt(args[2]));
                }
                if (Player.start) {
                    while (i < 20) {
                        if (!Game.endClient) {
                            System.out.println("Player @" + userID + " is thinking.");
                            randomInterval();
                            if(Math.random() < 0.1) {
                                server.quit();
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
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch() {

            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
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
