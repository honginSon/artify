package com.elice.artBoard.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBoardForm {

    @NotBlank(message = "이름은 필수 값 입니다.")
    private String title;

    @NotBlank(message = "설명은 필수 값 입니다.")
    private String description;

    private MultipartFile image;
}
