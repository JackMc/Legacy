package org.ossnipes.shadowdawn.dev.engine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Jack McCracken (Auv5)
 */
public class SockMan {
    private Socket cs;
    private int port;
    private String host;
    private PrintStream ps;
    private BufferedReader br;

    public SockMan(String host, int port)
    {
        this.port = port;
        this.host = host;
        run();
    }
    public SockMan(String host)
    {
        this.host = host;
        this.port = 9999;
    }
    public void run()
    {
        try {
            cs = new Socket(host, port);
            System.out.println("ShadowDawn Client Dev Build: Connection to " + cs.getLocalAddress() + " on port " + cs.getLocalPort());
            ps = new PrintStream(cs.getOutputStream());
            br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            ps.println("SDC SYSTEM TEST");
        } catch (Exception e) {e.printStackTrace();}
    }
}
