package com.back.boundedContext.payout.in;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.InvalidJobParametersException;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.launch.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.JobRestartException;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Profile("prod") // 운영 환경에서만 실행     // 운영 환경(prod, 실서버) <-> 개발 환경(dev, 개발 서버)
@Component
@RequiredArgsConstructor
public class PayoutScheduler {
    private final JobOperator jobOperator;
    private final Job payoutCollectItemsAndCompletePayoutsJob;

    // Payout -> PayoutDate -> Null인 데이터를 조회하여 정산
    // Batch 실패해도 이후 시간대의 작업으로 정산될 수 있음

    // 매일 01:00 (KST)
    @Scheduled(cron = "0 0 1 * * *", zone = "Asia/Seoul")
    public void runAt01() throws JobInstanceAlreadyCompleteException, InvalidJobParametersException,
            JobExecutionAlreadyRunningException, JobRestartException {
        runCollectItemsAndCompletePayoutsBatchJob();
    }

    // 매일 04:00 (KST)
    @Scheduled(cron = "0 0 4 * * *", zone = "Asia/Seoul")
    public void runAt04() throws JobInstanceAlreadyCompleteException, InvalidJobParametersException,
            JobExecutionAlreadyRunningException, JobRestartException {
        runCollectItemsAndCompletePayoutsBatchJob();
    }

    // 매일 22:00 (KST)
    @Scheduled(cron = "0 0 22 * * *", zone = "Asia/Seoul")
    public void runAt22() throws JobInstanceAlreadyCompleteException, InvalidJobParametersException,
            JobExecutionAlreadyRunningException, JobRestartException {
        runCollectItemsAndCompletePayoutsBatchJob();
    }

    private void runCollectItemsAndCompletePayoutsBatchJob() throws JobInstanceAlreadyCompleteException,
            InvalidJobParametersException, JobExecutionAlreadyRunningException, JobRestartException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString(
                        "runDateTime",
                        LocalDateTime.now().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                )
                .toJobParameters();

        JobExecution execution = jobOperator.start(payoutCollectItemsAndCompletePayoutsJob, jobParameters);
    }
}