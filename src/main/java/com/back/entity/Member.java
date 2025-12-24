package com.back.entity;

import com.back.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Member extends BaseIdAndTime {

    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    private int activityScore;

    public Member(String username, String password, String nickname){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.activityScore = 0; // 멤버가 생성될 때 score 0으로 초기화
    }

    public int increaseActivityScore(int amount) {
        return this.activityScore += amount;
    }
}
