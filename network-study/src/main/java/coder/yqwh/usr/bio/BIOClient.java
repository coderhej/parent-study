package coder.yqwh.usr.bio;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * BIO客户端
 * @author Administrator
 * @time 2020/5/5 20:10
 */
public class BIOClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8081);
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String msg = scanner.nextLine();
        outputStream.write(msg.getBytes(Charset.forName("utf-8")));
        scanner.close();
        socket.close();
    }
}
