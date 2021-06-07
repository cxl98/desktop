package com.cxl.common.util;

import java.util.concurrent.*;

/**
 * @author cxl
 */
public class TaskExecutors {
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE= Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 提交任务执行
     * @param callable 任务
     * @param interval 重试间隔
     * @param times 重试次数
     * @param <T> 任务结果类型
     * @return 任务执行结果
     * @throws
     */

    public static <T> T summit(Callable<T> callable, long interval, int times){
        ThreadLocal<Integer> count=new ThreadLocal<>();
        count.set(1);

        //第一次立即执行
        ScheduledFuture<T> schedule=SCHEDULED_EXECUTOR_SERVICE.schedule(callable,0, TimeUnit.MILLISECONDS);
        //执行失败则重试


        try {
            if (schedule.get()==null){
                while(count.get()<=times){
                    schedule=SCHEDULED_EXECUTOR_SERVICE.schedule(callable,interval, TimeUnit.MILLISECONDS);
                    //注意:想要重试,callable中的返回值必须null
                    if (schedule.get()!=null){
                        return schedule.get();
                    }
                    count.set(count.get()+1);
                }
            }else{
                return schedule.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            count.remove();
        }
        return null;
    }

    public static void submit(Runnable task,long delay){
        SCHEDULED_EXECUTOR_SERVICE.schedule(task,delay,TimeUnit.MILLISECONDS);
    }

    public static void submit(Runnable task,long delay,long period){
        SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(task,delay,period,TimeUnit.MILLISECONDS);
    }
    public static void shutdown(){
        SCHEDULED_EXECUTOR_SERVICE.shutdown();
    }

}
