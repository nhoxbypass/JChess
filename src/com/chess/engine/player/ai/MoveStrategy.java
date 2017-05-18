package com.chess.engine.player.ai;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

/**
 * Created by MyPC on 5/19/2017.
 */
public interface MoveStrategy {
    Move execute(Board board, int depth);
}
