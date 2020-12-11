package ClientFrogger.Model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadServer extends Observable implements Runnable{
    private Socket socket;
    private DataInputStream bufferIn = null;

    public ThreadServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            bufferIn = new DataInputStream(socket.getInputStream());

            String st = "";
            do {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(1000L)+100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    st = bufferIn.readUTF();
                    String [] player = st.split(";");
                    this.setChanged();
                    this.notifyObservers(player);
                    System.out.println(player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }while (!st.equals("FIN"));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
