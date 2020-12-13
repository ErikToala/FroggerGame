package ClientFrogger.Model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadClient extends Observable implements Runnable {

    private Socket socket;
    private DataInputStream bufferIn = null;
    public ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            bufferIn = new DataInputStream(socket.getInputStream());

            String st = "";
            String[] status = null;
            do {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(1000L)+100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    st = bufferIn.readUTF();
                    status = st.split(";");
                    this.setChanged();
                    this.notifyObservers(st);
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }while (!status[0].equals("ServerClosed"));
            System.out.println("Se cerro el hilo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
