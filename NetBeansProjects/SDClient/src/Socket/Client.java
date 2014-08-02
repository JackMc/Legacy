/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Socket;

import java.io.*;
import java.net.*;

/**
 *
 * @author themindtaker
 */
public class Client {
    public static void main(String args[]) throws Exception
    {
        run();

    }
    public static void run() throws Exception
    {
        Socket cs = new Socket("localhost", 9999);
        PrintStream ps = new PrintStream(cs.getOutputStream());
        ps.println("Hello, Sockets!");
        BufferedReader br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
        System.out.println("Recieved a line back from the server: " + br.readLine());

    }
}
