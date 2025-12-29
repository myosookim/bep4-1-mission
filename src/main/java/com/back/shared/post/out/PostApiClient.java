package com.back.shared.post.out;

import com.back.shared.post.dto.PostDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PostApiClient {
    private final RestClient restClient;

    // @Value 사용하여 yml 파일에 하드코딩된 변수로 변경 및 적용 (유지보수 편의를 위함)
    public PostApiClient(@Value("${custom.global.internalBackUrl}") String internalBackUrl){
        this.restClient = RestClient.builder()
                .baseUrl(internalBackUrl + "/api/v1/post")
                .build();
    }

    public List<PostDto> getItems() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public PostDto getItem(int id) {
        return restClient.get()
                .uri("/posts/%d".formatted(id))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
