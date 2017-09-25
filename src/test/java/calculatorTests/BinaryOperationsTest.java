package calculatorTests;


import javafx.scene.input.KeyCode;
import model.BinaryOperation;
import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static model.BinaryOperation.*;

/**
 * Created by Yaroslav on 25.07.2017.
 */

public class BinaryOperationsTest extends MyTestBox {

    private String currentValue = "792";

    @Test
    public void operationByKeyboardTest() {
        executeLine(currentValue);
        operationByKeyboardTestTemplate(MULTIPLY);
        operationByKeyboardTestTemplate(DIVIDE);
        operationByKeyboardTestTemplate(PLUS);
        operationByKeyboardTestTemplate(MINUS);
    }

    @Test
    public void plusTest() {
        // integer value
        combinationTestTemplate("1 + + 4± + -","-3","1 + -4 -");
        combinationTestTemplate("0 + + 0 -","0","0 + 0 -");
        combinationTestTemplate("0 + 1± +","-1","0 + -1 +");
        combinationTestTemplate("61 + 5 -","66");
        combinationTestTemplate("79 + 98 -","177");
        combinationTestTemplate("300 + 0 -","300","300 + 0 -");
        combinationTestTemplate("0 + 300 -","300", "0 + 300 -");
        combinationTestTemplate("9± + 6 -","-3","-9 + 6 -");
        combinationTestTemplate("4± + 3 -","-1","-4 + 3 -");
        combinationTestTemplate("24±5 + 245 -","0","-245 + 245 -");
        combinationTestTemplate("232± + 90 -","-142","-232 + 90 -");
        combinationTestTemplate("982 + 125 -","1 107");
        combinationTestTemplate("9± + 4272± -","-4 281","-9 + -4272 -");
        combinationTestTemplate("67±8 + 4±42 -","-1 120","-678 + -442 -");
        combinationTestTemplate("5695 + 7683 -","13 378");
        combinationTestTemplate("3279 + 953085 -","956 364");
        combinationTestTemplate("326899999±9 + 285712±4897587921 -","-2 857 128 166 587 920","-3268999999 + -2857124897587921 -");
        combinationTestTemplate("4325890129949955± + 21±45789919358327 -","-6 471 680 049 308 282","-4325890129949955 + -2145789919358327 -");
        combinationTestTemplate("23 ++++ +        50 -","73","23 + 50 -");
        combinationTestTemplate("236  + + 23±6 -","0","236 + -236 -");
        combinationTestTemplate("+ 43±6 + + 2± -","-438","0 + -436 + -2 -");
        combinationTestTemplate("125 ++ 4 + 982 -","1 111","125 + 4 + 982 -");
        combinationTestTemplate("7683 + 5695 ++++ 1 -","13 379","7683 + 5695 + 1 -");
        combinationTestTemplate("467892365 + 4678±92365 ++","0","467892365 + -467892365 +");
        combinationTestTemplate("34551572±4 + + 52367423 +","-293 148 301","-345515724 + 52367423 +");
        combinationTestTemplate("94 + 900000000000  + + + + +","900 000 000 094","94 + 900000000000 +");

        // float value
        combinationTestTemplate("2,5 + 2,5 -","5");
        combinationTestTemplate("4,5 + 4± -","0,5","4,5 + -4 -");
        combinationTestTemplate("22,5 + 22,5± +","0","22,5 + -22,5 +");
        combinationTestTemplate("90 + 6,66±66 +","83,3334","90 + -6,6666 +");
        combinationTestTemplate("6,666±6 + 90 -","83,3334","-6,6666 + 90 -");
        combinationTestTemplate("42 + 5963,09 -","6 005,09");
        combinationTestTemplate("69023,776 + 326 -","69 349,776");
        combinationTestTemplate("3426,200±6 + 7,6± -","-3 433,8006","-3426,2006 + -7,6 -");
        combinationTestTemplate("6673,000004 + 3555 -","10 228,000004");
        combinationTestTemplate("82,2 + 7,3252350202 -","89,5252350202");
        combinationTestTemplate("++ + 6,4 + 0 -","6,4","0 + 6,4 + 0 -");
        combinationTestTemplate("333 + + 52,6 -","385,6","333 + 52,6 -");
        combinationTestTemplate("532,51± + + 532,51 -","0","-532,51 + 532,51 -");
        combinationTestTemplate("325,3333 + + 7,3±26 -","318,0073","325,3333 + -7,326 -");
        combinationTestTemplate("400±3,62366 +++ 97690±2,35 -","-980 905,97366","-4003,62366 + -976902,35 -");

        // exp system //
        combinationTestTemplate("9999999999999999 + 0,00000001 -","9 999 999 999 999 999");
        combinationTestTemplate("9999999999999999 + 0,4999999999999999 -","9 999 999 999 999 999");
        combinationTestTemplate("9999999999999999,4 + 0,5 -","1,e+16","9999999999999999 + 0,5 -");
        combinationTestTemplate("9999999999999999 + 1 -","1,e+16");
        combinationTestTemplate("9999999999999999 + 2 -","1,e+16");
        combinationTestTemplate("9999999999999999± + 3± -","-1,e+16","-9999999999999999 + -3 -");
        combinationTestTemplate("9999999999999999 + 10 -","1,000000000000001e+16");
        combinationTestTemplate("9999999999999999 + 1345 -","1,000000000000134e+16");
        combinationTestTemplate("9999999999999999 + 9999999999999999 -","2,e+16");
        combinationTestTemplate("3257589199682235 + 9890099999999999 -","1,314768919968223e+16");
        combinationTestTemplate("9358732626723758 + 1212402357527735 -","1,057113498425149e+16");
        combinationTestTemplate("999999999999999±9 + 999999999±9999999 -","-2,e+16","-9999999999999999 + -9999999999999999 -");

        // overflow
        combinationTestTemplate("9999999999999999 sqr sqr sqr sqr sqr sqr sqr sqr sqr" +
                "+ 9999999999999999 sqr sqr sqr sqr sqr sqr sqr sqr sqr * =","Переполнение",
                "sqr( sqr( sqr( sqr( sqr( sqr( sqr( sqr( sqr( 9999999999999999 ) ) ) ) ) ) ) ) ) + " +
                        "sqr( sqr( sqr( sqr( sqr( sqr( sqr( sqr( sqr( 9999999999999999 ) ) ) ) ) ) ) ) ) ×");

    }

