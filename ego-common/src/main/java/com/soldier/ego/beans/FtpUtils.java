package com.soldier.ego.beans;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-9-5 下午4:48
 * @Describe: ftp 工具类
 **/
public class FtpUtils {

    /**
     * 完成图片的上传，通过ftp将图片上传到图片服务器
     *      图片服务器：172.18.25.172，域名：image.taobao.com
     *      可通过cat /etc/hosts命令查看
     */
    public static void main(String[] args) {

        String hostname = "172.18.25.172";
        int port = 21;
        String username = "ftpuser";
        String password = "ftpuser";
        String pathname = "/home/ftpuser/jd";
        String remote = "demo.jpg";
        InputStream local = null;
    }

    public static boolean uploadFile(String hostname, int port, String username,
            String password, String pathname, String remote, InputStream local) {

        boolean flag = false;

        try {
            //创建FTPClient对象
            FTPClient client = new FTPClient();
            //建立和ftp服务器的链接
            client.connect(hostname, port);
            //登陆ftp服务器
            boolean login = client.login(username, password);
            client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            System.out.println(login);
            //设置上传的文件类型
            client.setFileType(FTP.BINARY_FILE_TYPE);
            //调用此方法解决假死状态（设置为被动模式：客户端通知服务端开通一个端口用来数据传输）
//            client.enterLocalPassiveMode();
            //切换工作目录，文件上传后保存到哪个目录
            if (!client.changeWorkingDirectory(pathname)) {
                if (client.makeDirectory(pathname)) {
                    client.changeWorkingDirectory(pathname);
                }
            }

            local = new FileInputStream("/home/soldier/图片/01.jpg");
            //实现文件上传
            client.storeFile(remote, local);
            //关闭流，释放资源
            local.close();

            //退出并断开连接
            client.logout();
            client.disconnect();

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
