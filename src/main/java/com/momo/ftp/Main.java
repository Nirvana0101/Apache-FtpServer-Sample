package com.momo.ftp;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FtpClient fc=new FtpClient("114.115.215.115",2121,
                "tzy","123456","");
        fc.uploadFile("E:/ftp/hello.txt","hello.txt");

        fc.closeFtp();
    }


}
