package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.boundedContext.payout.out.PayoutMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PayoutSupport {
    private final PayoutMemberRepository payoutMemberRepository;

    // 홀딩멤버는 username을 holding으로 두고 관리중
    public Optional<PayoutMember> findHoldingMember(){
        return payoutMemberRepository.findByUsername("holding");
    }
    public Optional<PayoutMember> findMemberById(int id) {
        return payoutMemberRepository.findById(id);
    }
}
