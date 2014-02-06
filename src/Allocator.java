import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;


/**
 * This class represents worst-fit algorithms for allocating files to disks.
 * 
 * @author rcd
 */
public class Allocator
{
    private String myDescription;

    /**
     * Create with given descriptor.
     */
    public Allocator (String description)
    {
        myDescription = description;
    }

    /**
     * Allocates given files to the fewest number of disks.
     * 
     * @param data collection of files to be allocated to disks
     */
    public void doAlgorithm (List<Integer> data)
    {
        orderData(data);
        Collection<Disk> pq = allocateFiles(data);
        printResults(pq);
    }

    /**
     * Arrange given data in preparation for fitting disks.
     * 
     * @param disks collection of disks to be printed
     */
    protected void orderData (List<Integer> data)
    {
        // do nothing!!
    }

    /**
     * Allocates files to disks using a worst-fit algorithm.
     * 
     * @param data collection of files to be allocated to disks
     * @param pq queue of available disks
     */
    protected Collection<Disk> allocateFiles (List<Integer> data)
    {
        PriorityQueue<Disk> pq = new PriorityQueue<Disk>();
        pq.add(new Disk());
        for (Integer size : data)
        {
            Disk d = pq.peek();
            if (d.freeSpace() >= size)
            {
                pq.poll();
                d.add(size);
                pq.add(d);
            }
            else
            {
                Disk d2 = new Disk();
                d2.add(size);
                pq.add(d2);
            }
        }
        return pq;
    }

    /**
     * Prints disks as ordered in the given collection
     * 
     * @param disks collection of disks to be printed
     */
    protected void printResults (Collection<Disk> disks)
    {
        System.out.println();
        System.out.println(myDescription + " method");
        System.out.println("number of disks used: " + disks.size());
        for (Disk d : disks)
        {
            System.out.println(d);
        }
        System.out.println();
    }
}
