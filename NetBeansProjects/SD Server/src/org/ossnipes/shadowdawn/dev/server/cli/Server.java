package org.ossnipes.shadowdawn.dev.server.cli;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.ossnipes.shadowdawn.dev.server.engine.Sock;

/** Main class for opening sockets and making threads for sending data
 * to the client
 * @author Jack McCracken (Auv5)
 */
public class Server {
    private ServerSocket ss;
    private Socket ss_accept;
    private Sock s;
    private boolean ss_created = false;
    public boolean on = true;

    public static void main(String args[])
    {
        Server serv = new Server();
        serv.run();
    }


    public void run()
    {
        while (on)
        {
        s = new Sock(9999);
        Thread st = new Thread(new SThread(ss_accept));
        st.start();
        }
    }
}
