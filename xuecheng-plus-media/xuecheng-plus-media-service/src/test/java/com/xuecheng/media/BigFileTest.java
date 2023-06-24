package com.xuecheng.media;


import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//测试文件的拆分和合并
public class BigFileTest {


    //测试分块,把一个文件分成若干块
    @Test
    public void testChunk() throws IOException {
        //源文件

        File sourceFile = new File("C:\\Users\\chitoge\\Desktop\\testdata\\鹏华面试.mkv");
        //分块文件存储路径
        String chunkFilePath = "C:\\Users\\chitoge\\Desktop\\testdata\\chunk\\";
        File chunkFolder = new File(chunkFilePath);
        if(!chunkFolder.exists()) {
            chunkFolder.mkdir();
        }
        int n = 10; //
        //分块文件大小
        long chunkSize = 1024 * 1024 * n; //一个分块n MB
        //分块文件个数
        long chunkNum = (long) Math.ceil(sourceFile.length()*1.0/chunkSize);
        System.out.println("分块数目："+chunkNum);
        //缓冲区大小
        byte[] b = new byte[1024];
        //使用RandomAccessFile访问文件
        RandomAccessFile raf_read = new RandomAccessFile(sourceFile, "r");
        //分块
        for (int i = 0; i < chunkNum; i++) {
            //创建分块文件
            File file = new File(chunkFilePath + i);
            if(file.exists()){
                file.delete();
            }
            boolean newFile = file.createNewFile();
            if (newFile) {
                //向分块文件中写数据
                RandomAccessFile raf_write = new RandomAccessFile(file, "rw");
                int len = -1;
                while ((len = raf_read.read(b)) != -1) {
                    raf_write.write(b, 0, len);
                    if (file.length() >= chunkSize) {
                        break;
                    }
                }
                raf_write.close();
                System.out.println("完成分块"+i);
            }
        }
        raf_read.close();

    }

    //测试合并，将分块进行合并
    @Test
    public void testMerge() throws IOException {
        //块文件目录
        File chunkFolder = new File("C:\\Users\\chitoge\\Desktop\\testdata\\chunk\\");
        //原始文件
        File originalFile = new File("C:\\Users\\chitoge\\Desktop\\testdata\\鹏华面试.mkv");
        //合并文件
        File mergeFile = new File("C:\\Users\\chitoge\\Desktop\\testdata\\鹏华面试2.mkv");
        if (mergeFile.exists()) {
            mergeFile.delete();
        }
        //创建新的合并文件
        mergeFile.createNewFile();
        //用于写文件
        RandomAccessFile raf_write = new RandomAccessFile(mergeFile, "rw");
        //指针指向文件顶端
        raf_write.seek(0);
        //缓冲区
        byte[] b = new byte[1024];
        //分块列表
        File[] fileArray = chunkFolder.listFiles();
        // 转成集合，便于排序
        List<File> fileList = Arrays.asList(fileArray);
        // 从小到大排序
        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return Integer.parseInt(o1.getName()) - Integer.parseInt(o2.getName());
            }
        });
        //合并文件
        for (File chunkFile : fileList) {
            RandomAccessFile raf_read = new RandomAccessFile(chunkFile, "rw");
            int len = -1;
            while ((len = raf_read.read(b)) != -1) {
                raf_write.write(b, 0, len);

            }
            raf_read.close();
        }
        raf_write.close();

        //校验文件
        try (

                FileInputStream fileInputStream = new FileInputStream(originalFile);
                FileInputStream mergeFileStream = new FileInputStream(mergeFile);

        ) {
            //取出原始文件的md5
            String originalMd5 = DigestUtils.md5Hex(fileInputStream);
            //取出合并文件的md5进行比较
            String mergeFileMd5 = DigestUtils.md5Hex(mergeFileStream);
            if (originalMd5.equals(mergeFileMd5)) {
                System.out.println("合并文件成功");
            } else {
                System.out.println("合并文件失败");
            }

        }
    }
}
