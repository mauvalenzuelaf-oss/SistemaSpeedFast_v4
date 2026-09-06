package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

/**
 * Representa un pedido express dentro de SpeedFast.
 */
public class PedidoExpress extends Pedido
        implements Despachable, Cancelable, Rastreable {

    private static final int TIEMPO_BASE = 10;
    private static final int LIMITE_DISTANCIA = 5;
    private static final int TIEMPO_EXTRA = 5;

    /**
     * Construye un pedido express.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     * @param distanciaKm distancia en kilómetros
     */
    public PedidoExpress(
            int idPedido,
            String direccionEntrega,
            int distanciaKm
    ) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    /**
     * Asigna automáticamente un repartidor cercano.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Express]");
        System.out.println(
                "Buscando repartidor cercano "
                        + "con disponibilidad inmediata..."
        );

        String nombre = "Camila Soto";

        if (registrarRepartidor(nombre)) {
            System.out.println(
                    "Repartidor asignado automáticamente: " + nombre
            );
        }
    }

    /**
     * Asigna manualmente un repartidor al pedido express.
     *
     * @param nombre nombre del repartidor
     */
    @Override
    public void asignarRepartidor(String nombre) {
        System.out.println("[Pedido Express]");
        System.out.println(
                "Validando disponibilidad inmediata..."
        );

        if (registrarRepartidor(nombre)) {
            System.out.println(
                    "Repartidor asignado: " + nombre
            );
        }
    }

    /**
     * Calcula el tiempo estimado del pedido express.
     *
     * @return tiempo estimado en minutos
     */
    @Override
    public int calcularTiempoEntrega() {
        if (getDistanciaKm() > LIMITE_DISTANCIA) {
            return TIEMPO_BASE + TIEMPO_EXTRA;
        }

        return TIEMPO_BASE;
    }

    @Override
    public void despachar() {
        procesarDespacho();
    }

    @Override
    public void cancelar() {
        procesarCancelacion();
    }

    @Override
    public void verHistorial() {
        mostrarHistorialEventos();
    }
}