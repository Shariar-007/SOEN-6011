import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class SigmaCalculatorGuiStyleCompliantTest {

    private SigmaCalculatorGuiStyleCompliant calculator;

    @BeforeEach
    public void setUp() {
        calculator = new SigmaCalculatorGuiStyleCompliant();
    }

    @Test
    public void testSquare_positiveNumber() {
        assertEquals(25.0, calculator.square(5.0), 0.0001);
    }

    @Test
    public void testSquare_negativeNumber() {
        assertEquals(9.0, calculator.square(-3.0), 0.0001);
    }

    @Test
    public void testSquare_zero() {
        assertEquals(0.0, calculator.square(0.0), 0.0001);
    }

    @Test
    public void testSqrt_perfectSquare() {
        assertEquals(5.0, calculator.sqrt(25.0), 0.0001);
    }

    @Test
    public void testSqrt_smallValue() {
        assertEquals(0.1, calculator.sqrt(0.01), 0.0001);
    }

    @Test
    public void testSqrt_zero() {
        assertEquals(0.0, calculator.sqrt(0.0), 0.0001);
    }

    @Test
    public void testSqrt_negative() {
        assertEquals(-1.0, calculator.sqrt(-16.0), 0.0001); // handled as error case
    }

    @Test
    public void testAbsolute_positive() {
        assertEquals(7.5, calculator.absolute(7.5), 0.0001);
    }

    @Test
    public void testAbsolute_negative() {
        assertEquals(3.3, calculator.absolute(-3.3), 0.0001);
    }

    @Test
    public void testAbsolute_zero() {
        assertEquals(0.0, calculator.absolute(0.0), 0.0001);
    }
}
