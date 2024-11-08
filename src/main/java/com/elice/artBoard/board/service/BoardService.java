package com.elice.artBoard.board.service;

import com.elice.artBoard.board.domain.Board;
import com.elice.artBoard.board.dto.RequestBoardForm;
import com.elice.artBoard.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    @Transactional
    public Board save(RequestBoardForm form) {
        Board board = Board.create(form.getTitle(), form.getDescription());
        return boardRepository.save(board);
    }

    @Transactional
    public Board update(Long boardId, RequestBoardForm form) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("일치하는 게시판이 없습니다"));
        return findBoard.update(form.getTitle(), form.getDescription());
    }

    @Transactional
    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("일치하는 게시판이 없습니다"));
        boardRepository.delete(board);
    }

    public Board findBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("일치하는 게시판이 없습니다"));
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }
}
