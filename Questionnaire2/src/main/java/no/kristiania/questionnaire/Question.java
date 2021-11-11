package no.kristiania.questionnaire;

public class Question {
    private String questionTitle;
    private String questionText;
    private String questionLowLabel;
    private String questionHighLabel;
    private long id;


    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionLowLabel() {
        return questionLowLabel;
    }

    public void setQuestionLowLabel(String questionLowLabel) {
        this.questionLowLabel = questionLowLabel;
    }

    public String getQuestionHighLabel() {
        return questionHighLabel;
    }

    public void setQuestionHighLabel(String questionHighLabel) {
        this.questionHighLabel = questionHighLabel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}