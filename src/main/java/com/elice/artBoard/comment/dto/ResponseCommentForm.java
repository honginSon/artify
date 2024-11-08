package com.elice.artBoard.comment.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseCommentForm {

    private Long memberId;
    private List<ResponseContent> responseContents;

    @Data
    static class ResponseContent {
        private Long memberId;
        private String content;
    }
}
