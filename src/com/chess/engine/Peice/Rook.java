package com.chess.engine.Peice;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtil;

import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorAttackMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by MyPC on 5/10/2017.
 */
public class Rook extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8,-1,1,8};

    public Rook(Alliance peiceAlliance, int peicePosition) {
        super(PeiceType.ROOK,peicePosition, peiceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMove(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES){
            int candidateDestinationCoordinate = this.peicePosition;

            while (BoardUtil.isValidTileCoordinate(candidateDestinationCoordinate)){

                if(isFirstColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset) || isEightColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)){
                    break;
                }

                candidateDestinationCoordinate += candidateCoordinateOffset;

                if (BoardUtil.isValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (! candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
                    }else {
                        final Piece pieceDestination = candidateDestinationTile.getPeice();
                        final Alliance peiceAlliance = pieceDestination.getPeiceAlliance();

                        if (this.peiceAlliance != peiceAlliance){
                            legalMoves.add(new MajorAttackMove(board,this,candidateDestinationCoordinate, pieceDestination));
                        }
                        break;
                    }
                }
            }
        }



        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Rook movePeice(final Move move) {
        return new Rook(move.getMovedPiece().getPeiceAlliance(),move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return PeiceType.ROOK.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtil.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtil.EIGHT_COLUMN[currentPosition] && (candidateOffset == 1);
    }
}
