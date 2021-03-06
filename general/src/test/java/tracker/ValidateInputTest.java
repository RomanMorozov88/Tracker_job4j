package tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tracker.inputs.StubInput;
import tracker.inputs.ValidateInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (mailto:parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"invalid", "1"})
        );
        List<Integer> answer = new ArrayList<>();
        answer.add(1);
        input.ask("Enter", answer);
        assertThat(
                this.mem.toString(),
                is(
                        new StringBuilder()
                                .append("-Некорректо введён пункт меню.")
                                .append(System.lineSeparator())
                                .append("-Введите целое число  1 до 1.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}