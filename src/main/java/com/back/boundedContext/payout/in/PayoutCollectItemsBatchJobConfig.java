package com.back.boundedContext.payout.in;

import com.back.boundedContext.payout.app.PayoutFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 페이징식으로 청구 대기 항목에서 실제 청구 항목으로 전환하는 배치 작업 구성
@Slf4j
@Configuration
public class PayoutCollectItemsBatchJobConfig {
    // 한 번에 처리할 청구 항목 수
    private static final int CHUNK_SIZE = 10;

    private final PayoutFacade payoutFacade;

    // 생성자. 실제 청구 항목으로 전환하는 작업을 수행하는 PayoutFacade을 주입함
    public PayoutCollectItemsBatchJobConfig(PayoutFacade payoutFacade) {
        this.payoutFacade = payoutFacade;
    }

    // 청구 대기 항목에서 실제 청구 항목으로 전환하는 작업을 수행하는 배치 작업 정의
    @Bean
    public Job payoutCollectItemsJob(
            JobRepository jobRepository,
            Step payoutCollectItemsStep
    ) {
        return new JobBuilder("payoutCollectItemsJob", jobRepository)
                .start(payoutCollectItemsStep)
                .build();
    }

    // 청구 대기 항목에서 실제 청구 항목으로 전환하는 작업을 수행
    // 한 번에 CHUNK_SIZE 만큼 처리
    // 호출이 0을 반환할 때까지 반복해서 collectPayoutItemsMore 메서드를 호출
    @Bean
    public Step payoutCollectItemsStep(JobRepository jobRepository) {
        return new StepBuilder("payoutCollectItemsStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    int processedCount = payoutFacade.collectPayoutItemsMore(CHUNK_SIZE).getData();

                    if (processedCount == 0) {
                        return RepeatStatus.FINISHED;
                    }

                    contribution.incrementWriteCount(processedCount);

                    return RepeatStatus.CONTINUABLE;
                })
                .build();
    }
}
