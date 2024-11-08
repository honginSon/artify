package com.elice.artBoard.board.dto;

import com.elice.artBoard.board.validator.AttachFileCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Size(max = 200, message = "200자를 초과할 수 없습니다.")
    @NotBlank(message = "설명은 필수 값 입니다.")
    private String description;

    @AttachFileCheck
    private MultipartFile image;
}
