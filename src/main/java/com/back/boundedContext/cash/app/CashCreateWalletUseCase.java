package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.cash.out.WalletRepository;
import com.back.shared.cash.dto.CashMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashCreateWalletUseCase {
    private final CashMemberRepository cashMemberRepository;
    private final WalletRepository walletRepository;

    // Dto 추가에 따라 매개변수 CashMember->CashMembertDto 타입으로 변경
    // getRefernceById : 내장된 기능. 실제 조회 없이, FK 연관관계 설정용 CashMember 프록시를 가져옴 (불필요한 SELECT 쿼리 방지 위함)
    public Wallet createWallet(CashMemberDto member){
        CashMember _member = cashMemberRepository.getReferenceById(member.getId());
        Wallet wallet = new Wallet(_member);

        return walletRepository.save(wallet);
    }

}
