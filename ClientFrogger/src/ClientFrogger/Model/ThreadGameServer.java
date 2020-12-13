package ClientFrogger.Model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

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
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    st = bufferIn.readUTF();
                    this.setChanged();
                    this.notifyObservers(st);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }while (!st.equals("FIN"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
