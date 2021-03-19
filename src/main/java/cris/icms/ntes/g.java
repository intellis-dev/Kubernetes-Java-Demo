package cris.icms.ntes;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class g {

    /* renamed from: a  reason: collision with root package name */
    private static String f10376a;

    public static String a(String str) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(str.getBytes());
        byte[] digest = instance.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : digest) {
            stringBuffer.append(Integer.toString((b2 & 255) + 256, 16).substring(1));
        }
        return stringBuffer.toString();
    }

    public static String b() {
        if (f10376a == null) {
            f10376a = new String(Base64.decode("aHR0cHM6Ly9lbnF1aXJ5LmluZGlhbnJhaWwuZ292LmluL2NyaXNudGVzL0FwcFNlcnZBbmQ=", 2));
        }
        return f10376a;
    }

    public static String c(String str) {
        try {
            return a(str.trim() + "EA3541BC74345DDA").toUpperCase();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String d(String str) {
        try {
            return a(str.trim() + "645fbc1e56e23365f2f3c204ae0899f6").toUpperCase() + "#" + l.f(l.c("8EA4DB2CC1EB3DC5", "7DC5EB3BB4DB6EA8", str.trim()).trim());
        } catch (Exception unused) {
            return "";
        }
    }

    public static String e() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        while (stringBuffer.length() < 16) {
            stringBuffer.append(Integer.toHexString(random.nextInt()));
        }
        return stringBuffer.toString().toUpperCase().substring(0, 16);
    }
}