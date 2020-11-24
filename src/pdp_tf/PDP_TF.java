package pdp_tf;

import java.io.FileNotFoundException;
import static java.lang.System.exit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emanuel
 */
public class PDP_TF
{
    public static void main(String[] args) throws InterruptedException
    {
        if (args.length < 1)
        {
            System.out.println("Usage: pdp_tf input_filename");
            exit(0);
        }
        try 
        {
            Reader reader = new Reader(args[0]);
            Writer writer = new Writer("output.txt", reader);

            Encryptor crypt = new Encryptor(0xABCD, reader, 1);
            
            
            Thread p = new Thread(crypt);
            Thread c = new Thread(writer);
            p.start();
            c.start();

            c.join();
            p.join();

            
            
           reader.printBuffer();

            
            reader.end();
            writer.end();
        } catch (FileNotFoundException ex) 
        {
            System.err.println("File " + args[0] + " not found.");
            Logger.getLogger(PDP_TF.class.getName()).log(Level.SEVERE, "File " + args[0] + " not found.", ex);
        } catch (IOException ex) {
            System.err.println("Could not read file " + args[0]);
            Logger.getLogger(PDP_TF.class.getName()).log(Level.SEVERE, "Could not read file " + args[0], ex);
        }
        
        
    }
    
}
