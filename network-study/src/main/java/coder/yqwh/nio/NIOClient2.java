package coder.yqwh.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author Administrator
 * @time 2020/5/6 20:42
 */
public class NIOClient2 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 8090));
        while (!socketChannel.finishConnect()) {
            //没连接上，则一直等待
            Thread.yield();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String msg = scanner.nextLine();
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
        // 读取响应
        System.out.println("收到服务端响应");
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
        while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
            if (requestBuffer.position() > 0) {
                break;
            }
        }
        requestBuffer.flip();
        byte[] content= new byte[requestBuffer.limit()];
        requestBuffer.get(content);
        System.out.println(new String(content));
        scanner.close();
        socketChannel.close();
    }
}
