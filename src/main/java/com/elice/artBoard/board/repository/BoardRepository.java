package com.elice.artBoard.board.repository;

import com.elice.artBoard.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
