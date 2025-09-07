package cn.poile.ucs.auth.config;

import org.springframework.core.io.ClassPathResource;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
/**
 * @className: BaseUserDetails
 * @author: yzx
 * @date: 2025/8/21 6:24
 * @Version: 1.0
 * @description:
 */
public class ExtractPublicKeyToFile {
    public static void main(String[] args) {
        try {
            // 加载密钥库
            KeyStore ks = KeyStore.getInstance("JKS");
            ClassPathResource resource = new ClassPathResource("xz.keystore");
            ks.load(resource.getInputStream(), "xiaozuokeystore".toCharArray());

            // 获取证书和公钥
            String alias = "xzkey";
            Certificate cert = ks.getCertificate(alias);
            PublicKey publicKey = cert.getPublicKey();

            // 将公钥转换为 PEM 格式并写入 publickey.txt
            String publicKeyPem = "-----BEGIN PUBLIC KEY-----\n" +
                    Base64.getEncoder().encodeToString(publicKey.getEncoded()) + "\n" +
                    "-----END PUBLIC KEY-----";

            Files.write(Paths.get("publickey11.txt"), publicKeyPem.getBytes());
            System.out.println("公钥已提取到 publickey11.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}