package steps;

public class AssertText {

    public static void assertTextEquals(String actual, String expected) {
        if (!actual.equals(expected)) {
            System.out.println("Fail! Ожидалось: " + expected + ", но получили: " + actual);
        } else {
            System.out.println("Success! Текст соответствует ожидаемому: " + expected);
        }
    }
}