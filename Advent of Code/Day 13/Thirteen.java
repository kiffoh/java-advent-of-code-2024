import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;


public class Thirteen {
    // static String input = """
    // Button A: X+26, Y+91
    // Button B: X+82, Y+37
    // Prize: X=5592, Y=8072

    // Button A: X+61, Y+29
    // Button B: X+24, Y+48
    // Prize: X=15576, Y=17056

    // Button A: X+23, Y+45
    // Button B: X+43, Y+27
    // Prize: X=10347, Y=1007

    // Button A: X+24, Y+11
    // Button B: X+32, Y+94
    // Prize: X=2648, Y=2483

    // Button A: X+34, Y+15
    // Button B: X+33, Y+67
    // Prize: X=9272, Y=19572

    // Button A: X+11, Y+49
    // Button B: X+54, Y+26
    // Prize: X=13645, Y=10695

    // Button A: X+57, Y+20
    // Button B: X+20, Y+39
    // Prize: X=7060, Y=3894

    // Button A: X+41, Y+13
    // Button B: X+18, Y+45
    // Prize: X=520, Y=16974

    // Button A: X+29, Y+71
    // Button B: X+54, Y+22
    // Prize: X=19467, Y=5649

    // Button A: X+55, Y+25
    // Button B: X+16, Y+28
    // Prize: X=17372, Y=15776

    // Button A: X+20, Y+29
    // Button B: X+80, Y+31
    // Prize: X=2020, Y=1399

    // Button A: X+69, Y+33
    // Button B: X+22, Y+51
    // Prize: X=14455, Y=3503

    // Button A: X+13, Y+58
    // Button B: X+75, Y+25
    // Prize: X=3022, Y=2027

    // Button A: X+13, Y+45
    // Button B: X+43, Y+13
    // Prize: X=19014, Y=10818

    // Button A: X+40, Y+12
    // Button B: X+38, Y+75
    // Prize: X=18828, Y=9734

    // Button A: X+17, Y+91
    // Button B: X+52, Y+59
    // Prize: X=3246, Y=6408

    // Button A: X+74, Y+14
    // Button B: X+19, Y+68
    // Prize: X=11264, Y=2548

    // Button A: X+15, Y+42
    // Button B: X+62, Y+30
    // Prize: X=17598, Y=19268

    // Button A: X+31, Y+13
    // Button B: X+13, Y+24
    // Prize: X=17644, Y=11037

    // Button A: X+16, Y+48
    // Button B: X+74, Y+32
    // Prize: X=1478, Y=8944

    // Button A: X+13, Y+92
    // Button B: X+71, Y+45
    // Prize: X=4739, Y=4260

    // Button A: X+95, Y+70
    // Button B: X+15, Y+67
    // Prize: X=4050, Y=3208

    // Button A: X+35, Y+60
    // Button B: X+48, Y+19
    // Prize: X=12441, Y=6693

    // Button A: X+68, Y+20
    // Button B: X+13, Y+23
    // Prize: X=2972, Y=1948

    // Button A: X+84, Y+36
    // Button B: X+36, Y+94
    // Prize: X=7044, Y=3726

    // Button A: X+52, Y+11
    // Button B: X+61, Y+56
    // Prize: X=3601, Y=1322

    // Button A: X+37, Y+57
    // Button B: X+81, Y+30
    // Prize: X=1569, Y=711

    // Button A: X+64, Y+30
    // Button B: X+12, Y+98
    // Prize: X=6728, Y=9620

    // Button A: X+49, Y+99
    // Button B: X+54, Y+33
    // Prize: X=6606, Y=9009

    // Button A: X+76, Y+48
    // Button B: X+20, Y+52
    // Prize: X=3008, Y=2136

    // Button A: X+93, Y+96
    // Button B: X+69, Y+14
    // Prize: X=9894, Y=9412

    // Button A: X+42, Y+25
    // Button B: X+12, Y+89
    // Prize: X=2364, Y=9511

    // Button A: X+31, Y+63
    // Button B: X+96, Y+20
    // Prize: X=7662, Y=2614

    // Button A: X+63, Y+52
    // Button B: X+16, Y+59
    // Prize: X=840, Y=1655

    // Button A: X+62, Y+11
    // Button B: X+30, Y+78
    // Prize: X=17800, Y=5962

    // Button A: X+75, Y+20
    // Button B: X+16, Y+67
    // Prize: X=4190, Y=3805

    // Button A: X+43, Y+72
    // Button B: X+42, Y+17
    // Prize: X=3510, Y=9955

    // Button A: X+59, Y+20
    // Button B: X+26, Y+62
    // Prize: X=7714, Y=18646

    // Button A: X+12, Y+47
    // Button B: X+57, Y+34
    // Prize: X=3011, Y=2530

    // Button A: X+52, Y+20
    // Button B: X+21, Y+49
    // Prize: X=2935, Y=12971

    // Button A: X+14, Y+32
    // Button B: X+50, Y+18
    // Prize: X=700, Y=1988

    // Button A: X+90, Y+66
    // Button B: X+13, Y+98
    // Prize: X=1448, Y=6016

    // Button A: X+64, Y+92
    // Button B: X+79, Y+32
    // Prize: X=7142, Y=5536

    // Button A: X+11, Y+51
    // Button B: X+59, Y+21
    // Prize: X=17813, Y=2411

    // Button A: X+91, Y+42
    // Button B: X+47, Y+71
    // Prize: X=5705, Y=4704

    // Button A: X+51, Y+90
    // Button B: X+75, Y+36
    // Prize: X=10818, Y=11286

    // Button A: X+12, Y+40
    // Button B: X+49, Y+33
    // Prize: X=11136, Y=15788

    // Button A: X+78, Y+17
    // Button B: X+18, Y+69
    // Prize: X=7946, Y=6989

    // Button A: X+87, Y+15
    // Button B: X+58, Y+57
    // Prize: X=5974, Y=3474

    // Button A: X+61, Y+44
    // Button B: X+22, Y+57
    // Prize: X=6582, Y=6105

    // Button A: X+16, Y+47
    // Button B: X+58, Y+37
    // Prize: X=4044, Y=4677

    // Button A: X+31, Y+73
    // Button B: X+64, Y+23
    // Prize: X=9505, Y=9870

    // Button A: X+91, Y+12
    // Button B: X+23, Y+76
    // Prize: X=6308, Y=2656

    // Button A: X+40, Y+16
    // Button B: X+21, Y+58
    // Prize: X=19063, Y=10974

    // Button A: X+23, Y+41
    // Button B: X+51, Y+14
    // Prize: X=17282, Y=14600

    // Button A: X+25, Y+56
    // Button B: X+56, Y+27
    // Prize: X=1626, Y=4602

