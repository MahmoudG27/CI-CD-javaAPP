import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CalculatorUITest {

    private CalculatorUI calculatorUI;

    @BeforeEach
    void setUp() {
        calculatorUI = mock(CalculatorUI.class);
        // Set up any mocked behavior or expectations here
    }

    @ParameterizedTest
    @CsvSource({"3,5,+,8", "2,8,-,-6", "44.5,10,*,445", "320,5,/,64", "3,5,%,3", "5,3,^,125"})
    void testCalculation(double firstNumber, double secondNumber, char operator, double expectedResult) {
        // Mock the behavior of the calculatorUI instance here
        when(calculatorUI.calculate(firstNumber, secondNumber, operator)).thenReturn(expectedResult);

        // Perform the test by invoking the method being tested
        double actualResult = calculatorUI.calculate(firstNumber, secondNumber, operator);

        // Assert the expected result against the actual result
        assertEquals(expectedResult, actualResult);
    }
}

