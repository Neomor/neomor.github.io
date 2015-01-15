package auction;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
	private final Timer t = new Timer();

	public TimerTask repeat(final Runnable r, long delay) {
		final TimerTask task = new TimerTask() { public void run() { r.run(); }};
		t.scheduleAtFixedRate(task, 1, delay);
		return task;
	}
}
