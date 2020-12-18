package coder.yqwh.study.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO服务端
 * @author Administrator
 * @time 2020/5/5 19:59
 */
public class BIOServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("服务器启动成功");
        while (!serverSocket.isClosed()) {
            //阻塞
            Socket request = serverSocket.accept();
            System.out.println("收到新连接：" + request.toString());
            try {
                InputStream inputStream = request.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                String msg;
                while ((msg = reader.readLine()) != null) {
                    if (msg.length() == 0) {
                        break;
                    }
                    System.out.println(msg);
                }
                 System.out.println("收到数据，来自：" + request.toString());
                //支持http协议响应
                OutputStream outputStream = request.getOutputStream();
                outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                outputStream.write("Content-Length:11\r\n\r\n".getBytes());
                outputStream.write("hello world".getBytes());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                request.close();
            }
        }

        serverSocket.close();
    }
}
