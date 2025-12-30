package com.back.standard.resultType;

public interface ResultType {
    String getResultCode();

    String getMsg();

    // 필요 시 구현 클래스에서 타입 정해서 오버라이드 - 기본 구현은 null 반환.
    default <T> T getData(){
        return null;
    }
}