    // Button A: X+28, Y+81
    // Button B: X+91, Y+20
    // Prize: X=11081, Y=8947

    // Button A: X+83, Y+34
    // Button B: X+31, Y+99
    // Prize: X=4443, Y=7257

    // Button A: X+25, Y+77
    // Button B: X+72, Y+20
    // Prize: X=12369, Y=18557

    // Button A: X+43, Y+17
    // Button B: X+11, Y+83
    // Prize: X=1338, Y=2102

    // Button A: X+11, Y+68
    // Button B: X+63, Y+32
    // Prize: X=746, Y=1752

    // Button A: X+67, Y+15
    // Button B: X+30, Y+80
    // Prize: X=5711, Y=8195

    // Button A: X+42, Y+19
    // Button B: X+11, Y+39
    // Prize: X=3668, Y=4393

    // Button A: X+69, Y+23
    // Button B: X+66, Y+99
    // Prize: X=4959, Y=5426

    // Button A: X+91, Y+71
    // Button B: X+35, Y+91
    // Prize: X=5600, Y=9592

    // Button A: X+30, Y+17
    // Button B: X+15, Y+92
    // Prize: X=4050, Y=10478

    // Button A: X+11, Y+75
    // Button B: X+49, Y+55
    // Prize: X=5238, Y=9200

    // Button A: X+19, Y+50
    // Button B: X+74, Y+32
    // Prize: X=5700, Y=868

    // Button A: X+16, Y+80
    // Button B: X+60, Y+31
    // Prize: X=4212, Y=5189

    // Button A: X+21, Y+67
    // Button B: X+67, Y+18
    // Prize: X=4835, Y=3876

    // Button A: X+12, Y+43
    // Button B: X+63, Y+16
    // Prize: X=3173, Y=11951

    // Button A: X+78, Y+91
    // Button B: X+83, Y+27
    // Prize: X=9510, Y=9000

    // Button A: X+11, Y+38
    // Button B: X+61, Y+20
    // Prize: X=6577, Y=11900

    // Button A: X+40, Y+18
    // Button B: X+32, Y+75
    // Prize: X=5960, Y=7227

    // Button A: X+42, Y+12
    // Button B: X+19, Y+47
    // Prize: X=6736, Y=15480

    // Button A: X+19, Y+72
    // Button B: X+47, Y+11
    // Prize: X=1362, Y=11556

    // Button A: X+36, Y+17
    // Button B: X+54, Y+93
    // Prize: X=1854, Y=1888

    // Button A: X+82, Y+23
    // Button B: X+11, Y+51
    // Prize: X=18240, Y=15186

    // Button A: X+75, Y+34
    // Button B: X+18, Y+66
    // Prize: X=3519, Y=4950

    // Button A: X+66, Y+27
    // Button B: X+27, Y+66
    // Prize: X=12821, Y=16487

    // Button A: X+53, Y+36
    // Button B: X+32, Y+87
    // Prize: X=4887, Y=6909

    // Button A: X+11, Y+25
    // Button B: X+70, Y+46
    // Prize: X=3865, Y=17271

    // Button A: X+27, Y+52
    // Button B: X+49, Y+27
    // Prize: X=1951, Y=17651

    // Button A: X+62, Y+82
    // Button B: X+48, Y+12
    // Prize: X=7700, Y=6580

    // Button A: X+11, Y+28
    // Button B: X+68, Y+31
    // Prize: X=15425, Y=10214

    // Button A: X+54, Y+20
    // Button B: X+26, Y+43
    // Prize: X=4696, Y=2707

    // Button A: X+78, Y+85
    // Button B: X+93, Y+19
    // Prize: X=8118, Y=5388

    // Button A: X+31, Y+93
    // Button B: X+83, Y+46
    // Prize: X=4101, Y=6822

    // Button A: X+12, Y+44
    // Button B: X+59, Y+42
    // Prize: X=5345, Y=5826

    // Button A: X+79, Y+67
    // Button B: X+13, Y+94
    // Prize: X=7722, Y=9951

    // Button A: X+16, Y+93
    // Button B: X+63, Y+32
    // Prize: X=810, Y=2703

    // Button A: X+74, Y+14
    // Button B: X+20, Y+67
    // Prize: X=5048, Y=13449

    // Button A: X+72, Y+49
    // Button B: X+27, Y+61
    // Prize: X=4203, Y=4267

    // Button A: X+64, Y+29
    // Button B: X+33, Y+68
    // Prize: X=13980, Y=6910

    // Button A: X+25, Y+53
    // Button B: X+48, Y+15
    // Prize: X=5796, Y=5521

    // Button A: X+75, Y+46
    // Button B: X+34, Y+80
    // Prize: X=6494, Y=6408

    // Button A: X+48, Y+17
    // Button B: X+29, Y+72
    // Prize: X=10244, Y=14008

    // Button A: X+22, Y+14
    // Button B: X+18, Y+42
    // Prize: X=3030, Y=18190

    // Button A: X+17, Y+53
    // Button B: X+32, Y+12
    // Prize: X=2076, Y=15668

    // Button A: X+53, Y+18
    // Button B: X+11, Y+45
    // Prize: X=14337, Y=15983

    // Button A: X+84, Y+23
    // Button B: X+42, Y+80
    // Prize: X=8316, Y=6250

    // Button A: X+13, Y+47
    // Button B: X+81, Y+42
    // Prize: X=8348, Y=15587

    // Button A: X+85, Y+94
    // Button B: X+15, Y+80
    // Prize: X=9435, Y=15824

    // Button A: X+92, Y+12
    // Button B: X+74, Y+70
    // Prize: X=11570, Y=3742

    // Button A: X+53, Y+23
    // Button B: X+23, Y+52
    // Prize: X=15697, Y=3253

    // Button A: X+15, Y+53
    // Button B: X+36, Y+17
    // Prize: X=19649, Y=15127

    // Button A: X+52, Y+14
    // Button B: X+25, Y+45
    // Prize: X=14902, Y=3884

    // Button A: X+19, Y+53
    // Button B: X+58, Y+26
    // Prize: X=10556, Y=11852

    // Button A: X+57, Y+21
    // Button B: X+41, Y+77
    // Prize: X=5634, Y=5418

    // Button A: X+16, Y+52
    // Button B: X+59, Y+18
    // Prize: X=2166, Y=732

    // Button A: X+62, Y+17
    // Button B: X+23, Y+59
    // Prize: X=11646, Y=4671

    // Button A: X+30, Y+64
    // Button B: X+91, Y+16
    // Prize: X=7358, Y=3584

    // Button A: X+75, Y+21
    // Button B: X+20, Y+27
    // Prize: X=2475, Y=1656

