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
@Route("group1")
@PageTitle("Sección 1")
@PreserveOnRefresh
public class Group1View extends VerticalLayout {

    public Group1View(Quiz quiz) {
        add(new H2("Sección 1: Principios Fundamentales de Ciberseguridad"));
        /* Pregunta 1 */
        add(new H4("1. ¿Qué principio de seguridad se enfoca en garantizar que solo usuarios autorizados accedan a" +
                " los datos sensibles?"));
        RadioButtonGroup<String> radioGroupQ1 = new RadioButtonGroup<>();
        radioGroupQ1.setItems("a) Disponibilidad", "b) Confidencialidad", "c) Integridad", "d) Autenticación");
        radioGroupQ1.addThemeVariants(RadioGroupVariant.MATERIAL_VERTICAL);
        radioGroupQ1.addClassName("vertical-radio-group");
        add(radioGroupQ1);
        /* Pregunta 2 */
        add(new H4("2. ¿Qué es la autenticación multifactor (MFA)?"));
        RadioButtonGroup<String> radioGroupQ2 = new RadioButtonGroup<>();
        radioGroupQ2.setItems("a) Verificar el correo electrónico del usuario",
                "b) Usar más de un método de verificación para acceder al sistema",
                "c) Implementar contraseñas más largas",
                "d) Activar la autenticación automática");
        radioGroupQ2.addThemeVariants(RadioGroupVariant.MATERIAL_VERTICAL);
        add(radioGroupQ2);
        /* Pregunta 3 */
        add(new H4("3. Seleccione la opción que describe correctamente el principio de \"privilegios mínimos\":"));
        RadioButtonGroup<String> radioGroupQ3 = new RadioButtonGroup<>();
        radioGroupQ3.setItems("a) Dar acceso completo a todos los usuarios del sistema",
                "b) Restringir a los usuarios únicamente a las funciones necesarias para realizar su trabajo",
                "c) Permitir a los usuarios administrativos desactivar los controles de seguridad",
                "d) Proporcionar acceso temporal sin registros");
        radioGroupQ3.addThemeVariants(RadioGroupVariant.MATERIAL_VERTICAL);
        add(radioGroupQ3);
        if (quiz != null) {
            radioGroupQ1.setValue(quiz.getAnswers().get(1));
            radioGroupQ2.setValue(quiz.getAnswers().get(2));
            radioGroupQ3.setValue(quiz.getAnswers().get(3));
        }

        Binder<Quiz> binder = new Binder<>(Quiz.class);
        binder.forField(radioGroupQ1)
                .asRequired("")
                .bind(q -> quiz.getAnswers().get(1), (q, v) -> quiz.getAnswers().put(1, v));
        binder.forField(radioGroupQ2)
                .asRequired("")
                .bind(q -> quiz.getAnswers().get(2), (q, v) -> quiz.getAnswers().put(2, v));
        binder.forField(radioGroupQ3)
                .asRequired("")
                .bind(q -> quiz.getAnswers().get(3), (q, v) -> quiz.getAnswers().put(3, v));

        Button button = new Button("siguiente", e -> {
            if (quiz != null) {
                quiz.getAnswers().put(1, radioGroupQ1.getValue());
                quiz.getAnswers().put(2, radioGroupQ2.getValue());
                quiz.getAnswers().put(3, radioGroupQ3.getValue());
            }
            UI.getCurrent().navigate("group2");
        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickShortcut(Key.ENTER);
        button.setEnabled(false);
        binder.addStatusChangeListener(event -> button.setEnabled(binder.isValid()));
        add(button);
        addClassName("wider-content");
    }
}
