import java.rmi.Naming;
import java.util.Random;

public class GameClient {
    static final String ROOT_URL = "GameServer";
    static Random random = new Random();
    public static void main(String[] args) {
		if	(args.length != 1)  {
			System.out.println("Uso: java GameClient <maquina>");
			System.exit(1);
		}
        final String path = "//" + args[0] + "/" + ROOT_URL;
        GameInterface gameInterface;
        Integer userID = -1;
        PlayerInterface playerInterface;
        try {
            gameInterface = (GameInterface) Naming.lookup(path);
            userID = gameInterface.register();
            System.out.println("Client @" + userID + " was successfully conected with server!");
        } catch (Exception e) {
            System.out.println("rmi.Game client failed");
            e.printStackTrace();
        }
        randomInterval();
        try {
            playerInterface = (PlayerInterface) Naming.lookup(path);
            System.out.println("Player @" + userID + " was successfully conected with server!");
        } catch (Exception e) {
            System.out.println("rmi.Player client failed");
            e.printStackTrace();
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
