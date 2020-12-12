package ClientFrogger.Model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadGameServer extends Observable implements Runnable {

    private Socket socket;
    private DataInputStream bufferIn = null;

    public ThreadGameServer(Socket socket) {
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
                    String [] playerReceived = st.split(";");
                    this.setChanged();
                    this.notifyObservers(playerReceived);
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
