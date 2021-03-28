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
Recursive Depth-First traversal is used to solve this problem. StringBuilder object maintains the current path across the recursive calls. In addition, count of visited tiles is maintained across the recursive calls, will stop the recursoin and print the path when all the tiles are visited (meaning, when the count of visited tiles becomes total grid size, in this case 10x10).

## Algorithm
1) Start traversing from (0,0) tile in cheque board.
2) Mark the tile as visited, increment the visited tile count by 1 and add the tile to String Builder.
3) Stop recursion, print the path in String Builder and return, when the visited tile count matches with 10x10 grid size.
4) Loop through the next legal moves (should be on the board and not already visited) in the ascending order of next legal move count from each of these tiles and invoke recursive call (repeat from step 2).
5) If the current tile does not lead to the path which covers all tiles, mark it as unvisited, decrement the visited tile count by 1 and delete the tile added to String Builder.
