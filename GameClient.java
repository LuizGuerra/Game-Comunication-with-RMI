import java.rmi.Naming;

public class GameClient {
    static final String ROOT_URL = "GameServer";
    public static void main(String[] args) {
		if	(args.length != 1)  {
			System.out.println("Uso: java GameClient <maquina>");
			System.exit(1);
		}
        GameInterface gameInterface;
        Integer userID;
        try {
            gameInterface = (GameInterface) Naming.lookup("//localhost/" + ROOT_URL);
            userID = gameInterface.register();
            System.out.println("Client @" + userID + " was successfully conected with server!");
        } catch (Exception e) {
            System.out.println("rmi.HelloClient failed");
            e.printStackTrace();
        }
    }
}
