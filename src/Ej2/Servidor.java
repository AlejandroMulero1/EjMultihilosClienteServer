package Ej2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            // 1 - Crear socket de tipo servidor y le indicamos el puerto
            ServerSocket servidor = new ServerSocket(49200);
            InputStream is;
            BufferedReader br;
            InputStreamReader ir;
            String mensajeRecibido;
            Socket peticion;

            while (true) {
                // se recoge el mensaje del cliente
                peticion = servidor.accept();

                // Se convierte el mensaje a String para leerlo
                is = peticion.getInputStream();

                ir = new InputStreamReader(is);
                br = new BufferedReader(ir);

                mensajeRecibido = br.readLine();
                new GestorProcesos(mensajeRecibido, peticion).start();
            }


        } catch (IOException e) {
            System.err.println("Ha habido algún error en la creación del Socket Servidor");
            e.printStackTrace();
        }
    }
}
