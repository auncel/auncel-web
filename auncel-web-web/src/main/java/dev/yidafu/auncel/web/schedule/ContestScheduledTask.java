package dev.yidafu.auncel.web.schedule;

import dev.yidafu.auncel.web.dal.ContestRepository;
import dev.yidafu.auncel.web.domain.Contest;
import dev.yidafu.auncel.web.domain.ContestStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ContestScheduledTask {
    Log logger = LogFactory.getLog(ContestScheduledTask.class);

    @Autowired
    ContestRepository contestRepository;

    /**
     * @fixme 性能优化
     */
//    @Scheduled(fixedDelay = 3000)
    public  void modifyContestStatus() {
        logger.info("Task modify contest status start");
        List<Contest> contestList = contestRepository.findAll();
        contestList.stream()
                .forEach(contest -> {
                    if (contest.getStartTime().getTime() > System.currentTimeMillis()) {
                        contest.setStatus(ContestStatus.RUNNING);
                    }
                    if (contest.getEndTime().getTime() > System.currentTimeMillis()) {
                        contest.setStatus(ContestStatus.ENDED);
                    };
                    contestRepository.save(contest);
                });
    }
}
