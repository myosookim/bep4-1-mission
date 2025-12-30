package com.back.shared.payout.event;

import com.back.shared.payout.dto.PayoutMemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PayoutMemberCreatedEvent {
    private final PayoutMemberDto member;
}
