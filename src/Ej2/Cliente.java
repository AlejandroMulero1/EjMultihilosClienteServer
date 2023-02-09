package Ej2;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            //Crear un socket de tipo cliente indicando IP y puerto del servidor
            Socket socket = new Socket("192.168.1.60", 49200);

            //Abrir flujos de lectura y escritura
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            //Obtengo los datos
            System.out.println("Introduce una direccion");
            String direccion = sc.nextLine();

            // Se convierte los datos para poder enviar un String y no un int
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            // se envía
            osw.write(direccion);
            bw.newLine();
            bw.flush();

            //Intercambiar datos con el servidor

            //Obtengo los mensajes
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);
            System.out.println("El servidor me envía el siguiente mensaje: " + br.readLine());

            //Cerrar flujos de lectura y escritura
            is.close();
            os.close();

            //Cerrar la conexión
            socket.close();

        } catch (UnknownHostException e) {
            System.err.println("No se encuentra el host especificado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Se ha producido un error en la conexión con el servidor.");
            e.printStackTrace();
        }
    }
    }

