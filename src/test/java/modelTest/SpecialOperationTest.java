package modelTest;

import model.Calculator;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Yaroslav on 01.08.2017.
 */

public class SpecialOperationTest{
    private Calculator calculator = new Calculator();

    @Test
    public void testPercent(){

        //integer value
        templateForPercent("1,05","35","3");
        templateForPercent("3,12","52","6");
        templateForPercent("1,28","32","4");
        templateForPercent("-0,05","-5","1");
        templateForPercent("11,75","5","235");
        templateForPercent("394,92","6582","6");
        templateForPercent("3325.00","625","532");
        templateForPercent("143,29","623","23");
        templateForPercent("-443,92","-62","716");
        templateForPercent("-285,64","-772","37");
        templateForPercent("1255,52","236","532");
        templateForPercent("1585,92","672","236");
        templateForPercent("-5158,45","-83","6215");
        templateForPercent("28826,21","4627","623");
        templateForPercent("130451,72","24521","532");
        templateForPercent("-21034,68","236","-8913");
        templateForPercent("-12692,48","-2368","536");
        templateForPercent("147153,06","-6362","-2313");
        templateForPercent("2949660,72","124616","2367");
        templateForPercent("57713116,02","24521","235362");
        templateForPercent("-1532278,54","3926","-39029");
        templateForPercent("-24669454,32","-682986","3612");


        //float value
        templateForPercent("1434,28","-6236","-23");
        templateForPercent("-4515,264","7236","-62,4");
        templateForPercent("297688,3","-910","-32713");
        templateForPercent("14716,94","-23737,0","-62");
        templateForPercent("-1667,17096","-73,736","2261");
        templateForPercent("-19502,155132","-82,0004","23783");
        templateForPercent("-7871 799,968","-78923,2","9974");
        templateForPercent("530289,495744","-1623,0702","-32672");
        templateForPercent("451138,58200046","9422,839","4787,714");
        templateForPercent("604448,87274070","9266,041","6523,270");
        templateForPercent("57079,00690944","8960,080","637,0368");
        templateForPercent("479794,49692130","8807,845","5447,354");
        templateForPercent("695362,37957253","8543,607","8138,979");
        templateForPercent("205900,65132294","3241,086","6352,829");
        templateForPercent("183470,78832732","3101,228","5916,069");
        templateForPercent("159008,57637833","2516,549","6318,517");
        templateForPercent("191652,01507032","2160,116","8872,302");
        templateForPercent("22414,964260563","372,7807","6012,909");
        templateForPercent("1488,7681408156","69,40534","2145,034");
        templateForPercent("1237271,30391522","5235,517","23632,266");
        templateForPercent("89182251,67554522","95621396,517","93,266");
        templateForPercent("8577545,73471522","23612,517","36326,266");
        templateForPercent("19817526,10994522","325236,517","6093,266");
        templateForPercent("3945841116.510131608","63267236,182","6236,7844");

        // exception test
        templateForPercentException(null,"234");
        templateForPercentException("2235","");


    }

