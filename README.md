# Advent of Code 2024 in Java

All 25 days of [Advent of Code 2024](https://adventofcode.com/2024), solved in Java. I used it to learn the language: each puzzle pushed me into a different corner of Java, from collections and records to Maven and third-party libraries.

I usually got a working solution first, then went back to make it faster or cleaner once I understood the problem better. For example, Day 9's part two hands the heavy lifting to `OptimisedPart2.java`, and Day 11's part two replaces a simulation with memoisation.

## Puzzles I enjoyed

- **Day 11 (Plutonian Pebbles):** a naive simulation grows exponentially, so I switched to memoised recursion, caching the stone count for each stone and number of remaining blinks.
- **Day 16 (Reindeer Maze):** shortest paths with Dijkstra's algorithm and a priority queue, where turning costs more than moving. The same approach came back on Days 18 and 20.
- **Day 17 (Chronospatial Computer):** part two needed reverse engineering the puzzle's program. Each output depends on three bits of register A, so I rebuilt A three bits at a time instead of brute forcing it.
- **Day 23 (LAN Party):** finding the largest group of fully connected computers using set intersections.
- **Day 24 (Crossed Wires):** part two is a binary adder with swapped wires. I set up a Maven project with `graphviz-java` to render the circuit as a graph, which made the broken gates much easier to spot.

## Running a solution

Each day has its own folder with `Part1.java` and `Part2.java`. Puzzle inputs aren't included, since Advent of Code asks people not to share them: save yours as `input.txt` in the day's folder, then run:

```bash
cd day11
javac Part2.java && java Part2
```

Day 24's part two is a Maven project:

```bash
cd day24/part2
mvn compile exec:java -Dexec.mainClass=adventofcode.day24part2.App
```
