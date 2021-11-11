package no.kristiania.http;

import no.kristiania.questionnaire.Question;
import no.kristiania.questionnaire.QuestionDao;

import java.sql.SQLException;

public class ListQuestionController implements HttpController {
    private final QuestionDao questionDao;

    public ListQuestionController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public HttpMessage handle(HttpMessage request) throws SQLException {
        String response = "";

        for (Question question : questionDao.listAll()) {
            response += "<div>" + question.getLastName() + ", " + question.getFirstName() + "</div>";
        }

        return new HttpMessage("HTTP/1.1 200 OK", response);
    }
}