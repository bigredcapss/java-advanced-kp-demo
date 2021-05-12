package com.we.advanced.net.nio;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * NIO的内存映射文件示例
 * @author we
 * @date 2021-05-12 15:50
 **/
public class NIOMmapDemo {
    public static void main(String[] args) throws Exception{

        FileChannel inChannel = FileChannel.open(Paths.get("D:/logo_cp.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:/logo.png"), StandardOpenOption.CREATE, StandardOpenOption.READ,
                StandardOpenOption.WRITE);

        // 内存映射文件操作
        MappedByteBuffer inMapBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 读取和写入操作
        byte[] bytes = new byte[inMapBuffer.limit()];
        inMapBuffer.get(bytes);
        outMapBuffer.put(bytes);

        // 关闭Channel
        inChannel.close();
        outChannel.close();

    }

}
