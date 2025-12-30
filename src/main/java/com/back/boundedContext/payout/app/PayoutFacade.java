package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.Payout;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.payout.dto.PayoutMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PayoutFacade {
    private final PayoutCreatePayoutUseCase payoutCreatePayoutUseCase;
    private final PayoutAddPayoutCandidateItemsUseCase payoutAddPayoutCandidateItemsUseCase;
    private final PayoutSyncMemberUseCase payoutSyncMemberUseCase;

    @Transactional
    public void syncMember(MemberDto member){
        payoutSyncMemberUseCase.syncMember(member);
    }

    @Transactional
    public Payout createPayout(PayoutMemberDto payee){
        return payoutCreatePayoutUseCase.createPayout(payee);
    }

    @Transactional
    public void addPayoutCandidateItems(OrderDto order){
        payoutAddPayoutCandidateItemsUseCase.addPayoutCandidateItems(order);
    }
}
