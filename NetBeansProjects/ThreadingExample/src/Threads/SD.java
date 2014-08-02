/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Threads;

/**
 *
 * @author Jack McCracken (Auv5)
 */
public class SD {
    public static void main(String args[])
    {
        Thread one = new Thread(new Threads("One"));
        Thread two = new Thread(new Threads("Two"));
        Thread three = new Thread(new Threads("Three"));
        one.start();
        two.start();
        three.start();
    }

}
