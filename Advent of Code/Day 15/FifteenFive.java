
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FifteenFive {
    // static String warehouse = """
    // ##########
    // #..O..O.O#
    // #......O.#
    // #.OO..O.O#
    // #..O@..O.#
    // #O#..O...#
    // #O..O..O.#
    // #.OO.O.OO#
    // #....O...#
    // ##########
    // """;

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

    // static String robotMovements = """
    // <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
    // vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
    // ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
    // <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
    // ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
    // ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
    // >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
    // <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
    // ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
    // v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
    // """;

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
            parsedWarehouse[i] = manipulateRow(splitWarehouse[i].toCharArray());
        }

        maxY = parsedWarehouse.length;
        maxX = parsedWarehouse[0].length;


        return parsedWarehouse;
    }

    private static char[] manipulateRow(char[] row) {
        char[] manipulatedRow = new char[row.length * 2];
        for (int i = 0; i < row.length; i++) {
            char element = row[i];
            int j = i * 2;
            switch (element) {
                case '#' -> {
                    manipulatedRow[j] = '#';
                    manipulatedRow[j+1] = '#';
                }
                case 'O' -> {
                    manipulatedRow[j] = '[';
                    manipulatedRow[j+1] = ']';
                }
                case '.' -> {
                    manipulatedRow[j] = '.';
                    manipulatedRow[j+1] = '.';
                }
                case '@' -> {
                    manipulatedRow[j] = '@';
                    manipulatedRow[j+1] = '.';
                }
            }
        }
        return manipulatedRow;
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

    private static Box findWholeBox(int x, int y) {
        Point left;
        Point right;
        if (warehouseCharArray[y][x] == '[') {
            left = new Point(x, y);
            right = new Point(x+1, y);
        } else { // warehouseCharArray[y][x] == ']'
            left = new Point(x-1, y);
            right = new Point(x, y);
        }

        return new Box(left, right);
    }

    private static boolean nextSquareValid(Box current, List<Box> queue, List<Box> allBoxLocations, Point delta) {
        for (Point coordinate : current.coordinates) {
            int newX = coordinate.x + delta.x;
            int newY = coordinate.y + delta.y;
            
            // Hit a wall
            if (warehouseCharArray[newY][newX] == '#') {
                return false;
            }
            
            // Found another box, continue checking
            // Need to check conditions for both coordinates of the box
            if (warehouseCharArray[newY][newX] == '[' || warehouseCharArray[newY][newX] == ']') {
                Box currentBoxCoordinates = findWholeBox(newX, newY);

                // New box if box not already in coordinates
                if (!allBoxLocations.contains(currentBoxCoordinates)) {
                    queue.add(currentBoxCoordinates);
                    allBoxLocations.add(currentBoxCoordinates);
                }
            }
        }
        // Valid is either another box or an empty square. If it's an empty square then the queue is not added to
        return true;
    }

    private static Conditional boxesCanBeMoved(int x, int y, Point delta) {
        List<Box> queue = new ArrayList<>();
        List<Box> allBoxLocations = new ArrayList<>();
        Box currentBoxCoordinates = findWholeBox(x, y);
        
        // Start with the first box
        queue.add(currentBoxCoordinates);
        allBoxLocations.add(currentBoxCoordinates);
        
        // Keep checking in the direction of movement until we hit something that's not a box
        while (!queue.isEmpty()) {
            // System.err.println("Queue: ");
            // for (Box item: queue) {
            //     System.err.println(item.toString());
            // }

            Box currentBox = queue.remove(0);

            if (!nextSquareValid(currentBox, queue, allBoxLocations, delta)) {
                return new Conditional(false, null);
            }
            
        }

        // Found empty space, boxes can be moved
        return new Conditional(true, allBoxLocations);
    }

    private static CustomPoint newBoxLocation(Point coordinate, char symbol, Point direction) {
        int newX = coordinate.x + direction.x;
        int newY = coordinate.y + direction.y;
        
        // Move the box to new position
        warehouseCharArray[coordinate.y][coordinate.x] = '.';
        if (symbol == '[') {
            return new CustomPoint(newX, newY, '[');
        } else {
            return new CustomPoint(newX, newY, ']');
        }
    }

    private static void moveBoxes(List<Box> boxCoordinates, Point direction) {
        List<Point> oldCoordinateList = new ArrayList<>();
        List<CustomPoint> newCoordinateList = new ArrayList<>();

        for (Box coordinate: boxCoordinates) {
            // Collect coordinates for old boxes
            oldCoordinateList.add(coordinate.left);
            oldCoordinateList.add(coordinate.right);

            // Collect coordinates for new boxes
            newCoordinateList.add(newBoxLocation(coordinate.left, '[', direction));
            newCoordinateList.add(newBoxLocation(coordinate.right, ']', direction));
        }

        // Replace old box coordinates
        for (Point oldBoxCoordinates: oldCoordinateList) {
            warehouseCharArray[oldBoxCoordinates.y][oldBoxCoordinates.x] = '.';
        }

        // Add new box coordinates
        for (CustomPoint newBoxCoordinate: newCoordinateList) {
            warehouseCharArray[newBoxCoordinate.y][newBoxCoordinate.x] = newBoxCoordinate.symbol;
        }
    }

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

        } else if (newLocation == '[' || newLocation == ']') { // Either side of box
            Conditional potentialMovement = boxesCanBeMoved(newX, newY, delta);
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
                if (warehouseCharArray[i][j] == '[') {
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

class Conditional {
    boolean isPossible;
    List<Box> coordList;

    public Conditional(boolean isPossible, List<Box> coordList) {
        this.isPossible = isPossible;
        this.coordList = coordList;
    }
}

class CustomPoint {
    int x, y;
    char symbol;

    public CustomPoint(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CustomPoint)) return false;
        CustomPoint cp = (CustomPoint) o;
        return this.x == cp.x && this.y == cp.y && this.symbol == cp.symbol;
    }

    @Override
    public int hashCode() {
        return 31 * this.y + this.x;
    }

    @Override
    public String toString() {
        return "{ " + this.x + ", " + this.y + " }";
    }
}

class Box {
    Point left, right;
    List<Point> coordinates;

    public Box(Point left, Point right) {
        this.left = left;
        this.right = right;
        this.coordinates = new ArrayList<>(List.of(left, right));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Box)) return false;
        Box b = (Box) o;
        return this.left.x == b.left.x && this.left.y == b.left.y && this.right.x == b.right.x && this.right.y == b.right.y;
    }

    @Override
    public int hashCode() {
        return 31 * (31 * (31 * left.y + left.x) + right.y) + right.x;
    }

    @Override
    public String toString() {
        return left.y + ", [ " + left.x + ", " + right. x + " ]";
    }
}
