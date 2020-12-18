package coder.yqwh.study.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 非阻塞服务端
 * P.S. 一个线程只能同时处理多个请求
 *
 * @author Administrator
 * @time 2020/5/6 14:48
 */
public class NIOServerV1 {

    /**
     * 已经建立连接的集合
     */
    private static List<SocketChannel> channels = new ArrayList<>();

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
            // 低效的循环检查，会是NIO服务端的正确开发方式吗
            if (socketChannel != null) {
                System.out.println("收到新连接：" + socketChannel.getRemoteAddress());
                // 默认是阻塞的，一定要设置为非阻塞
                socketChannel.configureBlocking(false);
                channels.add(socketChannel);
            } else {
                // 没有新连接的情况下，就去处理现有连接数据，处理完的就删除掉
                for (Iterator<SocketChannel> iterator = channels.iterator(); iterator.hasNext(); ) {
                    SocketChannel channel = iterator.next();
                    ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                    if (channel.read(requestBuffer) == 0) {
                        //等于0，代表这个通道没有数据需要处理，那就待会再处理
                        continue;
                    }

                    while (channel.isOpen() && channel.read(requestBuffer) != -1) {
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
                    System.out.println("收到数据，来自：" + channel.getRemoteAddress());
                    // 响应结果 200
                    String response = "HTTP/1.1 200 OK\r\n" +
                            "Content-Length:11\r\n\r\n" + "hello world";
                    ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                    while (buffer.hasRemaining()) {
                        // 写入也是非阻塞
                        channel.write(buffer);
                    }
                    iterator.remove();
                }
            }
        }
    }
}