    @Test
    public void testSqrt(){

        specialOperationTestTemplate("0", "0");
        specialOperationTestTemplate("1", "1");
        specialOperationTestTemplate("2", "4");
        specialOperationTestTemplate("3", "9");
        specialOperationTestTemplate("16", "256");
        specialOperationTestTemplate("3.741657386773941385583748732316551", "14");
        specialOperationTestTemplate("10.72380529476360830481415967215429", "115");
        specialOperationTestTemplate("20.51828452868319106251973819914106", "421");
        specialOperationTestTemplate("48.70318264754368185519356917397970", "2372");
        specialOperationTestTemplate("96.57639463140048282054013268495451", "9327");
        specialOperationTestTemplate("82.54089895318562618156372638967549", "6813");
        specialOperationTestTemplate("164.9636323557407607073312715719339", "27213");
        specialOperationTestTemplate("680.2477489856177392509042691266213", "462737");
        specialOperationTestTemplate("1165.299961383334123724841929170941", "1357924");
        specialOperationTestTemplate("7563.971311421005118670971297795079", "57213662");
        specialOperationTestTemplate("249873.2151952265571199509947376528", "62436623672");
        specialOperationTestTemplate("86442666.98467590084746674917586310", "7472334675423577");

        // float value
        specialOperationTestTemplate("5.766745182509801169478837461800487", "33,25535");
        specialOperationTestTemplate("6.382966395023555190203961989761812", "40,74226");
        specialOperationTestTemplate("7.969828103541506120279917605752336", "63,51816");
        specialOperationTestTemplate("8.314416996999849778463065940053983", "69,12953");
        specialOperationTestTemplate("9.628065745517112950745258657151643", "92,69965");
        specialOperationTestTemplate("84.30434745610691220920366440289109", "7107,223");
        specialOperationTestTemplate("131.4496861920940456377350252115773", "17279,02");
        specialOperationTestTemplate("136.3848232025836516976667706818026", "18600,82");
        specialOperationTestTemplate("173.3632025546367206934090547614052", "30054,80");
        specialOperationTestTemplate("239.5889605136263368232576036089515", "57402,87");
        specialOperationTestTemplate("293.7841044032165", "86309,10");
        specialOperationTestTemplate("311.2337706612186", "96866,46");
        specialOperationTestTemplate("16930.83872700936", "2,866533E+8");
        specialOperationTestTemplate("26742.70367782585", "7,151722E+8");
        specialOperationTestTemplate("26979.8109704275", "7,279102E+8");
        specialOperationTestTemplate("28159.90411915495", "7,929802E+8");
        specialOperationTestTemplate("30620.58621254662", "9,376203E+8");

        // exception test
        specialOperationTestTemplateException("-5");
        specialOperationTestTemplateException("-6,002");
        specialOperationTestTemplateException("-51");
        specialOperationTestTemplateException("-89");
        specialOperationTestTemplateException("-8923");
        specialOperationTestTemplateException("-6125");
        specialOperationTestTemplateException("-521,125");
        specialOperationTestTemplateException("-356236");
        specialOperationTestTemplateException("-0,51321325");
        specialOperationTestTemplateException("-9999999999");
    }

    @Test
    public void testSqr(){
        //specialOperation = SpecialOperation.SQR;

        // float value
        specialOperationTestTemplate("76231.21", "-276.1");
        specialOperationTestTemplate("111088.89", "333.3");
        specialOperationTestTemplate("221935.21", "-471.1");
        specialOperationTestTemplate("676468081.00", "26009.0");
        specialOperationTestTemplate("329292.3456", "573.84");
        specialOperationTestTemplate("7360.267264", "85.792");
        specialOperationTestTemplate("1313336875.2001", "36239.99");
        specialOperationTestTemplate("4306375372.834441", "65622.979");
        specialOperationTestTemplate("6812471756.214601", "82537.699");
        specialOperationTestTemplate("3295397859.669136", "-57405.556");
        specialOperationTestTemplate("497189529593755684", "-705116678");
        specialOperationTestTemplate("338275955879652025", "-581614955");

        // integer value
        specialOperationTestTemplate("1", "1");
        specialOperationTestTemplate("25", "5");
        specialOperationTestTemplate("169", "-13");
        specialOperationTestTemplate("256", "16");
        specialOperationTestTemplate("5184", "-72");
        specialOperationTestTemplate("55696", "-236");
        specialOperationTestTemplate("426409", "653");
        specialOperationTestTemplate("659344", "812");
        specialOperationTestTemplate("670761", "819");
        specialOperationTestTemplate("28561", "-169");
        specialOperationTestTemplate("45369", "213");
        specialOperationTestTemplate("213444", "462");
        specialOperationTestTemplate("66049", "257");
        specialOperationTestTemplate("384400", "-620");
        specialOperationTestTemplate("547600", "-740");
        specialOperationTestTemplate("125316", "-354");
        specialOperationTestTemplate("425473129", "20627");
        specialOperationTestTemplate("859662400", "-29320");
        specialOperationTestTemplate("1427479524", "-37782");
        specialOperationTestTemplate("1973580625", "-44425");
        specialOperationTestTemplate("2080545769", "-45613");
        specialOperationTestTemplate("3195867024", "-56532");
        specialOperationTestTemplate("9297009241", "-96421");
        specialOperationTestTemplate("1126340721", "33561");
        specialOperationTestTemplate("1760473764", "41958");
        specialOperationTestTemplate("2262809761", "47569");
        specialOperationTestTemplate("2686348900", "51830");
        specialOperationTestTemplate("7474294116", "86454");

    }

