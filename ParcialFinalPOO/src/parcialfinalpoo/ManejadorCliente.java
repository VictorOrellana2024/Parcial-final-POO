package parcialfinalpoo;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

// Clase encargada de atender a un cliente en un hilo independiente
public class ManejadorCliente implements Runnable {
    private Socket socket;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            // Flujos para recibir y enviar datos al cliente
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
        ) {
            // Recibe nombre y número desde el cliente
            String nombre = entrada.readUTF();
            int numero = entrada.readInt();

            System.out.println("Cliente " + nombre + " conectado.");

            // Calcula el cuadrado del número recibido
            int cuadrado = numero * numero;

            // Obtiene la fecha y hora actual del servidor
            String fechaHora = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

            // Envía la respuesta al cliente
            salida.writeUTF("¡Bienvenido, " + nombre + "!");
            salida.writeInt(cuadrado);
            salida.writeUTF("Fecha y hora del servidor: " + fechaHora);

            System.out.println("Cliente " + nombre + " desconectado.");

        } catch (IOException e) {
            System.err.println("Error con cliente: " + e.getMessage());
        } finally {
            // Cierra el socket al finalizar
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Error al cerrar socket: " + ex.getMessage());
            }
        }
    }
}