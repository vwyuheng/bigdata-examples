package updateexample;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by matt on 4/16/16.
 */
public class ConfigUpdater {
	public static final String PATH = "/matt";

	private ActiveKeyValueStore store;
	private Random random = new Random();

	public ConfigUpdater(String hosts) throws IOException, InterruptedException {
		store = new ActiveKeyValueStore();
		store.connect(hosts);
	}

	public void run() throws InterruptedException, KeeperException {
		while (true) {
			String value = random.nextInt(100) + " ";
			store.write(PATH, value);
			System.out.printf("Set %s to %s \n", PATH, value);
			TimeUnit.SECONDS.sleep(random.nextInt(10));
		}
	}
	public static void main(String[] args) throws Exception{
		ConfigUpdater configUpdater=new ConfigUpdater(args[0]);
		configUpdater.run();
	}
}
