import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


/**
 * Runs a number of algorithms that try to fit files onto disks.
 */
public class Bins
{
    /**
     * Reads list of integer data from the given input.
     * 
     * @param input tied to an input source that contains space separated numbers
     * @return list of the numbers in the order they were read
     */
    public List<Integer> readData (Scanner input)
    {
        List<Integer> results = new ArrayList<Integer>();
        while (input.hasNext())
        {
            results.add(input.nextInt());
        }
        return results;
    }

    private static int allocateFiles(List<Integer> data, PriorityQueue<Disk> pq) {
		int diskId = 1;
		int total = 0;
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
		    total += size;
		}
		return total;
	}

	private static void printResults(PriorityQueue<Disk> pq, String description) {
		//System.out.println();
		System.out.println("\n" + description + " method");
		System.out.println("number of pq used: " + pq.size());
		PriorityQueue<Disk> pqcopy = new PriorityQueue<Disk>(pq);
		while (!pqcopy.isEmpty())
		{
		    System.out.println(pqcopy.poll());
		}
		System.out.println();
	}

	/**
     * The main program.
     */
    public static void main (String args[])
    {
        Bins b = new Bins();
        try
        {
            Scanner input = new Scanner(new File(args[0]));
            List<Integer> data = b.readData(input);

            PriorityQueue<Disk> pq = new PriorityQueue<Disk>();
            
			int total = allocateFiles(data, pq);
            System.out.println("total size = " + (1.0 * total / Disk.GIGABYTE) + "GB");
            printResults(pq, "worst-fit");

            Collections.sort(data, Collections.reverseOrder());
            allocateFiles(data, pq);
            printResults(pq, "worst-fit decreasing");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Could not open file: " + args[0]);
        }
    }
}
