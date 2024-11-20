package tech.codestrux.sca.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import tech.codestrux.sca.models.Quiz;

import java.util.Map;

/**
 * The main view contains a button and a click listener.
 */
@Route("result")
@PageTitle("Resultados")
@PreserveOnRefresh
@Slf4j
public class ResultView extends VerticalLayout {

    public ResultView(Quiz quiz) {
        add(new H2("Resultados"));
        Long score = countCorrectAnswers(quiz.getAnswers(), quiz.getCorrectAnswers());
        long scorePercentage = Math.round(((double) score / 15) * 100);
        add(new H3(score.toString() + "/15 -- " + scorePercentage + "%"));

        Span content = new Span();
        content.addClassName("content");
        content.add(createHeaders());
        quiz.getCorrectAnswers().forEach((key, value) -> content.add(createRows(key, value, quiz.getAnswers().get(key))));
        add(content);

        Button button = new Button("Finalizar", e -> {
            UI.getCurrent().navigate("");
        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickShortcut(Key.ENTER);
        addClassName("wider-content");
        add(button);
    }

    public Long countCorrectAnswers(Map<Integer, String> quiz, Map<Integer, String> correctAnswers) {
        return quiz.entrySet().stream()
                .filter(entry -> entry.getValue().equalsIgnoreCase(correctAnswers.get(entry.getKey())))
                .count();
    }

    public Component createHeaders(){
        Span span = new Span();
        span.addClassName("span-answers");
        span.add(new H5(""));
        span.add(new H5("Respuestas Correctas"));
        span.add(new H5("Respuestas Usuario"));
        return span;
    }

    public Component createRows(Integer key, String answer, String userAnswer){
        Span span = new Span();
        span.addClassName("span-answers");
        Paragraph pCorrectAnswer = new Paragraph(answer);
        Paragraph pUserAnswer = new Paragraph(userAnswer);
        pCorrectAnswer.addClassName("answer-1");
        pUserAnswer.addClassName(answer.equalsIgnoreCase(userAnswer) ? "answer-1" : "answer-2");
        span.add(new Paragraph(key.toString()+ ")"));
        span.add(pCorrectAnswer);
        span.add(pUserAnswer);
        return span;
    }


}