    // Button A: X+23, Y+55
    // Button B: X+36, Y+15
    // Prize: X=5337, Y=3610

    // Button A: X+29, Y+80
    // Button B: X+67, Y+18
    // Prize: X=11940, Y=9794

    // Button A: X+40, Y+65
    // Button B: X+93, Y+21
    // Prize: X=3664, Y=4913

    // Button A: X+20, Y+61
    // Button B: X+71, Y+14
    // Prize: X=5064, Y=2482

    // Button A: X+83, Y+29
    // Button B: X+12, Y+16
    // Prize: X=6819, Y=2477

    // Button A: X+60, Y+15
    // Button B: X+51, Y+98
    // Prize: X=5004, Y=5002

    // Button A: X+55, Y+25
    // Button B: X+18, Y+58
    // Prize: X=1238, Y=6098

    // Button A: X+50, Y+11
    // Button B: X+23, Y+67
    // Prize: X=11925, Y=14898

    // Button A: X+27, Y+93
    // Button B: X+97, Y+38
    // Prize: X=7056, Y=5649

    // Button A: X+32, Y+20
    // Button B: X+32, Y+69
    // Prize: X=1952, Y=3964

    // Button A: X+66, Y+27
    // Button B: X+14, Y+71
    // Prize: X=7192, Y=8164

    // Button A: X+49, Y+16
    // Button B: X+15, Y+44
    // Prize: X=14576, Y=11200

    // Button A: X+45, Y+16
    // Button B: X+53, Y+80
    // Prize: X=7471, Y=6448

    // Button A: X+32, Y+16
    // Button B: X+23, Y+51
    // Prize: X=16396, Y=15900

    // Button A: X+94, Y+12
    // Button B: X+44, Y+55
    // Prize: X=4632, Y=3258

    // Button A: X+39, Y+92
    // Button B: X+64, Y+45
    // Prize: X=7528, Y=10976

    // Button A: X+61, Y+28
    // Button B: X+23, Y+54
    // Prize: X=17310, Y=11430

    // Button A: X+73, Y+17
    // Button B: X+56, Y+83
    // Prize: X=7250, Y=3997

    // Button A: X+74, Y+33
    // Button B: X+13, Y+52
    // Prize: X=18063, Y=16927

    // Button A: X+47, Y+73
    // Button B: X+38, Y+17
    // Prize: X=16515, Y=16585

    // Button A: X+41, Y+24
    // Button B: X+26, Y+55
    // Prize: X=18682, Y=19724

    // Button A: X+28, Y+70
    // Button B: X+69, Y+25
    // Prize: X=1453, Y=13255

    // Button A: X+41, Y+66
    // Button B: X+38, Y+14
    // Prize: X=3711, Y=2436

    // Button A: X+27, Y+71
    // Button B: X+90, Y+40
    // Prize: X=2097, Y=2171

    // Button A: X+38, Y+18
    // Button B: X+29, Y+51
    // Prize: X=6707, Y=6005

    // Button A: X+54, Y+25
    // Button B: X+36, Y+64
    // Prize: X=1206, Y=795

    // Button A: X+82, Y+19
    // Button B: X+13, Y+48
    // Prize: X=5528, Y=4520

    // Button A: X+41, Y+69
    // Button B: X+51, Y+24
    // Prize: X=4447, Y=4578

    // Button A: X+14, Y+66
    // Button B: X+30, Y+21
    // Prize: X=4194, Y=8331

    // Button A: X+64, Y+19
    // Button B: X+14, Y+53
    // Prize: X=4934, Y=1709

    // Button A: X+51, Y+16
    // Button B: X+22, Y+55
    // Prize: X=18387, Y=15535

    // Button A: X+70, Y+12
    // Button B: X+57, Y+99
    // Prize: X=6918, Y=5112

    // Button A: X+22, Y+22
    // Button B: X+24, Y+98
    // Prize: X=1564, Y=4080

    // Button A: X+61, Y+84
    // Button B: X+90, Y+20
    // Prize: X=9158, Y=4712

    // Button A: X+27, Y+11
    // Button B: X+24, Y+40
    // Prize: X=12953, Y=3417

    // Button A: X+95, Y+63
    // Button B: X+37, Y+91
    // Prize: X=10185, Y=11739

    // Button A: X+78, Y+35
    // Button B: X+18, Y+86
    // Prize: X=2538, Y=3009

    // Button A: X+46, Y+25
    // Button B: X+11, Y+21
    // Prize: X=7217, Y=12172

    // Button A: X+11, Y+61
    // Button B: X+85, Y+34
    // Prize: X=1332, Y=19165

    // Button A: X+16, Y+71
    // Button B: X+73, Y+32
    // Prize: X=7017, Y=7491

    // Button A: X+20, Y+46
    // Button B: X+73, Y+44
    // Prize: X=6606, Y=10276

    // Button A: X+49, Y+19
    // Button B: X+41, Y+77
    // Prize: X=11409, Y=9153

    // Button A: X+87, Y+33
    // Button B: X+61, Y+84
    // Prize: X=2831, Y=3204

    // Button A: X+22, Y+53
    // Button B: X+73, Y+28
    // Prize: X=7187, Y=4450

    // Button A: X+12, Y+69
    // Button B: X+33, Y+30
    // Prize: X=1305, Y=6066

    // Button A: X+98, Y+24
    // Button B: X+28, Y+59
    // Prize: X=8722, Y=6881

    // Button A: X+46, Y+23
    // Button B: X+16, Y+60
    // Prize: X=4222, Y=3723

    // Button A: X+23, Y+62
    // Button B: X+55, Y+14
    // Prize: X=7186, Y=9780

    // Button A: X+29, Y+87
    // Button B: X+89, Y+47
    // Prize: X=3249, Y=6447

    // Button A: X+23, Y+61
    // Button B: X+98, Y+52
    // Prize: X=6779, Y=3841

    // Button A: X+58, Y+27
    // Button B: X+15, Y+80
    // Prize: X=4505, Y=5675

    // Button A: X+35, Y+12
    // Button B: X+58, Y+83
    // Prize: X=1921, Y=14923

    // Button A: X+46, Y+99
    // Button B: X+83, Y+37
    // Prize: X=8547, Y=5223

    // Button A: X+11, Y+28
    // Button B: X+68, Y+29
    // Prize: X=5710, Y=4160

    // Button A: X+11, Y+22
    // Button B: X+92, Y+28
    // Prize: X=1255, Y=2042

    // Button A: X+42, Y+37
    // Button B: X+82, Y+14
    // Prize: X=6234, Y=3570

    // Button A: X+42, Y+25
    // Button B: X+13, Y+31
    // Prize: X=3053, Y=3748

    // Button A: X+44, Y+49
    // Button B: X+14, Y+87
    // Prize: X=2498, Y=3853

