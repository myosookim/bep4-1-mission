package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.PayoutCandidateItem;
import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.boundedContext.payout.out.PayoutCandidateItemRepository;
import com.back.boundedContext.payout.out.PayoutMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PayoutSupport {
    private final PayoutMemberRepository payoutMemberRepository;
    private final PayoutCandidateItemRepository payoutCandidateItemRepository;

    public Optional<PayoutMember> findSystemMember(){
        return payoutMemberRepository.findByUsername("system");
    }

    // 홀딩멤버는 username을 holding으로 두고 관리중
    public Optional<PayoutMember> findHoldingMember(){
        return payoutMemberRepository.findByUsername("holding");
    }

    public Optional<PayoutMember> findMemberById(int id) {
        return payoutMemberRepository.findById(id);
    }

    public List<PayoutCandidateItem> findPayoutCandidateItems(){
        return payoutCandidateItemRepository.findAll();
    }
}
