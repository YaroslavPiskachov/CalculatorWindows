package modelTest;

import controller.Controller;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by Yaroslav on 20.07.2017.
 */

public class FormattingTest {
    Controller controller = new Controller();
    private static final BigDecimal MAX_VALUE = new BigDecimal("9.0E+9999");

    @Test
    public void testForMakeString(){
        // float number
        templateForMakeString("-6 812 536,562","-6812536.5620000");
        templateForMakeString("-56 815,0001","-56815.0001");
        templateForMakeString("-357,0003","-357.0003");
        templateForMakeString("-235,9002","-235.9002");
        templateForMakeString("-45,4511","-45.4511");
        templateForMakeString("-3 353,4","-3353.4");
        templateForMakeString("-0,523251","-0.523251");
        templateForMakeString("0,0033602150537634","0.003360215053763440860215053763440860");
        templateForMakeString("0,000000000000001","0.000000000000001");
        templateForMakeString("0,0000000000000001","0.0000000000000001");
        templateForMakeString("1","1.0000000000000001");
        templateForMakeString("3","3.0");
        templateForMakeString("4,00000002","4.00000002");
        templateForMakeString("9,999999999999999","9.999999999999999");
        templateForMakeString("10","9.999999999999999999999999999999999");
        templateForMakeString("12,1","12.1");
        templateForMakeString("42","42.00000000");
        templateForMakeString("78,0927","78.0927");
        templateForMakeString("342,6124512","342.6124512");
        templateForMakeString("5 625,5626","5625.5626");
        templateForMakeString("21 352,4","21352.400");
        templateForMakeString("4 578 195,591124","4578195.5911240000");
        templateForMakeString("23 689 622,7633","23689622.7633");
        templateForMakeString("83 656 153,3353","83656153.3353");
        templateForMakeString("2 263 088 938 723 702","2263088938723702.130247");

        // integer number
        templateForMakeString("-7 000 000 000 000 000","-7000000000000000");
        templateForMakeString("-5 782 150 000 000","-5782150000000");
        templateForMakeString("-4 457 821 583","-4457821583");
        templateForMakeString("-943 325","-00943325");
        templateForMakeString("-23 627","-23627");
        templateForMakeString("-4 692","-4692");
        templateForMakeString("-900","-900");
        templateForMakeString("-253","-253");
        templateForMakeString("-57","-57");
        templateForMakeString("0","0");
        templateForMakeString("2","2");
        templateForMakeString("733","733");
        templateForMakeString("999","999");
        templateForMakeString("1 000","1000");
        templateForMakeString("2 392","2392");
        templateForMakeString("4 004","4004");
        templateForMakeString("7 891","7891");
        templateForMakeString("9 146","9146");
        templateForMakeString("24 622","24622");
        templateForMakeString("597 219","597219");
        templateForMakeString("1 000 000","1000000");
        templateForMakeString("25 082 818 350,00006","25082818350.00006");
        templateForMakeString("5 782 150 000 000","5782150000000");

        // convert to exponential system
        templateForMakeString("5,657999999999999e+19","56579999999999994342");
        templateForMakeString("1,e+16","10000000000000000");
        templateForMakeString("1,000000000000001e+16","10000000000000010");
        templateForMakeString("1,e-17","0.00000000000000001");
        templateForMakeString("-4,994181778228363e+16","-49941817782283634.56573092347415261");
        templateForMakeString("1,94421291372949e-7","1.944212913729490292800473978045465E-7");
        templateForMakeString("1,582270293765309e-10","1.582270293765308864172582729182323E-10");

        // exception
        templateForMakeStringException(null);
    }

    @Test
    public void testForMakeDecimal(){
        // float number
        templateForMakeDecimal("4670235623623.532","4 670 235 623 623,532");
        templateForMakeDecimal("367820.752001","367 820,752001");
        templateForMakeDecimal("4780023.61","4 780 023,61");
        templateForMakeDecimal("56802.4","56 802.4");
        templateForMakeDecimal("2334.0001","2 334,0001");
        templateForMakeDecimal("650.421100","650,421100");
        templateForMakeDecimal("15.4200","15,4200");
        templateForMakeDecimal("3.01","3,01");
        templateForMakeDecimal("0.0","0,0");
        templateForMakeDecimal("0.001","0,001");
        templateForMakeDecimal("-572.6426223","-572,6426223");
        templateForMakeDecimal("-47326.402","-47 326,402");
        templateForMakeDecimal("-56815.0001","-56 815,0001");
        templateForMakeDecimal("-5672362366.666666","-5 672 362 366,666666");

        // integer number
        templateForMakeDecimal("599213624672482","599 213 624 672 482");
        templateForMakeDecimal("3680915","3 680 915");
        templateForMakeDecimal("3532","3 532");
        templateForMakeDecimal("35793","35 793");
        templateForMakeDecimal("731","731");
        templateForMakeDecimal("10","10");
        templateForMakeDecimal("94","94");
        templateForMakeDecimal("9","9");
        templateForMakeDecimal("4","4");
        templateForMakeDecimal("0","0");
        templateForMakeDecimal("-5","-5");
        templateForMakeDecimal("-552","-552");
        templateForMakeDecimal("-5892","-5 892");
        templateForMakeDecimal("-531455","-531 455");
        templateForMakeDecimal("-50244121","-50 244 121");
        templateForMakeDecimal("-67834774","-67 834 774");
        templateForMakeDecimal("-5346817295861","-5 346 817 295 861");


        // convert to exponential system
        templateForMakeDecimal("5.657999999999999E+19","5,657999999999999e+19");
        templateForMakeDecimal("1.E+16","1,e+16");
        templateForMakeDecimal("1.000000000000001E+16","1,000000000000001e+16");
        templateForMakeDecimal("-4.994181778228363E+16","-4,994181778228363e+16");
        templateForMakeDecimal("1.944212913729490292800473978045465E-7","1,944212913729490292800473978045465E-7");
        templateForMakeDecimal("1.582270293765309E-10","1,582270293765309e-10");

        // exception
        templateForMakeDecimalException(null);
        templateForMakeDecimalException("fewfwg");
        templateForMakeDecimalException("1e*5");
        templateForMakeDecimalException("1e/5");
        templateForMakeDecimalException("34t+3");
        templateForMakeDecimalException("trw");
        templateForMakeDecimalException("4+4e");
    }

    private void templateForMakeDecimal(String expected, String param){
        assertEquals(new BigDecimal(expected),controller.makeDecimal(param));
    }

    private void templateForMakeString(String expected, String param){

        assertEquals(expected,controller.makeString(new BigDecimal(param)));
    }


    private void templateForMakeStringException(String val){
        try {
            BigDecimal number = new BigDecimal(val);
            controller.makeString(number);

            fail("Exception was not thrown with with null param");
        } catch (Exception e) {
            //correct behavior
        }
    }

    private void templateForMakeDecimalException(String param){
        try {
            controller.makeDecimal(param);
            fail("Exception was not thrown with such parameter: " + param);
        } catch (Exception e){
            //correct behavior
        }
    }
}