    @Test
    public void minusTest() {  // value_1 subtracts from value_2

        // integer value
        combinationTestTemplate("282 ± - 90 -","-372","-282 - 90 -");
        combinationTestTemplate("67±8  - 442±  -","-236","-678 - -442 -");
        combinationTestTemplate("5±9  - 427±2  -","4 213","-59 - -4272 -");
        combinationTestTemplate("56235724 - 56235724 -","0");
        combinationTestTemplate("525695 - 6232 -","519 463");
        combinationTestTemplate("145325 - 9862 -","135 463");
        combinationTestTemplate("432535 - 570 -","431 965");
        combinationTestTemplate("6213361 - 5 -","6 213 356");
        combinationTestTemplate("3456443±27  - 233536 -","-345 877 863","-345644327 - 233536 -");
        combinationTestTemplate("3243680920746 - 900000000000 -","2 343 680 920 746");
        combinationTestTemplate("326899955±9 - 285716909368792±1  -","2 857 165 824 688 362","-3268999559 - -2857169093687921 -");
        combinationTestTemplate("99999999999999±99 - 99999999999±99999  -","0","-9999999999999999 - -9999999999999999 -");
        combinationTestTemplate("0 --- 0 -","0","0 - 0 -");
        combinationTestTemplate("3 - 0 +---","3","3 - 0 -");
        combinationTestTemplate("9±- -  - 6 -","-15","-9 - 6 -");
        combinationTestTemplate("79 --- 98 -","-19","79 - 98 -");
        combinationTestTemplate("631 - 4±  ------ -","635","631 - -4 -");
        combinationTestTemplate("436±  - 2±  - - - - - - - ","-434","-436 - -2 -");

        //float value
        combinationTestTemplate("795,64± - 808±,7 -","13,06","-795,64 - -808,7 -");
        combinationTestTemplate("86765,7 - 880,5 -","85 885,2");
        combinationTestTemplate("80759,9 - 8354,96 -","72 404,94");
        combinationTestTemplate("79719,6 - 82744,239 -","-3 024,639");
        combinationTestTemplate("626,38 - 2,53±  -","628,91","626,38 - -2,53 -");
        combinationTestTemplate("468,647 - 468,647 -","0");
        combinationTestTemplate("988,1585175119133 - 951,146425072481 -","37,0120924394323");
        combinationTestTemplate("559,5368190734623 - 761,3890463102514 -","-201,8522272367891");
        combinationTestTemplate("48,91938658223057±  - 529,53890222±75615  -","480,6195156453309","-48,91938658223057 - -529,5389022275615 -");
        combinationTestTemplate("63±,67438  - 212 -","-275,67438","-63,67438 - 212 -");
        combinationTestTemplate("159,±2378334240943  - 166,±4704900087808  -","7,2326565846865","-159,2378334240943 - -166,4704900087808 -");
        combinationTestTemplate("129,3215065814358 - 16481,23592425399 -","-16 351,91441767255");
        combinationTestTemplate("4±94,685818449823  - 594,±543776176685  -","99,857957726862","-494,685818449823 - -594,543776176685 -");
        combinationTestTemplate("633,2793±51349099  - 706±72,0331552599  -","70 038,7538039108","-633,279351349099 - -70672,0331552599 -");
        combinationTestTemplate("89884,±0603350258  - 93041,1856138255±  -","3 157,1252787997","-89884,0603350258 - -93041,1856138255 -");
        combinationTestTemplate("7838±9,1362073806  - 854,91764±6085231  -","-77 534,21856129537","-78389,1362073806 - -854,917646085231 -");
        combinationTestTemplate("71914,765±3506011  - 78729,±4865858898  -","6 814,7212352887","-71914,7653506011 - -78729,4865858898 -");
        combinationTestTemplate("60604,30325182377 - 78624,6126157167 -","-18 020,30936389293");
        combinationTestTemplate("71089,43807457638 - 838,589430747437 -","70 250,84864382894");
        combinationTestTemplate("85955,19005577093 - 84135,37131054002 -","1 819,81874523091");
        combinationTestTemplate("96184,93593943314 - 84641,39537837966 -","11 543,54056105348");
        combinationTestTemplate("326899955±9  - 285716909368±7921 -","2 857 165 824 688 362","-3268999559 - -2857169093687921 -");
        combinationTestTemplate("99999999999±99999  - 999999±9999999999  -","0","-9999999999999999 - -9999999999999999 -");

        //exp system
        combinationTestTemplate("999999±9999999999  - 5 -","-1,e+16","-9999999999999999 - 5 -");
        combinationTestTemplate("9345748699239879±  - 4354622000000000 -","-1,370037069923988e+16","-9345748699239879 - 4354622000000000 -");
        combinationTestTemplate("3532521523523570 - 9532521523525345  ±    -","1,306504304704892e+16","3532521523523570 - -9532521523525345 -");
    }

