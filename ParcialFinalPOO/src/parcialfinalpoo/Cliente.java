package parcialfinalpoo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try (
            Socket socket = new Socket(host, puerto);
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
        ) {
            System.out.print("Ingrese su nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese un número entero: ");
            int numero = scanner.nextInt();

            salida.writeUTF(nombre);
            salida.writeInt(numero);

            String mensajeBienvenida = entrada.readUTF();
            int cuadrado = entrada.readInt();
            String fechaHora = entrada.readUTF();

            System.out.println(mensajeBienvenida);
            System.out.println("El cuadrado de tu numero es: " + cuadrado);
            System.out.println("Fecha y hora del servidor: " + fechaHora);

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