    @Test
    public void testOneDivided(){

        // float values
        specialOperationTestTemplate("0,0019193857965451", "521.0");
        specialOperationTestTemplate("0,0027831895352073", "359.3");
        specialOperationTestTemplate("0,0015797788309636", "633.0");
        specialOperationTestTemplate("0,0027662517289073", "361.5");
        specialOperationTestTemplate("-0,1041666666666667", "-9.6");
        specialOperationTestTemplate("0,0017915688768654", "558.17");
        specialOperationTestTemplate("0,0010235100252806", "977.03");
        specialOperationTestTemplate("-0,003463803255975", "-288.7");
        specialOperationTestTemplate("-0,003789457728599", "-263.89");
        specialOperationTestTemplate("-0,0033332222259258", "-300.01");
        specialOperationTestTemplate("-0,002015072744126", "-496.26");
        specialOperationTestTemplate("0,0000812195934147", "12312.3");
        specialOperationTestTemplate("0,0000710307485007", "14078.41");
        specialOperationTestTemplate("0,0000302654278018", "33041.0");
        specialOperationTestTemplate("0,0000100147216408", "99853.0");
        specialOperationTestTemplate("0,0000193494108878", "51681.16");
        specialOperationTestTemplate("-0,0576368876080691", "-17.35");
        specialOperationTestTemplate("-0,0000288497445297", "-34662.352");
        specialOperationTestTemplate("-0,0000342491276062", "-29197.824");

        //integer values
        specialOperationTestTemplate("0,001187648456057", "842");
        specialOperationTestTemplate("0,0011695906432748", "855");
        specialOperationTestTemplate("0,001008064516129", "992");
        specialOperationTestTemplate("-0,0011534025374855", "-867");
        specialOperationTestTemplate("-0,0000288666936089", "-34642");
        specialOperationTestTemplate("-0,0000239034301422", "-41835");
        specialOperationTestTemplate("-0,0000214376058481", "-46647");
        specialOperationTestTemplate("0,0000402527875055", "24843");
        specialOperationTestTemplate("0,0000113526707157", "88085");
        specialOperationTestTemplate("-0,0000119340286894", "-83794");
        specialOperationTestTemplate("-0,0000194118217994", "-51515");
        specialOperationTestTemplate("-0,0000138730889819", "-72082");
        specialOperationTestTemplate("8,305098145123601e-9", "120407969");
        specialOperationTestTemplate("-1,077968002718894e-8", "-92767132");
        specialOperationTestTemplate("-4,985083384243497e-8", "-20059845");
        specialOperationTestTemplate("-1,078054053313285e-9", "-927597273");
        specialOperationTestTemplate("-1,010830220980344e-9", "-989285816");

        // exp system
        specialOperationTestTemplate("-1,819501439720543e-9", "-549601104");
        specialOperationTestTemplate("-1,624666006848643e-9", "-615511124");
        specialOperationTestTemplate("2,278597245288174e-10", "4 388 665 009");
        specialOperationTestTemplate("3,207608365628023e-18", "3,117587579318488e+17");
        specialOperationTestTemplate("7,436397753380989e-16", "1 344 737 106 814 043");
        specialOperationTestTemplate("3,427110041295483e-17", "2,917910390825938e+16");
        specialOperationTestTemplate("1,945765507183856e-17", "5,139365438990226e+16");
        specialOperationTestTemplate("8,610453076155628e-18", "1,161379071641695e+17");
        specialOperationTestTemplate("6,7618931003802e-18", "1,478875789893474e+17");
    }