    @Test
    public void multiplyTest() {

        //integer number
        combinationTestTemplate("0 * 63 /","0");
        combinationTestTemplate("87 * 1± /","-87","87 × -1 ÷");
        combinationTestTemplate("462 * 1± /","-462","462 × -1 ÷");
        combinationTestTemplate("781 * 1 /","781");
        combinationTestTemplate("3267 * 1±  /","-3 267","3267 × -1 ÷");
        combinationTestTemplate("3±97 * 33±3 /","132 201","-397 × -333 ÷");
        combinationTestTemplate("73221 * 711 /","52 060 131");
        combinationTestTemplate("791 * 75431 /","59 665 921");
        combinationTestTemplate("129±93 * 1±51  /","1 961 943","-12993 × -151 ÷");
        combinationTestTemplate("5899± * 53830± /","317 543 170","-5899 × -53830 ÷");
        combinationTestTemplate("3347±3 * 263±47 /","881 913 131","-33473 × -26347 ÷");
        combinationTestTemplate("496876663 * 317 /","157 509 902 171");
        combinationTestTemplate("554± * 4±6750289 /","25 899 660 106","-554 × -46750289 ÷");
        combinationTestTemplate("50254 * 3340682 /","167 882 633 228");
        combinationTestTemplate("124431005± * 13±1  /","16 300 461 655","-124431005 × -131 ÷");
        combinationTestTemplate("56846 * 446525491 /","25 383 188 061 386");
        combinationTestTemplate("87914 * 878076122 /","77 195 184 189 508");
        combinationTestTemplate("2640040±42  * 2274±2  /","6 003 979 923 164","-264004042 × -22742 ÷");
        combinationTestTemplate("39614±  * 31068±3645  /","12 307 421 913 030","-39614 × -310683645 ÷");
        combinationTestTemplate("561093526± * 46778±     /","26 246 832 959 228","-561093526 × -46778 ÷");
        combinationTestTemplate("3 *** 5 ** 3 *","45","3 × 5 × 3 ×");
        combinationTestTemplate("691 ** 1 *** 71031 / ****","49 082 421","691 × 1 × 71031 ×");
        combinationTestTemplate("7925 *************** 87529 ** /","693 667 325","7925 × 87529 ÷");
        combinationTestTemplate("3478 * * * 7548 ** 1 * 424 4 * 0/","0","3478 × 7548 × 1 × 4244 × 0 ÷");
        combinationTestTemplate("976426107 * * * 95533 * 1 * 1 ** /","93 280 915 280 031","976426107 × 95533 × 1 × 1 ÷");

        //float number
        combinationTestTemplate("4433,89 * 0 /","0");
        combinationTestTemplate("0 * 6136,623901 /","0");
        combinationTestTemplate("169 * 310,6 /","52 491,4");
        combinationTestTemplate("134,6±  * 205±  /","27 593","-134,6 × -205 ÷");
        combinationTestTemplate("14,5 * 26031,8 /","377 461,1");
        combinationTestTemplate("383,43 * 579 /","222 005,97");
        combinationTestTemplate("8848±2  * 95,5±  /","8 450 031","-88482 × -95,5 ÷");
        combinationTestTemplate("226±  * 32205±,9 /","7 278 533,4","-226 × -32205,9 ÷");
        combinationTestTemplate("450,2±1 * 434,13±  /","195 449,6673","-450,21 × -434,13 ÷");
        combinationTestTemplate("228 * 44781,34 /","10 210 145,52");
        combinationTestTemplate("352 * 51574,07 /","18 154 072,64");
        combinationTestTemplate("187,9 * 33671,687 /","6 326 909,9873");
        combinationTestTemplate("32167,064 * 509 /","16 373 035,576");
        combinationTestTemplate("318,7±  * 3703±9,33 /","11 804 434,471","-318,7 × -37039,33 ÷");
        combinationTestTemplate("737,14± * 72348±,77 /","53 331 172,3178","-737,14 × -72348,77 ÷");
        combinationTestTemplate("6205±7,64 * 647±,37 /","40 174 254,4068","-62057,64 × -647,37 ÷");
        combinationTestTemplate("29179± * 332±3,507±± /","96 976 610,753","-29179 × -3323,507 ÷");
        combinationTestTemplate("91680± * 99±681,98  /","9 138 843 926,4","-91680 × -99681,98 ÷");
        combinationTestTemplate("22191±  * 3146±8,209  /","698 311 025,919","-22191 × -31468,209 ÷");
        combinationTestTemplate("1257±6,821 * 187±,82  /","2 362 178,52022","-12576,821 × -187,82 ÷");
        combinationTestTemplate("54710,192 * 64576 /","3 532 965 358,592");
        combinationTestTemplate("66316,779 * 73824 /","4 895 769 892,896");
        combinationTestTemplate("71300,579 * 82352,4 /","5 871 773 802,0396");
        combinationTestTemplate("77201,9±22 * 7521±7,3 /","5 806 920 127,6506","-77201,922 × -75217,3 ÷");

        // exp system
        combinationTestTemplate("95916±0239 * 51556±3799 /","4,94508296668588e+17","-959160239 × -515563799 ÷");
        combinationTestTemplate("956507101± * 3±44158305 /","3,291898626006238e+17","-956507101 × -344158305 ÷");
        combinationTestTemplate("656105698±  * 58662933±9 /","3,848908519318736e+17","-656105698 × -586629339 ÷");
        combinationTestTemplate("89465129±  * 294539007±  /","2,63509702567869e+16","-89465129 × -294539007 ÷");
        combinationTestTemplate("1168040705 * 52245±5631 /","-6,102494435644599e+17","1168040705 × -522455631 ÷");
    }

