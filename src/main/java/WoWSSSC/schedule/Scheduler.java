package WoWSSSC.schedule;

import WoWSSSC.parser.AsyncHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Qualson-Lee on 2016-11-25.
 */
@Component
public class Scheduler
{
    @Autowired
    private AsyncHashMap asyncHashMap;

    private final static Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000, initialDelay = 24 * 60 * 60 * 1000)
    public void run() throws Exception
    {
        logger.info("Getting API data");
        asyncHashMap.run();
    }
}