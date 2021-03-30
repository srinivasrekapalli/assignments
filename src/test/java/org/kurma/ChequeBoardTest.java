package org.kurma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ChequeBoardTest {

    private ChequeBoard chequeBoard;

    @BeforeEach
    void setUp() {
        chequeBoard = new ChequeBoard(10);
    }

    @Test
    void testCanCoverAllTilesWithSingleTile() {
        chequeBoard = new ChequeBoard(1);
        assertTrue(chequeBoard.canCoverAllTiles());
    }

    @Test
    void testCanCoverAllTilesWithTilesThatCannotBeCovered() {
        chequeBoard = new ChequeBoard(2);
        assertFalse(chequeBoard.canCoverAllTiles());
        chequeBoard = new ChequeBoard(3);
        assertFalse(chequeBoard.canCoverAllTiles());
        chequeBoard = new ChequeBoard(4);
        assertFalse(chequeBoard.canCoverAllTiles());
    }

    @Test
    void testCanCoverAllTilesWithTilesThatCanBeCovered() {
        assertTrue(chequeBoard.canCoverAllTiles());
        chequeBoard = new ChequeBoard(8);
        assertTrue(chequeBoard.canCoverAllTiles());
        chequeBoard = new ChequeBoard(5);
        assertTrue(chequeBoard.canCoverAllTiles());
    }

    @Test
    void testNextTilesSortingByNextMoveCount() {
        List<Integer> actual = chequeBoard.nextMovesSortedByNextMoves(0, 0).stream().map(ChequeBoardTest::pointToInteger).collect(Collectors.toList());
        List<Integer> expected = Stream.of(new int[]{0, 3}, new int[]{3, 0}, new int[]{2, 2}).map(ChequeBoardTest::pointToInteger).collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    private static int pointToInteger(int[] point) {
        return point[0] * 10 + point[1];
    }

    @Test
    void testNextTiles() {
        assertEquals(3, chequeBoard.nextMoves(0, 0).size());
        assertEquals(3, chequeBoard.nextMoves(0, 9).size());
        assertEquals(3, chequeBoard.nextMoves(9, 0).size());
        assertEquals(3, chequeBoard.nextMoves(9, 9).size());
        assertEquals(8, chequeBoard.nextMoves(5, 5).size());
    }

    @Test
    void testIsSafeForLegalMoves() {
        assertTrue(chequeBoard.isSafe(0, 0));
        assertTrue(chequeBoard.isSafe(5, 5));
        assertTrue(chequeBoard.isSafe(9, 9));
    }

    @Test
    void testIsSafeForIllegalMoves() {
        assertFalse(chequeBoard.isSafe(-1, 0));
        assertFalse(chequeBoard.isSafe(5, 10));
    }
}