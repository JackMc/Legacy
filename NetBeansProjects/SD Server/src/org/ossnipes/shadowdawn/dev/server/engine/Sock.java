package org.ossnipes.shadowdawn.dev.server.engine;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** Class for sockets.
 * @author Jack McCracken (Auv5)
 */
public class Sock {
    private ServerSocket ss;
    public Socket ss_accept;
    public boolean ss_created = false;
    public Sock(int port)
    {
        try
        {
        ss = new ServerSocket(port);
        ss_accept = ss.accept();
        ss_created = true;
        } catch (IOException e) {e.printStackTrace();}
    }
}
