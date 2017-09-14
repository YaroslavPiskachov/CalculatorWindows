package calculatorTests;

import org.junit.After;
import org.junit.Test;


/**
 * Created by Yaroslav on 03.08.2017.
 */

public class CombinationTest extends MyTestBox {

    private String minPositiveValue = "0,0000000000000001 / 10000000000  = = = = = = = = / 10 = = = = * = = = = = = = = = * = = = = = = = = / 10000000000" +
            " = = = = = = = = = = / 1000000000000000 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =" +
            " = = = = = = = = = = = = = = = = = = / 10 = = = = = = = = = = = = = ="; // result on display 1,e-9999

    private String maxValueInNormalSystem = "9999999999999999 + 0,4999999999999999 ="; // result on display 9 999 999 999 999 999

    private String maxValue =  " 1000000000000000 * 10000000000 = = = = = = = = * 10 = = = = = * = = = = = = = = = * = = = = = = = = " +
            "* 1000000000000000 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =" +
            "* 10 = = = = = = = = = + = = = = = = = = * 1,111111111111111 = "; // result on display 9,999999999999999e+9999

    @Test
    public void combinationsTest() {

        // binary and special operations
        combinationTestTemplate( "6 + 67843 * 5 1/x =","13 569,8" ,"");
        combinationTestTemplate( "5 * 5 1/x - 55 sqr =","-3 024","");
        combinationTestTemplate( "356 - 57 √   =","348,4501655647293","");
        combinationTestTemplate( " 489009 sqr + 384 * 00000 + 333 = = =","999","");
        combinationTestTemplate( " 57 √ + 900 /","907,5498344352707","√( 57 ) + 900 ÷");
        combinationTestTemplate( "792 sqr +  8 1/x * 6 /","3 763 584,75","sqr( 792 ) + 1/( 8 ) × 6 ÷");
        combinationTestTemplate( "97 - 55 sqr * 97 - 55 sqr +","-287 041","97 - sqr( 55 ) × 97 - sqr( 55 ) +");
        combinationTestTemplate( "95 - 634 * 63 / 75 1/x + 532 *","-2 546 243","95 - 634 × 63 ÷ 1/( 75 ) + 532 ×");
        combinationTestTemplate( "  57 √ + 900 - 57√ + 900 +","1 800","√( 57 ) + 900 - √( 57 ) + 900 +");
        combinationTestTemplate( "97 + 12 - + / - 325 √ * 24 *","2 183,333846944321","97 + 12 - √( 325 ) × 24 ×");
        combinationTestTemplate( "325 √ * 24 + 900 * 0,001 =","1,332666153055679","");
        combinationTestTemplate( "4672 /  57 √ + 900 * 0,001 *","1,518821517220788","4672 ÷ √( 57 ) + 900 × 0,001 ×");
        combinationTestTemplate( "384 * 0 - 3 ± 1/x * 2 +","0,6666666666666667","384 × 0 - 1/( -3 ) × 2 +");
        combinationTestTemplate( "5783 + 97 - 55 sqr -  1 1/x √ * 4 -","11 416","5783 + 97 - sqr( 55 ) - √( 1/( 1 ) ) × 4 -");
        combinationTestTemplate( "537 ± * 646 - 90 +   4 √ 1/x +","-346 991,5","-537 × 646 - 90 + 1/( √( 4 ) ) +");
        combinationTestTemplate( "55 sqr * 97 + 564 - 0 + 24 1/x =","293 989,0416666667","");
        combinationTestTemplate( "6371 * 5783 + 97 + 12 - 325 √ *","36 843 583,97224362","6371 × 5783 + 97 + 12 - √( 325 ) ×");
        combinationTestTemplate( "6597 - 55 sqr * 3 ± 1/x * 2 +","-2 381,333333333333","6597 - sqr( 55 ) × 1/( -3 ) × 2 +");
        combinationTestTemplate( "6371 * 5783 * 6 √ sqr - 90 + 8 1/x =","221 060 868,125","");
        combinationTestTemplate( "55 sqr * 3 ± 1/x + 900 * 0,001 /","-0,1083333333333333","sqr( 55 ) × 1/( -3 ) + 900 × 0,001 ÷");
        combinationTestTemplate( "57 √ + 900 - 325√  * 24 + 0,5732 =","21 349,10307339082","");
        combinationTestTemplate(maxValueInNormalSystem,"9 999 999 999 999 999","");
        combinationTestTemplate(maxValue + " + 9999999999999999999999999999999999999999 = ","9,999999999999999e+9999","");

        // all buttons
        combinationTestTemplate("0, ", "0,","");
        combinationTestTemplate("64, ", "64,","");
        combinationTestTemplate("64 √ =", "8","");
        combinationTestTemplate("C 74 MS - 3 + 2 =", "73","");
        combinationTestTemplate("4682 + 5325 1/x C =", "0","");
        combinationTestTemplate("CE 0,5 - 531 + 22 =", "-508,5","");
        combinationTestTemplate("1 * 12 backSpace -", "1","1 × 1 -");
        combinationTestTemplate(" 39 - - 44± =", "83","");
        combinationTestTemplate("5325 1/x C - 12 /", "-12","0 - 12 ÷");
        combinationTestTemplate(" 3√  - 3 =", "-1,267949192431123","");
        combinationTestTemplate("4 + 8 - + * - sqr","144","4 + 8 - sqr( 12 )");
        combinationTestTemplate("85 + 3 √  * *- + - 3 + 2 =", "85,73205080756888","");
        combinationTestTemplate("5325 1/x C * 4 ± sqr 1/x =", "0","");
        combinationTestTemplate("7320 / 5325 1/x C - 3 + 2 +", "-1","0 - 3 + 2 +");
        combinationTestTemplate("C 74 MS + - 3 + 2 M+  / + * 12 backSpace -", "73","74 - 3 + 2 × 1 -");
        combinationTestTemplate("463 backSpace backSpace 6 + 4 * 34 1/x  CE 1 +", "50","46 + 4 × 1 +");
        combinationTestTemplate("64 + 8 * 64 sqr 1/x √", "0,015625","64 + 8 × √( 1/( sqr( 64 ) ) )");
        combinationTestTemplate("7320 / 34 1/x C 85 + 3 √ +", "86,73205080756888","85 + √( 3 ) +");
        combinationTestTemplate("352 * 4 ± sqr 1/x CE 0,5 - 531 /", "-355","352 × 0,5 - 531 ÷");
        combinationTestTemplate("3 * 1 + 3 √ * 12 backSpace -", "4,732050807568877","3 × 1 + √( 3 ) × 1 -");
        combinationTestTemplate("457 - 1 sqr - 7583133 backSpace backSpace +  - / +12 +", "-75 363","457 - sqr( 1 ) - 75831 + 12 +");

        // division by zero
        combinationTestTemplate("42 + 3 * 0 1/x ", "Деление на 0","42 + 3 × 1/( 0 )");
        combinationTestTemplate("4 ± sqr 1/x + 31 * 3 1/x / 0 +", "Деление на 0","1/( sqr( -4 ) ) + 31 × 1/( 3 ) ÷ 0 +");
        combinationTestTemplate("7320 + 3 *  0 1/x  -", "Деление на 0","7320 + 3 × 1/( 0 )");

        // overflow
        combinationTestTemplate(maxValue + " * 1,0000000000000011111111111111111 = ","Переполнение","9,999999999999999e+9999 ×");
        combinationTestTemplate(maxValue + "± * 1,000000000000001 = ","Переполнение","-9,999999999999999e+9999 ×");
        combinationTestTemplate(minPositiveValue + " / 1,000000000000001 =","Переполнение","1,e-9999 ÷");
        combinationTestTemplate(minPositiveValue + "± / 1,000000000000001 =","Переполнение","-1,e-9999 ÷");
        combinationTestTemplate("9999999999999999 sqr sqr sqr sqr sqr sqr sqr sqr sqr sqr","Переполнение","sqr( sqr( sqr( sqr( sqr( sqr( sqr( sqr( sqr( sqr( 9999999999999999 ) ) ) ) ) ) ) ) ) )");
        combinationTestTemplate("57891235 * 4328952 sqr * 21345382 * 458901234 = = = = = = = = = = * = = = = * = = = = + = = * = = = = =","Переполнение","");

        // exp system
        combinationTestTemplate(minPositiveValue ,"1,e-9999",""); // minimal positive value
        combinationTestTemplate(minPositiveValue + " ± " ,"-1,e-9999",""); // maximal negative value
        combinationTestTemplate(maxValue,"9,999999999999999e+9999",""); // maximal value
        combinationTestTemplate(maxValue+ " ± " ,"-9,999999999999999e+9999",""); // minimal value
        combinationTestTemplate(maxValueInNormalSystem,"9 999 999 999 999 999",""); // maximal value in normal system
        combinationTestTemplate(maxValueInNormalSystem+" + 0,0000000000000001 =","1,e+16","");
        combinationTestTemplate(maxValueInNormalSystem + " ± " ,"-9 999 999 999 999 999",""); // minimal value in normal system
        combinationTestTemplate(maxValueInNormalSystem + " ±  - 0,0000000000000001 =" ,"-1,e+16","");
        combinationTestTemplate("0,0000000000000001 / 1,000000000000001 =","9,99999999999999e-17","");
        combinationTestTemplate("0,0000000000000001 ±/ 1,000000000000001 =","-9,99999999999999e-17","");
        combinationTestTemplate(minPositiveValue + " MS 0,0000000000000001 / 10 = M- MR","-1,e-17","");
    }

    @After
    public void clear() {
        clickOn("C");
    }
}

