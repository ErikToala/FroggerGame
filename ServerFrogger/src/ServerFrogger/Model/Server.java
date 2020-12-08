package ServerFrogger.Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    ServerSocket servidor = null;
    Socket sc = null;
    DataInputStream in;
    DataOutputStream out;

    //puerto de nuestro servidor
    //final String HOST= "192.168.0.11";
    //int URL = Integer.parseInt(HOST);
    final int PUERTO = 5000;

    try {
        //Creamos el socket del servidor
        try {
            servidor = new ServerSocket(PUERTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Servidor iniciado");

        //Siempre estara escuchando peticiones
        while (true) {

            //Espero a que un cliente se conecte
            try {
                sc = servidor.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Cliente conectado");
            try {
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }



            //Leo el mensaje que me envia
            String mensaje = null;
            try {
                mensaje = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(mensaje);

            //Le envio un mensaje
            try {
                out.writeUTF("¿Estas listo para comenzar? \n ¡Mucha Suerte!");
            } catch (IOException e) {
                e.printStackTrace();
            }

                /*String notificacion = in2.readUTF();
                int noti = Integer.parseInt(notificacion);

                if(noti == 1){
                    out2.writeUTF("Ganaste\n Toma dos fichas más");
                }
                else if(noti == 0){
                    out2.writeUTF("Perdiste:(\n Una ficha menos");
                }*/

            //Cierro el socket
            sc.close();
            //System.out.println("Cliente desconectado");

        }

    } catch (IOException ex) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
    }



}


