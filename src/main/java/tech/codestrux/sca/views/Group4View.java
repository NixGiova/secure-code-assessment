package tech.codestrux.sca.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import tech.codestrux.sca.models.Quiz;

/**
 * The main view contains a button and a click listener.
 */
@Route("group4")
@PageTitle("Sección 4")
@PreserveOnRefresh
public class Group4View extends VerticalLayout {

    public Group4View(Quiz quiz) {
        add(new H2("Sección 4: Respuesta a Incidentes y Cumplimiento"));
        /* Pregunta 1 */
        add(new H4("10. ¿Qué acción se considera una buena práctica después de detectar una vulnerabilidad en tu aplicación?"));
        RadioButtonGroup<String> radioGroupQ10 = new RadioButtonGroup<>();
        radioGroupQ10.setItems("a) Ignorarla si no ha sido explotada",
                "b) Documentarla y solucionarla lo antes posible",
                "c) Comunicarla únicamente al equipo de desarrollo",
                "d) Deshabilitar temporalmente la aplicación");
        radioGroupQ10.addThemeVariants(RadioGroupVariant.MATERIAL_VERTICAL);
        radioGroupQ10.addClassName("vertical-radio-group");
        add(radioGroupQ10);
        /* Pregunta 2 */
        add(new H4("11. ¿Qué es el monitoreo continuo en ciberseguridad?"));
        RadioButtonGroup<String> radioGroupQ11 = new RadioButtonGroup<>();
        radioGroupQ11.setItems("a) Actualizar sistemas una vez al año",
                "b) Implementar un sistema de revisión periódica para identificar amenazas y actividades " +
                        "sospechosas en tiempo real",
                "c) Monitorear únicamente los accesos administrativos",
                "d) Realizar auditorías de código esporádicamente");
        radioGroupQ11.addThemeVariants(RadioGroupVariant.MATERIAL_VERTICAL);
        add(radioGroupQ11);
        /* Pregunta 3 */
        add(new H4("12. ¿Qué estándar es reconocido para gestionar riesgos en seguridad de la información?"));
        RadioButtonGroup<String> radioGroupQ12 = new RadioButtonGroup<>();
        radioGroupQ12.setItems("a) OWASP Top 10",
                "b) ISO/IEC 27001",
                "c) CIS Controls",
                "d) GDPR");
        radioGroupQ12.addThemeVariants(RadioGroupVariant.MATERIAL_VERTICAL);
        add(radioGroupQ12);
        if (quiz != null) {
            radioGroupQ10.setValue(quiz.getAnswers().get(10));
            radioGroupQ11.setValue(quiz.getAnswers().get(11));
            radioGroupQ12.setValue(quiz.getAnswers().get(12));
        }

        Binder<Quiz> binder = new Binder<>(Quiz.class);
        binder.forField(radioGroupQ10)
                .asRequired("")
                .bind(q -> quiz.getAnswers().get(10), (q, v) -> quiz.getAnswers().put(10, v));
        binder.forField(radioGroupQ11)
                .asRequired("")
                .bind(q -> quiz.getAnswers().get(11), (q, v) -> quiz.getAnswers().put(11, v));
        binder.forField(radioGroupQ12)
                .asRequired("")
                .bind(q -> quiz.getAnswers().get(12), (q, v) -> quiz.getAnswers().put(12, v));

        Button button = new Button("siguiente", e -> {
            if (quiz != null) {
                quiz.getAnswers().put(10, radioGroupQ10.getValue());
                quiz.getAnswers().put(11, radioGroupQ11.getValue());
                quiz.getAnswers().put(12, radioGroupQ12.getValue());
            }
            UI.getCurrent().navigate("group5");
        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickShortcut(Key.ENTER);
        button.setEnabled(false);
        binder.addStatusChangeListener(event -> button.setEnabled(binder.isValid()));
        add(button);
        addClassName("wider-content");
    }
}
