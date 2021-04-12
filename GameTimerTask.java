import java.util.TimerTask;

class GameTimerTask extends TimerTask {
    PlayerInterface playerReference;

    public GameTimerTask(PlayerInterface playerReference) {
        this.playerReference = playerReference;
    }

    @Override
    public void run() {
        System.out.println("Running task thread");
        try {
            this.playerReference.verify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}