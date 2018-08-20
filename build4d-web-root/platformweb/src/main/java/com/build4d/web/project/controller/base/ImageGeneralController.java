package com.build4d.web.project.controller.base;

import com.build4d.project.constructionproject.general.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Hashtable;

@Controller
@RequestMapping("project/imagegeneral")
public class ImageGeneralController {

    @RequestMapping(value = "buildurlqrcode")
    public void buildUrlQRCode(HttpServletRequest request, HttpServletResponse response,String Url) throws WriterException, IOException {

        //String text = "http://www.baidu.com"; // 二维码内容
        int width = 300; // 二维码图片宽度
        int height = 300; // 二维码图片高度
        String format = "gif";// 二维码的图片格式

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   // 内容所使用字符集编码

        BitMatrix bitMatrix = new MultiFormatWriter().encode(Url, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedOutputStream bos = null;
        bos = new BufferedOutputStream(response.getOutputStream());

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename="
                + URLEncoder.encode("qrcode.gif", "UTF-8").replaceAll("\\+", "%20"));
        MatrixToImageWriter.writeToStream(bitMatrix, format, bos);
        //bos.write(imageBuff);
        bos.close();
        //response.setHeader("Content-Length", String.valueOf(bitMatrix.ge));
    }
}
