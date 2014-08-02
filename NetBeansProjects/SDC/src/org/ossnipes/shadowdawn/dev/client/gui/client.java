

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.ImageIcon;
import org.ossnipes.shadowdawn.dev.client.engine.*;

/**
 * @author Jack McCracken (Auv5)
 */
public class client extends Core {
    String host = "localhost";
    int port = 9999;
    Socket cs;
    Image bg;
    BufferedReader br;
    PrintStream ps;
    String brText;
    public static void main(String args[])
    {
        new client().run();
    }
    @Override
    public void init()
    {
        super.init();
        try
        {
            bg = new ImageIcon("C:\\JTest\\bg.png").getImage();
            cs = new Socket(host,port);
            ps = new PrintStream(cs.getOutputStream());
            System.out.println("Created writer stream");
            br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            System.out.println("Created input reader");
            ps.println("Hello, SDServer!");
            brText = br.readLine();
            if (brText != null)
            {
                System.out.println("Recieved text from server: " + brText);
            }

        } catch(Exception e) {e.printStackTrace();}
    }
    public void draw(Graphics2D g)
    {
        g.drawString(brText, 250, 250);
        g.drawImage(bg, 0, 0, null);
    }

}
