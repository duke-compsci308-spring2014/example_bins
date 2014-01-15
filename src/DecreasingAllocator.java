import java.util.Collections;
import java.util.List;


/**
 * Uses worst-fit decreasing algorithm to fit disks.
 * 
 * @author rcd
 */
public class DecreasingAllocator extends Allocator
{
    public DecreasingAllocator (String description)
    {
        super(description);
    }
    
    @Override
    protected void orderData (List<Integer> data)
    {
        Collections.sort(data, Collections.reverseOrder());
    }
}
