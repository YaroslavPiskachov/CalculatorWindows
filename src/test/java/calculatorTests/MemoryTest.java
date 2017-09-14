package calculatorTests;

import org.junit.Test;

/**
 * Created by Yaroslav on 25.07.2017.
 */

public class MemoryTest extends MyTestBox {

    @Test
    public void combinationMemoryTest(){
        combinationTestTemplate("M+","0","");
        combinationTestTemplate("M-","0","");
        combinationTestTemplate("MS","0","");
        combinationTestTemplate("MR M+","0","");
        combinationTestTemplate("MS M+","0","");
        combinationTestTemplate("M+ M- MR","0","");
        combinationTestTemplate("2 MS MR MС","2","");
        combinationTestTemplate("4 MS C MR + 16 -","20","4 + 16 -");
        combinationTestTemplate("2 MS 0 MR","2","");
        combinationTestTemplate("8 M+ MS MR","8","");
        combinationTestTemplate("124 MS M- MR","0","");
        combinationTestTemplate("5 ± MS 4 MR","-5","");
        combinationTestTemplate("1 + 2 MS 5 MR","2","1 +");
        combinationTestTemplate("2 MR MS 90 MR","2","");
        combinationTestTemplate("51 + MS 1 MR","51","51 +");
        combinationTestTemplate("91 MS 1 M+ MR","92","");
        combinationTestTemplate("30 MS 4 M+ MR","34","");
        combinationTestTemplate("0,5 MS 2 MR + 2 * 3 =","7,5","");
        combinationTestTemplate("4 MS / 2 + MR =","6","");
        combinationTestTemplate("34 MS 2 + 1 = MR","34","");
        combinationTestTemplate("1 MS C C M+ C 32 MR","1","");
        combinationTestTemplate("2 + 1 MS C MR * 34","34","1 ×");
        combinationTestTemplate("1244 MS 57422 MR","1 244","");
        combinationTestTemplate("2 MS 3 M+ 2 MR * 38 =","190","");
        combinationTestTemplate("3,532 ± MS 1 M+ C MR","-2,532","");
        combinationTestTemplate("64 MS CE 300 M+ MR", "364","");
        combinationTestTemplate("5 MS 53 / 2 M- M- + 4","4","53 ÷ 2 +");
        combinationTestTemplate("4 MS + 3 M+ * 2 MR","7","4 + 3 ×");
        combinationTestTemplate("22 - 2 MS / 2 C MR * 2","2","2 ×");
        combinationTestTemplate("0,53 MS - 3 M+ MR","3,53","0,53 -");
        combinationTestTemplate("1 MS 0,12 M- M- * 3 = MR","0,76","");
        combinationTestTemplate("93 MS - 4 M+ * 2 MR M-","97","93 - 4 ×");
        combinationTestTemplate("53 + 4 MS 4 M- 5 M+ * 12 MR =","290","");
        combinationTestTemplate(" 64√ MS CE 300 + 22 - MR / 2 =", "157","");
        combinationTestTemplate("40 MS 2,341 M- M- / 2 + MR =","36,4885","");
        combinationTestTemplate("57 MS 2 M+ M+ M+ MR / 2 +","31,5","63 ÷ 2 +");
        combinationTestTemplate("43 MS + 5325 1/x C backSpace M- - 3 * MR =", "-129","");
        combinationTestTemplate("7320 / 3 * 1 + 3 √  MS C MR * 2" , "2","1,732050807568877 ×");
        combinationTestTemplate("42 + 3 MS - 200 *  3 1/x M+ M+ MR + 29 /","-539,3333333333333","42 + 3 - 200 × 3,666666666666667 + 29 ÷");
    }
}
