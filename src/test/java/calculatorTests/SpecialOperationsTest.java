package calculatorTests;


import org.junit.Test;


/**
 * Created by Yaroslav on 01.08.2017.
 */
public class SpecialOperationsTest extends MyTestBox{

    @Test
    public void testPercent(){
        //integer value
        combinationTestTemplate("0 + 14%","0","0 + %( 14 )");
        combinationTestTemplate("17 +  0%","0","17 + %( 0 )");
        combinationTestTemplate("32 + 4%","1,28","32 + %( 4 )");
        combinationTestTemplate("35 + 3%","1,05","35 + %( 3 )");
        combinationTestTemplate("52 + 6 %","3,12","52 + %( 6 )");
        combinationTestTemplate("5± + 1%","-0,05","-5 + %( 1 )");
        combinationTestTemplate("5 + 235%","11,75","5 + %( 235 )");
        combinationTestTemplate("623 + 23%","143,29","623 + %( 23 )");
        combinationTestTemplate("6582 + 6%","394,92","6582 + %( 6 )");
        combinationTestTemplate("625 + 532%","3 325","625 + %( 532 )");
        combinationTestTemplate("772 ± + 37%","-285,64","-772 + %( 37 )");
        combinationTestTemplate("236 + 532%","1 255,52","236 + %( 532 )");
        combinationTestTemplate("672 + 236%","1 585,92","672 + %( 236 )");
        combinationTestTemplate("62 ±  + 716%","-443,92","-62 + %( 716 )");
        combinationTestTemplate("4627 + 623%","28 826,21","4627 + %( 623 )");
        combinationTestTemplate("83 ± + 6215%","-5 158,45","-83 + %( 6215 )");
        combinationTestTemplate("24521 + 532%","130 451,72","24521 + %( 532 )");
        combinationTestTemplate("2368 ± + 536%","-12 692,48","-2368 + %( 536 )");
        combinationTestTemplate("236 + 8913 ± %","-21 034,68","236 + %( -8913 )");
        combinationTestTemplate("6362 ± + 231±3%","147 153,06","-6362 + %( -2313 )");
        combinationTestTemplate("124616 + 2367%","2 949 660,72","124616 + %( 2367 )");
        combinationTestTemplate("24521 + 235362%","57 713 116,02","24521 + %( 235362 )");
        combinationTestTemplate("3926 + 3902±9%","-1 532 278,54","3926 + %( -39029 )");
        combinationTestTemplate(" 6±82986 + 3612%","-24 669 454,32","-682986 + %( 3612 )");
        combinationTestTemplate("623± 6 + 23±%","1 434,28","-6236 + %( -23 )");

        // float values
        combinationTestTemplate("7236 + 6±2,4%","-4 515,264","7236 + %( -62,4 )");
        combinationTestTemplate("910 ± + 327±13%","297 688,3","-910 + %( -32713 )");
        combinationTestTemplate("237±37,0 + 62±%","14 716,94","-23737 + %( -62 )");
        combinationTestTemplate("73±,736 + 2261%","-1 667,17096","-73,736 + %( 2261 )");
        combinationTestTemplate("82,00±04 + 23783%","-19 502,155132","-82,0004 + %( 23783 )");
        combinationTestTemplate("78923,2± + 9974%","-7 871 799,968","-78923,2 + %( 9974 )");
        combinationTestTemplate("1623,0702± + 32±672%","530 289,495744","-1623,0702 + %( -32672 )");
        combinationTestTemplate("9422,839 + 4787,714%","451 138,58200046","9422,839 + %( 4787,714 )");
        combinationTestTemplate("9266,041 + 6523,270%","604 448,8727407","9266,041 + %( 6523,27 )");
        combinationTestTemplate("8960,08 + 637,0368%","57 079,00690944","8960,08 + %( 637,0368 )");
        combinationTestTemplate("8807,845 + 5447,354%","479 794,4969213","8807,845 + %( 5447,354 )");
        combinationTestTemplate("8543,607 + 8138,979%","695 362,37957253","8543,607 + %( 8138,979 )");
        combinationTestTemplate("3241,086 + 6352,829%","205 900,65132294","3241,086 + %( 6352,829 )");
        combinationTestTemplate("3101,228 + 5916,069%","183 470,78832732","3101,228 + %( 5916,069 )");
        combinationTestTemplate("2516,549 + 6318,517%","159 008,57637833","2516,549 + %( 6318,517 )");
        combinationTestTemplate("2160,116 + 8872,302%","191 652,01507032","2160,116 + %( 8872,302 )");
        combinationTestTemplate("372,7807 + 6012,909%","22 414,964260563","372,7807 + %( 6012,909 )");
        combinationTestTemplate("69,40534 + 2145,034%","1 488,7681408156","69,40534 + %( 2145,034 )");
        combinationTestTemplate("5235,517 + 23632,266%","1 237 271,30391522","5235,517 + %( 23632,266 )");
        combinationTestTemplate("95621396,517 + 93,266%","89 182 251,67554522","95621396,517 + %( 93,266 )");
        combinationTestTemplate("23612,517 + 36326,266%","8 577 545,73471522","23612,517 + %( 36326,266 )");
        combinationTestTemplate("325236,517 + 6093,266%","19 817 526,10994522","325236,517 + %( 6093,266 )");
        combinationTestTemplate("63267236,182 + 6236,7844%","3 945 841 116,510132","63267236,182 + %( 6236,7844 )");
    }

