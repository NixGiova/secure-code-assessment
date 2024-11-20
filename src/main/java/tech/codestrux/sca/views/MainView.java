package tech.codestrux.sca.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import tech.codestrux.sca.models.Quiz;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PageTitle("SCA")
@PreserveOnRefresh
public class MainView extends VerticalLayout {

    public MainView(Quiz quiz) {
        quiz.getAnswers().clear();
        quiz.setName(null);
        add(new H1("SCA"));
        add(new Paragraph("ETC - Iniciemos con el test de ciberseguridad ..."));
        TextField nombre = new TextField("Cuál es tu nombre?");
        if (quiz != null) {
            nombre.setValue(quiz.getName());
        }
        Button button = new Button("Iniciar", e -> {
            if (quiz != null) {
                quiz.setName(nombre.getValue());
            }
            UI.getCurrent().navigate("group1");
        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickShortcut(Key.ENTER);
        addClassName("wider-content");
        add(nombre, button);
    }
}
