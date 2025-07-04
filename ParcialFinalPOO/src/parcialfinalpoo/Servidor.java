package parcialfinalpoo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);

            while (true) {
                Socket socketCliente = servidor.accept(); // Acepta clientes
                ManejadorCliente manejador = new ManejadorCliente(socketCliente);
                Thread hilo = new Thread((Runnable) manejador);
                hilo.start(); // Lanza el hilo
            }
        }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
