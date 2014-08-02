/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Threads;

import java.util.Random;

/**
 *
 * @author themindtaker
 */
public class Threads implements Runnable {
    int time;
    String name;
    public Threads(String name)
    {
        this.name = name;
        Random r = new Random();
        this.time = r.nextInt(9999);
    }
    public void run()
    {
        System.out.println(name + "is sleeping for " + time);
        try{
        Thread.sleep(time);
        } catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("Thread " + name + "Has finished sleeping");
    }

}
