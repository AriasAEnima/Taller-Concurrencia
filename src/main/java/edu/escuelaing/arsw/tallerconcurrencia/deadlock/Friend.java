/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.deadlock;

/**
 *
 * @author J. Eduardo Arias
 */
public class Friend {
    private final String name;
    private Object lock1=new Object();
    private Object lock2=new Object();

    public Friend(String name) {
        this.name=name;
     
    }

    public String getName() {
        return name;
    }
    
    public void bow(Friend bower){
        synchronized(lock1){
                System.out.format("%s: %s has bowed to me ! %n",this.name,
                bower.getName());
                bower.bowBack(this);
        }      
    }
    
    
    public void bowBack(Friend bower){
        synchronized(lock2){
            System.out.format("%s : %s has bowed back to me!%n",
            this.name, bower.getName());
        }
    }
    
    public static void main(String[] args){       
        final Friend alphonse=new Friend("Alphonse");
        final Friend gaston=new Friend("Gaston");
        new Thread(new Runnable(){
            public void run(){
                alphonse.bow(gaston);
            }
        }).start();
        new Thread(new Runnable(){
            public void run(){
                gaston.bow(alphonse);
            }
        }).start();
    }
    
    
    
}
