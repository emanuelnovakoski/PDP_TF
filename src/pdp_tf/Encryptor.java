package pdp_tf;

import static java.lang.Thread.yield;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emanuel
 */
public class Encryptor implements Runnable
{
    int id;
    private int key;
    private Reader reader;
    
    public Encryptor(int k, Reader r, int id)
    {
        this.key = k;
        this.reader = r;
        this.id = id;
    }
    
    public boolean encrypt()
    {
        int encrypted;
        TextFragment tf = reader.getFragment();
        if (tf == null) return false;
        //encrypted = key ^ (int) tf.getText();
        encrypted = tf.getText();   
        tf.setText((char)encrypted);
        reader.addToBuffer(tf);
        //System.out.println("Thread " + Integer.toString(id) + " running.");
        return true;
        
        

    }

    @Override
    public void run() 
    {
        while (this.encrypt());
    }
}
