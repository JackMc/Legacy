/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author themindtaker
 */
public class Server {
    public static void main(String args[]) throws Exception
    {
        run();
    }
    public static void run() throws Exception
    {
        ServerSocket ss = new ServerSocket(9999);
        System.out.println("Waiting for connection on port " + ss.getLocalPort());
        Socket ss_accept = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(ss_accept.getInputStream()));
        String BrText = br.readLine();
        if (BrText.contains(BrText))
        if (BrText != null)
        {
        System.out.println("Recieved a line from the client: " + BrText);
        PrintStream ps = new PrintStream(ss_accept.getOutputStream());
        ps.println("Message Recieved. Copy of message: " + BrText);
        }
    }
}
