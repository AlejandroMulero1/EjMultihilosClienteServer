package Ej1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

    public class GestorProcesos extends Thread {
        DatagramSocket socket;
        DatagramPacket datagramaEntrada;

        int numServidor;

        int numCliente;

        public GestorProcesos(DatagramSocket socket, DatagramPacket datagramaEntrada, int numServidor) {
            super();
            this.socket = socket;
            this.datagramaEntrada = datagramaEntrada;
            this.numServidor = numServidor;
        }

        @Override
        public void run() {
            realizarProceso();
        }

        public void realizarProceso() {
            // Recepción de mensaje del cliente
            String mensajeRecibido = new String(datagramaEntrada.getData()).trim();

            // Gestion del mensaje
            String resultadoComparacion="";

            if (Integer.parseInt(mensajeRecibido)==this.numServidor){
                //Caso numCliente==numServidor
                resultadoComparacion="Enhorabuena, has acertado el numero y has ganado";

            } else if(Integer.parseInt(mensajeRecibido)<this.numServidor){
                //Caso numCliente<numServidor
                resultadoComparacion="Has fallado, el numero del servidor es mayor";
            } else{
                //Caso numCliente>numServidor2
                resultadoComparacion="Has fallado, el numero del servidor es menor";
            }


            // Generación y envío de la respuesta
            byte[] mensajeEnviado = resultadoComparacion.getBytes();
            DatagramPacket packetSalida = new DatagramPacket(mensajeEnviado, mensajeEnviado.length,
                    datagramaEntrada.getAddress(), datagramaEntrada.getPort());
            try {
                socket.send(packetSalida);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