    // Button A: X+16, Y+39
    // Button B: X+71, Y+32
    // Prize: X=9870, Y=12547

    // Button A: X+18, Y+49
    // Button B: X+66, Y+35
    // Prize: X=8738, Y=1019

    // Button A: X+32, Y+29
    // Button B: X+93, Y+12
    // Prize: X=2580, Y=2049

    // Button A: X+49, Y+78
    // Button B: X+37, Y+11
    // Prize: X=14799, Y=12586

    // Button A: X+26, Y+59
    // Button B: X+39, Y+15
    // Prize: X=562, Y=8680

    // Button A: X+27, Y+88
    // Button B: X+33, Y+20
    // Prize: X=3765, Y=5004

    // Button A: X+99, Y+40
    // Button B: X+73, Y+98
    // Prize: X=7600, Y=7044

    // Button A: X+94, Y+62
    // Button B: X+42, Y+89
    // Prize: X=10494, Y=9067

    // Button A: X+84, Y+77
    // Button B: X+21, Y+84
    // Prize: X=2709, Y=6433

    // Button A: X+53, Y+22
    // Button B: X+14, Y+59
    // Prize: X=10134, Y=5907

    // Button A: X+85, Y+20
    // Button B: X+74, Y+88
    // Prize: X=4578, Y=3336

    // Button A: X+45, Y+86
    // Button B: X+93, Y+46
    // Prize: X=9369, Y=5654

    // Button A: X+49, Y+25
    // Button B: X+25, Y+43
    // Prize: X=1942, Y=13450

    // Button A: X+20, Y+66
    // Button B: X+68, Y+54
    // Prize: X=2704, Y=5856

    // Button A: X+77, Y+85
    // Button B: X+12, Y+66
    // Prize: X=8479, Y=14477

    // Button A: X+39, Y+74
    // Button B: X+73, Y+14
    // Prize: X=2873, Y=2214

    // Button A: X+55, Y+32
    // Button B: X+28, Y+58
    // Prize: X=6017, Y=5336

    // Button A: X+42, Y+17
    // Button B: X+60, Y+98
    // Prize: X=6654, Y=5863

    // Button A: X+13, Y+42
    // Button B: X+64, Y+23
    // Prize: X=888, Y=16196

    // Button A: X+35, Y+99
    // Button B: X+89, Y+33
    // Prize: X=7632, Y=3432

    // Button A: X+22, Y+40
    // Button B: X+79, Y+35
    // Prize: X=4342, Y=2680

    // Button A: X+65, Y+50
    // Button B: X+26, Y+65
    // Prize: X=1625, Y=3050

    // Button A: X+95, Y+62
    // Button B: X+16, Y+87
    // Prize: X=9646, Y=7903

    // Button A: X+71, Y+52
    // Button B: X+38, Y+88
    // Prize: X=1640, Y=2224

    // Button A: X+14, Y+78
    // Button B: X+54, Y+63
    // Prize: X=2126, Y=8277

    // Button A: X+31, Y+71
    // Button B: X+84, Y+16
    // Prize: X=7820, Y=5916

    // Button A: X+83, Y+34
    // Button B: X+20, Y+28
    // Prize: X=7445, Y=4258

    // Button A: X+28, Y+38
    // Button B: X+98, Y+26
    // Prize: X=7616, Y=4344

    // Button A: X+17, Y+29
    // Button B: X+46, Y+21
    // Prize: X=798, Y=296

    // Button A: X+56, Y+14
    // Button B: X+29, Y+66
    // Prize: X=16054, Y=13056

    // Button A: X+94, Y+53
    // Button B: X+14, Y+88
    // Prize: X=2450, Y=7870

    // Button A: X+92, Y+26
    // Button B: X+20, Y+29
    // Prize: X=7368, Y=3810

    // Button A: X+70, Y+16
    // Button B: X+14, Y+55
    // Prize: X=17000, Y=18456

    // Button A: X+86, Y+24
    // Button B: X+50, Y+69
    // Prize: X=8928, Y=7776

    // Button A: X+71, Y+12
    // Button B: X+19, Y+67
    // Prize: X=8799, Y=1300

    // Button A: X+24, Y+53
    // Button B: X+54, Y+22
    // Prize: X=9692, Y=14256

    // Button A: X+34, Y+35
    // Button B: X+17, Y+98
    // Prize: X=3179, Y=9954

    // Button A: X+23, Y+43
    // Button B: X+78, Y+23
    // Prize: X=8167, Y=5197

    // Button A: X+17, Y+62
    // Button B: X+87, Y+63
    // Prize: X=2938, Y=4612

    // Button A: X+11, Y+30
    // Button B: X+28, Y+13
    // Prize: X=13471, Y=7940

    // Button A: X+55, Y+20
    // Button B: X+12, Y+39
    // Prize: X=10636, Y=18147

    // Button A: X+60, Y+34
    // Button B: X+27, Y+62
    // Prize: X=2991, Y=6038

    // Button A: X+18, Y+91
    // Button B: X+76, Y+12
    // Prize: X=2826, Y=887

    // Button A: X+69, Y+80
    // Button B: X+16, Y+94
    // Prize: X=1224, Y=4588

    // Button A: X+26, Y+68
    // Button B: X+79, Y+49
    // Prize: X=4631, Y=3443

    // Button A: X+83, Y+89
    // Button B: X+84, Y+14
    // Prize: X=12588, Y=9314

    // Button A: X+12, Y+38
    // Button B: X+71, Y+23
    // Prize: X=12238, Y=19124

    // Button A: X+18, Y+11
    // Button B: X+11, Y+25
    // Prize: X=9288, Y=3870

    // Button A: X+36, Y+12
    // Button B: X+20, Y+45
    // Prize: X=16912, Y=3749

    // Button A: X+26, Y+88
    // Button B: X+96, Y+55
    // Prize: X=8924, Y=10230

    // Button A: X+50, Y+11
    // Button B: X+37, Y+69
    // Prize: X=9513, Y=2495

    // Button A: X+35, Y+17
    // Button B: X+34, Y+61
    // Prize: X=18744, Y=4425

    // Button A: X+47, Y+18
    // Button B: X+13, Y+64
    // Prize: X=8530, Y=16180

    // Button A: X+64, Y+24
    // Button B: X+13, Y+56
    // Prize: X=4374, Y=3992

    // Button A: X+56, Y+22
    // Button B: X+12, Y+26
    // Prize: X=2544, Y=1340

    // Button A: X+78, Y+70
    // Button B: X+67, Y+11
    // Prize: X=13306, Y=7618

    // Button A: X+23, Y+95
    // Button B: X+84, Y+24
    // Prize: X=7720, Y=9280

