import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalTime;

public class NonBlockingServerExample {
    public static void main(String[] args) {
        int port = 8090;
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            System.out.println("Started server and port " + port);

            while (true) {
                System.out.println(LocalTime.now());

                SocketChannel clientChannel = serverSocketChannel.accept();
                if (clientChannel == null) {
                    Thread.sleep(500);
                    continue;
                }

                System.out.println("Connected Client: " + clientChannel.getRemoteAddress());

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int bytesRead = clientChannel.read(buffer);

                if (bytesRead != -1) {
                    buffer.flip();
                    byte[] data = new byte[buffer.limit()];
                    buffer.get(data);
                    System.out.println("Received message: " + new String(data));
                }

                if (clientChannel.isOpen()) {
                    System.out.println("Closing Client: " + clientChannel.getRemoteAddress());
                    clientChannel.close();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
