package pro.sky.coursework2.service.Impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.coursework2.exception.QuestionAlreadyExistsException;
import pro.sky.coursework2.exception.QuestionNotFoundException;
import pro.sky.coursework2.exception.QuestionsAreEmptyException;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.QuestionService;
import pro.sky.coursework2.service.impl.JavaQuestionService;

import java.util.HashSet;


public class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beForeEach() {
        questionService.add(new Question("Вопрос 1", "Ответ 1"));
        questionService.add(new Question("Вопрос 2", "Ответ 2"));
        questionService.add(new Question("Вопрос 3", "Ответ 3"));
    }

    @AfterEach
    public void afterEach() {
        new HashSet<>(questionService.getAll()).forEach(questionService::remove);
    }

    @Test
    public void addTest() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Вопрос 4", "Ответ 4");

        Assertions.assertThat(questionService.add(question))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        Assertions.assertThat(questionService.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    public void add1Test() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Вопрос 4", "Ответ 4");

        Assertions.assertThat(questionService.add("Вопрос 4", "Ответ 4"))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        Assertions.assertThat(questionService.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    public void addNegativeTest() {
        Question question = new Question("Вопрос 1", "Ответ 1");

        Assertions.assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(question));
    }

    @Test
    public void add1NegativeTest() {
        Assertions.assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add("Вопрос 1", "Ответ 1"));
    }

    @Test
    public void removeTest() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Вопрос 2", "Ответ 2");

        Assertions.assertThat(questionService.remove(question))
                .isEqualTo(question)
                .isNotIn(questionService.getAll());
        Assertions.assertThat(questionService.getAll()).hasSize(beforeCount - 1);
    }

    @Test
    public void removeNegativeTest() {
        Assertions.assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove((new Question("Вопрос 5", "Ответ 5"))));
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(questionService.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Вопрос 1", "Ответ 1"),
                        new Question("Вопрос 2", "Ответ 2"),
                        new Question("Вопрос 3", "Ответ 3")
                );

    }

    @Test
    public void getRandomQuestionTest() {
        Assertions.assertThat(questionService.getRundomQuestion())
                .isIn(questionService.getAll());
    }

    @Test
    public void getRandomQuestionNegativeTest() {
        afterEach();

        Assertions.assertThatExceptionOfType(QuestionsAreEmptyException.class)
                .isThrownBy(questionService::getRundomQuestion);
    }
}
