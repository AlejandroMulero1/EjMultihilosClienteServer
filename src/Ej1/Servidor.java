package Ej1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;

public class Servidor {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // 1 - Crear DatagramSocket y le indicamos el puerto
            System.out.println("(Servidor) Creando socket...");
            socket = new DatagramSocket(49201);

            //Creacion del numero
            int numServidor=(int)(Math.random() *100)+1;
            System.out.println(numServidor);

            while (true) {
                //Crear array de bytes que actuará de buffer
                byte[] buffer = new byte[64];

                //Creación de datagrama con la clase DatagramPacket
                DatagramPacket datagramaEntrada = new DatagramPacket(buffer, buffer.length);



                //Recepción del datagrama mediante el método receive
                socket.receive(datagramaEntrada);

                new GestorProcesos(socket, datagramaEntrada, numServidor).start();
            }

        } catch (SocketException e) {
            System.out.println("Error al crear el Socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al recibir el paquete");
            e.printStackTrace();
        }
    }
}
