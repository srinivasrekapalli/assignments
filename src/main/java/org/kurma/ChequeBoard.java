package org.kurma;

import java.util.*;

public class ChequeBoard {
    private final int size;
    private final int[][] board;
    private final int[][] moves = new int[][] {{-3,0}, {-2,2}, {0,3}, {2,2}, {3,0}, {2,-2}, {0,-3}, {-2,-2}};

    public ChequeBoard(int size) {
        this.size = size;
        this.board = new int[size][size];
    }

    public boolean canCoverAllTiles() {
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                if (canVisitAllTilesStartingFrom(x, y))
                    return true;
        return false;
    }

    private boolean canVisitAllTilesStartingFrom(int startX, int startY) {
        for (int i = 0; i < size; i++)
            Arrays.fill(board[i], 0);

        return dfs(startX, startY, 0, new StringBuilder());
    }

    private boolean dfs(int x, int y, int visitedTileCount, StringBuilder sb) {
        board[x][y] = 1;
        sb.append('{').append(x).append(',').append(y).append("},");
        visitedTileCount++;
        if (visitedTileCount == size * size) {
            sb.deleteCharAt(sb.length() - 1);
            System.out.println("One possible path to cover all tiles:\n" + sb.toString());
            return true;
        }

        for (int[] move : nextMovesSortedByNextMoves(x, y)) {
            if (dfs(move[0], move[1], visitedTileCount, sb))
                return true;
        }
        board[x][y] = 0;
        sb.delete(sb.lastIndexOf("},", sb.length() - 3) + 2, sb.length());
        return false;
    }

    private List<int[]> nextMovesSortedByNextMoves(int x, int y) {
        List<int[]> nextMoves = nextMoves(x, y);
        if (!nextMoves.isEmpty()) {
            Map<Integer, Integer> movesToNextMoves = new HashMap<>();
            for (int[] move : nextMoves) {
                movesToNextMoves.put(move[0] * size + move[1], nextMoves(move[0], move[1]).size());
            }
            nextMoves.sort(Comparator.comparingInt(a -> movesToNextMoves.get(a[0] * size + a[1])));
        }
        return nextMoves;
    }

    List<int[]> nextMoves(int x, int y) {
        List<int[]> nextMoves = new ArrayList<>();
        for (int[] move : moves) {
            if (isSafe(x + move[0], y + move[1])) {
                nextMoves.add(new int[] {x + move[0], y + move[1]});
            }
        }
        return nextMoves;
    }

    boolean isSafe(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && board[x][y] == 0;
    }

    public static void main(String[] args) {
        int size = 10;
        ChequeBoard board = new ChequeBoard(size);
        if (board.canCoverAllTiles()) {
            System.out.println("It is possible to visit all tiles on " + size + "x" + size + " board");
        } else {
            System.out.println("Not possible to visit all tiles on " + size + "x" + size + " board");
        }
    }
}
