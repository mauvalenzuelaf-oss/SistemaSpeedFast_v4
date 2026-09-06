package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

/**
 * Representa un pedido de comida dentro de SpeedFast.
 */
public class PedidoComida extends Pedido
        implements Despachable, Cancelable, Rastreable {

    private static final int TIEMPO_BASE = 15;
    private static final int MINUTOS_POR_KM = 2;

    /**
     * Construye un pedido de comida.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     * @param distanciaKm distancia en kilómetros
     */
    public PedidoComida(
            int idPedido,
            String direccionEntrega,
            int distanciaKm
    ) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    /**
     * Asigna automáticamente un repartidor con mochila térmica.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Comida]");
        System.out.println(
                "Buscando repartidor con mochila térmica..."
        );

        String nombre = "Luis Díaz";

        if (registrarRepartidor(nombre)) {
            System.out.println(
                    "Repartidor asignado automáticamente: " + nombre
            );
        }
    }

    /**
     * Asigna manualmente un repartidor al pedido de comida.
     *
     * @param nombre nombre del repartidor
     */
    @Override
    public void asignarRepartidor(String nombre) {
        System.out.println("[Pedido Comida]");
        System.out.println(
                "Validando disponibilidad de mochila térmica..."
        );

        if (registrarRepartidor(nombre)) {
            System.out.println(
                    "Repartidor asignado: " + nombre
            );
        }
    }

    /**
     * Calcula el tiempo estimado de entrega del pedido de comida.
     *
     * @return tiempo estimado en minutos
     */
    @Override
    public int calcularTiempoEntrega() {
        return TIEMPO_BASE
                + (MINUTOS_POR_KM * getDistanciaKm());
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
