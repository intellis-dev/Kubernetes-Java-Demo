package cris.icms.ntes;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class l {
    private static void a(StringBuffer stringBuffer, byte b2) {
        stringBuffer.append("0123456789ABCDEF".charAt((b2 >> 4) & 15));
        stringBuffer.append("0123456789ABCDEF".charAt(b2 & 15));
    }

    public static String b(String str, String str2, String str3) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(str2.getBytes("UTF-8"));
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("UTF-8"), "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            instance.init(2, secretKeySpec, ivParameterSpec);
            return new String(instance.doFinal(Base64.decode(str3.getBytes(), 0)));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String c(String str, String str2, String str3) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(str2.getBytes("UTF-8"));
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("UTF-8"), "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            instance.init(1, secretKeySpec, ivParameterSpec);
            return new String(Base64.encode(instance.doFinal(str3.getBytes("UTF-8")), 0));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String d(String str) {
        return new String(e(str));
    }

    public static byte[] e(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String f(String str) {
        return g(str.getBytes());
    }

    public static String g(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b2 : bArr) {
            a(stringBuffer, b2);
        }
        return stringBuffer.toString();
    }
}