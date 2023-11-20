package elementos;

import validador.LargoValidador;
import validador.Validador;
import validador.mensaje.MensajeFormateable;

import java.util.ArrayList;
import java.util.List;

abstract public class ElementoForm {
    protected String valor;
    protected String nombre;
    private List<Validador> validadores;
    private List<String> errores;

    public ElementoForm() {
        this.validadores = new ArrayList<>();
        this.errores = new ArrayList<>();
    }

    public ElementoForm(String nombre) {
        this();
        this.nombre = nombre;
    }

    public ElementoForm addValidador(Validador validador) {
        this.validadores.add(validador);
        return this;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<String> getErrores() {
        return errores;
    }

    public boolean esValido(){
        for (Validador validador: this.validadores) {
            if(!validador.esValido(this.valor)) {
                if (validador instanceof MensajeFormateable) {
                    this.errores.add((((MensajeFormateable) validador).getMensajeFormateado(nombre)));
                } else {
                    this.errores.add(String.format(validador.getMensaje(), nombre));
                }
            }
        }

        return this.errores.isEmpty();
    }

    abstract public String dibujarHtml();
}
