package no.kristiania.http;

import no.kristiania.questionnaire.Question;
import no.kristiania.questionnaire.QuestionDao;

import java.sql.SQLException;
import java.util.Map;

public class AddQuestionController implements HttpController {
    private final QuestionDao questionDao;

    public AddQuestionController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public HttpMessage handle(HttpMessage request) throws SQLException {
        Map<String, String> queryMap = HttpMessage.parseRequestParameters(request.messageBody);
        Question question = new Question();
        question.setFirstName(queryMap.get("firstName"));
        question.setLastName(queryMap.get("lastName"));
        questionDao.save(question);

        return new HttpMessage("HTTP/1.1 200 OK", "It is done");
    }
}