    @Test
    public void testSqrt(){

        //integer value
        combinationTestTemplate("0 √","0","√( 0 )");
        combinationTestTemplate(" 1√","1","√( 1 )");
        combinationTestTemplate("4√","2","√( 4 )");
        combinationTestTemplate("9√","3","√( 9 )");
        combinationTestTemplate("256√","16","√( 256 )");
        combinationTestTemplate("14√","3,741657386773941","√( 14 )");
        combinationTestTemplate("115√","10,72380529476361","√( 115 )");
        combinationTestTemplate("421√","20,51828452868319","√( 421 )");
        combinationTestTemplate("2372√","48,70318264754368","√( 2372 )");
        combinationTestTemplate("9327√ ","96,57639463140048","√( 9327 )");
        combinationTestTemplate("6813√","82,54089895318563","√( 6813 )");
        combinationTestTemplate("27213√","164,9636323557408","√( 27213 )");
        combinationTestTemplate("462737√","680,2477489856177","√( 462737 )");
        combinationTestTemplate("1357924√","1 165,299961383334","√( 1357924 )");
        combinationTestTemplate("57213662√","7 563,971311421005","√( 57213662 )");
        combinationTestTemplate("62436623672√","249 873,2151952266","√( 62436623672 )");
        combinationTestTemplate("7472334675423577√","86 442 666,9846759","√( 7472334675423577 )");

        // float value
        combinationTestTemplate("14√","3,741657386773941","√( 14 )");
        combinationTestTemplate("115√","10,72380529476361","√( 115 )");
        combinationTestTemplate("421√","20,51828452868319","√( 421 )");
        combinationTestTemplate("2372√","48,70318264754368","√( 2372 )");
        combinationTestTemplate("9327√ ","96,57639463140048","√( 9327 )");
        combinationTestTemplate("6813√","82,54089895318563","√( 6813 )");
        combinationTestTemplate("27213√","164,9636323557408","√( 27213 )");
        combinationTestTemplate("462737√","680,2477489856177","√( 462737 )");
        combinationTestTemplate("1357924√","1 165,299961383334","√( 1357924 )");
        combinationTestTemplate("57213662√","7 563,971311421005","√( 57213662 )");
        combinationTestTemplate("62436623672√","249 873,2151952266","√( 62436623672 )");
        combinationTestTemplate("7472334675423577√","86 442 666,9846759","√( 7472334675423577 )");

        // exception test
        combinationTestTemplate("5±√","Введены неверные данные","√( -5 )");
        combinationTestTemplate("6±,002√","Введены неверные данные","√( -6,002 )");
        combinationTestTemplate("5±1√","Введены неверные данные","√( -51 )");
        combinationTestTemplate("89± √","Введены неверные данные","√( -89 )");
        combinationTestTemplate("8923±√","Введены неверные данные","√( -8923 )");
        combinationTestTemplate("6125± √","Введены неверные данные","√( -6125 )");
        combinationTestTemplate("521±,125√","Введены неверные данные","√( -521,125 )");
        combinationTestTemplate("3562±36√","Введены неверные данные","√( -356236 )");
        combinationTestTemplate("0,5±1321325√","Введены неверные данные","√( -0,51321325 )");
        combinationTestTemplate("9999999999±√","Введены неверные данные","√( -9999999999 )");
    }

