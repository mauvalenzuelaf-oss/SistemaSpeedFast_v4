![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 🧠 Semana 4 - Actividad Formativa N° 3 - Desarrollo Orientado a Objetos II

## 👤 Autor del proyecto

* **Nombre completo:** Mauricio Francisco Valenzuela Fuentes
* **Carrera:** Analista Programador Computacional
* **Sede:** Online

---

## 📘 Descripción general del sistema

Este proyecto corresponde a la **Actividad Formativa de la Semana 4** de la asignatura **Desarrollo Orientado a Objetos II**.

Se trata de la cuarta etapa de **SistemaSpeedFast**, una aplicación desarrollada en Java que representa la gestión de distintos tipos de pedidos para la empresa SpeedFast, dedicada al reparto a domicilio.

En esta versión se mantienen los conceptos desarrollados durante las semanas anteriores y se incorpora **programación concurrente**, permitiendo que distintos repartidores realicen entregas de manera simultánea.

El proyecto utiliza principalmente:

* Clases abstractas.
* Herencia.
* Polimorfismo.
* Sobrescritura y sobrecarga de métodos.
* Interfaces.
* Encapsulamiento.
* Enumeraciones.
* `Runnable`.
* `Thread.sleep()`.
* `ExecutorService`.
* Ejecución concurrente de tareas.
* Manejo de `InterruptedException`.

El sistema continúa gestionando tres tipos de pedidos:

* **Pedido de comida:** considera un tiempo base de 15 minutos más 2 minutos por cada kilómetro de distancia.
* **Pedido de encomienda:** considera un tiempo base de 20 minutos más 1,5 minutos por cada kilómetro, ajustando el resultado a un valor entero mediante redondeo.
* **Pedido express:** considera un tiempo base de 10 minutos y agrega 5 minutos adicionales cuando la distancia supera los 5 kilómetros.

Además, esta versión incorpora distintos estados para los pedidos:

```text
CREADO
RESERVADO
DESPACHADO
CANCELADO
```

Esto permite controlar de manera más clara el avance de cada pedido y evitar transiciones de estado incorrectas.

La principal incorporación de esta semana es la clase `Repartidor`, que implementa la interfaz `Runnable`. Cada repartidor posee una lista de pedidos asignados y los procesa de manera secuencial, mientras varios repartidores pueden trabajar al mismo tiempo mediante un `ExecutorService`.

---

## 🧱 Estructura general del proyecto

```text
📁 SistemaSpeedFast_v4/
│
└── 📁 Semana 4/
    │
    └── 📁 SistemaSpeedFast_v4/
        │
        ├── 📁 src/
        │   ├── 📁 app/
        │   │   └── Main.java
        │   │
        │   ├── 📁 gestores/
        │   │   └── ControladorDeEnvios.java
        │   │
        │   ├── 📁 hilos/
        │   │   └── Repartidor.java
        │   │
        │   ├── 📁 interfaces/
        │   │   ├── Cancelable.java
        │   │   ├── Despachable.java
        │   │   └── Rastreable.java
        │   │
        │   └── 📁 model/
        │       ├── EstadoPedido.java
        │       ├── Pedido.java
        │       ├── PedidoComida.java
        │       ├── PedidoEncomienda.java
        │       └── PedidoExpress.java
        │
        ├── 📄 .gitignore
        ├── 📄 SistemaSpeedFast_v4.iml
        └── 📄 README.md
```

---

## 🧩 Organización por paquetes

El proyecto se encuentra organizado en cinco paquetes principales:

### 1. `app`

Contiene la clase encargada de iniciar y ejecutar el programa.

#### `Main.java`

Crea los distintos tipos de pedidos mediante referencias del tipo base `Pedido`, manteniendo el uso de polimorfismo desarrollado en las semanas anteriores.

También:

* Realiza asignaciones automáticas y manuales de repartidores.
* Muestra un resumen inicial de los pedidos.
* Crea seis pedidos en total.
* Distribuye dos pedidos a cada repartidor.
* Instancia tres repartidores.
* Utiliza `ExecutorService` para ejecutar los repartidores concurrentemente.
* Espera hasta que finalicen todas las entregas.
* Muestra el estado final y el historial de cada pedido.

---

### 2. `model`

Contiene las clases que representan los pedidos y sus estados.

#### `Pedido.java`

Clase abstracta base que concentra los atributos y comportamientos comunes:

* `idPedido`
* `direccionEntrega`
* `distanciaKm`
* `repartidorAsignado`
* `estado`
* `historial`

Contiene métodos comunes como:

* `mostrarResumen()`
* `asignarRepartidor()`
* `asignarRepartidor(String nombre)`
* `reservar()`

Además, declara el método abstracto:

```java
calcularTiempoEntrega()
```

También controla operaciones como la reserva, el despacho y la cancelación según el estado actual del pedido.

#### `EstadoPedido.java`

Enumeración que representa los posibles estados de un pedido:

```text
CREADO
RESERVADO
DESPACHADO
CANCELADO
```

Permite registrar el avance de los pedidos durante la simulación y evitar cambios de estado que no correspondan.

#### `PedidoComida.java`

Representa los pedidos de comida.

Su tiempo estimado se calcula considerando:

```text
15 minutos + 2 minutos por kilómetro.
```

También especializa la asignación de repartidores para este tipo de pedido.

#### `PedidoEncomienda.java`

Representa los pedidos de encomienda.

Su tiempo estimado se calcula considerando:

```text
20 minutos + 1,5 minutos por kilómetro.
```

El resultado se ajusta a un número entero mediante redondeo.

#### `PedidoExpress.java`

Representa los pedidos express.

Considera un tiempo base de:

```text
10 minutos.
```

Cuando la distancia supera los 5 kilómetros, se agregan 5 minutos adicionales.

---

### 3. `interfaces`

Contiene las interfaces utilizadas para separar responsabilidades específicas del sistema.

#### `Despachable.java`

Define el método:

```java
despachar()
```

#### `Cancelable.java`

Define el método:

```java
cancelar()
```

#### `Rastreable.java`

Define el método:

```java
verHistorial()
```

Las clases concretas de pedido implementan estas interfaces, permitiendo mantener separadas las distintas operaciones del sistema.

---

### 4. `gestores`

Contiene la clase encargada de coordinar las operaciones sobre los pedidos.

#### `ControladorDeEnvios.java`

Permite gestionar:

* Reserva de pedidos.
* Despacho.
* Cancelación.
* Consulta del historial.

El controlador trabaja mediante la clase base `Pedido` y las interfaces `Despachable`, `Cancelable` y `Rastreable`, evitando depender directamente de una clase concreta de pedido.

---

### 5. `hilos`

Contiene las clases asociadas a la ejecución concurrente del sistema.

#### `Repartidor.java`

Representa a un repartidor que realiza entregas.

La clase implementa:

```java
Runnable
```

Cada repartidor contiene:

* Su nombre.
* Una lista de pedidos asignados.
* Una referencia al controlador de envíos.

El método:

```java
run()
```

recorre los pedidos asignados de manera secuencial.

Para simular el tiempo necesario para realizar una entrega se utiliza:

```java
Thread.sleep()
```

con una pausa generada de manera aleatoria.

Aunque cada repartidor procesa sus propios pedidos en orden, los diferentes repartidores son ejecutados simultáneamente mediante `ExecutorService`.

---

## 🧵 Ejecución concurrente

Para administrar la ejecución de los repartidores se utiliza un grupo de tres hilos:

```java
ExecutorService executor =
        Executors.newFixedThreadPool(3);
```

Luego se ejecutan los tres repartidores:

```java
executor.execute(luis);
executor.execute(camila);
executor.execute(daniela);
```

Cada repartidor ejecuta su método `run()` de manera independiente.

Por esta razón, los mensajes de la consola pueden aparecer en un orden diferente en cada ejecución.

Por ejemplo:

```text
[Repartidor: Luis Díaz] Entregando PedidoComida #101...
[Repartidor: Daniela Tapia] Entregando PedidoEncomienda #103...
[Repartidor: Camila Soto] Entregando PedidoExpress #102...
```

Esto representa que los tres repartidores se encuentran realizando entregas al mismo tiempo.

Dentro de cada repartidor, sin embargo, los pedidos se siguen procesando secuencialmente.

---

## ⚠️ Manejo de interrupciones

El método `Thread.sleep()` puede generar una excepción de tipo:

```java
InterruptedException
```

Por esta razón, la clase `Repartidor` utiliza un bloque `try/catch`.

Cuando ocurre una interrupción se restaura el estado del hilo mediante:

```java
Thread.currentThread().interrupt();
```

y se finaliza la ejecución del repartidor de manera controlada.

La clase `Main` también maneja una posible interrupción mientras espera la finalización de las tareas ejecutadas mediante `ExecutorService`.

---

## 🔄 Estados de los pedidos

Los pedidos comienzan en estado:

```text
CREADO
```

Al comenzar su procesamiento pasan a:

```text
RESERVADO
```

y después de realizar la entrega pasan a:

```text
DESPACHADO
```

Por lo tanto, el flujo normal de un pedido entregado es:

```text
CREADO
   ↓
RESERVADO
   ↓
DESPACHADO
```

También existe el estado:

```text
CANCELADO
```

para aquellos pedidos que sean cancelados antes de su despacho.

El historial registra los principales eventos asociados a cada pedido.

---

## ⚙️ Instrucciones para clonar y ejecutar el proyecto

1. Clona el repositorio desde GitHub:

```bash
git clone https://github.com/mauvalenzuelaf-oss/SistemaSpeedFast_v4.git
```

2. Abre **IntelliJ IDEA**.

3. Selecciona la opción `Open`.

4. Dentro del repositorio clonado, busca la siguiente carpeta:

```text
Semana 4/SistemaSpeedFast_v4
```

5. Selecciona `SistemaSpeedFast_v4` como proyecto.

6. Verifica que el código fuente se encuentre dentro de la carpeta `src`.

7. Confirma que dentro de `src` se encuentren los paquetes:

```text
app
gestores
hilos
interfaces
model
```

8. Abre la clase principal:

```text
src/app/Main.java
```

9. Ejecuta el método `main()`.

---

## 🖥️ Ejemplo de funcionamiento

```text
=== SISTEMA SPEEDFAST - SEMANA 4 ===

[Pedido Comida]
Buscando repartidor con mochila térmica...
Repartidor asignado automáticamente: Luis Díaz

[Pedido Express]
Buscando repartidor cercano con disponibilidad inmediata...
Repartidor asignado automáticamente: Camila Soto

[Pedido Encomienda]
Validando peso y embalaje... OK
Repartidor asignado automáticamente: Daniela Tapia

=== RESUMEN DE PEDIDOS ===

PedidoComida #101
Dirección: Av. Italia 456
Distancia: 4 km
Estado: CREADO
Tiempo estimado: 23 minutos
Repartidor: Luis Díaz

PedidoExpress #102
Dirección: Av. Apoquindo 1500
Distancia: 7 km
Estado: CREADO
Tiempo estimado: 15 minutos
Repartidor: Camila Soto

PedidoEncomienda #103
Dirección: Av. Santa Rosa 567
Distancia: 7 km
Estado: CREADO
Tiempo estimado: 31 minutos
Repartidor: Daniela Tapia

=== INICIO DE ENTREGAS CONCURRENTES ===

Pedido #102 reservado correctamente.
Pedido #103 reservado correctamente.
Pedido #101 reservado correctamente.

[Repartidor: Luis Díaz] Entregando PedidoComida #101...
[Repartidor: Daniela Tapia] Entregando PedidoEncomienda #103...
[Repartidor: Camila Soto] Entregando PedidoExpress #102...

Pedido despachado correctamente.
[Repartidor: Camila Soto] Pedido #102 entregado.

Pedido despachado correctamente.
[Repartidor: Luis Díaz] Pedido #101 entregado.

Pedido despachado correctamente.
[Repartidor: Daniela Tapia] Pedido #103 entregado.

[Repartidor: Daniela Tapia] Finalizó sus entregas.
[Repartidor: Luis Díaz] Finalizó sus entregas.
[Repartidor: Camila Soto] Finalizó sus entregas.

=== ESTADO FINAL DE PEDIDOS ===

PedidoComida #101 - DESPACHADO
- PedidoComida #101 - pedido reservado
- PedidoComida #101 - entregado por Luis Díaz

PedidoExpress #102 - DESPACHADO
- PedidoExpress #102 - pedido reservado
- PedidoExpress #102 - entregado por Camila Soto

PedidoEncomienda #103 - DESPACHADO
- PedidoEncomienda #103 - pedido reservado
- PedidoEncomienda #103 - entregado por Daniela Tapia

[Main] Simulación finalizada.
```

> El orden de los mensajes correspondientes a los repartidores puede variar entre ejecuciones debido al funcionamiento concurrente de los hilos.

---

**Repositorio GitHub:**  
https://github.com/mauvalenzuelaf-oss/SistemaSpeedFast_v4

**Fecha de entrega:** 07/09/2026
