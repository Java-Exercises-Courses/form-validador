import elementos.*;
import elementos.select.Opcion;
import validador.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputForm username = new InputForm("username");
        username.addValidador(new RequeridoValidador());

        InputForm password = new InputForm("clave", "password");
        password.addValidador(new RequeridoValidador())
                .addValidador(new LargoValidador(6, 12));

        InputForm email = new InputForm("email", "email");
        email.addValidador(new RequeridoValidador())
                .addValidador(new EmailValidador());

        InputForm edad = new InputForm("edad", "number");
        edad.addValidador(new NumeroValidador());

        TextareaForm experiencia = new TextareaForm("exp", 5, 9);

        SelectForm lenguaje = new SelectForm("lenguaje");
        lenguaje.addValidador(new NoNuloValidador());

        lenguaje.addOpcion(new Opcion("1", "Java"))
                .addOpcion(new Opcion("2", "Python"))
                .addOpcion(new Opcion("3", "JavaScript"))
                .addOpcion(new Opcion("4", "TypeScript").setSelected())
                .addOpcion(new Opcion("5", "PHP"));

        ElementoForm saludar = new ElementoForm() {
            @Override
            public String dibujarHtml() {
                return "<input disabled name='" + this.nombre
                        + "' value='" +this.valor+ "' >";
            }
        };

        saludar.setValor("Hola, campo deshabilitado");
        username.setValor("john.doe");
        password.setValor("0000");
        email.setValor("john.doe@mail.com");
        edad.setValor("21S");
        experiencia.setValor("Desarrollador Java");

        List<ElementoForm> elementos = Arrays.asList(
                username, password, email, edad, experiencia, lenguaje, saludar
        );

        elementos.forEach(elemento -> {
            System.out.println(elemento.dibujarHtml());
            System.out.println("<br>");
        });

        elementos.forEach(elemento -> {
            if(!elemento.esValido()) {
                elemento.getErrores().forEach(System.out::println);
            }
        });

    }
}