import java.rmi.Naming;

public class GameClient {
    public static void main(String[] args) {
        try {
            ServerInterface helloInterface = (HelloInterface) Naming.lookup("//localhost/Hello");
            System.out.println(helloInterface.say());
        } catch (Exception e) {
            System.out.println("rmi.HelloClient failed");
            e.printStackTrace();
        }
    }
}