    // Button A: X+50, Y+64
    // Button B: X+87, Y+35
    // Prize: X=5591, Y=3873

    // Button A: X+12, Y+57
    // Button B: X+76, Y+16
    // Prize: X=9300, Y=15855

    // Button A: X+57, Y+37
    // Button B: X+21, Y+48
    // Prize: X=4085, Y=13370

    // Button A: X+15, Y+44
    // Button B: X+44, Y+12
    // Prize: X=10425, Y=8492

    // Button A: X+74, Y+29
    // Button B: X+22, Y+92
    // Prize: X=8086, Y=10256

    // Button A: X+60, Y+14
    // Button B: X+14, Y+43
    // Prize: X=5810, Y=15293

    // Button A: X+11, Y+35
    // Button B: X+98, Y+24
    // Prize: X=2737, Y=1801

    // Button A: X+12, Y+37
    // Button B: X+67, Y+11
    // Prize: X=2990, Y=13316

    // Button A: X+11, Y+23
    // Button B: X+66, Y+12
    // Prize: X=2409, Y=2391

    // Button A: X+51, Y+20
    // Button B: X+30, Y+68
    // Prize: X=11711, Y=15900

    // Button A: X+74, Y+44
    // Button B: X+30, Y+71
    // Prize: X=7374, Y=4863

    // Button A: X+55, Y+25
    // Button B: X+52, Y+76
    // Prize: X=3497, Y=2951

    // Button A: X+45, Y+21
    // Button B: X+17, Y+41
    // Prize: X=635, Y=7715

    // Button A: X+32, Y+34
    // Button B: X+19, Y+99
    // Prize: X=1584, Y=2944

    // Button A: X+17, Y+63
    // Button B: X+66, Y+62
    // Prize: X=6131, Y=6653

    // Button A: X+21, Y+51
    // Button B: X+59, Y+17
    // Prize: X=7033, Y=5083

    // Button A: X+51, Y+60
    // Button B: X+14, Y+97
    // Prize: X=3924, Y=10173

    // Button A: X+14, Y+49
    // Button B: X+76, Y+31
    // Prize: X=17132, Y=602

    // Button A: X+51, Y+33
    // Button B: X+25, Y+91
    // Prize: X=6213, Y=9183

    // Button A: X+13, Y+42
    // Button B: X+68, Y+33
    // Prize: X=10437, Y=1640

    // Button A: X+19, Y+39
    // Button B: X+98, Y+64
    // Prize: X=6836, Y=5528

    // Button A: X+48, Y+89
    // Button B: X+89, Y+54
    // Prize: X=7800, Y=6469

    // Button A: X+36, Y+70
    // Button B: X+59, Y+27
    // Prize: X=3458, Y=2110

    // Button A: X+92, Y+36
    // Button B: X+28, Y+42
    // Prize: X=3680, Y=3582

    // Button A: X+95, Y+11
    // Button B: X+38, Y+52
    // Prize: X=5738, Y=3568

    // Button A: X+17, Y+54
    // Button B: X+65, Y+41
    // Prize: X=4997, Y=3628

    // Button A: X+59, Y+12
    // Button B: X+19, Y+78
    // Prize: X=4589, Y=11276

    // Button A: X+55, Y+24
    // Button B: X+16, Y+28
    // Prize: X=9554, Y=2248

    // Button A: X+70, Y+15
    // Button B: X+26, Y+43
    // Prize: X=3336, Y=2998

    // Button A: X+28, Y+98
    // Button B: X+35, Y+14
    // Prize: X=3178, Y=2226

    // Button A: X+33, Y+58
    // Button B: X+96, Y+20
    // Prize: X=3177, Y=4394

    // Button A: X+11, Y+30
    // Button B: X+70, Y+48
    // Prize: X=2437, Y=2502

    // Button A: X+88, Y+21
    // Button B: X+50, Y+49
    // Prize: X=12120, Y=6006

    // Button A: X+11, Y+53
    // Button B: X+44, Y+29
    // Prize: X=1364, Y=4193

    // Button A: X+32, Y+96
    // Button B: X+65, Y+50
    // Prize: X=2523, Y=3654

    // Button A: X+11, Y+46
    // Button B: X+56, Y+26
    // Prize: X=8261, Y=6566

    // Button A: X+12, Y+59
    // Button B: X+31, Y+12
    // Prize: X=6526, Y=6227

    // Button A: X+20, Y+39
    // Button B: X+53, Y+13
    // Prize: X=16371, Y=12249

    // Button A: X+68, Y+30
    // Button B: X+47, Y+91
    // Prize: X=8384, Y=9320

    // Button A: X+29, Y+74
    // Button B: X+65, Y+19
    // Prize: X=14573, Y=10077

    // Button A: X+72, Y+37
    // Button B: X+44, Y+99
    // Prize: X=10560, Y=12760

    // Button A: X+13, Y+17
    // Button B: X+92, Y+28
    // Prize: X=5523, Y=2607

    // Button A: X+17, Y+55
    // Button B: X+85, Y+50
    // Prize: X=4216, Y=5090

    // Button A: X+46, Y+14
    // Button B: X+45, Y+74
    // Prize: X=2852, Y=19436

    // Button A: X+14, Y+51
    // Button B: X+61, Y+29
    // Prize: X=10598, Y=2717

    // Button A: X+47, Y+12
    // Button B: X+21, Y+52
    // Prize: X=4792, Y=11264

    // Button A: X+25, Y+11
    // Button B: X+18, Y+26
    // Prize: X=2044, Y=18684

    // Button A: X+91, Y+34
    // Button B: X+34, Y+99
    // Prize: X=7594, Y=5944

    // Button A: X+18, Y+42
    // Button B: X+61, Y+15
    // Prize: X=11002, Y=3110

    // Button A: X+63, Y+26
    // Button B: X+11, Y+38
    // Prize: X=4706, Y=16468

    // Button A: X+98, Y+11
    // Button B: X+99, Y+85
    // Prize: X=9996, Y=8363

    // Button A: X+30, Y+13
    // Button B: X+17, Y+36
    // Prize: X=634, Y=15136

    // Button A: X+27, Y+37
    // Button B: X+87, Y+11
    // Prize: X=6717, Y=3469

    // Button A: X+63, Y+30
    // Button B: X+27, Y+61
    // Prize: X=15569, Y=14347

    // Button A: X+11, Y+32
    // Button B: X+66, Y+38
    // Prize: X=7337, Y=6252

    // Button A: X+53, Y+12
    // Button B: X+35, Y+57
    // Prize: X=3999, Y=5175

    // Button A: X+18, Y+39
    // Button B: X+55, Y+24
    // Prize: X=628, Y=7829

    // Button A: X+33, Y+57
    // Button B: X+91, Y+18
    // Prize: X=8612, Y=6246

