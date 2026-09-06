package gestores;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;
import model.Pedido;

/**
 * Controla las principales operaciones relacionadas
 * con la gestión de pedidos de SpeedFast.
 */
public class ControladorDeEnvios {

    /**
     * Reserva un pedido.
     *
     * @param pedido pedido que será reservado
     */
    public void reservarPedido(Pedido pedido) {
        pedido.reservar();
    }

    /**
     * Despacha un pedido.
     *
     * @param pedido pedido que será despachado
     */
    public void despacharPedido(Despachable pedido) {
        pedido.despachar();
    }

    /**
     * Cancela un pedido.
     *
     * @param pedido pedido que será cancelado
     */
    public void cancelarPedido(Cancelable pedido) {
        pedido.cancelar();
    }

    /**
     * Muestra el historial de un pedido.
     *
     * @param pedido pedido cuyo historial será mostrado
     */
    public void mostrarHistorial(Rastreable pedido) {
        pedido.verHistorial();
    }
}
