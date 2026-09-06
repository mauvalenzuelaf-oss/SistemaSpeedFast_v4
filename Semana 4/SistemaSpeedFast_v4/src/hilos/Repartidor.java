package hilos;

import gestores.ControladorDeEnvios;
import interfaces.Despachable;
import model.EstadoPedido;
import model.Pedido;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representa a un repartidor que realiza entregas
 * de forma concurrente.
 */
public class Repartidor implements Runnable {

    // Atributos
    private String nombre;
    private ArrayList<Pedido> pedidosAsignados;
    private ControladorDeEnvios controlador;
    private Random random;

    /**
     * Construye un repartidor con sus pedidos asignados.
     *
     * @param nombre nombre del repartidor
     * @param pedidosAsignados pedidos que debe entregar
     * @param controlador controlador de envíos
     */
    public Repartidor(
            String nombre,
            ArrayList<Pedido> pedidosAsignados,
            ControladorDeEnvios controlador
    ) {
        this.nombre = nombre;
        this.pedidosAsignados = pedidosAsignados;
        this.controlador = controlador;
        this.random = new Random();
    }

    /**
     * Ejecuta secuencialmente las entregas asignadas
     * al repartidor.
     */
    @Override
    public void run() {

        for (Pedido pedido : pedidosAsignados) {

            controlador.reservarPedido(pedido);

            System.out.println(
                    "[Repartidor: " + nombre
                            + "] Entregando "
                            + pedido.getClass().getSimpleName()
                            + " #"
                            + String.format(
                            "%03d",
                            pedido.getIdPedido()
                    )
                            + "..."
            );

            try {

                int pausa = 1000 + random.nextInt(1000);

                Thread.sleep(pausa);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(
                        "[Repartidor: " + nombre
                                + "] Entrega interrumpida."
                );

                return;
            }

            controlador.despacharPedido(
                    (Despachable) pedido
            );

            if (pedido.getEstado()
                    == EstadoPedido.DESPACHADO) {

                System.out.println(
                        "[Repartidor: " + nombre
                                + "] Pedido #"
                                + String.format(
                                "%03d",
                                pedido.getIdPedido()
                        )
                                + " entregado."
                );
            }
        }

        System.out.println(
                "[Repartidor: " + nombre
                        + "] Finalizó sus entregas."
        );
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Pedido> getPedidosAsignados() {
        return pedidosAsignados;
    }

    public void setPedidosAsignados(
            ArrayList<Pedido> pedidosAsignados
    ) {
        this.pedidosAsignados = pedidosAsignados;
    }
}