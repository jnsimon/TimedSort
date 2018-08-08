import java.util.concurrent.CountDownLatch;

public class TimedSort {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);
		int[] values = {5, 2, 6, 10, 1, 7, 0, 6};
		
		for(int val : values){
			new Thread(new TimedSortThread(latch, val)).start();
		}
		
		latch.countDown();
		System.out.println("All Sorted");
	}

	
}

class TimedSortThread implements Runnable
{
    CountDownLatch latch;
    int sortValue;
    public TimedSortThread(CountDownLatch latch, int value) 
    {
        this.latch = latch;
        this.sortValue = value;
    }
    @Override
    public void run() 
    {
        try 
        {
            latch.await();          //The thread keeps waiting till it is informed
            Thread.sleep(sortValue * 100); // sleep 100ms * sort value
            System.out.println(this.sortValue);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
