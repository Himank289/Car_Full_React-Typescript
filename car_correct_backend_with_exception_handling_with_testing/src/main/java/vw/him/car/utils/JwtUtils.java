package vw.him.car.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JwtUtils {

    public static String decodeJWT(String jwt) {

        String[] jwtParts = jwt.split("\\.");

        if (jwtParts.length != 3) {
            throw new IllegalArgumentException("Invalid JWT format");
        }


        byte[] bytes = Base64.getUrlDecoder().decode(jwtParts[1]);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {

        String jwt = "eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MjA2NzY0MzYsImV4cCI6MTcyMDc2MjgzNiwiZW1haWwiOiJuaXJtYWxAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOiJST0xFX2FkbWluIn0.rigXgyQSQwXXdQ2Zh_sCMnK249eScWGq6mryz0A-kiUaWRvWU4lQ2imwcIVxQc3u";


        String decodedPayload = decodeJWT(jwt);
        System.out.println("Decoded JWT payload:");
        System.out.println(decodedPayload);
    }
}

