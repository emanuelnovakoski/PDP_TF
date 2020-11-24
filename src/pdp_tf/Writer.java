package pdp_tf;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Emanuel
 */
public class Writer implements Runnable
{
    private FileWriter fw;
    Reader reader;
    
    public Writer(String filepath, Reader reader) throws IOException
    {
        fw = new FileWriter(filepath);
        this.reader = reader;
    }
    
    public void write(char c) throws IOException
    {
        fw.write(c);
        fw.flush();
    }
    
    public void end() throws IOException
    {
        fw.close();
    }

    @Override
    public void run()
    {
        while(!reader.buffer.isEmpty())
        {
            TextFragment buf = reader.getFromBuffer();
            try {
                write(buf.getText());
            } catch (IOException ex) {
                Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
