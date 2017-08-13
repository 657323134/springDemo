import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * TestQrcode.java
 */

public class QRCodeUtil {

    /**
     * 方法描述:
     *
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        Qrcode rcode = new Qrcode();
        // 这个值最大40，值越大可以容纳的信息越多，够用就行了
        rcode.setQrcodeVersion(20);

        byte[] content = "http://www.tpyjr.com.cn/generalize/regist?member=%2B4r3NNLycJsULV2zoOcjyA%3D%3D".getBytes("utf-8");
        BufferedImage bufImg = new BufferedImage(380, 380, BufferedImage.TYPE_INT_RGB); // 图片的大小
        String url1 = "http://localhost:8080/border/getOrderTmepData?tempIds=299&bid=101&phonemodel=dfsdf,12&contractnum=132556565651ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd325565656513";
        System.out.println(url1.length());

        String url2 = "http://localhost:8080/border/getOrderTmepData?tempIds=301&bid=101&phonemodel=ddfdsf&contractnum=13255565656";

        bufImg = QRCodeUtil.create_img(url1);
//        Graphics2D gs = bufImg.createGraphics();
//        gs.setBackground(Color.WHITE);
//        gs.clearRect(0, 0, 380, 380);
//        gs.setColor(Color.BLACK);

        // 输出内容> 二维码
//        if (content.length > 0 && content.length < 120) {
//            boolean[][] codeOut = rcode.calQrcode(content);
//            for (int i = 0; i < codeOut.length; i++) {
//                for (int j = 0; j < codeOut.length; j++) {
//                    if (codeOut[j][i]) {
//                        gs.fillRect(j * 10 + 5, i * 10 + 5, 10, 10);
//                    }
//                }
//            }
//        } else {
//            System.err.println("QRCode content bytes length = " + content.length + " not in [ 0,120 ]. ");
//        }
//        gs.dispose();
//        bufImg.flush();
        File imgFile = new File("E:\\test.png");
        // 生成二维码QRCode图片
        try {
            //图片格式
            ImageIO.write(bufImg, "png", imgFile);
            Desktop.getDesktop().open(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成好友邀请地址二维码
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static BufferedImage genQRCode(String url) throws Exception {
        Qrcode rcode = new Qrcode();
        // 这个值最大40，值越大可以容纳的信息越多，够用就行了
        rcode.setQrcodeVersion(5);
        byte[] content = url.getBytes("utf-8");
        BufferedImage bufImg = new BufferedImage(380, 380, BufferedImage.TYPE_INT_RGB); // 图片的大小
        Graphics2D gs = bufImg.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.clearRect(0, 0, 380, 380);
        gs.setColor(Color.BLACK);

        // 输出内容> 二维码
        if (content.length > 0 && content.length < 120) {
            boolean[][] codeOut = rcode.calQrcode(content);
            for (int i = 0; i < codeOut.length; i++) {
                for (int j = 0; j < codeOut.length; j++) {
                    if (codeOut[j][i]) {
                        gs.fillRect(j * 10 + 5, i * 10 + 5, 10, 10);
                    }
                }
            }
        } else {
            System.err.println("QRCode content bytes length = " + content.length + " not in [ 0,120 ]. ");
        }
        gs.dispose();
        return bufImg;
    }


    public static BufferedImage create_img(String url) {
        BufferedImage bi = null;
        try {
            Qrcode qrcode = new Qrcode();
            //错误修正容量
            //L水平   7%的字码可被修正
            //M水平   15%的字码可被修正
            //Q水平   25%的字码可被修正
            //H水平   30%的字码可被修正
            //QR码有容错能力，QR码图形如果有破损，仍然可以被机器读取内容，最高可以到7%~30%面积破损仍可被读取。
            //相对而言，容错率愈高，QR码图形面积愈大。所以一般折衷使用15%容错能力。
            qrcode.setQrcodeErrorCorrect('L');
            qrcode.setQrcodeEncodeMode('B');
            qrcode.setQrcodeVersion(20);
            byte[] d = url.getBytes("GBK");
            bi = new BufferedImage(139, 139, BufferedImage.TYPE_INT_RGB);
            // createGraphics
            Graphics2D g = bi.createGraphics();
            // set background
            g.setBackground(Color.WHITE);
            g.clearRect(0, 0, 139, 139);
            //设置二维码图片颜色
            g.setColor(Color.BLACK);

            if (d.length > 0 && d.length < 300) {
                boolean[][] b = qrcode.calQrcode(d);
                for (int i = 0; i < b.length; i++) {
                    for (int j = 0; j < b.length; j++) {
                        if (b[j][i]) {
                            g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
                        }
                    }
                }
            }

            g.dispose();
            bi.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bi;
    }
}
