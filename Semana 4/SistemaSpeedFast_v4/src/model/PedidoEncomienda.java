package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

/**
 * Representa un pedido de encomienda dentro de SpeedFast.
 */
public class PedidoEncomienda extends Pedido
        implements Despachable, Cancelable, Rastreable {

    private static final int TIEMPO_BASE = 20;
    private static final double MINUTOS_POR_KM = 1.5;

    /**
     * Construye un pedido de encomienda.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     * @param distanciaKm distancia en kilómetros
     */
    public PedidoEncomienda(
            int idPedido,
            String direccionEntrega,
            int distanciaKm
    ) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    /**
     * Asigna automáticamente un repartidor
     * validando peso y embalaje.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Encomienda]");
        System.out.println(
                "Validando peso y embalaje... OK"
        );

        String nombre = "Daniela Tapia";

        if (registrarRepartidor(nombre)) {
            System.out.println(
                    "Repartidor asignado automáticamente: " + nombre
            );
        }
    }

    /**
     * Asigna manualmente un repartidor a la encomienda.
     *
     * @param nombre nombre del repartidor
     */
    @Override
    public void asignarRepartidor(String nombre) {
        System.out.println("[Pedido Encomienda]");
        System.out.println(
                "Validando peso y embalaje... OK"
        );

        if (registrarRepartidor(nombre)) {
            System.out.println(
                    "Repartidor asignado: " + nombre
            );
        }
    }

    /**
     * Calcula el tiempo estimado de entrega de la encomienda.
     *
     * @return tiempo estimado en minutos
     */
    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.round(
                TIEMPO_BASE
                        + (MINUTOS_POR_KM * getDistanciaKm())
        );
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
