import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServerThread {

    private static final int PORT = 12345;

    public static void main(String[] args) {
        TCPEchoServerThread server = new TCPEchoServerThread();
        server.start();
    }

    public void start() {
        try (ServerSocket servSock = new ServerSocket(PORT)) {
            System.out.println("Server telah berjalan di komputer ini pada port " + PORT);

            while (true) {
                Socket clientSocket = servSock.accept();
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Tidak dapat memulai koneksi");
            System.exit(1);
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                // Handle client communication here
                // You can add your code to process client requests
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}