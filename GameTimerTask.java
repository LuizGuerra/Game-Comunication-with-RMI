import java.util.TimerTask;

class GameTimerTask extends TimerTask {
    PlayerInterface playerReference;

    public GameTimerTask(PlayerInterface playerReference) {
        this.playerReference = playerReference;
    }

    @Override
    public void run() {
        this.playerReference.verify();
    }
}