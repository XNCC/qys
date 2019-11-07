package com.Servlet;

import com.Utils.RSAUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ncc
    验证签名
        成功返回true字符串
        失败返回false字符串
 */
@WebServlet("/checksas")
public class checkRSA extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String header = req.getHeader("X-SID");
        String X_Signature = req.getHeader("X-Signature");
//        String publicKey = req.getHeader("publicKey");
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD5rsc/1rMpvkDA7c8J5C7m3U9c0PR0lNvDExsLd0SsaD6iM6yvzs4m09O8lVYHFM5dPZjF2iH1yQdOxZBUrY3rKzkoAmDqM4nZx5fDxLBZK2BN69mn0IiKsvx1MWvq8EuuD85ctkqYYcgol3iXRg4k0i6e+ITlT/E+4hpjN71ZHQIDAQAB";
        try {
            boolean verify = RSAUtils.verify(header, RSAUtils.getPublicKey(publicKey), X_Signature);
            if(verify){
                System.out.println("解密成功");
                resp.getWriter().append("true");
            }else{
                System.out.println("解密失败");
                resp.getWriter().append("false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
