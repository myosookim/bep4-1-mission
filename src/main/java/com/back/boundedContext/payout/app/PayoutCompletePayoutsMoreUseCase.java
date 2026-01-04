package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.Payout;
import com.back.boundedContext.payout.out.PayoutRepository;
import com.back.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayoutCompletePayoutsMoreUseCase {
    private final PayoutRepository payoutRepository;

    public RsData<Integer> completePayoutsMore(int limit){
        List<Payout> activePayouts = findActivePayouts(limit);

        if (activePayouts.isEmpty()){
            // RsData의 기본 data값은 null이므로, RsData 생성할 때는 data값에 0 꼭 넣어줘야 한다
            return new RsData<>("200-1", "더 이상 정산할 정산내역이 없습니다.",0);
        }

        activePayouts.forEach(Payout::completePayout);

        return new RsData<>(
                "201-1",
                "%d개의 정산내역을 정산처리하였습니다.".formatted(activePayouts.size()),
                activePayouts.size()
        );
    }

    private List<Payout> findActivePayouts(int limit) {
        return payoutRepository.findByPayoutDateIsNullAndAmountGreaterThanOrderByIdAsc(0, PageRequest.of(0, limit));
    }
}
