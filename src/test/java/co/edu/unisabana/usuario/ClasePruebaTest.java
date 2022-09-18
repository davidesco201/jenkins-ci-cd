package co.edu.unisabana.usuario;

import co.edu.unisabana.usuario.service.ClasePrueba;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClasePruebaTest {

    ClasePrueba servicio = new ClasePrueba();

    //TODOS LOS TEST, deben ser publicos y no retonar nada (void)
    /*
        dado que: given
        Cuando: when
        Entonces: then

     */
    //este es el unico lugar donde encontraran un metodo que comienza en mayuscula
    @Test //esto indica QUE ES UN TEST
    public void When_invoke_suma_Then_return_10() {
        int resultado = servicio.sumar(3, 7);
        //Hay dos maneras de validar el resultado
        //1. Asserts
        //2. Verify ()
        Assertions.assertEquals(10, resultado);
    }

    //SOLO AQUI EN ESTE lugar donde un metodo comienza en mayuscula y es snake case
    @Test
    public void Given_num_is_odd_When_invoke_obtenerMensaje_Then_resut_chao() {
        String resultado = servicio.obtenerMensaje(3);
        Assertions.assertEquals("chao", resultado);
    }

    @Test
    public void x() {
        boolean result = servicio.esMayorDe5(10);
        Assertions.assertTrue(result);
    }


    @Test
    public void y() {
        boolean result = servicio.esMayorDe5(2);
        Assertions.assertFalse(result);
    }

    @Test
    public void ppp() {
        Book book = new Book();
        book.setCategory("Violencia");
        int result = servicio.registrarLibro(book);

        Assertions.assertEquals(0, result);

    }


}
