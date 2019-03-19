package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;
import java.util.Deque;
import java.util.ArrayDeque;


/**
 * Для проверки класса на корректность следует использовать тесты.
 * Команда {@code ./gradlew clean build} должна завершаться корректно.
 * <p>
 * При решении задания следует обратить внимание на использование ключевых слов {@code final} и {@code static}
 * <p>
 * В результирующим PR нужно предоставить
 * - Код решения
 * Реализовать все методы
 * Весь код внутри CorrectBracketSequencePredicate
 * Сигнатуры класса, конструктора и метода следует оставить неизменными
 * Комментарии оставить неизменными
 * Можно добавлять дополнительные методы
 * - Тесты
 * Внутри package ru.mail.polis.open.task3
 * В нём будут видны public / protected / package_private методы
 */
public final class CorrectBracketSequenceChecker {

    private CorrectBracketSequenceChecker() {
        /* todo: append code if needed */
    }

    private static final char[] OPEN_BRACKETS = {'(','[','{'};
    private static final char[] CLOSE_BRACKETS = {')',']','}'};

    private static int counterOfCorrectTries = 0;
    private static int counterOfWrongTries = 0;
    private static String lastSuccessSequence;
    private static Deque<Character> stackOfChar = new ArrayDeque<>();
    private static Character prevElementInStack;


    /**
     * Метод проверяющий скобочную последовательность на правильность.
     * <p>
     * Пустая строка
     * — правильная скобочная последовательность.
     * Правильная скобочная последовательность, взятая в скобки одного типа
     * — правильная скобочная последовательность.
     * Правильная скобочная последовательность,
     * к которой слева или справа приписана правильная скобочная последовательность
     * — правильная скобочная последовательность.
     * <p>
     * Последовательности из больше чем ста символов или с символами не скобок — некорректные.
     * <p>
     * Скобки бывают:
     * 1. Круглые '(', ')'
     * 2. Квадратные '[', ']'
     * 3. Фигурные '{', '}'
     *
     * @param sequence — входная строка
     * @return {@code true} — если скобочная последовательность корректна и {@code false} иначе
     * @throws IllegalArgumentException если в строке содержатся символы, не являющиеся скобками
     *                                  или если входная строка содержит больше ста символов
     */
    public static boolean checkSequence(@Nullable String sequence) {
        //нулевая строка - правильная
        if (sequence == null) {
            counterOfCorrectTries++;
            return true;
        }

        //пустая строка - правильная
        if (sequence.equals("")) {
            counterOfCorrectTries++;
            return true;
        }


        //длина строки больше ста символов
        if (sequence.length() > 100) {
            counterOfWrongTries++;
            throw new IllegalArgumentException("The string length is more than 100");
        }

        for (int i = 0; i < sequence.length(); i++) {
            //Проверка на наличие символов, неявляющихся скобками

            if ((sequence.charAt(i) != OPEN_BRACKETS[0]) && (sequence.charAt(i) != CLOSE_BRACKETS[0])
                    && (sequence.charAt(i) != OPEN_BRACKETS[1]) && (sequence.charAt(i) != CLOSE_BRACKETS[1])
                    && (sequence.charAt(i) != OPEN_BRACKETS[2]) && (sequence.charAt(i) != CLOSE_BRACKETS[2])) {
                stackOfChar.clear();
                counterOfWrongTries++;
                throw new IllegalArgumentException("This symbol isn't bracket: " + sequence.charAt(i));
            }


            //Открытые скобки
            for (int j = 0; j < 3; j++) {
                if (sequence.charAt(i) == OPEN_BRACKETS[j]) {
                    stackOfChar.push(sequence.charAt(i));
                }
            }

            //Закрытые скобки
            for (int j = 0; j < 3; j++) {
                if (sequence.charAt(i) == CLOSE_BRACKETS[j]) {
                    if (stackOfChar.size() == 0) {
                        counterOfWrongTries++;
                        return false;
                    } else {
                        prevElementInStack = stackOfChar.pop();
                        if (prevElementInStack != OPEN_BRACKETS[j]) {
                            stackOfChar.clear();
                            counterOfWrongTries++;
                            return false;
                        }
                    }
                }
            }
        }

        lastSuccessSequence = sequence;

        if (stackOfChar.size() != 0) {
            stackOfChar.clear();
            counterOfWrongTries++;
            return false;
        }
        counterOfCorrectTries++;
        return true;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        return counterOfCorrectTries;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return counterOfWrongTries;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return lastSuccessSequence;
    }

    public static void resetParameters() {
        lastSuccessSequence = null;
        counterOfCorrectTries = 0;
        counterOfWrongTries = 0;
    }
}
