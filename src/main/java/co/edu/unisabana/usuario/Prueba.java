package co.edu.unisabana.usuario;

public class Prueba {

    /*
        public void hacerAlgo() {
            //aca NO USA inyección de dependencia
            ClaseCliente cliente = new ClaseCliente();
            System.out.println(cliente.obtenerSaludo());
        }
     */

    //inyección de dependencia a través de método
    public void hacerAlgoConInyeccion(ClaseCliente cliente) {
        System.out.println(cliente.obtenerSaludo());
    }

    //inyección + inversión
    public void hacerAlgoConInyeccion(InterfazClientePrueba cliente) {
        System.out.println(cliente.obtenerSaludo());
    }

}
