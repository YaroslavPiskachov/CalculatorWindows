package calculatorTests;

import org.junit.Test;

/**
 * Created by Yaroslav on 28.07.2017.
 */
public class CalculateButtonTest extends MyTestBox {

    @Test
    public void severalPressingTestPlus() {
        combinationTestTemplate("5 + 4 = = =","17");
        combinationTestTemplate("1 + 63 = = =","190");
        combinationTestTemplate("6 + 1 = = = = =","11");
        combinationTestTemplate("5,53 + 35 = =","75,53");
        combinationTestTemplate("664 + 0,7 = = =","666,1");
        combinationTestTemplate("664 + 0,7 = = =","666,1");
        combinationTestTemplate("53 + 0 = = = = = = =","53");
        combinationTestTemplate("35,9294 + 0,0052 = = =","35,945");
        combinationTestTemplate("5 + 94 = = = = = = = = = = =","1 039");
        combinationTestTemplate("18 + 6 + 4 = = = = = =","48");
    }

    @Test
    public void severalPressingTestMinus() {
        combinationTestTemplate("52 - 8 = = =","28");
        combinationTestTemplate("1 - 6,5 = = =","-18,5");
        combinationTestTemplate("46 - 0,7 = = =","43,9");
        combinationTestTemplate("6,52 - 25 = =","-43,48");
        combinationTestTemplate("35,02 - 3,4 = = = = =","18,02");
        combinationTestTemplate("0 - 0 - 6 = = = = = = =","-42");
        combinationTestTemplate("51,24 - 0,0052 = = =","51,2244");
        combinationTestTemplate("50 - 94 = = = = = = = = = = =","-984");
    }

    @Test
    public void severalPressingTestMultiply() {
        combinationTestTemplate("1 * 0,63 = =","0,3969");
        combinationTestTemplate("6 * 10 = = = = =","600 000");
        combinationTestTemplate("1,53 * 3,05 = =","14,232825");
        combinationTestTemplate("1 * 6 * 8 * 4 = = = = =","49 152");
        combinationTestTemplate("641 * 0,347 = = =","26,782212643");
        combinationTestTemplate("64,4 * 0,13 = = = =","0,018393284");
        combinationTestTemplate("5,0003 * 21 = = = =","972 463,3443");
        combinationTestTemplate("53,111 * 0,4 = = = = = = =","0,0870170624");
        combinationTestTemplate("5,9294 * 4,1052 = = =","410,2170524431259");
        combinationTestTemplate("19 * 5,54 = = = = = = = = = = =","2 866 559 845,132388");
    }

    @Test
    public void severalPressingTestDivide() {
        combinationTestTemplate("1 / 63 = =","2,519526329050139e-4");
        combinationTestTemplate("1,3 / 3,05 = =","0,1397473797366299");
        combinationTestTemplate("6 / 5,3 = = = = =","0,0014347356919028");
        combinationTestTemplate("41 / 0,47 = = =","394,9028635273494");
        combinationTestTemplate("14,4 / 0,36 = = = =","857,3388203017833");
        combinationTestTemplate("0,05 / 5,6 = = = =","5,084144627238651e-5");
        combinationTestTemplate("1 / 4,3 / 12 / 4 = = = = =","1,892562984496124e-5");
        combinationTestTemplate("3,294 / 4,105 = = =","0,0476194209903065");
        combinationTestTemplate("3,1411 / 0,8 = = = = = = =","14,97793197631836");
        combinationTestTemplate("19,4 / 2,54 = = = = = = = = = = =","6,833305653157216e-4");
    }

}
