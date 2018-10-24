package net.slipp.www.api.dto.board;

import lombok.*;

public class BoardDto {

    @Data
    @NoArgsConstructor
    public static class Response {
        private Long id;

        private CategoryResponse category;

        private String title;

        private String content;

        private String imageUrl;
    }

    @Data
    @NoArgsConstructor
    public static class CategoryResponse {
        private Long no;

        private String name;

        private String descriptions;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private Long categoryNo;

        private String title;

        private String content;

        private String imageUrl;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
        private Long categoryNo;

        private String title;

        private String content;

        private String imageUrl;
    }
}