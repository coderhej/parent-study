package coder.yqwh.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 非阻塞服务端
 * P.S. 一个线程只能同时处理多个请求
 *
 * @author Administrator
 * @time 2020/5/6 14:48
 */
public class NIOServerV2 {

    public static void main(String[] args) throws Exception {
        // 创建网络服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 构建一个Selector选择器，并且将channel注册上去
        Selector selector = Selector.open();
        // 将serverSocketChannel注册到Selector选择器
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0, serverSocketChannel);
        // serverSocketChannel只能支持accept操作
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
        // 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8090));
        System.out.println("服务端启动成功");

        while (true) {
            // 不再轮询通道，改用下面轮询事件的方式.select方法有阻塞效果,直到有事件通知才会有返回
            selector.select();
            // 获取事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历查询结果
            Iterator<SelectionKey> iter = selectionKeys.iterator();
            while (iter.hasNext()) {
                // 被封装的查询结果
                SelectionKey key = iter.next();
                iter.remove();
                // 关注Accept事件
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.attachment();
                    // 将拿到的客户端连接通道，注册到selector上面
                    SocketChannel clientSocketChannel = server.accept();
                    clientSocketChannel.configureBlocking(false);
                    clientSocketChannel.register(selector, SelectionKey.OP_READ, clientSocketChannel);
                    System.out.println("收到新连接：" + clientSocketChannel.getRemoteAddress());
                }
                // 关注Read事件
                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.attachment();
                    ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                    while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
                        // 长连接情况下，需要手动判断数据有没有读取结束
                        if (requestBuffer.position() > 0) {
                            break;
                        }
                    }
                    // 如果没数据了，则不继续后面的处理
                    if (requestBuffer.position() == 0) {
                        continue;
                    }
                    // position为0，转换为读模式
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

            // 立即选择
            selector.selectNow();
        }

        // 问题: 此处一个selector监听所有事件,一个线程处理所有请求事件. 会成为瓶颈! 要有多线程的运用
    }
}