    @Test
    public void testSqr() {

        // float value
        combinationTestTemplate(" 85,792 sqr","7 360,267264","sqr( 85,792 )");
        combinationTestTemplate("276,1 ± sqr","76 231,21","sqr( -276,1 )");
        combinationTestTemplate("333,3 sqr","111 088,89","sqr( 333,3 )");
        combinationTestTemplate("471,1 ± sqr","221 935,21","sqr( -471,1 )");
        combinationTestTemplate("26009,0 sqr","676 468 081","sqr( 26009 )");
        combinationTestTemplate("573,84 sqr","329 292,3456","sqr( 573,84 )");
        combinationTestTemplate(" 36239,99 sqr","1 313 336 875,2001","sqr( 36239,99 )");
        combinationTestTemplate("65622,979sqr","4 306 375 372,834441","sqr( 65622,979 )");
        combinationTestTemplate("82537,699 sqr","6 812 471 756,214601","sqr( 82537,699 )");
        combinationTestTemplate("574±05,556 sqr","3 295 397 859,669136","sqr( -57405,556 )");

        // integer value
        combinationTestTemplate("1 sqr","1","sqr( 1 )");
        combinationTestTemplate("5 sqr","25","sqr( 5 )");
        combinationTestTemplate("13± sqr","169","sqr( -13 )");
        combinationTestTemplate("16 sqr","256","sqr( 16 )");
        combinationTestTemplate("72 ±sqr","5 184","sqr( -72 )");
        combinationTestTemplate("236± sqr","55 696","sqr( -236 )");
        combinationTestTemplate("653 sqr","426 409","sqr( 653 )");
        combinationTestTemplate("812 sqr","659 344","sqr( 812 )");
        combinationTestTemplate(" 819 sqr","670 761","sqr( 819 )");
        combinationTestTemplate("169± sqr","28 561","sqr( -169 )");
        combinationTestTemplate("213 sqr","45 369","sqr( 213 )");
        combinationTestTemplate(" 462 sqr","213 444","sqr( 462 )");
        combinationTestTemplate(" 257 sqr","66 049","sqr( 257 )");
        combinationTestTemplate(" 620± sqr","384 400","sqr( -620 )");
        combinationTestTemplate("740±sqr","547 600","sqr( -740 )");
        combinationTestTemplate("354 ± sqr","125 316","sqr( -354 )");
        combinationTestTemplate("20627sqr","425 473 129","sqr( 20627 )");
        combinationTestTemplate("29320 ±sqr","859 662 400","sqr( -29320 )");
        combinationTestTemplate("37782±sqr","1 427 479 524","sqr( -37782 )");
        combinationTestTemplate("44425 ±sqr","1 973 580 625","sqr( -44425 )");
        combinationTestTemplate("45613±sqr","2 080 545 769","sqr( -45613 )");
        combinationTestTemplate("56532±sqr","3 195 867 024","sqr( -56532 )");
        combinationTestTemplate("96421±sqr","9 297 009 241","sqr( -96421 )");
        combinationTestTemplate("33561sqr","1 126 340 721","sqr( 33561 )");
        combinationTestTemplate("41958sqr","1 760 473 764","sqr( 41958 )");
        combinationTestTemplate("47569 sqr","2 262 809 761","sqr( 47569 )");
        combinationTestTemplate("51830 sqr","2 686 348 900","sqr( 51830 )");
        combinationTestTemplate("86454 sqr","7 474 294 116","sqr( 86454 )");
    }

