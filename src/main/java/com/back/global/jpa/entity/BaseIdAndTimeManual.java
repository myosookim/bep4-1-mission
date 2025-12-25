package com.back.global.jpa.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

// 생성일 및 수정일이 원본과 동일해야하는데 상이해지는 경우의 수를 제거하기 위함
// BaseIdAndTime 상속받음 : Post, Member
// BaseIdAndTimeManual 상속받음 : PostMember
@MappedSuperclass
@Getter
public abstract class BaseIdAndTimeManual extends BaseEntity {
    @Id
    private int id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