    @Test
    public void divideTest() {   // value_2 divides on value_1

        //integer number
        combinationTestTemplate("292 / 73 /","4");
        combinationTestTemplate("777 / 1 ± /","-777","777 ÷ -1 ÷");
        combinationTestTemplate("863 / 1 /","863");
        combinationTestTemplate("723± / 7±22 /","1,001385041551247","-723 ÷ -722 ÷");
        combinationTestTemplate("387 / 15953 /","0,0242587601078167");
        combinationTestTemplate("519 / 22733 /","0,022830246777812");
        combinationTestTemplate("827 / 55450 /","0,0149143372407574");
        combinationTestTemplate("0 / 236582 /","0");
        combinationTestTemplate("51142 / 21580 /","2,369879518072289");
        combinationTestTemplate("143225076 / 116 /","1 234 698,931034483");
        combinationTestTemplate("287167790 / 123 /","2 334 697,479674797");
        combinationTestTemplate("704468565 / 401 /","1 756 779,463840399");
        combinationTestTemplate("923±72 / 9±3265 /","0,9904251326864311","-92372 ÷ -93265 ÷");
        combinationTestTemplate("87589± / 9±1826 /","0,9538583843355912","-87589 ÷ -91826 ÷");
        combinationTestTemplate("804± / 79±227 /","0,0101480555871105","-804 ÷ -79227 ÷");
        combinationTestTemplate("721± / 69217± /","0,0104165161737723","-721 ÷ -69217 ÷");
        combinationTestTemplate("12204±2108 / 266± /","458 804,9172932331","-122042108 ÷ -266 ÷");
        combinationTestTemplate("90697 / 602328747 /","1,505772395087097e-4");
        combinationTestTemplate("92009 / 710954317 /","1,294161914484866e-4");
        combinationTestTemplate("94867 / 852928786 /","1,112249950490005e-4");
        combinationTestTemplate("719450892± / 620271096± /","1,15989749746456","-719450892 ÷ -620271096 ÷");
        combinationTestTemplate("849099439± / 827±963423 /","1,025527717061965","-849099439 ÷ -827963423 ÷");
        combinationTestTemplate("4 /// 2 // 1 / / 2 // /","1","4 ÷ 2 ÷ 1 ÷ 2 ÷");
        combinationTestTemplate("700 / 2 / /// 1 //// 2 *","175","700 ÷ 2 ÷ 1 ÷ 2 ×");
        combinationTestTemplate("740 // / 550077867 /  /","1,345264087856856e-6","740 ÷ 550077867 ÷");
        combinationTestTemplate("23662 / 23662 /// 1 / 1 ///  ","1","23662 ÷ 23662 ÷ 1 ÷ 1 ÷");

        //float number
        combinationTestTemplate("389,9 / 485,2 /","0,8035861500412201");
        combinationTestTemplate("187,±7 / 134,±84 /","1,39202017205577","-187,7 ÷ -134,84 ÷");
        combinationTestTemplate("743,93 / 648,2 /","1,147685899413761");
        combinationTestTemplate("85056,9 / 7069 /","12,03238081765455");
        combinationTestTemplate("0 / 2369,6237 /","0");
        combinationTestTemplate("57856,9 / 1 /","57 856,9");
        combinationTestTemplate("57856,9 / 1±  /","-57 856,9","57856,9 ÷ -1 ÷");
        combinationTestTemplate("68531,96 / 621,19 /","110,3236690867528");
        combinationTestTemplate("17691,972 / 220 /","80,41805454545455");
        combinationTestTemplate("802 / 67838 /","0,0118222824965359");
        combinationTestTemplate("451±,4 / 451,4± -","1","-451,4 ÷ -451,4 -");
        combinationTestTemplate("704±,4 / 817±46 /","0,0086169353852176","-704,4 ÷ -81746 ÷");
        combinationTestTemplate("57637± / 50817± /","1,134207056693626","-57637 ÷ -50817 ÷");
        combinationTestTemplate("43318±,6 / 43115±,2 /","1,004717593795228","-43318,6 ÷ -43115,2 ÷");
        combinationTestTemplate("347±,6 / 34021±,3 /","0,0102171286811498","-347,6 ÷ -34021,3 ÷");
        combinationTestTemplate("80649±,56 / 842± /","95,78332541567696","-80649,56 ÷ -842 ÷");
        combinationTestTemplate("59846±,009 / 547±,1 /","109,3876969475416","-59846,009 ÷ -547,1 ÷");
        combinationTestTemplate("35049,06± / 378± /","92,72238095238095","-35049,06 ÷ -378 ÷");
        combinationTestTemplate("329,64± / 246±,648 /","1,336479517368882","-329,64 ÷ -246,648 ÷");
        combinationTestTemplate("27067,843 / 3935,54 /","6,87779643962455");
        combinationTestTemplate("856,669 / 89357,869 /","0,0095869452750714");
        combinationTestTemplate("97078,524 / 96807,909 /","1,002795381108789");

        // exp system*
        combinationTestTemplate("94 / 784813831 /","1,197736281994755e-7");
        combinationTestTemplate("0,0000000000000001 / 100 /","1,e-18");
        combinationTestTemplate("0,11 / 9999999999999999 /","1,1e-17");

        // exception division by zero
        combinationTestTemplate("0 / 0 =","Деление на 0","0 ÷");
        combinationTestTemplate("0,002 / 0 =","Деление на 0","0,002 ÷");
        combinationTestTemplate("35 / 0 =","Деление на 0","35 ÷");
    }