    // Button A: X+83, Y+14
    // Button B: X+63, Y+66
    // Prize: X=3278, Y=2048

    // Button A: X+76, Y+57
    // Button B: X+19, Y+56
    // Prize: X=3743, Y=6356

    // Button A: X+54, Y+15
    // Button B: X+17, Y+70
    // Prize: X=2761, Y=11385

    // Button A: X+77, Y+22
    // Button B: X+36, Y+88
    // Prize: X=879, Y=1650

    // Button A: X+93, Y+20
    // Button B: X+20, Y+89
    // Prize: X=1291, Y=2988

    // Button A: X+55, Y+20
    // Button B: X+26, Y+59
    // Prize: X=13498, Y=5182

    // Button A: X+95, Y+22
    // Button B: X+42, Y+56
    // Prize: X=1739, Y=958

    // Button A: X+88, Y+67
    // Button B: X+23, Y+82
    // Prize: X=2265, Y=7335

    // Button A: X+82, Y+39
    // Button B: X+32, Y+53
    // Prize: X=6268, Y=5928

    // Button A: X+29, Y+39
    // Button B: X+65, Y+14
    // Prize: X=2499, Y=2700

    // Button A: X+26, Y+58
    // Button B: X+55, Y+17
    // Prize: X=15448, Y=19380

    // Button A: X+40, Y+75
    // Button B: X+92, Y+34
    // Prize: X=2272, Y=2044

    // Button A: X+48, Y+79
    // Button B: X+88, Y+19
    // Prize: X=5872, Y=5386

    // Button A: X+30, Y+54
    // Button B: X+44, Y+19
    // Prize: X=15868, Y=14205

    // Button A: X+31, Y+22
    // Button B: X+14, Y+50
    // Prize: X=1359, Y=3168

    // Button A: X+75, Y+54
    // Button B: X+24, Y+59
    // Prize: X=3561, Y=5234

    // Button A: X+49, Y+68
    // Button B: X+36, Y+13
    // Prize: X=263, Y=11346

    // Button A: X+13, Y+59
    // Button B: X+71, Y+25
    // Prize: X=18180, Y=12844

    // Button A: X+12, Y+65
    // Button B: X+80, Y+24
    // Prize: X=13988, Y=4259

    // Button A: X+19, Y+54
    // Button B: X+33, Y+13
    // Prize: X=12480, Y=1050

    // Button A: X+36, Y+14
    // Button B: X+30, Y+44
    // Prize: X=11588, Y=10646

    // Button A: X+76, Y+90
    // Button B: X+92, Y+31
    // Prize: X=6968, Y=6147

    // Button A: X+71, Y+31
    // Button B: X+13, Y+50
    // Prize: X=9528, Y=301

    // Button A: X+85, Y+16
    // Button B: X+29, Y+48
    // Prize: X=7657, Y=3696

    // Button A: X+47, Y+95
    // Button B: X+98, Y+62
    // Prize: X=10268, Y=10412

    // Button A: X+71, Y+15
    // Button B: X+13, Y+65
    // Prize: X=11803, Y=19415

    // Button A: X+59, Y+99
    // Button B: X+82, Y+32
    // Prize: X=3493, Y=1743

    // Button A: X+17, Y+86
    // Button B: X+83, Y+36
    // Prize: X=5280, Y=2526

    // Button A: X+56, Y+13
    // Button B: X+63, Y+78
    // Prize: X=8617, Y=8021

    // Button A: X+61, Y+91
    // Button B: X+70, Y+21
    // Prize: X=9132, Y=8701

    // Button A: X+11, Y+38
    // Button B: X+72, Y+40
    // Prize: X=8960, Y=6392

    // Button A: X+81, Y+26
    // Button B: X+36, Y+91
    // Prize: X=5382, Y=8957

    // Button A: X+65, Y+32
    // Button B: X+31, Y+65
    // Prize: X=7003, Y=2317

    // Button A: X+16, Y+12
    // Button B: X+13, Y+55
    // Prize: X=1739, Y=2345
    // """;

    static String input = """
    Button A: X+94, Y+34
    Button B: X+22, Y+67
    Prize: X=8400, Y=5400

    Button A: X+26, Y+66
    Button B: X+67, Y+21
    Prize: X=12748, Y=12176

    Button A: X+17, Y+86
    Button B: X+84, Y+37
    Prize: X=7870, Y=6450

    Button A: X+69, Y+23
    Button B: X+27, Y+71
    Prize: X=18641, Y=10279
    """;

    // private static Map<String, Double> getCoefficients(Button buttonA, Button buttonB, Prize prize) {
    //     double aPresses = (prize.y - (buttonB.y * prize.x / buttonB.x)) / (buttonA.y - (buttonB.y * buttonA.x / buttonB.x));
    //     double b = (prize.x - buttonA.x * aPresses) / buttonB.x;

    //     double bPresses = (prize.x - (buttonA.x * prize.y / buttonA.y)) / (buttonB.x - (buttonA.x * buttonB.y / buttonA.y));
    //     double a = (prize.y - buttonB.y * bPresses) / buttonA.y;

    //     Map<String, Double> coefficients = new HashMap<>();
    //     coefficients.put("a", a);
    //     coefficients.put("b", b);

    //     return coefficients;
    // }

    // private static void findNonNegativeSolutions(Button buttonA, Button buttonB, Prize prize, List<Solution> result) {
    //     Map<String, Double> coefficients = getCoefficients(buttonA, buttonB, prize);

    //     double a = coefficients.get("a");
    //     double b = coefficients.get("b");

    //     System.err.println("aPresses: " + a + ", bPresses: " + b);
    //     // One or both of the coefficients is not equal to 0
    //     if (a % 1 != 0 || b % 1 != 0) {
    //         // Min and Max for a coefficient
    //         int aMin = (int) Math.floor(a);
    //         int aMax = (int) Math.ceil(a);

    //         // Min and Max for b coefficient
    //         int bMin = (int) Math.floor(b);
    //         int bMax = (int) Math.ceil(b);

    //         for (int aIteration = aMin; aIteration <= aMax; aIteration++) {
    //             for (int bIteration = bMin; bIteration <= bMax; bIteration++) {
    //                 boolean equation1 = buttonA.x * aIteration + buttonB.x * bIteration == prize.x;
    //                 boolean equation2 = buttonA.y * aIteration + buttonB.y * bIteration == prize.y;
    //                 // System.err.println("a: " + aIteration + ", b: " + bIteration + ".   Equation1: " + equation1 + ", equation2: " + equation2);
    //                 if (equation1 && equation2) {
    //                     Solution foundSolution = new Solution(aIteration, bIteration);
    //                     result.add(foundSolution);
    //                 }
    //             }
    //         }

