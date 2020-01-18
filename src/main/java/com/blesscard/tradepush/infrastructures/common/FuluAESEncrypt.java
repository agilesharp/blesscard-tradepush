package com.blesscard.tradepush.infrastructures.common;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class FuluAESEncrypt {
    public static final String ALGORITHM = "AES/ECB/PKCS7Padding";
    public static String Aes256Encode(String str, byte[] key) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            result = cipher.doFinal(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(Base64.getEncoder().encode(result));
    }

    public static String Aes256Decode(String str, byte[] key) {
        byte[] bytes = Base64.getDecoder().decode(str);
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decoded = cipher.doFinal(bytes);
            result = new String(decoded, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) throws Exception {
//        String content = "CD1000250201906121742434441";
//        String aesKey = "0a091b3aa4324435aab703142518a8f7";
//        String Result = FuluAESEncrypt.Aes256Encode(content, aesKey.getBytes());
//        System.out.println("加密結果:" + Result);
//        String value = FuluAESEncrypt.Aes256Decode(Result, aesKey.getBytes());
//        System.out.println("解密結果:" + value);
//    }
}