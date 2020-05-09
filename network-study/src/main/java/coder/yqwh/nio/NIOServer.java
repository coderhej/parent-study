package coder.yqwh.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 非阻塞服务端
 * P.S. 一个线程只能同时处理一个请求
 * @author Administrator
 * @time 2020/5/6 14:48
 */
public class NIOServer {

    public static void main(String[] args) throws Exception {
        // 创建网络服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8090));
        System.out.println("服务端启动成功");
        while (true) {
            // 获取新的tcp连接通道
            SocketChannel socketChannel = serverSocketChannel.accept();
            // tcp请求 读取/响应
            if (socketChannel != null) {
                System.out.println("收到新连接：" + socketChannel.getRemoteAddress());
                // 默认是阻塞的，一定要设置为非阻塞
                socketChannel.configureBlocking(false);
                ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                //while阻塞
                while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
                    // 长连接情况下，需要手动判断数据有没有读取结束
                    if (requestBuffer.position() > 0) {
                        break;
                    }
                }
                //如果没有数据了，则不继续后面的处理
                if (requestBuffer.position() == 0) {
                    continue;
                }
                requestBuffer.flip();
                byte[] content = new byte[requestBuffer.limit()];
                requestBuffer.get(content);
                System.out.println(new String(content));
                System.out.println("收到数据，来自：" + socketChannel.getRemoteAddress());
                // 响应结果 200
                String response = "HTTP/1.1 200 OK\r\n" +
                        "Content-Length:11\r\n\r\n" + "hello world";
                ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                while (buffer.hasRemaining()) {
                    // 写入也是非阻塞
                    socketChannel.write(buffer);
                }
            }
        }
    }
}