    //     } else {
    //         int aInteger = (int) a;
    //         int bInteger = (int) b;
    //         boolean equation1 = buttonA.x * aInteger + buttonB.x * bInteger == prize.x;
    //         boolean equation2 = buttonA.y * aInteger + buttonB.y * bInteger == prize.y;

    //         System.err.println("equation1: " + equation1 + ", equation2: " + equation2);

    //         if (equation1 && equation2) {
    //             Solution foundSolution = new Solution(aInteger, bInteger);
    //             result.add(foundSolution);
    //         }
    //     }

    // }
    
    // private static int solve(Button buttonA, Button buttonB, Prize prize) {
        
    //     // double aPresses_1 = (prize.y - buttonB.y * bPresses_1) / buttonA.y;

    //     List<Solution> result = new ArrayList<>();

    //     // System.out.println("aPresses_1: " + aPresses_1 + ", bPresses_1: " + bPresses_1);
        
    //     findNonNegativeSolutions(buttonA, buttonB, prize, result);

    //     int bestCost = Integer.MAX_VALUE;
    //     if (result.size() > 1) {
    //         // Find best solution
    //         for (Solution foundSolution: result) {
    //             int cost = foundSolution.x * 3 + foundSolution.y;
    //             if (cost < bestCost) {
    //                 bestCost = cost;
    //             }
    //         }
    //     } else if (result.size() == 1) {
    //         Solution onlyFoundSolution = result.get(0);
    //         bestCost = onlyFoundSolution.x * 3 + onlyFoundSolution.y;
    //     } else {
    //         bestCost = 0;
    //     }

    //     // System.err.println("");

    //     // double bPresses_2 = (prize.x - buttonA.x * aPresses_2) / buttonB.x;

    //     // System.out.println("aPresses_2: " + aPresses_2 + ", bPresses_2: " + bPresses_2);
        
    //     // boolean equation1_2 = buttonA.x * aPresses_2 + buttonB.x * bPresses_2 == prize.x;
    //     // boolean equation2_2 = buttonA.y * aPresses_2 + buttonB.y * bPresses_2 == prize.y;

    //     // System.err.println("equation1_2: " + equation1_2 + ", equation2_2: " + equation2_2);

    //     return bestCost;
    // }

    // // Parse the buttons and prize
    // private static Button parseButton(String line) {
    //     Pattern pattern = Pattern.compile("Button .: X\\+(\\d+), Y\\+(\\d+)");
    //     Matcher matcher = pattern.matcher(line);
    //     if (matcher.find()) {
    //         return new Button(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    //     }
    //     return null;
    // }
    
    // private static Prize parsePrize(String line) {
    //     Pattern pattern = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
    //     Matcher matcher = pattern.matcher(line);
    //     if (matcher.find()) {
    //         return new Prize(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    //     }
    //     return null;
    // }

    // public static void main(String[] args) {
    //     String[] machines = input.trim().split("\n\n");
    //     int totalTokens = 0;
    //     int prizesWon = 0;
        
    //     for (String machine : machines) {
    //         String[] lines = machine.trim().split("\n");
    //         Button buttonA = parseButton(lines[0]);
    //         Button buttonB = parseButton(lines[1]);
    //         Prize prize = parsePrize(lines[2]);
            
    //         System.out.println("Machine: ");
    //         System.out.println("Button A: " + buttonA);
    //         System.out.println("Button B: " + buttonB);
    //         System.out.println("Prize: " + prize);
            
    //         int solutionCost = solve(buttonA, buttonB, prize);
            
    //         if (solutionCost != 0) {
    //             totalTokens += solutionCost;
    //             prizesWon++;
    //         } else {
    //             System.out.println("No solution exists for this machine");
    //         }
            
    //         System.out.println();
    //     }
        
    //     System.out.println("Total prizes won: " + prizesWon);
    //     System.out.println("Total tokens required: " + totalTokens);
    // }

    public static void main(String[] args) {
        String[] machines = input.trim().split("\n\n");
        int totalTokens = 0;
        int prizesWon = 0;
        
        for (String machine : machines) {
            String[] lines = machine.trim().split("\n");
            Button buttonA = parseButton(lines[0]);
            Button buttonB = parseButton(lines[1]);
            Prize prize = parsePrize(lines[2]);
            
            System.out.println("Machine: ");
            System.out.println("Button A: " + buttonA);
            System.out.println("Button B: " + buttonB);
            System.out.println("Prize: " + prize);
            
            Solution solution = findBestSolution(buttonA, buttonB, prize);
            
            if (solution != null) {
                int cost = solution.x * 3 + solution.y;
                totalTokens += cost;
                prizesWon++;
                System.out.println("Solution found: A=" + solution.a + ", B=" + solution.b + ", Cost=" + cost);
            } else {
                System.out.println("No solution exists for this machine");
            }
            
            System.out.println();
        }
        
        System.out.println("Total prizes won: " + prizesWon);
        System.out.println("Total tokens required: " + totalTokens);
    }
    
    private static Solution findBestSolution(Button buttonA, Button buttonB, Prize prize) {
        // Linear Diophantine equation solver for:
        // a*buttonA.x + b*buttonB.x = prize.x
        // a*buttonA.y + b*buttonB.y = prize.y
        
        // We need to find integer solutions where a >= 0 and b >= 0
        // and minimize 3*a + b
        
        Solution bestSolution = null;
        int bestCost = Integer.MAX_VALUE;
        
        // Search a reasonable range (problem states no more than 100 presses)
        // But we'll search a wider range just to be safe
        for (int a = 0; a <= 200; a++) {
            // Calculate the required b value to satisfy the X equation
            // a*buttonA.x + b*buttonB.x = prize.x
            // b = (prize.x - a*buttonA.x) / buttonB.x
            
            if (buttonB.x == 0) {
                // Check if a solution exists with this a value
                if (a * buttonA.x == prize.x) {
                    // Calculate b from the Y equation
                    int neededY = prize.y - a * buttonA.y;
                    if (neededY % buttonB.y == 0) {
                        int b = neededY / buttonB.y;
                        if (b >= 0) {
                            int cost = 3 * a + b;
                            if (cost < bestCost) {
                                bestCost = cost;
                                bestSolution = new Solution(a, b);
                            }
                        }
                    }
                }
                continue;
            }
            
            if ((prize.x - a * buttonA.x) % buttonB.x != 0) {
                continue; // b must be an integer
            }
            
            int b = (prize.x - a * buttonA.x) / buttonB.x;
            if (b < 0) {
                continue; // b must be non-negative
            }
            
            // Verify the Y equation is also satisfied
            if (a * buttonA.y + b * buttonB.y == prize.y) {
                int cost = 3 * a + b;
                if (cost < bestCost) {
                    bestCost = cost;
                    bestSolution = new Solution(a, b);
                }
            }
        }
        
        return bestSolution;
    }

