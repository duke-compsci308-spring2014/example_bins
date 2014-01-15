import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Runs a number of algorithms that try to fit files onto disks.
 * 
 * @author rcd
 */
public class Bins
{
    private static final String WORST_FIT = "worst fit";
    private static final String WORST_FIT_DECREASING = "worst fit decreasing";

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

    /**
     * Given a collection of numbers, return their sum.
     * 
     * @param data collection of numbers to be summed
     * @return total size of files in data
     */
    public int getTotalFileSize (List<Integer> data)
    {
        int total = 0;
        for (Integer size : data)
        {
            total += size;
        }
        return total;
    }

    /**
     * The main program.
     */
    public static void main (String args[])
    {
        // all possible algorithms to compare --- add new instances here!
        Allocator algortihmsToCompare[] = {
            new Allocator(WORST_FIT),
            new DecreasingAllocator(WORST_FIT_DECREASING)
        };

        try
        {
            Bins b = new Bins();
            Scanner input = new Scanner(new File(args[0]));
            List<Integer> data = b.readData(input);
            System.out.println("total size = " + (1.0 * b.getTotalFileSize(data) / Disk.GIGABYTE) + "GB");

            // run all algorithms
            for (Allocator al : algortihmsToCompare)
            {
                al.doAlgorithm(data);
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Could not open file: " + args[0]);
        }
    }
}
