package com.soldier.ego.beans;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create19-9-5下午4:48
 * @Describe:ftp工具类
 **/
public class FtpUtils {

    /**
     * 完成图片的上传，通过ftp将图片上传到图片服务器
     * 图片服务器：172.18.25.172、192.168.1.172，域名：image.taotao.com
     * 可通过cat /etc/hosts命令查看
     */
    public static void main(String[] args) {

        /**
         * 完成图片的上传，通过ftp将图片上传到图片服务器
         */
        String hostname="172.18.25.172";
//        String hostname="192.168.1.172";
        int port=21;
        String username="ftpuser";
        String password="ftpuser";
        String pathname="/home/ftpuser/jd";
        String remote="demo.jpg";
        InputStream local=null;
        uploadFile(hostname, port, username, password, pathname, remote,local);
    }

    /**
     * 上传文件到ftp服务器
     * @param hostname 服务器地址
     * @param port     服务器端口
     * @param username 服务器用户名
     * @param password 服务器密码
     * @param pathname 要上传文件的路径
     * @param remote   文件上传后的名字
     * @param local    要上传文件的输入流
     * @return 上传是否成功
     */
    public static boolean uploadFile(String hostname,
                                     int port, String username,
                                     String password, String pathname,
                                     String remote, InputStream local) {

        boolean flag = false;

        try {
            //创建FtpClient对象
            FTPClient client = new FTPClient();
            //建立和ftp服务器的链接
            client.connect(hostname, port);
            //登陆ftp服务器
            boolean login = client.login(username, password);
//            client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
//            System.out.println(login?"登陆成功":"登陆失败");
            //设置上传的文件的类型
            client.setFileType(FTP.BINARY_FILE_TYPE);
            //切换工作目录，文件上传后保存到那个目录
            if (!client.changeWorkingDirectory(pathname)) {
                if (client.makeDirectory(pathname)) {
                    client.changeWorkingDirectory(pathname);
                }
            }

//            local = new FileInputStream("/home/soldier/图片/01.jpg");
            //实现文件上传
            client.storeFile(remote, local);

            local.close();

            client.logout();
            client.disconnect();
            flag = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }
}
