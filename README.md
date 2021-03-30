# Programming Assignment
A pawn can move on 10x10 chequerboard horizontally, vertically and diagonally by these
rules:
1) 3 tiles moving North (N), West (W), South (S) and East (E)
2) 2 tiles moving NE, SE, SW and NW
3) Moves are only allowed if the ending tile exists on the board
4) Starting from initial position, the pawn can visit each cell only once

Write a program that finds out if it’s possible for the pawn to visit all tiles on the board
following the above rules, starting from any tile.

## Approach
Recursive Depth-First traversal with a tweak is used to solve this problem. StringBuilder object maintains the current path across the recursive calls. In addition, count of visited tiles is maintained across the recursive calls, will stop the recursoin and print the path when all the tiles are visited (meaning, when the count of visited tiles becomes total grid size, in this case 10x10).

## Tweak
The normal DFS ends up in endless number of iterations. The trick is to loop through the next possible unvisited tiles with least number of next legal moves first. At each tile, we are going to sort the next possible moves in the order of next legal move count in ascending order to pick up the next tile.

## Algorithm
1) Start traversing from (0,0) tile in cheque board.
2) Mark the tile as visited, increment the visited tile count by 1 and add the tile to String Builder.
3) Stop recursion, print the path in String Builder and return, when the visited tile count matches with 10x10 grid size.
4) Loop through the next legal moves (should be on the board and not already visited) in the ascending order of next legal move count from each of these tiles and invoke recursive call (repeat from step 2).
5) If the current tile does not lead to the path which covers all tiles, mark it as unvisited, decrement the visited tile count by 1 and delete the tile added to String Builder.

## How to run

> java org.kurma.ChequeBoard

ouput:
> One of the possible paths to cover all tiles:
{0,0},{0,3},{2,1},{5,1},{8,1},{8,4},{8,7},{6,9},{9,9},{9,6},{7,8},{4,8},{1,8},{1,5},{1,2},{3,0},{3,3},{1,1},{1,4},{1,7},{3,9},{0,9},{0,6},{2,8},{5,8},{8,8},{6,6},{3,6},{5,4},{2,4},{0,2},{2,0},{4,2},{6,0},{9,0},{7,2},{5,0},{8,0},{8,3},{8,6},{8,9},{5,9},{2,9},{0,7},{3,7},{1,9},{4,9},{7,9},{9,7},{6,7},{8,5},{8,2},{6,4},{9,4},{9,1},{6,1},{3,1},{0,1},{0,4},{3,4},{1,6},{4,6},{7,6},{9,8},{6,8},{3,8},{0,8},{2,6},{5,6},{5,3},{2,3},{0,5},{2,7},{5,7},{7,5},{4,5},{6,3},{9,3},{7,1},{4,1},{4,4},{4,7},{7,7},{7,4},{9,2},{9,5},{6,5},{3,5},{3,2},{6,2},{4,0},{1,0},{1,3},{4,3},{2,5},{2,2},{5,2},{5,5},{7,3},{7,0}
It is possible to visit all tiles on 10x10 board