    @Test
    public void combinationsTest(){
        combinationTestTemplate( "8 + 90 / 2 *","49");
        combinationTestTemplate( "2 / 2 / 4 / 4 * 9 +","0,5625");
        combinationTestTemplate( "2 + 2 + 2 + 2 + 2 + 2 + 2 =","14");
        combinationTestTemplate( "4 + 6 - 19 + 25 * 1,006 +","16,096");
        combinationTestTemplate( "8673 - 98 - 4532 + 8490 * 3 -","37 599");
        combinationTestTemplate( "784 / 8 * 9 + 77893 - 87942 -","-9 167");
        combinationTestTemplate( "451 + 498 - 9,89 + 0,3333333 * 0 /","0");
        combinationTestTemplate( "9999999999999999 + 0,5 + 0,5 /","1,e+16");
        combinationTestTemplate( "231 * 43 / 1 + 460 - 2 * 32 -","332 512");
        combinationTestTemplate( "462 + 7 - 8 * 2 / 6 +","153,6666666666667");
        combinationTestTemplate( "350 + 350 / 2 * 0,5 * 4 - 800,1 +","-100,1");
        combinationTestTemplate( "98784 - 999 * 4 / 0,98 +","399 122,4489795918");
        combinationTestTemplate( "4789 - 895 * 94 / 8993 + 9 =","49,70232402980096");
        combinationTestTemplate( "478 / 0,52 + 124 / 6 + 99994 *","100 167,8717948718");
        combinationTestTemplate( "563 - 3523 / 2144 - 4,9 + 67,1 =","60,81940298507463");
        combinationTestTemplate( "3267 * 82 - 1908 + 2 / 1672,604 +","159,0262847631597");
        combinationTestTemplate( "4,889 + 90 / 1 * 408 - 12 * 0,0000001 *","0,0038702712");
        combinationTestTemplate( "9999999999999999 + 0,5 + 0,5 + 9 =","1,000000000000001e+16");
        combinationTestTemplate( "3 * 478953125 - 0,9999 * 432 - 2888888 +","620 720 360 680,0432");
    }

    @After
    public void clear() {
        clickOn("C");
    }

    private void operationByKeyboardTestTemplate(BinaryOperation operation) {
        switch (operation) {
            case MULTIPLY:
                push(KeyCode.MULTIPLY);
                break;
            case DIVIDE:
                push(KeyCode.DIVIDE);
                break;
            case PLUS:
                push(KeyCode.ADD);
                break;
            case MINUS:
                push(KeyCode.SUBTRACT);
                break;
        }
        assertEquals(currentValue + " " + binaryOperationStringMap.get(operation) + " ", historyLabel.getText());
    }
}
