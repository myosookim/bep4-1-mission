package com.back.boundedContext.post.domain;

import com.back.shared.member.domain.ReplicaMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// MSA 염두 설계
// Post -> Member(N:1)의 의존성 방향을 낮추기 위함
@Entity
@Table(name = "POST_MEMBER")
@NoArgsConstructor
@Getter
public class PostMember extends ReplicaMember {
    public PostMember(int id, LocalDateTime createDate, LocalDateTime modifyDate, String username, String password, String nickname) {
        super(id, createDate, modifyDate, username, password, nickname);
    }
}
