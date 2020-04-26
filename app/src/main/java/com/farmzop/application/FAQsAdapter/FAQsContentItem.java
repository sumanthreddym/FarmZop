package com.farmzop.application.FAQsAdapter;

/**
 * Created by Gaurav on 27-02-2016.
 */
public class FAQsContentItem implements FAQsItem {

    private static final int TYPE=1;
    String question;
    String answer;
    boolean answerExpanded;


    int id;

    public FAQsContentItem( int id, String question,String answer) {
        this.answer = answer;
        this.id = id;
        this.question = question;
        answerExpanded=false;
    }
    public boolean isAnswerExpanded() {
        return answerExpanded;
    }

    public void setAnswerExpanded(boolean answerExpanded) {
        this.answerExpanded = answerExpanded;
    }

    public static int getTYPE() {
        return TYPE;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getId() {
        return id;
    }
}
