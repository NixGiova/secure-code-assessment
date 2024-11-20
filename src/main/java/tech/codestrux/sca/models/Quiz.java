package tech.codestrux.sca.models;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@SpringComponent
@VaadinSessionScope
@Getter
@Setter
public class Quiz {

    private String name;
    private Map<Integer, String> answers;
    private Map<Integer, String> correctAnswers;

    public Quiz() {
        this.answers = new HashMap<>();
        correctAnswers = new HashMap<>();
        correctAnswers.put(1, "b) Confidencialidad");
        correctAnswers.put(2, "b) Usar más de un método de verificación para acceder al sistema");
        correctAnswers.put(3, "b) Restringir a los usuarios únicamente a las funciones necesarias para realizar su trabajo");
        correctAnswers.put(4, "b) Falta de sanitización en la entrada del usuario");
        correctAnswers.put(5, "b) Codificar correctamente las entradas del usuario al renderizarlas");
        correctAnswers.put(6, "a) Control de acceso basado en roles (RBAC)");
        correctAnswers.put(7, "b) Usar un algoritmo hash con sal (salted hashing)");
        correctAnswers.put(8, "b) Un encabezado que especifica qué recursos pueden cargarse en una página web");
        correctAnswers.put(9, "b) Para minimizar la superficie de ataque");
        correctAnswers.put(10, "b) Documentarla y solucionarla lo antes posible");
        correctAnswers.put(11, "b) Implementar un sistema de revisión periódica para identificar amenazas y actividades sospechosas en tiempo real");
        correctAnswers.put(12, "b) ISO/IEC 27001");
        correctAnswers.put(13, "c) Contraseñas reutilizadas en otros sistemas");
        correctAnswers.put(14, "c) Ataque DDoS - Usar un balanceador de carga o WAF");
        correctAnswers.put(15, "b) Usar tokens únicos que se validen en cada solicitud");
    }

    public String getName() {
        if (name == null) {
            return "";
        }
        return name;
    }
}
