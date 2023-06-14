package pro.sky.coursework2.service;

import pro.sky.coursework2.model.Question;

import java.util.Collection;

public interface QuestionService {

    Question add (String question, String answer);

    Question add (String question);

    Question remove (String question);

    Collection<Question> getAll();

    Question getRundomQuestion();
}