    @Test
    public void testNegate(){

        // float values
        specialOperationTestTemplate("-563.0", "563.0");
        specialOperationTestTemplate("-543.0", "543.0");
        specialOperationTestTemplate("684.0", "-684.0");
        specialOperationTestTemplate("518,7", "-518.7");
        specialOperationTestTemplate("-858,2", "858.2");
        specialOperationTestTemplate("-574,5", "574.5");
        specialOperationTestTemplate("203,11", "-203.11");
        specialOperationTestTemplate("-49537", "49537.0");
        specialOperationTestTemplate("95954", "-95954.0");
        specialOperationTestTemplate("-99 232,8", "99232.8");
        specialOperationTestTemplate("59 830,2", "-59830.2");
        specialOperationTestTemplate("-6 896,332", "6896.332");
        specialOperationTestTemplate("67 725,44", "-67725.44");
        specialOperationTestTemplate("97 901,35", "-97901.35");
        specialOperationTestTemplate("859 243 625,6", "-8.592436256E8");

        // integer values
        specialOperationTestTemplate("-49", "49");
        specialOperationTestTemplate("739", "-739");
        specialOperationTestTemplate("220", "-220");
        specialOperationTestTemplate("271", "-271");
        specialOperationTestTemplate("-792", "792");
        specialOperationTestTemplate("290", "-290");
        specialOperationTestTemplate("382", "-382");
        specialOperationTestTemplate("609", "-609");
        specialOperationTestTemplate("-23 225", "23225");
        specialOperationTestTemplate("92 701", "-92701");
        specialOperationTestTemplate("96 476", "-96476");
        specialOperationTestTemplate("54 894", "-54894");
        specialOperationTestTemplate("-29 039", "29039");
        specialOperationTestTemplate("39 368", "-39368");
        specialOperationTestTemplate("51 205", "-51205");
        specialOperationTestTemplate("45 306", "-45306");
        specialOperationTestTemplate("385 030 389", "-385030389");
        specialOperationTestTemplate("401 666 838", "-401666838");
        specialOperationTestTemplate("729 814 849", "-729814849");
        specialOperationTestTemplate("731 724 305", "-731724305");

        // exp system
        specialOperationTestTemplate("-4,871503436323872e+16", "4,871503436323872e+16");
        specialOperationTestTemplate("-207 116,01", "207 116,01");
        specialOperationTestTemplate("-2 111 021 132,2225", "2 111 021 132,2225");
        specialOperationTestTemplate("-747 533,16", "747 533,16");
        specialOperationTestTemplate("-8 425 220 521", "8 425 220 521");
        specialOperationTestTemplate("-914 012,4816", "914 012,4816");
        specialOperationTestTemplate("-6 553 753,6009", "6 553 753,6009");
        specialOperationTestTemplate("-80 012,608225", "80 012,608225");
        specialOperationTestTemplate("-3 930 559 704,318976", "3 930 559 704,318976");
        specialOperationTestTemplate("-5,74669657474133e+17", "5,74669657474133e+17");
        specialOperationTestTemplate("-7 973 061 264", "7 973 061 264");
    }

    private void specialOperationTestTemplate(String expected ,String val) {
        //assertEquals(new BigDecimal(expected), specialOperation.executeOperation(new BigDecimal(val)));
    }

    private void specialOperationTestTemplateException(String val) {
        try {
            //specialOperation.executeOperation(new BigDecimal(val));
            fail("exception was not thrown with such param: "+val);
        }catch (NullPointerException e){
            // correct behavior
        }
    }

    private void templateForPercent(String expected, String percentFrom, String percentOf) {
        //assertEquals(new BigDecimal(expected), calculator.percent(new BigDecimal(percentOf),new BigDecimal(percentFrom)));

    }

    private void templateForPercentException(String currentValue,  String percentFrom){
        try {
            //calculator.setLastValue(new BigDecimal(percentFrom));
            //specialOperation.executeOperation(new BigDecimal(currentValue));
            //fail("Exception was not thrown with such parameter: " + currentValue +" , "+calculator.getLastValue());
        } catch (NullPointerException e) {
            //correct behavior
        }
    }
}
