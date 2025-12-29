package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

// Lombok + Jackson을 함께 쓰기 위한 생성자 설정
@AllArgsConstructor(
        // AllArgsConstructor로 만든 생성자에 @JsonCreator를 닮
        onConstructor_ = @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
)
@Getter
public class PostDto {
    private final int id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final int authorId;
    private final String authorName;
    private final String title;
    private final String content;

    public PostDto(Post post) {
        this(
                post.getId(),
                post.getCreateDate(),
                post.getModifyDate(),
                post.getAuthor().getId(),
                post.getAuthor().getNickname(),
                post.getTitle(),
                post.getContent());
    }
}
