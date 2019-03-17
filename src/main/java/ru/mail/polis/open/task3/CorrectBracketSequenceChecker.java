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

    private static final char OPEN_ROUND_BRACE = '(';
    private static final char OPEN_SQUARE_BRACE = '[';
    private static final char OPEN_FIGURE_BRACE = '{';
    private static final char CLOSE_ROUND_BRACE = ')';
    private static final char CLOSE_SQUARE_BRACE = ']';
    private static final char CLOSE_FIGURE_BRACE = '}';

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
        counterOfWrongTries = 0;
        counterOfCorrectTries = 0;
        //пустая строка - правильная
        if (sequence.equals("")) {
            counterOfCorrectTries++;
            return true;
        }

        //длина строки больше ста символов
        if (sequence.length() > 100) {
            counterOfWrongTries++;
            counterOfWrongTries += counterOfCorrectTries;
            throw new IllegalArgumentException("The string length is more than 100");
        }

        for (int i = 0; i < sequence.length(); i++) {
            //Проверка на наличие символов, неявляющихся скобками
            if ((sequence.charAt(i) != OPEN_ROUND_BRACE)
                    && (sequence.charAt(i) != OPEN_FIGURE_BRACE)
                    && (sequence.charAt(i) != OPEN_SQUARE_BRACE)
                    && (sequence.charAt(i) != CLOSE_FIGURE_BRACE)
                    && (sequence.charAt(i) != CLOSE_ROUND_BRACE)
                    && (sequence.charAt(i) != CLOSE_SQUARE_BRACE)) {
                counterOfWrongTries++;
                counterOfWrongTries += counterOfCorrectTries;
                throw new IllegalArgumentException("This symbol isn't bracket: " + sequence.charAt(i));
            }

            //Открытые скобки
            if ((sequence.charAt(i) == OPEN_ROUND_BRACE)
                    || (sequence.charAt(i) == OPEN_SQUARE_BRACE)
                    || (sequence.charAt(i) == OPEN_FIGURE_BRACE)) {

                stackOfChar.push(sequence.charAt(i));
            }

            //Круглые скобки
            if (sequence.charAt(i) == CLOSE_ROUND_BRACE) {
                if (stackOfChar.size() == 0) {
                    counterOfWrongTries++;
                    counterOfWrongTries += counterOfCorrectTries;
                    return false;
                } else {
                    prevElementInStack = stackOfChar.pop();
                    if (prevElementInStack != OPEN_ROUND_BRACE) {
                        counterOfWrongTries++;
                        counterOfWrongTries += counterOfCorrectTries;
                        return false;
                    } else {
                        counterOfCorrectTries++;
                    }
                }
            }

            //Квадратные скобки
            if (sequence.charAt(i) == CLOSE_SQUARE_BRACE) {
                if (stackOfChar.size() == 0) {
                    counterOfWrongTries++;
                    counterOfWrongTries += counterOfCorrectTries;
                    return false;
                } else {
                    prevElementInStack = stackOfChar.pop();
                    if (prevElementInStack != OPEN_SQUARE_BRACE) {
                        counterOfWrongTries++;
                        counterOfWrongTries += counterOfCorrectTries;
                        return false;
                    } else {
                        counterOfCorrectTries++;
                    }
                }
            }

            //Фигурные скобки
            if (sequence.charAt(i) == CLOSE_FIGURE_BRACE) {
                if (stackOfChar.size() == 0) {
                    counterOfWrongTries++;
                    counterOfWrongTries += counterOfCorrectTries;
                    return false;
                } else {
                    prevElementInStack = stackOfChar.pop();
                    if (prevElementInStack != OPEN_FIGURE_BRACE) {
                        counterOfWrongTries++;
                        counterOfWrongTries += counterOfCorrectTries;
                        return false;
                    } else {
                        counterOfCorrectTries++;
                    }
                }
            }
        }
        counterOfWrongTries += counterOfCorrectTries;
        lastSuccessSequence = sequence;

        if (stackOfChar.size() != 0) {
            return false;
        }

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
}
