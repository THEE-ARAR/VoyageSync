package edu.famu.voyage;

import java.util.Random;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;

public class RandomString {
    private final Random random;

    private final char[] symbols;

    private final char[] buf;
    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase(Locale.ROOT);

    public static final String digits = "0123456789";

    public static final String alphanum = upper + lower + digits;

    public String nextString(int length) {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public RandomString(int length, Random random) {
        if (length < 1) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = alphanum.toCharArray();
        this.buf = new char[length];
    }

    public RandomString(int length) {
        this(length, new SecureRandom());
    }


}
