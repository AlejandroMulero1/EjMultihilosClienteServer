package Ej1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        int puertoServidor = 49201;
        String nombreServidor = "localhost";
        Scanner sc = new Scanner(System.in);
        String nombre;
        try {
            // 1 - Obtención de la dirección del servidor usando el métod getByName de
            // InetAddress

            InetAddress direccionServidor = InetAddress.getByName(nombreServidor);

            // 2 - Creación del socket UDP
            socket = new DatagramSocket();

            //Gestion del programa
            boolean numeroAdivinado=false;
            do {
                //Generación del datagrama

                System.out.println("Dime el numero:");
                int num= sc.nextInt();
                String stringNum=Integer.toString(num).trim();

                byte[] bufferSalida = stringNum.getBytes();
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor,
                        puertoServidor);

                //Envío del datagrama a través de send
                socket.send(paqueteSalida);

                //Recepción de la respuesta
                byte[] bufferEntrada = new byte[64];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length, direccionServidor,
                        puertoServidor);
                socket.receive(paqueteEntrada);
                System.out.println("Mensaje recibido: " + new String(bufferEntrada).trim());

                //Valuacion de la respuesta
                String respuesta= new String(bufferEntrada);
                if(respuesta.split(",")[0].contains("Enhorabuena")){
                    numeroAdivinado=true;
                }
            } while (!numeroAdivinado);


            // 6 - Cierre del socket
            System.out.println("(Cliente): Cerrando conexión...");
            socket.close();
            System.out.println("(Cliente): Conexión cerrada.");

        } catch (SocketException e) {
            System.err.println("Error al conectar con el servidor.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("No se ha podido enviar o recibir el paquete");
            e.printStackTrace();
        }
    }
}
