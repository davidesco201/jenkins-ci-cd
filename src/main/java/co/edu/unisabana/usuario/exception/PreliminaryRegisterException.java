package co.edu.unisabana.usuario.exception;

//Convertimos una clase de java en una excepcion PERSONALIZADA
//todas las excepciones personalizas deben ser hijas de RuntimeException
public class PreliminaryRegisterException extends RuntimeException {

    public PreliminaryRegisterException(String message) {
        super("No existe libro para actualizar con codigo " + message);
    }
}
