package ncc.frontdesk.frontdesk.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ncc.frontdesk.frontdesk.Entity.filemetas;
import ncc.frontdesk.frontdesk.Utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-02 10:49
 */
@Controller
public class index {
    @Autowired
    RestTemplate restTemplate;

    @Value("${ncc.remoteServer}")
    String remoteServer;

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest req) throws Exception {
        KeyPair keyPair = RSAUtils.getKeyPair();
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPmuxz/Wsym+QMDtzwnkLubdT1zQ9HSU28MTGwt3RKxoPqIzrK/OzibT07yVVgcUzl09mMXaIfXJB07FkFStjesrOSgCYOozidnHl8PEsFkrYE3r2afQiIqy/HUxa+rwS64Pzly2SphhyCiXeJdGDiTSLp74hOVP8T7iGmM3vVkdAgMBAAECgYEAtTCvqVUeCVHmXdt1nqD3pFPADwQYEi4gWK0ZuDViIZc4tK65R4/VLHoWMslQd60SoLldj17fvCSZaAGY3spfrHVo30NfKY+sfnWjEExuWV92/0Eb5BVMZUn2Y9hUa7NaqLivktp/N6ihPjzuEupRMIkCW4Ok1w9exV4P6cmHwQUCQQD/T6rTuqvv+ebuk6dATez5rPDL28j2lT0aVeghNowg2S2TZJBR8BECxhfNGxIr2khPLwZL+K7BWjSiiqbuyglbAkEA+ls5Qsob7ZELxlL/Glhu6HdAFDEbtUkg5Uw+uqcsT2vOKGLpMeZ/ICHbZy+OoLZmcG8LT60zT168y9c9wpk45wJAP1z9T4jvrSI/bwyUIMB2RMzoAiAs8w282XFiqAOKqYhc6Ey4CK3k+uRA+fVzABEviv9nt6kjbfc+QuzYW0EnKwJAAxkb5Uqw+8iet66RP5c2kCPhpB5TdHPaN1wxLoghET6JR5CpyBdNHW2t1GIPRsfbDqshHbRL/UBz36XjOsRAowJAEapUuGRG5bMaoGKshrX+iq1ckihNeSUAHGBm+NZpEU+ZeQNlVu0BB6Y0LwKsijG+ojdXIKbdlkOJkp3Qltz1hA==";
        //随机产生字符串,并以此生成签名
        String sid = RandomcreateString();
        String sign = RSAUtils.sign(sid, RSAUtils.getPrivateKey(privateKey));
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SID", sid);
        headers.add("X-Signature", sign);
//        headers.add("publicKey", publicKey);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
//        String returncheck = restTemplate.postForObject("http://127.0.0.1:8080/checksas", requestEntity, String.class);
        String returncheck = restTemplate.postForObject(remoteServer + "/checksas", requestEntity, String.class);
        if (returncheck.equals("true")) {
//            String forObject = restTemplate.getForObject("http://127.0.0.1:8080/showTenMetasServlet", String.class);
            String forObject = restTemplate.getForObject(remoteServer + "/showTenMetasServlet", String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                List<filemetas> list = new ArrayList<>();
                filemetas[] filemetas = objectMapper.readValue(forObject, filemetas[].class);
                if (filemetas != null) {
                    for (int i = 0; i < filemetas.length; i++) {
                        list.add(filemetas[i]);
                    }
                    model.addAttribute("list", list);
                    System.out.println(filemetas);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            model.addAttribute("msg", "签名错误,无法访问正确页面");
            return "/error";
        }
        return "/upload";
    }

    @PostMapping("/createSign")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//       签名认证
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPmuxz/Wsym+QMDtzwnkLubdT1zQ9HSU28MTGwt3RKxoPqIzrK/OzibT07yVVgcUzl09mMXaIfXJB07FkFStjesrOSgCYOozidnHl8PEsFkrYE3r2afQiIqy/HUxa+rwS64Pzly2SphhyCiXeJdGDiTSLp74hOVP8T7iGmM3vVkdAgMBAAECgYEAtTCvqVUeCVHmXdt1nqD3pFPADwQYEi4gWK0ZuDViIZc4tK65R4/VLHoWMslQd60SoLldj17fvCSZaAGY3spfrHVo30NfKY+sfnWjEExuWV92/0Eb5BVMZUn2Y9hUa7NaqLivktp/N6ihPjzuEupRMIkCW4Ok1w9exV4P6cmHwQUCQQD/T6rTuqvv+ebuk6dATez5rPDL28j2lT0aVeghNowg2S2TZJBR8BECxhfNGxIr2khPLwZL+K7BWjSiiqbuyglbAkEA+ls5Qsob7ZELxlL/Glhu6HdAFDEbtUkg5Uw+uqcsT2vOKGLpMeZ/ICHbZy+OoLZmcG8LT60zT168y9c9wpk45wJAP1z9T4jvrSI/bwyUIMB2RMzoAiAs8w282XFiqAOKqYhc6Ey4CK3k+uRA+fVzABEviv9nt6kjbfc+QuzYW0EnKwJAAxkb5Uqw+8iet66RP5c2kCPhpB5TdHPaN1wxLoghET6JR5CpyBdNHW2t1GIPRsfbDqshHbRL/UBz36XjOsRAowJAEapUuGRG5bMaoGKshrX+iq1ckihNeSUAHGBm+NZpEU+ZeQNlVu0BB6Y0LwKsijG+ojdXIKbdlkOJkp3Qltz1hA==";
        //随机产生字符串,并以此生成签名
        String sid = RandomcreateString();
        String sign = RSAUtils.sign(sid, RSAUtils.getPrivateKey(privateKey));
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SID", sid);
        headers.add("X-Signature", sign);
//        headers.add("publicKey", publicKey);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        String returncheck = restTemplate.postForObject(remoteServer + "/checksas", requestEntity, String.class);
        if (returncheck.equals("true")) {
            System.out.println("上传签名验证成功");
            return "true";
        } else {
            System.out.println("上传签名验证失败");
            return "false";
        }
    }


    //    随机生成字符串
//    这里使用的是49-127之间的数字对应的ASCII字符
//    @Test
    public String RandomcreateString() {
        String s = "";
        for (int i = 0; i < 10; i++) {
            double random3 = Math.random();
            int random1 = (int) (random3 * 78);
            char random = (char) (random1 + 49);
//            System.out.println(random1 + 49);
            s = s + random;
        }
        System.out.println("ss>>>" + s);
        return s;
    }


}
