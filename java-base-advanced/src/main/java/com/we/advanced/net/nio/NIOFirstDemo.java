package com.we.advanced.net.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO实现文件复制功能
 * @author we
 * @date 2021-05-12 14:58
 **/
public class NIOFirstDemo {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(new File("E:/test.txt"));
            FileOutputStream fos = new FileOutputStream(new File("E:/test_cp.txt"));

            FileChannel fin = fis.getChannel();
            FileChannel fout = fos.getChannel();

            // 初始化缓冲区
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            // 读取数据到缓冲区
            fin.read(allocate);

            allocate.flip();

            // 将缓冲区的数据写出
            fout.write(allocate);
            allocate.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
