package pdp_tf;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
/**
 *
 * @author Emanuel
 */
public class Reader 
{
    private final FileReader fr;
    private int lastRead;
    private boolean done = false;
    private LinkedList<TextFragment> text;
    LinkedList<TextFragment> buffer = new LinkedList<>();
    private int capacity = 2;
    
    public Reader(String filepath) throws FileNotFoundException, IOException
    {
        fr = new FileReader(filepath);
        lastRead = 0;
        text = new LinkedList<>();
        this.read();
    }
    
    public boolean isDone()
    {
        return done;
    }
    
    private void read() throws IOException
    {
        int read = fr.read();
        while (read != -1)
        {
            
            TextFragment frag = new TextFragment(lastRead, (char)read);
            text.add(frag);
            lastRead++;
            read = fr.read();
        }
    }
    
    public synchronized TextFragment getFragment()
    {
        if(text.size() > 0)
            return text.pop();
        return null;
    }
    
    public void printBuffer()
    {
        for (int i=0; i< buffer.size(); i++)
            System.out.print(buffer.get(i).getText());
    }
    
    
    public void end() throws IOException
    {
        fr.close();
    }

    public void addToBuffer(TextFragment tf)
    {
        synchronized(this)
        {
            buffer.add(tf);
        }

    }
    
    public synchronized TextFragment getFromBuffer()
    {
        synchronized(this)
        {
            if (buffer.size() == 0)
                return null;
            else
                return buffer.pop();
        }
    }
    
}