    @Test
    public void testOneDivided(){

        // float values
        combinationTestTemplate("9±,6 1/x","-0,1041666666666667","1/( -9,6 )");
        combinationTestTemplate("521,0 1/x","0,0019193857965451","1/( 521 )");
        combinationTestTemplate("359,3 1/x","0,0027831895352073","1/( 359,3 )");
        combinationTestTemplate("633,0 1/x","0,0015797788309636","1/( 633 )");
        combinationTestTemplate("361,5 1/x","0,0027662517289073","1/( 361,5 )");
        combinationTestTemplate("558,17 1/x","0,0017915688768654","1/( 558,17 )");
        combinationTestTemplate("977,03 1/x","0,0010235100252806","1/( 977,03 )");
        combinationTestTemplate("288,±71/x","-0,003463803255975","1/( -288,7 )");
        combinationTestTemplate("263,89± 1/x","-0,003789457728599","1/( -263,89 )");
        combinationTestTemplate("300,01±1/x","-0,0033332222259258","1/( -300,01 )");
        combinationTestTemplate("496,26± 1/x","-0,002015072744126","1/( -496,26 )");
        combinationTestTemplate("12312,3 1/x","0,0000812195934147","1/( 12312,3 )");
        combinationTestTemplate("14078,41 1/x","0,0000710307485007","1/( 14078,41 )");
        combinationTestTemplate("33041,0 1/x","0,0000302654278018","1/( 33041 )");
        combinationTestTemplate("99853,0 1/x","0,0000100147216408","1/( 99853 )");
        combinationTestTemplate("51681,16 1/x","0,0000193494108878","1/( 51681,16 )");
        combinationTestTemplate("17,35 ± 1/x","-0,0576368876080691","1/( -17,35 )");
        combinationTestTemplate("3466±2,3521/x","-0,0000288497445297","1/( -34662,352 )");
        combinationTestTemplate("2919±7,8241/x","-0,0000342491276062","1/( -29197,824 )");


        //integer values
        combinationTestTemplate(" 842 1/x","0,001187648456057","1/( 842 )");
        combinationTestTemplate(" 855 1/x","0,0011695906432748","1/( 855 )");
        combinationTestTemplate("992 1/x","0,001008064516129","1/( 992 )");
        combinationTestTemplate("867± 1/x","-0,0011534025374855","1/( -867 )");
        combinationTestTemplate("34±642 1/x","-0,0000288666936089","1/( -34642 )");
        combinationTestTemplate(" 41±835 1/x","-0,0000239034301422","1/( -41835 )");
        combinationTestTemplate("4±66471/x","-0,0000214376058481","1/( -46647 )");
        combinationTestTemplate(" 24843 1/x","0,0000402527875055","1/( 24843 )");
        combinationTestTemplate("88085 1/x","0,0000113526707157","1/( 88085 )");
        combinationTestTemplate("8±3794 1/x","-0,0000119340286894","1/( -83794 )");
        combinationTestTemplate("51515± 1/x","-0,0000194118217994","1/( -51515 )");
        combinationTestTemplate("72082 ± 1/x","-0,0000138730889819","1/( -72082 )");
        combinationTestTemplate("120407969 1/x","8,305098145123601e-9","1/( 120407969 )");
        combinationTestTemplate("92767132 ± 1/x","-1,077968002718894e-8","1/( -92767132 )");
        combinationTestTemplate("20059845 ± 1/x","-4,985083384243497e-8","1/( -20059845 )");
        combinationTestTemplate("927597273 ± 1/x","-1,078054053313285e-9","1/( -927597273 )");
        combinationTestTemplate("9892858±16 1/x","-1,010830220980344e-9","1/( -989285816 )");
    }

    @Test
    public void testNegate(){
        // float values
        combinationTestTemplate("56±3,0","-563,0","");
        combinationTestTemplate("543,0±","-543,0","");
        combinationTestTemplate("684,0±±","684,0","");
        combinationTestTemplate("51±8±,7","518,7","");
        combinationTestTemplate("8±58,2","-858,2","");
        combinationTestTemplate("574,5±","-574,5","");
        combinationTestTemplate("203,1±1±","203,11","");
        combinationTestTemplate("49±537,0","-49 537,0","");
        combinationTestTemplate("95±±954,0","95 954,0","");
        combinationTestTemplate("992±32,8","-99 232,8","");
        combinationTestTemplate("5983±0,2±","59 830,2","");
        combinationTestTemplate("6±896,332","-6 896,332","");
        combinationTestTemplate("6±7725,44±","67 725,44","");
        combinationTestTemplate("9±7±9±01,35±","97 901,35","");
        combinationTestTemplate("8±59,243625±6","859,2436256","");

        // integer values
        combinationTestTemplate("49 ±","-49","");
        combinationTestTemplate("7±39","-739","");
        combinationTestTemplate("22±0","-220","");
        combinationTestTemplate("2±71","-271","");
        combinationTestTemplate("792±","-792","");
        combinationTestTemplate("290±±","290","");
        combinationTestTemplate("3±82±","382","");
        combinationTestTemplate("6±09±","609","");
        combinationTestTemplate("2322±5","-23 225","");
        combinationTestTemplate("92±701±","92 701","");
        combinationTestTemplate("96±476±","96 476","");
        combinationTestTemplate("548±±94","54 894","");
        combinationTestTemplate("29±0±3±9","-29 039","");
        combinationTestTemplate("3±9368±","39 368","");
        combinationTestTemplate("512±0±5","51 205","");
        combinationTestTemplate("45±306±","45 306","");
        combinationTestTemplate("38±50303±89","385 030 389","");
        combinationTestTemplate("72±9814±849","729 814 849","");
        combinationTestTemplate("7±3±1±72430±±5±","731 724 305","");
        combinationTestTemplate("4±±±016±±±6±6±±838±±±","401 666 838","");
    }

