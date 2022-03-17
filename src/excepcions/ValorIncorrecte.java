package excepcions;

/**
 * Excepció que controla que no s'introdueixi un valor incorrecte (un preu o estoc negatius, coordenades impossibles, etc)
 *
 * @author Marc Janssens Rayo
 *
 * @version 2.0
 *
 */
public class ValorIncorrecte extends  Exception {

    public ValorIncorrecte() {
        super("Error: el/s valor/s introduïts són incompatibles.");
    }
}
