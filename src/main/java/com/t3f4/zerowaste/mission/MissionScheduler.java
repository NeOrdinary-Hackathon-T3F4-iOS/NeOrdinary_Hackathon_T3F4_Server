package com.t3f4.zerowaste.mission;

import com.t3f4.zerowaste.mission.domain.PeriodType;
import com.t3f4.zerowaste.mission.service.MissionCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionScheduler {

    private final MissionCommandService missionCommandService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void assignDailyMissionsToAllMembers() {
        missionCommandService.assignMissionsByBatch(PeriodType.DAILY, 2);
    }

    @Scheduled(cron = "0 0 0 * * MON", zone = "Asia/Seoul")
    public void assignWeeklyMissionsToAllMembers() {
        missionCommandService.assignMissionsByBatch(PeriodType.WEEKLY, 4);
    }
}
