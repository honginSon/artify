package com.elice.artBoard.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String description;

    //TODO 회원 추가
    private Board(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public static Board create(String title, String description) {
        return new Board(title, description);
    }

    public Board update(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }
}
