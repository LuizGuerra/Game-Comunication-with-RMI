import java.rmi.Naming;

public class GameClient {
    public static void main(String[] args) {
		if	(args.length != 1)  {
			System.out.println("Uso: java GameClient <maquina>");
			System.exit(1);
		}
        try {
            ServerInterface helloInterface = (HelloInterface) Naming.lookup("//localhost/Hello");
            System.out.println(helloInterface.say());
        } catch (Exception e) {
            System.out.println("rmi.HelloClient failed");
            e.printStackTrace();
        }
    }
}