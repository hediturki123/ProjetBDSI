package photonum.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * <strong>Another Base 64 Hash</strong>
 * <p>Classe permettant l'encodage et le décodage d'entiers en base 64.</p>
 * <pre>"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ=+"</pre>
 * @author Alexis YVON
 */
public final class AB64H {

    private final static String TO_HASHSET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-=";

    private final static Map<Character,Long> FROM_HASHSET = TO_HASHSET.transform(s -> {
        Map<Character,Long> chars = new HashMap<>();
        long l = 0L;
        for (char c : s.toCharArray()) chars.put(c,l++);
        return chars;
    });

    public final static String encode(long n) {
        long l = n;
        String hash = "";
        do {
            hash = TO_HASHSET.charAt((int)(l%64)) + hash;
            l /= 64;
        } while (l != 0);
        return hash;
    }

    public final static long decode(String hashed) {
        String h = String.valueOf(hashed);
        long l = 0L;
        int hl = h.length();
        for (int i  = hl-1; i >= 0; i--)
            l += FROM_HASHSET.get(h.charAt(i))<<(6*(hl-i-1));
        return l;
    }

}