    private static Button parseButton(String line) {
        Pattern pattern = Pattern.compile("Button .: X\\+(\\d+), Y\\+(\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return new Button(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        }
        return null;
    }
    
    private static Prize parsePrize(String line) {
        Pattern pattern = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return new Prize(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        }
        return null;
    }

}


// public class Thirteen {
//     static String input = """
//     Button A: X+94, Y+34
//     Button B: X+22, Y+67
//     Prize: X=8400, Y=5400

//     Button A: X+26, Y+66
//     Button B: X+67, Y+21
//     Prize: X=12748, Y=12176

//     Button A: X+17, Y+86
//     Button B: X+84, Y+37
//     Prize: X=7870, Y=6450

//     Button A: X+69, Y+23
//     Button B: X+27, Y+71
//     Prize: X=18641, Y=10279
//     """;

//     // Extended Euclidean Algorithm to find GCD and Bézout coefficients
//     public static GcdResult extendedGcd(int a, int b) {
//         if (a == 0) {
//             return new GcdResult(b, 0, 1);
//         }
        
//         GcdResult result = extendedGcd(b % a, a);
//         int gcd = result.gcd;
//         int x = result.y - (b / a) * result.x;
//         int y = result.x;
        
//         return new GcdResult(gcd, x, y);
//     }
    
//     // Find all non-negative solutions to ax + by = c
//     private static List<Press> findNonNegativeSolutions(int a, int b, int c) {
//         List<Press> solutions = new ArrayList<>();
        
//         // Check if a solution exists
//         GcdResult gcdResult = extendedGcd(Math.abs(a), Math.abs(b));
//         int gcd = gcdResult.gcd;
        
//         if (c % gcd != 0) {
//             // No integer solutions exist
//             return solutions;
//         }
        
//         // Find particular solution
//         int x0 = gcdResult.x * (c / gcd);
//         int y0 = gcdResult.y * (c / gcd);
        
//         // Adjust for sign of a and b
//         if (a < 0) x0 = -x0;
//         if (b < 0) y0 = -y0;
        
//         // General solution is x = x0 + k*(b/gcd), y = y0 - k*(a/gcd)
//         // We need to find k such that both x and y are non-negative
        
//         // Calculate steps in the solution space
//         int step_x = b / gcd;
//         int step_y = a / gcd;
        
//         // Ensure steps have correct signs
//         if (step_x < 0) step_x = -step_x;
//         if (step_y < 0) step_y = -step_y;
        
//         // Find smallest k where x >= 0
//         int k_min_x = x0 < 0 ? (int)Math.ceil(-1.0 * x0 / step_x) : 0;
        
//         // Find smallest k where y >= 0
//         int k_min_y = y0 < 0 ? (int)Math.ceil(-1.0 * y0 / step_y) : 0;
        
//         // Start from the larger of the two k values
//         int k = Math.max(k_min_x, k_min_y);
        
//         // Generate all solutions within reasonable bounds (button presses <= 100)
//         while (true) {
//             int x = x0 + k * step_x;
//             int y = y0 - k * step_y;
            
//             if (x < 0 || y < 0 || x > 100 || y > 100) {
//                 break;
//             }
            
//             solutions.add(new Press(x, y));
//             k++;
//         }
        
//         return solutions;
//     }
    
//     // Solve for a machine with given buttons and prize
//     private static Press solveMachine(Button buttonA, Button buttonB, Prize prize) {
//         // Solve x-equation: a*A_x + b*B_x = Prize_x
//         List<Press> xSolutions = findNonNegativeSolutions(buttonA.x, buttonB.x, prize.x);
        
//         if (xSolutions.isEmpty()) {
//             return null;
//         }
        
//         // Solve y-equation: a*A_y + b*B_y = Prize_y
//         List<Press> ySolutions = findNonNegativeSolutions(buttonA.y, buttonB.y, prize.y);
        
//         if (ySolutions.isEmpty()) {
//             return null;
//         }
        
//         // Find common solution with minimum cost
//         Press bestSolution = null;
//         int minCost = Integer.MAX_VALUE;
        
//         for (Press xSol : xSolutions) {
//             for (Press ySol : ySolutions) {
//                 // Check if this is a common solution
//                 if (xSol.x == ySol.x && xSol.y == ySol.y) {
//                     int cost = 3 * xSol.x + xSol.y;
//                     if (cost < minCost) {
//                         minCost = cost;
//                         bestSolution = xSol;
//                     }
//                 }
//             }
//         }
        
//         return bestSolution;
//     }
    
//     // Parse the buttons and prize
//     private static Button parseButton(String line) {
//         Pattern pattern = Pattern.compile("Button .: X\\+(\\d+), Y\\+(\\d+)");
//         Matcher matcher = pattern.matcher(line);
//         if (matcher.find()) {
//             return new Button(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
//         }
//         return null;
//     }
    
//     private static Prize parsePrize(String line) {
//         Pattern pattern = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
//         Matcher matcher = pattern.matcher(line);
//         if (matcher.find()) {
//             return new Prize(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
//         }
//         return null;
//     }

//     public static void main(String[] args) {
//         String[] machines = input.trim().split("\n\n");
//         int totalTokens = 0;
//         int prizesWon = 0;
        
//         for (String machine : machines) {
//             String[] lines = machine.trim().split("\n");
//             Button buttonA = parseButton(lines[0]);
//             Button buttonB = parseButton(lines[1]);
//             Prize prize = parsePrize(lines[2]);
            
//             System.out.println("Machine: " + machine);
//             System.out.println("Button A: " + buttonA);
//             System.out.println("Button B: " + buttonB);
//             System.out.println("Prize: " + prize);
            
//             Press solution = solveMachine(buttonA, buttonB, prize);
            
//             if (solution != null) {
//                 int cost = 3 * solution.x + solution.y;
//                 System.out.println("Solution found: " + solution.x + " A presses, " + solution.y + " B presses");
//                 System.out.println("Cost: " + cost + " tokens");
//                 totalTokens += cost;
//                 prizesWon++;
//             } else {
//                 System.out.println("No solution exists for this machine");
//             }
            
//             System.out.println();
//         }
        
//         System.out.println("Total prizes won: " + prizesWon);
//         System.out.println("Total tokens required: " + totalTokens);
//     }
// }

class Button {
    int x, y;
    
    Button(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "X+" + x + ", Y+" + y;
    }
}

class Prize {
    int x, y;
    
    Prize(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "X=" + x + ", Y=" + y;
    }
}

class Press {
    int x, y;
    
    Press(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    int x, y;
    
    Solution(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// 34067
