package pdp_tf;

/**
 *
 * @author Emanuel
 */
public class TextFragment implements Comparable<TextFragment>
{
    private final int index;
    private char text;
    
    public TextFragment(int i, char t)
    {
        this.index = i;
        this.text = t;
    }
    
    public char getText()
    {
        return this.text;
    }
    
    public void setText(char c)
    {
        this.text = c;
    }

    @Override
    public int compareTo(TextFragment tf)
    {
        return this.index - tf.index;
    }
}
