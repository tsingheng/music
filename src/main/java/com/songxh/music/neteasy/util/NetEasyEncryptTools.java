package com.songxh.music.neteasy.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Description:
 * Created by xiangheng.song on 2017/11/10 12:23.
 */
public class NetEasyEncryptTools {

    public static String encrypt(String text, String secKey) throws Exception {
        byte[] raw = secKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        // "算法/模式/补码方式"
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
    //字符填充
    public static String zfill(String result, int n) {
        if (result.length() >= n) {
            result = result.substring(result.length() - n, result.length());
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = n; i > result.length(); i--) {
                stringBuilder.append("0");
            }
            stringBuilder.append(result);
            result = stringBuilder.toString();
        }
        return result;
    }

//    public static void main(String[] args) throws Exception {
//        String key = "cd859f54539b24b7";
//        String text = "{\"ids\":\"[2001472]\",\"br\":128000,\"csrf_token\":\"\"}";
//        String modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
//        String nonce = "0CoJUm6Qyw8W8jud";
//        String pubKey = "010001";
//        //2次AES加密，得到params
//        String params = encrypt(encrypt(text, nonce), key);
//        StringBuffer stringBuffer = new StringBuffer(key);
//        //逆置私钥
//        key = stringBuffer.reverse().toString();
//        String hex = HexUtils.toHexString(key.getBytes());
//        BigInteger bigInteger1 = new BigInteger(hex, 16);
//        BigInteger bigInteger2 = new BigInteger(pubKey, 16);
//        BigInteger bigInteger3 = new BigInteger(modulus, 16);
//        //RSA加密计算
//        BigInteger bigInteger4 = bigInteger1.pow(bigInteger2.intValue()).remainder(bigInteger3);
//        String encSecKey= HexUtils.toHexString(bigInteger4.toByteArray());
//        //字符填充
//        encSecKey= zfill(encSecKey, 256);
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.set("Content-Type", "application/x-www-form-urlencoded");
//        requestHeaders.set("Cookie", "mail_psc_fingerprint=151fdb6a44b464611f399727aa11cb70; _ntes_nnid=2171cbc5ab8f7841d044452ac039a581,1502154280794; _ntes_nuid=2171cbc5ab8f7841d044452ac039a581; __gads=ID=bf8f7c24e429cc9a:T=1502161553:S=ALNI_Mai94LNwtEr5Xr0NgA07uCXXQ7F3g; UM_distinctid=15dbfceb81d2fd-0009b32cc1163d-6315107d-100200-15dbfceb81e36b; vjuids=985a258a2.15dbfcebe0a.0.46d7be82750aa; usertrack=c+xxClmc3UUnXts9CDZWAg==; P_INFO=tsing_heng@163.com|1507356374|0|mail163|11&15|fuj&1503038240&mail163#fuj&350100#10#0#0|&0||tsing_heng@163.com; NTES_CMT_USER_INFO=119683682%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B078zFy%7C%7Cfalse%7CdHNpbmdfaGVuZ0AxNjMuY29t; vjlast=1502161584.1507511591.11; vinfo_n_f_l_n3=35614ab5f7c8ccbf.1.2.1502161583643.1505289626666.1507511965470; JSESSIONID-WYYY=fNcqtnkOprXAZP1Qm5%2FUSevpkWrfw3ky3z9Txo5ZWB5lZkfB01VXV8n%5Ci8ZXlDGBwRdPSYKOgcikIpe9WOeRmjj9Hm%2B%5CCgskwkWlOKWBeXGOrgpPcNyHwmqpmzRbvilnSbhzJwizRYusnNZBgRbfu4zAVehOC8a3KgznPs7B3sFqFGfN%3A1510290012363; _iuqxldmzr_=32; playerid=96951047; __utma=94650624.1122302029.1510109315.1510201984.1510288213.6; __utmb=94650624.4.10.1510288213; __utmc=94650624; __utmz=94650624.1510109315.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic");
//        requestHeaders.set("Referer", "http://music.163.com/song?id=32785700");
//        String content = "params=" + URLEncoder.encode(params, "UTF-8") + "&encSecKey=" + URLEncoder.encode(encSecKey, "UTF-8");
//        HttpEntity<String> httpEntity = new HttpEntity<>(content, requestHeaders);
//        String result = restTemplate.postForObject("http://music.163.com/weapi/song/enhance/player/url?csrf_token=", httpEntity, String.class);
////        String result = restTemplate.postForObject("http://music.163.com/weapi/v1/resource/comments/R_SO_4_437859519/", httpEntity, String.class);
//
//        System.out.println(result);
//    }
//
//    private static void decode(String param, String encSecKey){
//        String pubKey = "010001";
//        String modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
//        BigInteger bigInteger4 = new BigInteger(HexUtils.fromHexString(encSecKey));
//        BigInteger bigInteger2 = new BigInteger(pubKey, 16);
//        BigInteger bigInteger3 = new BigInteger(modulus, 16);
//        System.out.println(bigInteger3 +":" + bigInteger2 + ":" + bigInteger4);
//    }
}
