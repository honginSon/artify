package com.elice.artBoard.board.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AttachFileCheckValidator.class)
public @interface AttachFileCheck {
    String message() default "이미지 파일만 업로드 가능합니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
