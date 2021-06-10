package codingdojo.romannumerals;

import org.junit.*;
import org.junit.rules.ExpectedException;

public class IntegerToRomanTest {

    IntegerToRoman integerToRoman;

    @Rule
    public ExpectedException myExcep = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        integerToRoman = new IntegerToRoman();
      //  myExcep
    }

    @Test
    public void testConvertZeroToRoman() {
        Assert.assertEquals("nulla", integerToRoman.convert(0));
    }

    @Test
    public void testConvertOneToRoman() {
        Assert.assertEquals("I", integerToRoman.convert(1));
    }

    @Test
    public void testConvertTwoToRoman() {
        Assert.assertEquals("II", integerToRoman.convert(2));
    }

    @Test
    public void testConvertThreeToRoman() {
        Assert.assertEquals("III", integerToRoman.convert(3));
    }

    @Test
    public void testConvertFourToRoman() {
        Assert.assertEquals("IV", integerToRoman.convert(4));
    }

    @Test
    public void testConvertFiveToRoman() {
        Assert.assertEquals("V", integerToRoman.convert(5));
    }

    @Test
    public void testConvertSixToRoman() {
        Assert.assertEquals("VI", integerToRoman.convert(6));
    }

    @Test
    public void testConvertSevenToRoman() {
        Assert.assertEquals("VII", integerToRoman.convert(7));
    }

    @Test
    public void testConvertEightToRoman() {
        Assert.assertEquals("VIII", integerToRoman.convert(8));
    }

    @Test
    public void testConvertNineToRoman() {
        Assert.assertEquals("IX", integerToRoman.convert(9));
    }

    @Test
    public void testConvertTenToRoman() {
        Assert.assertEquals("X", integerToRoman.convert(10));
    }

    @Test
    public void testConvertNineteenToRoman() {
        Assert.assertEquals("XIX", integerToRoman.convert(19));
    }

    @Test
    public void testConvertTwentyFourToRoman() {
        Assert.assertEquals("XXIV", integerToRoman.convert(24));
    }

    @Test
    public void testConvertThirtyNineToRoman() {
        Assert.assertEquals("XXXIX", integerToRoman.convert(39));
    }

    @Test
    public void testConvertFortyToRoman() {
        Assert.assertEquals("XL", integerToRoman.convert(40));
    }

    @Test
    public void testConvertSixtyToRoman() {
        Assert.assertEquals("LX", integerToRoman.convert(60));
    }

    @Test
    public void testConvertTwoHundredTwoToRoman() {
        Assert.assertEquals("CCII", integerToRoman.convert(202));
    }

    @Test
    public void testConvertFourSixtyFiveToRoman() {
        Assert.assertEquals("CDLXV", integerToRoman.convert(465));
    }

    @Test
    public void testConvertFiveFourteenToRoman() {
        Assert.assertEquals("DXIV", integerToRoman.convert(514));
    }

    @Test
    public void testConvertNineNinetyNineToRoman() {
        Assert.assertEquals("CMXCIX", integerToRoman.convert(999));
    }

    @Test
    public void testConvertTwoNineNinetyNineToRoman() {
        Assert.assertEquals("MMCMXCIX", integerToRoman.convert(2999));
    }

    @Test(expected = NumberFormatException.class)
    public void testConvertMinusTwoToRoman() {
        integerToRoman.convert(-2);
    }


    @Test
    public void testConvertMinusTwoToRomanAgain(){
        myExcep.expect(NumberFormatException.class);
        myExcep.expect(IllegalArgumentException.class);

        integerToRoman.convert(-2);
    }

    @Test
    public void testConvertThreeOneTwoEightToRoman() {
        myExcep.expect(StringIndexOutOfBoundsException.class);
        Assert.assertEquals("MMMCXXVIII", integerToRoman.convert(3128));
    }
}
