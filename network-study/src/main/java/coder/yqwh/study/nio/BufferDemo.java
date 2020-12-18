package coder.yqwh.study.nio;

import java.nio.ByteBuffer;

/**
 * @author Administrator
 * @time 2020/5/6 11:08
 */
public class BufferDemo {

    public static void main(String[] args) {
        // 构建一个byte字节缓冲区，容量是4
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        // 默认写入模式，查看3个重要指标
        System.out.println(String.format("容量：%s,位置：%s,限制：%s.", byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);
        System.out.println(String.format("容量：%s,位置：%s,限制：%s.", byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));
        // 转换为读取模式
        // 不调用flip方法，也可以读取数据，但是position记录读取的位置不对
        System.out.println("######开始读取");
        byteBuffer.flip();
        byte b1 = byteBuffer.get();
        System.out.println(b1);
        byte b2 = byteBuffer.get();
        System.out.println(b2);
        System.out.println(String.format("读取第二个字节后容量：%s,位置：%s,限制：%s.", byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));
        // 继续写入3字节，此时读模式下，limit=3，position=2,继续写入只能覆盖写入一条数据
        // clear方法是清除整个缓冲区。compact方法仅清除已阅读的数据，转为写入模式
        byteBuffer.compact();
        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 5);
        byteBuffer.put((byte) 6);
        System.out.println(String.format("最终情况容量：%s,位置：%s,限制：%s.", byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));
        // rewind方法重置position为0
        byteBuffer.rewind();
        byte a1 = byteBuffer.get();
        // mark方法标记position位置
        byteBuffer.mark();
        System.out.println(a1);
        byte a2 = byteBuffer.get();
        System.out.println(a2);
        // reset方法重置position为上次mark方法标记的位置
        byteBuffer.reset();
        byte a3 = byteBuffer.get();
        System.out.println(a3);
    }
}
