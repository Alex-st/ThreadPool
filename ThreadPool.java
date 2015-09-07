package threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by alex on 9/3/15.
 */
public class ThreadPool {
    private List<MyThread> threads;
    private Queue<Runnable> tasks;


    public ThreadPool(int threadNumber) {
        threads = new ArrayList<MyThread>(threadNumber);
        tasks = new LinkedList<Runnable>();

//        for (int i = 0; i < threadNumber; i++) {
//            MyThread curThread = new MyThread(this);
//            threads.add(curThread);
//            curThread.start();
            //threads.add(new threadpool.MyThread(this));

//        }

    }

    public void addThreads(int threadNumber) {
        for (int i = 0; i < threadNumber; i++) {
            MyThread curThread = new MyThread(this);
            threads.add(curThread);
            curThread.start();
        }
    }

    public void addTask(Runnable task) {
        synchronized (tasks) {
            tasks.offer(task);
            tasks.notifyAll();
        }
    }

    public Runnable getTask() {
        Runnable taskToReturn;

        synchronized (tasks) {
            while (tasks.size() == 0) {
                try {
                    tasks.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            taskToReturn = tasks.poll();
            tasks.notifyAll();
        }

        return taskToReturn;
    }

//    public void threadPoolStart() {
//        for (threadpool.MyThread i: threads) {
//            i.start();
//        }
//    }

    public Queue<Runnable> getTaskQueue() {
        return tasks;
    }

    public void threadPoolEnd() {
        for (MyThread i: threads) {
            i.setFinishFlagAsTrue();
        }
    }



    public static void main(String[] args) {
        ThreadPool testPool = new ThreadPool(3);
        testPool.addThreads(3);

        System.out.println("psvm");

        testPool.addTask(new Runnable() {
            public void run() {
               int res = 0;
               for (int i = 0; i < 500; i++) {
                   res=res+i*i/2;
               }
                System.out.println("task1");
            }
        });

        testPool.addTask(new Runnable() {
            public void run() {
                int res = 0;
                for (int i = 0; i < 500; i++) {
                    res=res+i*i/2;

                }
                System.out.println("task2");
            }
        });

        testPool.addTask(new Runnable() {
            public void run() {
                int res = 0;
                for (int i = 0; i < 500; i++) {
                    res=res+i*i/2;
                }
                System.out.println("task3");
            }
        });

        testPool.addTask(new Runnable() {
            public void run() {
                int res = 0;
                for (int i = 0; i < 500; i++) {
                    res=res+i*i/2;
                }
                System.out.println("task4");
            }
        });

        testPool.addTask(new Runnable() {
            public void run() {
                int res = 0;
                for (int i = 0; i < 500; i++) {
                    res=res+i*i/2;
                }
                System.out.println("task5");
            }
        });

        testPool.addTask(new Runnable() {
            public void run() {
                int res = 0;
                for (int i = 0; i < 500; i++) {
                    res=res+i*i/2;
                }
                System.out.println("task6");
            }
        });


    //testPool.threadPoolStart();
        testPool.threadPoolEnd();
        for (MyThread i: testPool.threads) {
            try {
                i.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
