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
        PriorityQueue<Disk> pq = new PriorityQueue<Disk>();
        allocateFiles(data, pq);
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
    protected void allocateFiles (List<Integer> data, PriorityQueue<Disk> pq)
    {
        int diskId = 1;
        pq.add(new Disk(0));
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
                Disk d2 = new Disk(diskId);
                diskId++;
                d2.add(size);
                pq.add(d2);
            }
        }
    }

    /**
     * Prints disks as ordered in the given collection
     * 
     * @param disks collection of disks to be printed
     */
    protected void printResults (PriorityQueue<Disk> disks)
    {
        System.out.println();
        System.out.println(myDescription + " method");
        System.out.println("number of disks used: " + disks.size());
        PriorityQueue<Disk> copy = new PriorityQueue<Disk>(disks);
        while (!copy.isEmpty())
        {
            System.out.println(copy.poll());
        }
        System.out.println();
    }
}
