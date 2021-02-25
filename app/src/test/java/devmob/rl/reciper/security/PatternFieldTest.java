package devmob.rl.reciper.security;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class PatternFieldTest {

    @Test
    public void NAME_RECIPEtest(){
        assertRegex(PatternField.NAME_RECIPE,"1234",true);
        assertRegex(PatternField.NAME_RECIPE,"",false);
        assertRegex(PatternField.NAME_RECIPE,"1",false);
        assertRegex(PatternField.NAME_RECIPE,"a",false);
        assertRegex(PatternField.NAME_RECIPE,"a1",false);
        assertRegex(PatternField.NAME_RECIPE,"1a",false);
        assertRegex(PatternField.NAME_RECIPE,"1a3",true);
        assertRegex(PatternField.NAME_RECIPE,"1 3",true);
        assertRegex(PatternField.NAME_RECIPE,"1 a",true);
        assertRegex(PatternField.NAME_RECIPE,"124a-",false);
        assertRegex(PatternField.NAME_RECIPE,"124a@",false);
        assertRegex(PatternField.NAME_RECIPE,"124a()",true);
        assertRegex(PatternField.NAME_RECIPE,"124a()\"",true);
        assertRegex(PatternField.NAME_RECIPE,"124a+",false);
        assertRegex(PatternField.NAME_RECIPE,"124a=",false);
        assertRegex(PatternField.NAME_RECIPE,"125as125as125as125as125as125as125as125as125as125as",true);
        assertRegex(PatternField.NAME_RECIPE,"125as125as125as125as125as125as125 as125as125as125as",false);
    }

    @Test
    public void FIELDtest(){
        assertRegex(PatternField.FIELD,"1234",true);
        assertRegex(PatternField.FIELD,"",false);
        assertRegex(PatternField.FIELD,"1",false);
        assertRegex(PatternField.FIELD,"a",false);
        assertRegex(PatternField.FIELD,"a1",false);
        assertRegex(PatternField.FIELD,"1a",false);
        assertRegex(PatternField.FIELD,"1a3",true);
        assertRegex(PatternField.FIELD,"1 3",true);
        assertRegex(PatternField.FIELD,"1 a",true);
        assertRegex(PatternField.FIELD,"124a-",true);
        assertRegex(PatternField.FIELD,"124a@",false);
        assertRegex(PatternField.FIELD,"124a()",true);
        assertRegex(PatternField.FIELD,"124a()\"",true);
        assertRegex(PatternField.FIELD,"124a+",false);
        assertRegex(PatternField.FIELD,"124a=",false);
        assertRegex(PatternField.FIELD,"125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as1" +
                "25as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125a" +
                "s125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as" +
                "125as125as125as125as125as125as125as125as125as",true);
        assertRegex(PatternField.FIELD,"125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as1" +
                "25as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125a" +
                "s125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as125as" +
                "125as125as125as125as125as125as125as125as125asFSGD",false);
    }

    private void assertRegex(String regex, String sequence, boolean expected) {
        assertEquals(expected, Pattern.matches(regex, sequence));
    }
}
