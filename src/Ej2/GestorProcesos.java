package Ej2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GestorProcesos extends Thread{
    private Socket socket;
    private OutputStream os;
    private OutputStreamWriter osw;
    String mensajeRecibido;

    public GestorProcesos(String mensajeRecibido, Socket socket) {
        this.socket = socket;
        this.mensajeRecibido = mensajeRecibido;
    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void realizarProceso() throws IOException {
        os = this.socket.getOutputStream();
        osw = new OutputStreamWriter(os);

        BufferedWriter bw = new BufferedWriter(osw);
        osw.write(leerFichero(mensajeRecibido));
        bw.newLine();
        bw.flush();
    }
    public static String leerFichero(String direccion) {

        BufferedReader br = null;
        String[] contenido;
        String ip = "Direccion encontradan't";

        try {

            br = new BufferedReader(new FileReader("DNS.txt"));
            Scanner sc = new Scanner(br);

            while (sc.hasNext()) {
                contenido = sc.nextLine().split(":");
                System.out.println(contenido[0]);
                if (contenido[0].contains(direccion)) {
                    ip = contenido[1];
                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ip;
    }
}
