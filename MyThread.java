package threadpool;

/**
 * Created by alex on 9/3/15.
 */
public class MyThread extends Thread{
    private ThreadPool threadPool;
    private boolean finishFlag = false;

    public MyThread(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+" launched");
        while (!finishFlag) {
            System.out.print(Thread.currentThread().getName() + ":");
            threadPool.getTask().run();
        }
    }

    public void setFinishFlagAsTrue() {
        finishFlag = true;
    }

}
