package htof.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by miaoch on 2017/11/4.
 */
@Component
public class OrderTask {
    @Scheduled(cron="0 30 23 * * ? ") //每天23.30执行一次
    public void taskCycle(){

    }
}