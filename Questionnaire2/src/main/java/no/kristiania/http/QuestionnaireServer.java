package no.kristiania.http;

import no.kristiania.questionnaire.OptionDao;
import no.kristiania.questionnaire.QuestionDao;
import no.kristiania.questionnaire.QuestionDao;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class QuestionnaireServer {
    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) throws IOException {
        DataSource dataSource = createDataSource();
        OptionDao optionDao = new OptionDao(dataSource);
        QuestionDao questionDao = new QuestionDao(dataSource);
        HttpServer httpServer = new HttpServer(1962);
        httpServer.addController("/api/roleOptions", new OptionController(optionDao));
        httpServer.addController("/api/newPerson", new AddQuestionController(questionDao));
        httpServer.addController("/api/people", new ListQuestionController(questionDao));
        logger.info("Starting http://localhost:{}/index.html", httpServer.getPort());
    }

    private static DataSource createDataSource() throws IOException {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader("pgr203.properties")) {
            properties.load(reader);
        }

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(properties.getProperty(
                "dataSource.url",
                "jdbc:postgresql://localhost:5432/person_db"
        ));
        dataSource.setUser(properties.getProperty("dataSource.user", "person_dbuser"));
        dataSource.setPassword(properties.getProperty("dataSource.password"));
        Flyway.configure().dataSource(dataSource).load().migrate();
        return dataSource;
    }
}