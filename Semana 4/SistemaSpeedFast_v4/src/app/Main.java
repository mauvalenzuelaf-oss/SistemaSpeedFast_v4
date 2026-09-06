package app;

import gestores.ControladorDeEnvios;
import hilos.Repartidor;
import interfaces.Rastreable;
import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal del sistema SpeedFast.
 * Ejecuta varios repartidores de forma concurrente.
 */
public class Main {

    public static void main(String[] args) {

        ControladorDeEnvios controlador =
                new ControladorDeEnvios();

        // Creación de pedidos mediante referencias de tipo Pedido

        Pedido pedido1 =
                new PedidoComida(
                        101,
                        "Av. Italia 456",
                        4
                );

        Pedido pedido2 =
                new PedidoExpress(
                        102,
                        "Av. Apoquindo 1500",
                        7
                );

        Pedido pedido3 =
                new PedidoEncomienda(
                        103,
                        "Av. Santa Rosa 567",
                        7
                );

        Pedido pedido4 =
                new PedidoEncomienda(
                        104,
                        "Av. Grecia 820",
                        5
                );

        Pedido pedido5 =
                new PedidoComida(
                        105,
                        "Av. Providencia 1200",
                        3
                );

        Pedido pedido6 =
                new PedidoExpress(
                        106,
                        "Av. Las Condes 600",
                        6
                );

        Pedido[] pedidos = {
                pedido1,
                pedido2,
                pedido3,
                pedido4,
                pedido5,
                pedido6
        };

        System.out.println(
                "=== SISTEMA SPEEDFAST - SEMANA 4 ==="
        );

        System.out.println();

        // Asignación automática

        pedido1.asignarRepartidor();
        pedido2.asignarRepartidor();
        pedido3.asignarRepartidor();

        System.out.println();

        // Asignación manual

        pedido4.asignarRepartidor("Luis Díaz");
        pedido5.asignarRepartidor("Camila Soto");
        pedido6.asignarRepartidor("Daniela Tapia");

        System.out.println();

        System.out.println(
                "=== RESUMEN DE PEDIDOS ==="
        );

        for (Pedido pedido : pedidos) {

            pedido.mostrarResumen();

            System.out.println(
                    "Tiempo estimado: "
                            + pedido.calcularTiempoEntrega()
                            + " minutos"
            );

            System.out.println(
                    "Repartidor: "
                            + pedido.getRepartidorAsignado()
            );

            System.out.println();
        }

        // Lista de pedidos de Luis

        ArrayList<Pedido> pedidosLuis =
                new ArrayList<>();

        pedidosLuis.add(pedido1);
        pedidosLuis.add(pedido4);

        // Lista de pedidos de Camila

        ArrayList<Pedido> pedidosCamila =
                new ArrayList<>();

        pedidosCamila.add(pedido2);
        pedidosCamila.add(pedido5);

        // Lista de pedidos de Daniela

        ArrayList<Pedido> pedidosDaniela =
                new ArrayList<>();

        pedidosDaniela.add(pedido3);
        pedidosDaniela.add(pedido6);

        // Creación de repartidores

        Repartidor luis =
                new Repartidor(
                        "Luis Díaz",
                        pedidosLuis,
                        controlador
                );

        Repartidor camila =
                new Repartidor(
                        "Camila Soto",
                        pedidosCamila,
                        controlador
                );

        Repartidor daniela =
                new Repartidor(
                        "Daniela Tapia",
                        pedidosDaniela,
                        controlador
                );

        System.out.println(
                "=== INICIO DE ENTREGAS CONCURRENTES ==="
        );

        // Ejecución concurrente

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        executor.execute(luis);
        executor.execute(camila);
        executor.execute(daniela);

        executor.shutdown();

        try {

            executor.awaitTermination(
                    1,
                    TimeUnit.MINUTES
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    "[Main] La ejecución fue interrumpida."
            );
        }

        System.out.println();

        System.out.println(
                "=== ESTADO FINAL DE PEDIDOS ==="
        );

        for (Pedido pedido : pedidos) {

            System.out.println(
                    pedido.getClass().getSimpleName()
                            + " #"
                            + String.format(
                            "%03d",
                            pedido.getIdPedido()
                    )
                            + " - "
                            + pedido.getEstado()
            );

            controlador.mostrarHistorial(
                    (Rastreable) pedido
            );

            System.out.println();
        }

        System.out.println(
                "[Main] Simulación finalizada."
        );
    }
}