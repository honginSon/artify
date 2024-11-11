package com.elice.artBoard.board.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class AttachFileCheckValidator implements ConstraintValidator<AttachFileCheck, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {

        if (file.isEmpty()) {
            return true;
        }

        String contentType = file.getContentType();
        return contentType.startsWith("image/");
    }
}