    @Test
    public void combinationTest(){
        combinationTestTemplate( "1941317165 √ √","209,905647611963","√( √( 1941317165 ) )");
        combinationTestTemplate( "1113642533 1/x √","0,0000299658840876","√( 1/( 1113642533 ) )");
        combinationTestTemplate( "1374605492 1/x √","0,000026971864091","√( 1/( 1374605492 ) )");
        combinationTestTemplate( "1485689250 1/x √","0,0000259439446742","√( 1/( 1485689250 ) )");
        combinationTestTemplate( "16114±73845 1/x","-6,205499413488774e-10","1/( -1611473845 )");
        combinationTestTemplate( "1681947901 √ ± sqr","1 681 947 901","sqr( negate( √( 1681947901 ) ) )");
        combinationTestTemplate( "1156246513 sqr 1/x sqr","5,594976361205657e-37","sqr( 1/( sqr( 1156246513 ) ) )");
        combinationTestTemplate( "1882623713 ± sqr ±","-3,544272044749906e+18","negate( sqr( -1882623713 ) )");
        combinationTestTemplate( "1992029943 sqr √ sqr 1/x","2,520044881899142e-19","1/( sqr( √( sqr( 1992029943 ) ) ) )");
        combinationTestTemplate( "1162±687379 √ √","Введены неверные данные","√( -1162687379 )");
        combinationTestTemplate( "1143909392 sqr ± sqr sqr 1/x","3,410884346438859e-73","1/( sqr( sqr( negate( sqr( 1143909392 ) ) ) ) )");
        combinationTestTemplate( "983787573 √ 1/x √ 1/x √ sqr  ","177,1027615178389","sqr( √( 1/( √( 1/( √( 983787573 ) ) ) ) ) )");
        combinationTestTemplate( "1197900634 √ 1/x √ ± ±","0,0053752021439962","negate( negate( √( 1/( √( 1197900634 ) ) ) ) )");
        combinationTestTemplate( "1364005784 1/x ± sqr" ,"5,374865192483768e-19","sqr( negate( 1/( 1364005784 ) ) )");
        combinationTestTemplate( "1126760686± sqr √ √ √ sqr","33 567,25615834574","sqr( √( √( √( sqr( -1126760686 ) ) ) ) )");
        combinationTestTemplate( "1434189007 1/x sqr √ sqr sqr 1/x ±","-4,230829825869764e+36","negate( 1/( sqr( sqr( √( sqr( 1/( 1434189007 ) ) ) ) ) ) )");
        combinationTestTemplate( "2045599636 √ √ 1/x ± sqr √ ±","-0,0047021322590797","negate( √( sqr( negate( 1/( √( √( 2045599636 ) ) ) ) ) ) )");
        combinationTestTemplate( "97686240±4 sqr sqr √ ± ± 1/x","1,047932257620771e-18","1/( negate( negate( √( sqr( sqr( -976862404 ) ) ) ) ) )");
        combinationTestTemplate( "197936665 √ sqr ± 1/x ± ±","-5,052121091360209e-9","negate( negate( 1/( negate( sqr( √( 197936665 ) ) ) ) ) )");
        combinationTestTemplate("9999999999999999 sqr sqr sqr sqr sqr sqr sqr sqr sqr ","9,999999999999488e+8191","sqr( sqr( sqr( sqr( sqr( sqr( sqr( sqr( sqr( 9999999999999999 ) ) ) ) ) ) ) ) )");
    }

    @Test
    public void sqrtAndSqrTest(){
        combinationTestTemplate("0 √ sqr","0","sqr( √( 0 ) )");
        combinationTestTemplate("1 √ sqr","1","sqr( √( 1 ) )");
        combinationTestTemplate("12 √ sqr","12","sqr( √( 12 ) )");
        combinationTestTemplate("5,25501 √ sqr","5,25501","sqr( √( 5,25501 ) )");
        combinationTestTemplate( " 158615±0048 sqr √","1 586 150 048","√( sqr( -1586150048 ) )");
        combinationTestTemplate("7328761739 √ sqr","7 328 761 739","sqr( √( 7328761739 ) )");
        combinationTestTemplate("46166312,23691 √ sqr","46 166 312,23691","sqr( √( 46166312,23691 ) )");
    }

}
