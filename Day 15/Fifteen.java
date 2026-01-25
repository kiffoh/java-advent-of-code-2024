
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Fifteen {
    static String warehouse = """
    ##################################################
    #O..O......O....OOO..O.OO.#.O#.OO..O.#.#....#..O.#
    ##.O.OO.#.O...O..O.#.O.O.#O..O..OO#..O#O#O#...O..#
    #...O...OOOO.O#OO.#O#.O.#O...#.#..O.O..O........##
    #O.#.O..O.....#O.#.#.#O.....O.....O....O....O..O##
    #O#...O...O#..#.O##O#.O....#OOO..O..O...O...#....#
    #.O....O...###.O..O.O#....O#..O..O...O.OO.OO#...O#
    #O..##...O.......O.O.#.O..O.OOOO...#..OOO.OO.O...#
    #...O....O.O..O....O.......#....#..O.OOO....#O..O#
    ##O....OO.....OOO.#O.#O.#OO#O...........O......#.#
    #O#.......#........#OO.....O.#..#..O...OO.....O.O#
    #O##...OO..#.O...OOOO..O..O.#.O.OO#...O.O.#O...O.#
    #O#O.#O..O.....O#.##.O......O..#...O....OO....O#O#
    #O...O....#.O..##....O.#.....#...##O##...#.O.....#
    #OO...OO#...O#.#.O.O..O..OO..OO.O..O.....#..#.OOO#
    #..#O.#O.#..O.OO.....O#....O.OO...#..O.O.#OO.##..#
    #O..O.##...O....O..#OO..O..O..O...O..O...........#
    ##O....O....O..O...O#..O....O.OO......O.O..O....O#
    #OOOOO.#....O.O#.OO..O...O.......O.#OO...#O..O...#
    #O....O.#.O..O##...OO..##O#....O.........O#.O....#
    #...........O...O.OO.O...O#......O...#OO.O.O.....#
    #..OOO.....O#......O...#..O..O.#..O..OO#O..O.#.O.#
    #.OO...O..O.O.O##OO.O.OO#.................#..O.#.#
    #.#..#O#.O.........O.O...O.#......O.O..O...O.O..O#
    #..O.OOO......#...O.O...@.O..O...O.OO..#...O..O#O#
    #...#O...OO.O...O..OO..###O..O....O......##....O.#
    #....O.#.##...O#.#O.OOO...#.O.......#O.#.##......#
    ##.O..O.OO..O.#.....O..OO..O#....#..O#...#..O....#
    #OOO.O.OOO.O..O..O..O..#OOO....#O..O.......OO..#.#
    #..O......O.O...O....O.OOO#......O.#....O.....OO.#
    #....#..O#......OOOO.O......O.OOOO...O..O.#.....O#
    ##...OO....#.OO....OOO..O.#.OO.#O..O##..O..O.....#
    #..OOO...O....O.....O#O...O...O.....OOOO...O..#.O#
    #...#....#.OO..#.......OO..OOO.O.#.....O....O.O..#
    #O.O.........OO.O..OO##O..OO.O.........OO.O..OO..#
    #...O...#OO#O..O..#..O#OO.....O..#O.....OO.....O.#
    #O..O##O...OOO#OO.O....O#.O.O#..O..OOO..O#.O..##.#
    #.O..O...OO..O#O.#.O.OO.#OO...O..OO..O.O.O..O..OO#
    ##.O.......O.OO..O#.##O.....O....#.....OO.O.#.#OO#
    #O#..O.....OOO#.....O.......O....O...#..O..O##...#
    #.#..O.OO..#.O.OOOO.O..............O...O....O#...#
    #....O..O..#..##..OOO...........O........O....OO##
    #OOO.#...O....O.....#.......OO#.O..OOOO..OO......#
    #......O...........OOOO#OO.......O........O.OOO.O#
    #........O.O.#....#...O.#O..O.#....O.O#..#..#O#..#
    ##................O...OO..O.#O.OO#.O.....O..O..OO#
    #.O...OO.........#OO......O.O.OOO......O#........#
    #............O.#.........O.....O.O...#.O......OO.#
    #O..O......#O...OOO......#..#.#.....O#O.O.....O#.#
    ##################################################
    """;

    static char[][] warehouseCharArray;

    static int maxX; 
    static int maxY; 

    static String robotMovements = """
    v>v^<v<^^<v<<>>>>vv<>vvv^><>>vvv><v<v^>><vvv>vv^>vvv^vvv<>><vv^>v>v>>^><>v<<<<vv^>^<^^^<^v>v<v^^v<^<^><v<<>v>^v^<v<^v>v<^><^v<>>v>v<>v^<<^^v^^<vvv<^><<v^v^v><^^v<>><>^<v^>>v<v^v^^v<v<^^v^^^^>^v><>^<v^>^<>><v^>vv>><^vv^v^^v<vvv<>><v^><>>><<^>^<<v>^<^>><^>>v^vv>>>v<^>^<>^<^v>^v<>v<>vv<v>vv^v<v>v<^^>vv>^<^<<^^v><>>^^<>^>>>v^v<v>v<>vvvvvv^<>^><<v<<v<v<>v^<v^^^v><^<v>>vv>v>>v^v^^v<<>v<>v>v>^v^vv^^<v<<v<<<^v><>><>v>>v<^<<^><^^v^<v<>><^>^^<^^>><<vvvv<>vvv<>>>>^<v<v>^^v><v^^>vv><<<v<>^<^^v^>v<v><>>^>><^<<>^<>^^^^vvvv^^>^v<<>^vvv>^<<^><v<v<<>>v<^v<>^>v^<><^><<<>^<vv^>><><<vvv>^<>vv><>>v<^^>>v^^<^^>vv<v><<^<^^vv<^^v>^^>>>>v^v>>vv^>v<>^v>v<<<^<>>vv^>^^<^<<vvvv^<<><<^^>^<v^<<^v<>>^>>^>><vv<^vv<<<<>v><><^<<<><^<v^v<^<vv>v^>>^v<>v<><<>v><^^^v>>^<<>vv<>^^><<^>><v<>^>^><v^<><^<>^<^^^^<^>v<^^<vvv^<^<><v^>>>>>><><>^<>^^>v>>^^<<<>>^v<^>v>^<vv>^><^>v<<><^<v>>^<^>^<v^<v<>><<v<<^vv^vv>v<<^vv^^>><v^^^^<<><>v<^<^^^><<v^>>>>^v^^<v<<<^vv<<vv>>>><>v^vv>v^><>^><>^<^<v<<>v<v^vv>v<<v^^<^<^v^<><^v<^>>>^^<>^vv^vv^<>^
    ><<^v<^v<<>><>^^v>>v^^<v<^>^v^>v<^<^>v^v<<<<<<>vv>^>v<vv^v<><<>>>>>^^vv<^v^v^><>^^v<^^vv>vv^^vv<v<<<^^^^<><><v><><>>v><^^>^^><^>v^><^^<>^<^vv><>>>^^<^>vv>v<v^>v^v<><^<>^^v<>>^v>><v><v^<><v^>vv>^<^>^v<^v<^<^v<>vv<v<>vv<^<<^><v^v^>v^>v<vvvvv><<><>><^<<<vv<^v<^^v<^v^<<>v<><^^>><v<^^^vv<<>^v^v<>^^><<<v^<v>v^vv^^^vv<<<v<v^^>>^v<^^^^^^>v<<v<<>^>><vv>^v>^><^<<^v>^<^>>>^v>>v><<>vv>>v^>^v<<^<v<v<vv<>^><vv<vvvvv<>vvv^<>><><^v^<<>^vv^<>><>v>vvv^<v<>v>^vv<>v^vv><<vv^v^v>^^>^><>v<<<^<^^v^vv><<^v>v^>><^<v<>^<>v^>v>^<^v^<><^>><>^>^v<<v>>^<<v<^<^v^^>>^v<<vvv^v<v<^>^vv<v><v^><v^<>>><<v<^<^>^^>>vv^^v>>v^^v^^v<^vv<>><^>^^^>vv<><^>>><v>^>v^vvv<v><>^>vv>v^v<>>>^>^>v^v>vv<v><><^<<^^v<>>^^<><>><^>v<v<<v^><v^vv>v><<v>v^^<v^^<<v^^<^^<<>v<vv><<^<<v><>^<><v>>^^vv^<>^>^vvv<>^^<>>^><<^^<^v<^^v<^^^^v<<^vv^>>>vv^v>^<>^^<><^^><>^<>v<^>v^<<>^<v>v>v<v>>v^v^v>>^v^^^^>^<>vv^>v^^>><<<>v><^<v<v<^<v>>^vv^<^>>>^<v^><><^<<<^>vvvv<v<v<^^>^v>v>^v^v^>^<^v^>><^<v^vv<>^^<^^v><<^>vv^>vv><<>>><v>^<>vv<^v^>>^><^^v<vv^^>vvv>^^>v>>v<>v
    <<^vv<vv><<<<><<v^vv>>^v>>>v<<><v<v<vv<><<>^v^v<^v^v>^vv^>>>v^v^vv<<v^<^>>>^v><><<<v>v<>>>>^<vv^^vvvv^<>v>v><v<>^vv>v<<<>v<^vv<>^^v>>>v^<<<^>v<v><v><>v^<>>v^><vv<>>^v>>>^<<>^^<v<<^^^<>v>^><>v^>v<v>^<><v<^^vv>^>>^vvv>v^>>^<><v^>v<<^^>>>>^><<v^<^^v>>^><vv^^<v<>v^>v>vv<><^v<v<^>v<<vv<vv>v<>>^>>^><v^^<^<v<v^><><<^v<v>vv^<vv^^>>v^>v^^^>vv>v^<vvvv<<>v>v>^>vv><<>><v^v^^<v>>^^v>>vv^^v>^v>^<vv>^^^<^vvvv><<^^vv<<><^^>><vvv><>><<^v>>^^<v^vvv^vv>v<<><<>^><^v<>^<<v^>>>^v^^v<>v^><^^v^><<>v>^vv<v^^v>v^v^^^><v<<<^>><^v>>><v^>>><v^>^>v<<^vv>>><<<vvv>^<^vvv^>><>>>^<v<>^v<<^v^v<^v^<<<^v>v>v>^v^^<><^<^>^>^v><^>v^^^<<vv<^<v<><^>v<^v^^><v<^><>^v^vv^v<><<<><<^vv^>><>>^^<v<v^v>v<^>^<>vv<>><<<^>^<vv>^>^v<v<<<v<><>>^>><^v><<vv>v<>v>>>><^^v^v<^v<>v^<<^v<v^<>v<v^^<^>^<^>v<^>vv<v<>v><<^v>vv>^^v<^^>^vv^>v^>vv<^^v^^v<^>><v<>^<<<>vv^v^v<>v^^<^>v^>>^>>><>>>^v<^v^^^v^^^v>v>v<^<^vvv^vv<^<<<vvvv^^>v>v^vv^>vv>v<>><^><v<<^><v^<^v>>v^><v>v>>>v><>^^v<><vv^v>^<>>^><^^>^<<<v>^<v^v^>^v^^v^<^^vv<^^v^^<>v^^^<v<<v^v<>><>^v<^^vv>>^
    <^v^<^>v^^<>v<v^>v^><^>^>^><^v^^<^<^>><^>>>><>><v^v^><<<^>^v<<v<><>v>vvvv<<<^^>v>^>>^<<<>^^vvv<>^v^^<<<>v<>>v><>vv^>vvv>v>>>>^<v>>^<v^>v>^><<vv^^v^<><<v>^<<vv^<>^^><^>><^vvv>>v^v<>^<>^v<<<^>v^>^<^v>v^<^<^^>v^v<>v^v>^<<v<^v<v>v><>^>v<^<^>^<vv>v^vvv><v<vv<><^^^v<>v^<<^>^vv>^^<^>>>vvv<^><v<>>^^<^>vvv^<^v^^<>><<v^^>v<v^^<>^<<^<>^vv^v>^v<>>^<^<<<>v^v<v<<>vv<>^v<^<<>^<>^<<^>v<>>><v>>v<<<<^<v>><^^>v<^v>^v<^v^v<v>>><v^>v><<<>^vvv^^v^><^vv<>>^>^^<<v>^^>><v<<<v^<<v>>^^><^vv^<<>v<^<v^>v<>>^^>^vv><<^v<vvv^><v><>^>^<v^^<><v<v><<<<vv>^^<<v<>>^>^>>v^vv<^v><^<v>^v><vvvv>v<<><><>v^<>v>vv<^^>><>v<<>><><^^<><>>^<><<^vv><>^^v>>^>>>v<v>>^^<^v><v>>v^^vv<>><<<vvv^^^^>>^>v<<^>v^^<>>>vv>^^^^vv^^<^v^>^<<<^v<>v><^vvv^>>^<v<>vv>v<vv>>v<<<><<<<>vv>v<<><>>vv^^<><>>v^<<>>^>^>>v<<<vv^v^<<>>>^^<v<><>>><<<>v^<v>^^><v><>><v^>>v>><^>v^>v<v^<>^>^^^v^vv>>><^^v<><^^^v^<v^v^>v^^^vv><^^<^^>v<>v^^><>v>>^v<v<v^^><<>>v>^v^<^<>^vvv>>v<^vvvvvvvvv^^vv<^v<^<<>^<>^>^vv^<<v^>v<v<><><><<<>^^>>>v<>>><^<<>^<><^<>^<>>>^v>vv^^<>^^<>^>^v><v
    ><>v<^v^>>^^^vvv^^^v>^<<v><>>>>>^>>v^<v<^>^<^^<>v>>><vvv>><v<<><>^v<v<v^>>^v>v^>>^<^><<<v^>^<>v^v<<<^v<v<^>>^^^v>>><<><><>vv^>>^v>>^^vv^<><v^^<^v>v^>>vv><v<v>>^><^<<<^^><v^><>^<<<<^><v^<><<<v>^v^><^^^>^vv>v<<v><<^><^<^><<v^<<^^^v^vvv>v>v><><><v<vvv<><vv<v>v^<<^v^v^v>><><v^^^>v>>>^v<>^^v>^>><vv<><><v<>v^<vvv^>v^^<<vv<<v><v<><<>><^<<v<>^vvv^>vv^^v<vv^v^^vvvvv<<v<v<^^^^^<<v>v^v^>>>><v><><v><>^>v>^>>v>vv^<<^v>^>vv<<>>^v>v><>^><^v^>^vv>^>vvv<^<<^>^^<<v>>^vvv<<vvv>^^>v><^<><><v<^><<^v^>>v<>^^>v^v<^v<vv>>vv^v^<vv>^>v<v<vvv<<>>v^>v^><><<<<>>>>^<v<^<^>v>^>^^^^v>^^>^^^<v>^vv^>>>><>><>^>^>v>v^>>>^^^>vv>^v<>v><><^v>>^<^>>>>><>><v^<><<>v^v>v<v^v<>^>v^>>>^>><^>vv>>>><v><^>^v>>^><^>^>vv<v<>>vvv<^<><^<>>^>^^v<vv><>^^<>><>^v>>^v^><<v<<<><^>v>>><v<^^^>>v>>^^^>vv>>v^>>v<<^^>v>vv^>^v<^><<v><^v<^v>v<>>v^<>>^<v<>^>v<v^<>v>^>>v^v><<<><v<>><v^v>>vv<>>v>v^^<>v>^^>^^^<v<^v<<<^v^>>v<^^<<^<<v^^>>^<vvvv><>^v^^<<>>v><>^v<vv<<<v>^<v<<>><v^>v>>^>>>v>^v>^<>^^v><^v<v>v^><<<<^><><>vv<v^>><v<^<<>>^v>><>v<<v>>><<vv><<>><<
    ^<vv<^><>>v^vv<^v<v^^>>>^><v^<v<<<><<><<vvv<<><<<<<v>>><<^>>v^<><>>^<v<>^<vvv<^<v^^<<<v^>>vv^v><>v^^<v<^^^^^><v><>^>>v>v^^><<v<v<<>>vv^<>vvv>^v><>^v><<v^<v^>><>^v><^><<>v^>^v>v>>><>^v<>v<<<<<v<^><v><^><v^<v<<>>v>v><><^v^vv<<>>><v^<v<^>^v>^^>><>^>^^>^^^<^v<v>^<vvv^^^^<v^>v<<>vv<<>^<^>>^v<^<v<<<>^>v^>^<v^<<v<^<v><^^<<>vvv<<>v><>>^^><<^>v^<>vvv<^<^^>>^^<>>^^<^>^^v>^vvv>><^><^vvvv<v>v^v^<v^^>v>>v>^v><<>v>v<<<^>v<><^<<^<>v^><>vvvvvv>vv<><^v>v^<><v<>^><<<<>>^^vv<<^>>v^>>>^^<^<<v^<<^<<<v<v<^vv^v>vv<<^<<<>><<>^v^v<^>>>><><^><<><v<<v^><^<v^<v^>^v<vv>>vvv^>^<^v<<<^^>v<<>><>vv>^<<<<<><^<^^v>^><><v>^^v>vvvv>^^>^>>>vv^^>v><vv^><>v<v<<^><v^v><^v<^<v><>>v><^v>^v^>><vv<>><><>><v>v<vvv>v^<>>^>^>v^>>>>^v<^^<>><v>><<v<v><<vvv<vv<<<<<<^<<>v<>>^vv^><v^<<>^><v^v>><>>vv<<^v>^^^v^><vv<v>>^^>>>v>>^v^v<>>^<<^>^><^<>>vvvvvv>^^<>v>^>v^<<<v<><v<>>^>>^v<^v^<^<><<^<><^^^vvv^^^v^><><^^^<<<>>v<^^vv>vv>^<v^<>v>v^><>>v^^^^<^^>>^<v^<>v>>><v<<<<vv<^v>>v^>vv<>vv>^>vv>><^<>^<vvv>>v<>>v<v<<v>^<v<^<>^^^^^v<<>>^><^v^<>v>v<^>vv
    <>><>^<^v><>^>><^^^>^^^^v<>^<<vv^^><^^^>><><^^^><<<>v^>><<v><v<^v^<<>>>^v<^v<<<^^vv><vv><vv>^v<^>v^<v^v>>>^^v<<^^>^<<><v^><>v><^<>>><>^v^v^v<v>>v><<v>><><>^<v<^><^<vv^^>^<^<vv^v>^^^<<<vv<<^v<v^<vv>>v^^>^v>vvv>v><^^v^v<<<>^><v<v<<^v>>v>vv><><vv<^^^v^<>>^^^>^<v^v>vvv<<><>^>>>>>>vv<vvv<v<>>v><<>v>^<><>^v<v<v<vvv^^^>^^><^<^<>v^<>^vv<^^^<^<^>>vv^v^<^><<^v^v>v<><<>>v<vv><v>v<vv<><^>><>^^vvv<^^^^^^^v>>^><><>v>><vvv<vv^^^^<v<<<^v^v^^v^^vv>vv>^<^>v<<^>^<^vvv<vv^v^v<>>vvvv<<^>vv>vvv<v<v<>^vvvv>v>^^^<<^<<v<^vv<v>^<^<vv><vv<v<v^^^^<^^^<v^^^^<v^<v>v>><^^v>v<v><^vv^>v^v<^<v><vv<v<<<vv<vv^<^<vv<><<v>v<v<>vv^^<>vv^<v^v<^^v^^^^<vv^vv>><<v>v>^v>^><>>>><><<^^><^>>^<^<<>>^v<^v><<<<<^<^><v^v^^>^<<>v^^>^vv^<<v<v>^><^>><>v>^<<v><^v^>^v<<><<<^^<<^><v<^><<v<<^^>><v<^^<v<>v^v<^^>v>><<<v><v>^^>^><<^v^v^>vv<vv>>v<<<<^><v>v<v<^vv^>^<><^v<>^>v^^<^^v^^vv^>>^<^>v><><^<v^>v>^^>>><^<vv>v>>^v<^v>>^>^>^>>^vv><>vv><>v><>vv>><^>vv>v^>vv^>^^v^>v>>^<vv><>^^><^<<^>><<v^^>><<<^^<<^v>v<<^>>>^^<^v<>>v>><v>v<^>v<^^>><<<^^>>^v<^>^
    <><^^><<>^^v>^<^><<^><>^^v^<^><<<<^<v><>v^^>^<<^<v^<v^v>v<<<<>^><<vv><<^vv>v<>>^>^>^vv>>^>^^vv<^><^>>>><^^^>>v>^v<^v>>>^v>><^<^>^^<<vv<><^^vv><>>vv<^<^v^<^>v^<<^v^vvv^v>><v^v<>v><vvv^^^<^>^v^<>v<v^<^<v<<^<^<>vv^^<vv^>>^vv>>v<><vv<><<^^<<>v>^v>^^v<<<>>>>v>v>v^v<<<><<^v^>><>><v<>v>v>>>v<v><vv>^<<>>>^<^^v<^<v><v<^v>^vv>><<v<^vv>^<>vvv><><>><<<<>>^^v><v>^<>><<><^<>>v^vvv<><>v>^>v^vv^<v>vv^v^v<v<v>v^<<^<><v>>^v^^vvv^<v^>vvv^vvv<v>^>><^<<<vvv><<v<^>v^v>>v><v^v>^v^^>^<>v^^v^><<<<v^vv^^v<v<>v>^<^v>^v><>vv<v^^>vv<^>>>^<>>>v^<v><v>vv^<<<><><^>^><>>v^vv^>^<><<>>>v^><v^vv<v<><v<><^<vv<><<^v><v><<>>^vvv>>v^<^>v^><>^vv^><v^><<<v<vv>><^vv^><^^v><>vv^<^v>^^<^<v^<<<>v>>>>><^v>^><>>vvv><><v<>>v><>^<>^^>><v^<<><<vv<>^><v^^v<^^>><v>v<v^^<^<<<^>vv>>v^>v^v^^^^vvvvv<^^v<>>>^v^v>>^<v^<<^>v<^>>^^<<^^^>>><vv>v<<><v><v<<v<vv^^^v<<<>>^>^^<v^^><>^^^>^>>^<v<<v<>^v<<^^^v>><<vv^vv<v<v^^<<v>v><<v>>>v<^<<>v<><>v>^v<>^v>^>^^<^>v^v^>v<^>^>>v^<^^vv<v><v<^^>>>v>>v^v><^<^vvvv>v><^vv^<vv<>><vvv^vv<>v^<<v>>v<^v>^>v>^v^^v>v^v^
    >><>>><>^>^^<>^v>^v>>^vvvvv^vv<><^v^>>><^^<v<<v>>v<<v<>v>>^<<<v>v>>><^<vvv>v^v<^<<<^^^v>^>v>v<>^<v^<v^v<><<v<vv<>>v<v>^<v>^<<><<^>v^^><<>>>v^>v^<^v>v>>v<><>^><<<><^>^>^>>>^><vv>^<v>>>>^<><<<>^^>^<^v>^^<vv<^v^^^vv^<v<v>^v<^v<><>>v^>>>><>^^<^^v^v^<v>>>v<^v^>^>^^^v<^v><<v<^>>^^<^>vv<<<>>^v>^^<v<^v<vv<v<<^v<>vv><v^^<^v><^>^v>><<<<vv^><>^<>>^>vv^v^>v<^><v<<v^>>^>^v^>v^>^<<^^>^><>^^v^<>vv<><><v<<v<v<<<^<^<<><vvv^>>v<<^v>>>>><><v^<v<v<>^^v<>>v>^v^<>^<^v<>^v<<<<>^v<v^v>>>^v<v<^>vv<^><><v^^<^><^>>^^v>^^>^><^>>>v>^^v<^>v>^v>>vv<v^>v<^>vv<<^^v^vv>v^<<v<^>v^vvv>^^><v<<^<^><>^>^^<<<v>^^^^<><>><v<v<<^><^<^v>v^<^v>><^<<>>^<<>>^>><>^><^^vvv^v>>^>vv^>>v<>^^<><>vv>v<>v^<<v^v<v>^<><<v>>v>vv><^^<^>>vv^^^>vvv<^>^^v^v<vv<^^>v^<<><vv<v<vv<<^>^<^<>^>>v<><^<<<>^>>^^^<>^^^<v>^^^^^>^<^vvvv^^v><<^vv<>><^v^v^>v^vv^^>^v^v>>v>>^<>vv<^vv^<^^>v^>^<v^^v<>v<v^<<<>^^>><>^^v<>^<<<>>><>^^<v<<>v>><<^v^<><>>^<vv<^^<><v^><<>>>^v^^v^<^v^v<<v^><v^<^<>>^<v^<<<v><<^><<vvv<^>^>v^<v>^v>v<^<v^vv>^^^<><^v^<>v>><v>v^v^vv><^<>^^>^>^<v^
    ^>^vv<<^^^v^>^<v^v<<^<>v^^><><v^v<v<v<v<^<>>v<<vv^><<<<><>v<>^^^^v^^^vv^v^<^vv>>>><<v<v>^>><^^^><<>v>v^>v>>^>><^^v<<<^<<>v><^>vv^vvvv^>v<^^>><>^><<>>>^^v><<^<^v<><>^<>v<><v<v<v><v<^vv><>v>v<v>v><<^>>v<^^>^>v<vv><^^^<><>v><>>><<v<><^<>vvvv<vv<><^>^><>>^<>^^>^<^v^v^^^^<^><vv<<vv>>v^<<<<><>><>vv^<v^>vv^><^^v^>v^<<>vvv>v<^>><v^^v^>^^<^^v>v^^v>^v<^>v<<^v>^<vv>^<<^<^<v>v<^>^<>>>>vvvv^^^>^^>^>^><^^>>>^<>>v^<<^>^<v^^^^^>><^^<vv<>v>^<v><^>^<^<^<v><v<><><^^>>><<^>^>^^v^<^>^>^vv^v^vvv^><<vv^^<^^v<v^<<^^^v<^<v<^>^^^vvv^^vv<^v><<^^<^<vv>^<<<v<vv^<><vvvv<<^<vv<<<^^><^>>>^v<v^>^<^>^><>vv^^>v><^vv<^<v<^<^><^^<<<>><<v<v><<v^v<><v>vv<v^^>v^<v<^><^>v^^v^^<<vvvv<v^^<><^<>v>v<>>^><^v>vv^^v>>vv<v^>^<>^<^<<v^^vv^vv>^<<<^v>><><^v>><<<v<<^v<<<v<<v>^vv><><><^>v^<vvv^^>^^vvv^v<v<v^<^v<vvv<^v<<vv>^<<^v<>^>vvvv<<^v^>^v<<<<<><^<^^^<vvv<<v^v>^v><>>v<v^v<<<>>>v^<>^>vvv<^<>>^^<><v^>>>>^^v<>^vvv>>^^v^^^<v>^v^^^<<^>>^>^<<v>>>><<>^v^>v^v>>v>v^v>^^^v^><^^<<v>^^<^<<<v^>^v>^^v^>^^^^<v<<>^v^^>^^>^><v>^^>>v>v>><>^^<^^v>>vv><<
    ^v>v>v^>v><><v<^vv^vv>>>^>^>><vv^v><^^>^vv>>^vvvvv<^<v<><<<<<>^<><^<<<^<v^v>><><^^><^v^v<<>>>^<^<<^>v><>v>><<v^v>v><<<v>^v>^^<<^v>^>v>vv>v><^^><v<<v^<v<>v<vv<v^^^v<v^><v>>v^<v^v^<v^v^^v<>vvvv>^^^^>vv><>vv^v<^v>v^v>v<<<<>^^<<^^<><v<v^<v>>vv^<vv^<^>><<v<<v<v^^^<^<^^vv<<^v<<v<v>^^v>^^<^^>>>><<>>vv^<vv^vv^^<<v^<>^><^^<v>vvvv<><>^^v<>v<^><<><v>^^^v<v^>v>>^^v^vv><v>^vv>v>vvv^^v>vv<vv>>>^^^vv^v^<<v^>>><vvv<^v^>^>^v<><>^vv><><<<v><^v>v>^<vv<vv^^<vv>>>^<<v^<^<^^^>>^v^v^<<>v<<v^^<^v^>>^^<>vvvvvv^^<v<v<v>>^v>>vvvv<<^^<v<><^^v<v<<v>v<<>^><v^vv^>vvv>v<<v^<<<><<vv<<v>>>^vvv>^^^>v<><<v<v>vv>^<<vv<^^><vv^>v<<><>><>>>>>><><^<>vv^v>v^v>v^<v>^<<v>v<^^^^v<v<>>v>v<<<^^<<^^<<<<v<^>v<^^^^vv^^>^>>>><<v<>^><>>^<v<<v<>v^>^^^^^<^<^vv^v^^>v>>v>>>>vvv><^^<<v^^v^>^<^<><vv<^^^<^<^<v>v^<<<^v<v^v<^vvv^<v>v>^<^v>^><<^^v>><v<>v<<<^v><^>v>v^v>^>vv^^<>>^>^v^<^^>^^>v>>^v^>>^>^>^>>^^>>>^v<v^v>v^>^v><^>^<v^<<^^>^^<<>>>v^^<><^v<>><<<<v>^vv^<<v^><vv><v><^<<^v^v^^<v<v<v<^<>v^v<^<^>><<v<<>>^><^^>vv>>vvvv>vv>vv<>^v>v>>><<<v<v<>^<
    ^^v^^^^^><v><^v^v^vv^v<^v^^>^vvvv>^^^^^^<v><v<><>v^v<^<>^>^^<<<v^<v<^>^v><^^^><v><^^^<v^vv<^<vv<<^><>><<v^><^v><>v^<^^>^v>^v>><<vv>^^^<^^>^^v<><<<v^v<^^^^^vv>^>>^^>><<<v^<<v^><<^vv^^vvv^<>>v^^v>^<<v<^>^>v^>><>>><<^>><<v>^>v<^<>vv<vv>><^v^<><<><vvvv><v^>^vvvv^^>^>^<><>v^>>^v><>^v<^<<^<><^v^^>><<<><><<<v<^<^v^^vv<^v>v>^<<^^^<>v<>^v^<^vv<v^<^>^v>^^^>^>v^v^^^v<<v>v>v^<v^v<<>v<^>^v><<v<>v^v><<v<^>><><^v<v<vvv<><v<^^^<^<<^^^<vv^<^>v^v><<^v>^^<>^<<v<^v><vv^>v^><v<<<^<^v<^^^^vv^><v><<<^>>^<<^^<<<><><>>vv<vvv^<^<>vv><<v^^><^>>><^v^^>>>^<^^>>^^^<v><<^<^^vv^^<v<v><^<v^<>v>>>^<>vv>^v<^^<^>>v>>><<v^^<<>>>>>v<<^^<v>>^^^vv<^v<<^<v>>><^^><>v^v<>v^<<<>^>>^^^v^vv><^vv<>>^>^>>><v^<<v^v^<>vv^>v<v><^^<>^<^v<>v^><^<<^>v>>^<^^v>^<^>^vvv<><^^^<>><><<><v<>vvvv>^<vv^><<<v<v^<<<>^^<<v<^<v^v^v^v>^v>^<^v^vv>^>><<>>>^vv>^<v>^<v<^vv><v<><>>vvv<^vv<<<>v^>^v>vvv^>^v^v>vv^<v<v^>>><>^^^>^<<>^><>^<<>v<>^^<><v<^<v^<^^v>vv^<>v<v>^^<v>><<><<vvv^<v>^>^^<<<<>>^>vv>^><><^^<>v^v>v<<><>^^>>>^>^>vv>^v^v<v<^^><v^<^<v<<v><<<<v>>^<^
    v^vv><^<^^<>v^<vv<>^v<>>>v><<<<^^<v<<<>>^>vv<^<><<<>^v<<^^^v><>><v<<><<^>^<><vv^^^>>^<>^^v>v>^v<<<>^<><vvv<>>^v>^>v<>>^vvvv^<><>v>vv^<<^>>v>^><<>v<<^v^<<>v^>^>v<>^vv>vv<v<>^v<v^vv>vv^<<>^<^><v<^^v<v^><^^^<^><v<^<v<<>>vvvv><^v^v<v><<<^^^v>^>^^<v^<<vv><>v<<<><vv^>><<v>vv>vv>>vv^v^<><^^v>><<>^v<vv^^vv<<vvv<<v<>v^>^^v>>vv<^<<<<^<>^^><>>>><>vv<<^<>^^vv><v><^v<^>v>>v><^^^><><<v<v<^^<>^v>vvvv><<^v^<>vv^^>^v^v<v<>^>><<>>v<^^><v>>v<^^><^vvv^v<<v>vv^>v<><v^<v^>v^v>vv^<<^^<<<><><^>vv<><^v>v^^>^<>><^<v<^>v<v^^^^vv^>^><>v><<v^^v^>^>^<vv>>^><>v<^v>><>>>^><^<v^^<v^^><^<^>>^<v^<<>vvv<v^vv^v>v>>v>^>^>>><<><vv<>^^v>v^^v>v><^<>^<v>v<><<>>v>^<^<<v<^>v<^^>v^<<v^^<v>^v>>vv^>^>^v>^^<>v<<<><^v^v<v<>>vv>^>^<>>><^^v^<<v^<>^<>>><<^^v<v<vvv>v>v^^<<^>>v^v^>^<v><vv^<^<>vv^<^^v>vv^v>><><^^<v^vv^<<^^<<^vv<>>v<v>>^vv^v<^^v>><<>v<^^>^<<v>>>v>>v<v<>^v^<>v<>^<v^^<^<<v<<^<<^v<^><<<^><<^>^>^v>v^<v<vv>v>^<<^><^^^vv^><v^v>^v<<^v><^^vv<vv^<<><<v<>^>v<v^v>>v^<<><<v><vvv^v>^>v<vv>>>v>><^v<^<>^v^v^<><<vv>v<>v<v^vvvvv^v>v><<^<v<>
    vv>><^<><v^v^>vv^><v^<>>^>>v<^v^^<v^v^>^v<^^><vv^v^^^<v>>><><<v^>v<v^>>v^vv<<^v^<<<<<^v>>v>v>^<<<>^>v^>>><v<^>v<<v>>v^><>^><^^^^^v<v<>^<^v<v^>^v<<>><>vvv<^^>>^<>>^v<>vv<v^v^>><^<vv^v>^<>>^^^^^^><^<v^>>v>v>v^<^^<v^v^><^^><>v>^<^>>vvvv>>>vv<>>><v<^v>v^v^>^>>>>>>>v<^>^<vvv>^^v>v>v<vvv>v<v^^>v^^^v<v^<^^vv<^<v><<^><v^^<<>>vv<>v<^^^^><<v^><<v^<vvv<v^^<vvvv^v^v<^<<>>>^>>^>^>^v^^<^>>^v^^<vv<^>v^vvv>^><^^<v<v>^v^<>^>>^>^^>v<^^v>>^^v>^vv>^v>v>vv^><>^>>>^>^v<^<>v^>>v<v<v^>>^><><<<v^vv^>v>v><<<>vv>v^<v><^^v^>><^v^>v>v^vv^^vv><>vv<v>>><^v>^^^<^^^>>^^^^v>><><<v^<^<vvv^^<vv^<^>v>>^<<^>^vv^<v>>^v^v<><^><<v>>^>^>^<<^vvv>v>>^>^>v<<^v^^^<<^^^<^vv^^^<<^v<^>>^<^<>>^<><>^<><^<^v<^v^<^<^^v<><><<>vv^v>^<>><^<^vvv>>^v>^^^^<^^<>vv<>^v>><<^v^v<^v>^v^^><v<^<<<>><v>v^^>><^<>^>vv^v^^vv<>v<>^v<<>>^><^^>vv^>><^<>vv<><<<>>>><^>><v^><^^<v>^<v><v<>><^^v<^^v>>><<<<>^v><v>^^vv^>>>>^<<vvv^>>v<<>vv><>v><<^<v^>>>v>v^<><>v>>><<v>vv>^^v>>^^^v^v<<^v^^^v>><<^<><>^<vv>vv^<v>^v<v^<vv><v^><v><^v<v><v><vv<<>^<v^<>^>v^<^<v<<>v<<^<>^v
    >^v<v>^^<><<><^v<^<v^<>^^^<>>v><<<>v><>^v>^^^<v<^>^<vvv<^>^v>v<^v^><<>vv<>^v><>^vv<<v^>^<v<<v^v<<<<<<<<v^v>v<^vv^><>^<v^><<>v^>>vv<^vvv>v>^^^vv<v<vv<v^>v^^<<v^^^v<<^<<^^<v>v^^^^<<v>>^^v<<<><>vv<^v^<<^^^>v<vv^<v^>vv^<<v>^<v>^<><vv^^^^<><^^<>>>^<>^<>^^v>>^^vvv<v^>>v<^<vvv><<<>^<<^v^><^>>^>>v^<^vvv>vv>^><^>><<^>^><^>^v<><^v^^v^<>v^v>v<<<<><^<v><^>>v<>v^<v><<>v^^^>>^>v<>^v>^v><^v>^<<^<vv<v^<v<>v><><vv>vv<><><vvv^<>^^v>>^^>v<<<<vv><<vv<^v<v^><<>^<v>><^^vv>vv><v>>v^>>><v<^<>v>^v>v>v>^vv^<v^^><^>v<v<v^^>vv>^<^<>><v<<v<>^<v^^^>^v>v<>^>>^vv<<>v^^v^^^^^^^<v^v><v><>v^^><v>^^<>>>^^<<^^><^<vv^v>v^><v<v^>v<v>v^^>^v><<><>^<>^^<^><>v^><><<><^>>^^>>vvv>>>vvv><>>><^^v^>v<<^v>v^<><v><<^>>v^><>v^^><^^<^>^<v<>vv^>>^^><<^v^<>^>vvv><<^^><>^^v<>^v^^^^^>v<vv>>^>vv<><<<>>vvvv^^>^^v>>>v>vvv<v<^^vv>v>^>><v><^v>^<v><^>^^<^<^<v<^v<v>^^<^v>v^<<><vvv>v^><>v>>v<<<>v^^^><>>><v>^<<v^v^><>v^<>>^v>>v^v^vvv^<><vv>v><<vvv>^v<<^<v<>^^^v^^<<^v^><vv>v^v<v>^<>v<vv<<^v>^<<>v>v<^>^v<>v>><v^><>v^<>>^v^<<<v^>vv<><v<^<<><^^>><v^^>^>
    ^<^v^<v>v^^>v>vv><<<<<^>v>>vvv<<^v<><>^vv>v<>^>vv<>^<^>^v>^>^>v^v<<^v>vv<^^>^^^v<v>>^<^<v^<^<^v<<<>v>v<>vv><>>v><v>vv>v^^<^^<v^v>>v>>>vv>^v^>^^<>v<v^^vv^^<v<<^^v^vv<v>>vv^v^<^vv>v>^>>>><^<^vvvvv<<>>v>>v<v>>><^^<v>^v>v<><v<vv<^<><^^v^>^<^<^<^v>><v>v>^<^^^v><<>v^>v>>^^>^v><^<>^^v<<<v>><><v>vv^^<v>>^>><v<v<^^^^^^<>>v^<^<>v^<>v><^>^^>^>>^>><v^<^><v>v<^>^<<<>>^^<<<>>^^v>^<><^<v^^<v<<^<>v<^<^<>><v^<>>v^^>>v^^<<><^vv^<>^^<v^<v>^>v^><vv>vvv^^^<<v^<<^<vv<<>><>^>v>vv^<>vvv^v>><><<><v^^v^<>v<<v^vv<^^vvv><vv^<<^<^<>>>>>vv<<<vv<v<<>vv<^v<<v<^>^<<>v>v<>^v^v<v<><<<<vv^^<^>^^>v^v<<<<vv>v<>>v>vv^v<<<>v>^^v>^><>^^<>v^<<<v<>v^v<vv>><vv<^vv<<^<<^^>>v^>v><<v^^^^>><^^v^vvv<^^>vv^<>><<<<^>vvv>^>vv<>v^>>^^><>>^<>><><^>>>>>^<>v^>^vv^>>>vv<v>^vvv>>v^<v><^><^^v><><<<<<<vv<><v><v>><>v>><^v<<>^>>>^<v^<<<><>>^vv>><v>^v<<>v>>^v^v<^>^^v<<><^^><>><^v<>>>>^^v<vv<>vv<<>^v<>^<^<>v>v<v^^<^vv^>>><^v^v<^>>>^<<v<><>v><v<vv<>>><>v^><^<^><<>v>>>^<><>v^^^vvv^><<<<v^v^v<^^^<^^<v<^v>v>^^<^><<v<v><>>^>v>v<^<vvv<<<>>^vvv>>v<v<v^>v<
    <>>^<^vv>>><><^v^>^>v<vv><v^v<>><<^^vv<>^<>vvvvv<v><vv^^^<^<<^<v<<v>v^^^^v>v<v^>^<^<<^^>^<v^v<<>^>v^^><v>>><<^v<^<^<<v><><v<^<>>vv>vv<v>^><v^^v^>^vv><^>^^v><^^^<<vv^^^^v>v>^<^><^v<^^v>>v^v>^>^><<<><>^^>v^v<><v<>>^<<v^^v>^>v>v>v^<>>>v^><><>vv<v>^^<>^<v><>v<<vv>>^^<^<<>>v^>^v<v><<<v^><>^<^v><>>^^^v^^v^^<^^<^><^v<>>vv<^^><v^>^<<<v>^>^^>v>v>^><^>>^><<^v^v^<<>v><^>v<>^^^<>v<^>^<v<>^^v^<^<v>>^><^vv<<^^><<<vv<<<>^vvvv>^^^^<vv^<>^>vv<^<^>><<><>v<><v<<<><^v<>vvv<>v<<>>v<^^<v>^>v<<>>^v>^>^vv<>v^>^v^v<^^><<><<<<><<><<<<>v>v<><^>^^><>v^^v<v^vv>^<<><v<v^<v<v<<>^vvvvv^><^>v<v^v><<<vvv<vvv><^<^v^vv^v>>vvv>v^>^>vvv<<>v<^<v^><^v<^>v<^>v^>^>^v<vv<<v<v>v<v><v<<>^<>^<v>^^^<>>>>v<v<>^v<<<<^v<<><<^v<^>^vvv<vv^v<v<<vv><v<^><><<^^><<^>v<^<^^^v^>>^^>vv^v>^>>><vvv<^>>^>><v<<<>>><>>^<v^vv<>v^^^><<v><^vvv>>v^^^<v<^><v>v<>><>v^<^>>>>v^^v^v>^^^v>v<v>^v>><v<^<v<>>^><<^^>v<>^>^^<>>v>vv<v<^<v<>^<>vv<>>v^><^^<<v<^><vv><^v<v>>>v>>^>>^^^v<<<^^><<>vv^<v<^>^>^v>>><^vv<><><>^<v<^^>>>>>v^^<>^v<vvv>>v<><^>^^^v^><<>v<vv>vv<<<v
    ^<v^<>>v^>>><>><>v^<><>>>^vvv^^<<^v^v>^><^>v^^<>><^v>^>^v<v<<vv^<<^^><^^>vv<v>><<^^^v>^v>v<<^^v^^<vvv^v>^^>^^<<^>^v>>><^v^v^v<<v^><v>>^>^v<<v>>v><>><v<^v^^><<^>^<v^<vv>^v>^^<>>v><<^^v<<>>^vvv<^<^^<>^v^<^^>vv<><^^<>v<vvv^>v><<v<<v<<>^v<<>v>><^><<^vv^v><v><vv>^^<<<vvvv<<vv>><>>v<^vv<^<^v<v>>>><>^<vv^><<<v^><^v<<v^^<>^vv<>>vv>>>vv<<^<<>vv><>v>>>v<>>vv^^^^vv<^<^v<>>v>>v^v^^<<^^<<^v>v<^>v^^<><<>>v<v<^v^v^v>><v<<>vv^<>v<vvvv>><vv<>vv>>^>>v<^v>v>><<^>^<>v^^<>v<>>v<<v^vv<^^^^vv^>v>v^>><<^>^^v<v>^v<>^^vv^>^<^>^v^^<<v^>^^<><^<>^<>^^v<<^<<vv<^vv^v^>^<vv<>^v>v>vv<^><<^^^<>v<<v<v><v>v>^>^^^<<>v<^^<^^^<^><^^>vv^<<>v>vv^<>>^^v<>v<^<<^^vvvv<>><v<^vv>^>^^v^>v^v^v>v^>v^>vv>v><v><<v^v^^^<^v>><^v<>v><>>v><v><v<<^>v^^^^>><^^v^^>^vv>>vv>>>>v<v^>><<v>>v^<v^<^<^v<<<v>vv<<^^<><><v><v^v<<^v<<vv<^^<>><<><><<^<<<<v><>>^>v^v>v^^^<>^v>v^>^><v^<^<v^^vv<^><><^^v>v>^^v^>>^<><<^<<v>><v>>^v<><<>>>^v<vvv^v<^><>vv>^^^<<>>>>^><<^<<><<<>>>v>>^>v><^v^<vv>>vv^<v>vv^^<>v>>>v>^>>^v><>>v<vvv<<^<v^>>^><^<^<>v>vv<v^^>v^<>>><^^<<v<
    ><^>v^>>>>^v^>v^>vvvv>><vv^<><v<vv><<<>><v<^<^<<>>v^><<<>v<^<>v<<><^vvv^^>v^<<<><<<><^>v<<<>>>^<^<<<vvv^v>v<^v<<^v^<><^vv^v^>^<<vv^v>vv><>>>>^<>>v><<<<^^^>^<v<vv>>^^><v^<<>><vv<><v<>^v<^^vv<><^>v<>>>>v<>>^^^v<<^><>v<>v><>^><^v>v>>^vv^^vvvv^<<<<<v<<>^>v>^vv<>v^^v>><^v><v>^v><>^v<^v><v^<<vvv^<>^v^^>^^v^^^<vv^v^<^<^v>^<><>>^>><^^>^v<^vv><^^v^<<vv<>^^<v>><^><><<>^^^v>^v>vv^>>>v^v<<<^^^>v><v<v<v><^^vvv<>>vv^<<^v^><<vv<<^v^>vv>^>^><vvv>v^^^vv<v<^v^v^^^<>>^v^v<<v>^^v<<<<v<>^<>>>^^>>v^<<^v>^<^v>>v<^>>vvv<<^><^>>v<>v<><^^^vvv<>^<v^^v>v^>v><<>^<v^^>>v<<<^^<>>^v<><<v><^<>>><v<v^><<v^<<>vv^<^<^>^>v^^<^<vvv^>v>v>^^>>v><^^<><<<v>^<>v>^v><^^<<<>^>>><<<^^<<^^<vv<>^<><vv>>>>v>^>>>^^>><v>vv^><>><v>^^v<vv><<>><v^<>^<>>><><>^^^v<>><>^<<v>v>^><^><v>>vv^^<v>v^v^^<^>v>v^^<><>>^>v>vv>vv>vv^>^>^vvv<v>^>>>>^vvv^<^>v<v<<v<<v<v><vv<>v<>v>>>^vv<<<^><<<vv<<v<^^^>v^>>v<<>^<>>>v^v>v^^<^^^^><v>^^>v<>vv<>^<v^>>>^^<<v>v<^>v^<v<^^<^>>><^<^v<v>v>>^>>^v^^>>vvvv^>v>>v<v<<<vvv<<vv^v<^vv^^^<<^^<^^<>v^^<v<^^v^><>>vvv<^^^^vv>>>
    v<><<^<v^>v^><>v<<><v<>vvvv>>>vv<vv^^<<vv>vv<v^<>v<^v^v<vv^<><<<v>v^>v^^>^vv<<vv^<><<<<>v>v^>v<>^v^><<<>v^vvv><<<vv<>>v>v><<<>^<^>v<<>v><v>^>>v<>>^^<>v><<vv^v>^v>^<<^^<<v<v><v>v^^^<^^<><>v>v>v><<<^><v<>v>^vv><^^^^vv^<v>^vv^><>>v<^<><^<<^>^v>^v>vv>v>><>^v<<>v><>v^<^^>>vv^^vvv<><v^^<^<^>^v<^>>vv^^><v^<<v^>vv^v<<><v^vv<><<vv><v>^<^>>v>^v><<><^<>^>^vv^v><>>>^v^<<v^<^v^>v^<vv^><vv<>^^<^<<^>vvv^v<<^^vv<^<v>>v>v>^^vv><>v^^<v<<<^<v>^v><<>^v><<v>>v^>><v>^v^<v<<>>^^>v^v<^^v<>><v^^v^>v<>>^^>^><<<^v>>^vv<^>^vv^^>^<>^<>v^>>^v^><<^<><>><><^<<v<<v>^<<><v<<>^^^v<<<>v>v<^v>>v<^>^<<>^v^<<>vv<v><^^v>v<<><><<<vv<v<v><v>vvv^<^vv<v<<^v^v^v<^>^^v^^^^v>>vv^vvv><v^>^<^^>v^><><>^^<^^v<<vv^>v>^<>v>vvv<<v>>vv<>^<v<<<^>v<>vv>>><>^<^v<><<>v^<>>v^^^^v^v<v^^^><<><>^>v^>v^^^v>>^>>^<<<<^v^v<>v<<>><<v>>v>v<>>^^>v>^<^^^v^^<<v<>^>>>>><>^<><^v><vv<>vv^<><vv<>><v>v>>v>^>^v<<<>^<>^<^^<^<>v><^^v<><^^<<vv<v><^v><vv^^>>v^^>^<v^>>vvvvv<^^>^v^><<v>><<<^<v<<>>v>v^v<^<<><><>^<v^^><^<v^^>>v>^<v<>v^v<>>v>v^v^><>v^v>v<<><^^<v<^v>><<>v
    """;

    static Map<String, Point> directions = Map.of(
            "<", new Point(-1, 0),
            ">", new Point(1, 0),
            "^", new Point(0, -1),
            "v", new Point(0, 1)
    );

    private static String[] parseRobotMovements(String robotMovements) {
        return robotMovements.trim().replaceAll("\n", "").split("");
    }

    private static char[][] parseWarehouse(String warehouse) {
        String[] splitWarehouse = warehouse.trim().split("\n");
        char[][] parsedWarehouse = new char[splitWarehouse.length][];

        for (int i = 0; i < splitWarehouse.length; i++) {
            parsedWarehouse[i] = splitWarehouse[i].toCharArray();
        }

        maxY = parsedWarehouse.length;
        maxX = parsedWarehouse[0].length;


        return parsedWarehouse;
    }

    private static Point findRobot() {
        for (int i = 1; i < warehouseCharArray.length - 1; i++) {
            for (int j = 1; j < warehouseCharArray[0].length - 1; j++) {
                char current = warehouseCharArray[i][j];
                if (current == '@') {
                    // Remove robot from map
                    // warehouseCharArray[i][j] = '.';
                    
                    // Set robot's inital coordinates
                    return new Point(j, i);
                }
            }
        }

        // Should never reach here, but required by compiler
        throw new IllegalStateException("Robot '@' not found in warehouse");
    }

    private static Box boxesCanBeMoved(int x, int y, Point delta) {
        List<Point> boxCoordinates = new ArrayList<>();
        Point currentPos = new Point(x, y);
        
        // Start with the first box
        boxCoordinates.add(new Point(currentPos.x, currentPos.y));
        
        // Keep checking in the direction of movement until we hit something that's not a box
        while (true) {
            int newX = currentPos.x + delta.x;
            int newY = currentPos.y + delta.y;
            
            // Hit a wall
            if (warehouseCharArray[newY][newX] == '#') {
                return new Box(false, boxCoordinates);
            }
            
            // Found empty space, boxes can be moved
            if (warehouseCharArray[newY][newX] == '.') {
                return new Box(true, boxCoordinates);
            }
            
            // Found another box, continue checking
            if (warehouseCharArray[newY][newX] == 'O') {
                currentPos = new Point(newX, newY);
                boxCoordinates.add(new Point(currentPos.x, currentPos.y));
            }
        }
    }


    private static void moveBoxes(List<Point> boxCoordinates, Point direction) {
        // Move boxes one by one starting from the last one in the chain
        for (int i = boxCoordinates.size() - 1; i >= 0; i--) {
            Point box = boxCoordinates.get(i);
            int newX = box.x + direction.x;
            int newY = box.y + direction.y;
            
            // Move the box to new position
            warehouseCharArray[newY][newX] = 'O';
            
            // Only clear the old position if it's not the robot's new position
            if (i > 0 || warehouseCharArray[box.y][box.x] != '@') {
                warehouseCharArray[box.y][box.x] = '.';
            }
        }
    }

    // private static void moveBoxes(List<Point> coordList, Point direction) {
    //     Point coordinate = coordList.get(0);
    //     // Remove box from previous coordinates
    //     warehouseCharArray[coordinate.y][coordinate.x] = '.';

    //     if (coordList.size() > 1) {
    //         // Move coordinate to final coordinate in array
    //         coordinate = coordList.get(coordList.size() - 1);
    //     }

    //     // Move box to new coordinates
    //     int newY = coordinate.y + direction.y;
    //     int newX = coordinate.x + direction.x;
    //     warehouseCharArray[newY][newX] = 'O';
    // }

    private static void calculateMovement(Point robot, String movement) {
        Point delta = directions.get(movement);
        int newX = robot.x + delta.x;
        int newY = robot.y + delta.y;

        // Out of bounds
        if (Math.min(newX, newY) <= 0 || newX >= maxX - 1 || newY >= maxY - 1) {
            return;
        }

        char newLocation = warehouseCharArray[newY][newX];

        if (newLocation == '.') {
            // Move robot to empty space
            warehouseCharArray[robot.y][robot.x] = '.';
            warehouseCharArray[newY][newX] = '@';

            // Update robot position
            robot.x = newX;
            robot.y = newY;

        } else if (newLocation == 'O') {
            Box potentialMovement = boxesCanBeMoved(newX, newY, delta);
            if (potentialMovement.isPossible) {
                // Move the boxes
                moveBoxes(potentialMovement.coordList, delta);
                
                // Move the robot
                warehouseCharArray[robot.y][robot.x] = '.';
                warehouseCharArray[newY][newX] = '@';

                // Update robot position
                robot.x = newX;
                robot.y = newY;
            }
        }
        // If newLocation is a wall '#', robot doesn't move
    }

    private static int calculateTotalGPS() {
        int total = 0;
        for (int i = 1; i < warehouseCharArray.length - 1; i++) {
            for (int j = 1; j < warehouseCharArray[0].length - 1; j++) {
                if (warehouseCharArray[i][j] == 'O') {
                    total += 100 * i + j;
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {
        String[] parsedRobotMovements = parseRobotMovements(robotMovements);
        warehouseCharArray = parseWarehouse(warehouse);

        Point robotLocation = findRobot();

        System.err.println("Initial state: ");
        for (char[] line: warehouseCharArray) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println("");

        for (String movement: parsedRobotMovements) {
            calculateMovement(robotLocation, movement);

            // System.err.println("Move " + movement + ":");
            // for (char[] line: warehouseCharArray) {
            //     System.out.println(Arrays.toString(line));
            // }
            // System.out.println("");
        }

        int total = calculateTotalGPS();

        System.err.println("The total GPS of the boxes is: " + total);

        System.err.println("Final:");
        for (char[] line: warehouseCharArray) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println("");

    }
}

class Box {
    boolean isPossible;
    List<Point> coordList;

    public Box(boolean isPossible, List<Point> coordList) {
        this.isPossible = isPossible;
        this.coordList = coordList;
    }
}
