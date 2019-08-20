package com.momo.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FtpClient {
    private FTPClient ftpClient;

    public FtpClient() {
    }
    public  FtpClient(String server, int port, String user,
                      String password, String path) {
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, password);
            if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){ //判断ftp服务器是否连通
                System.out.println("FtpServer连接失败！");
            }else {
                System.out.println("FtpServer连接成功！");
            }

            if (path!=null&&path.length() != 0) {
                ftpClient.changeWorkingDirectory(path);
            }
            ftpClient.setBufferSize(1024);//设置上传缓存大小
            ftpClient.setControlEncoding("UTF-8");//设置编码
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置文件类型
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void closeFtp() {
        try {
            if (ftpClient != null && ftpClient.isConnected())
                ftpClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Close Server Success");
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }
    public boolean uploadFile(String localFilePath, String remoteFileName)
            throws IOException {
        boolean flag = false;
        InputStream iStream = null;
        try {
            iStream = new FileInputStream(localFilePath);
            System.out.println(iStream);
            flag = ftpClient.storeFile(remoteFileName, iStream);
        } catch (IOException e) {
            flag = false;
            return flag;
        } finally {
            if (iStream != null) {
                iStream.close();
            }
        }
        System.out.println(flag);
        return flag;
    }
}
