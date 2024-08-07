package steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AssertText {

    private static final Logger logger = LogManager.getLogger(AssertText.class);

    public static void assertTextEquals(String actual, String expected) {
        if (!actual.equals(expected)) {
            logger.error("Ошибка! Ожидалось: {}, но получили: {}", expected, actual);
            throw new AssertionError("Expected: " + expected + ", but got: " + actual);
        } else {
            logger.info("Успешно! Текст соответствует ожидаемому: {}", expected);
        }
    }

    public static void assertWindowNotNull(String windowNotNull, String errorMessage) {
        if (windowNotNull == null) {
            logger.error("Ошибка! {}", errorMessage);
            throw new AssertionError(errorMessage);
        } else {
            logger.info("Значение не равно нулю");
        }
    }
}
