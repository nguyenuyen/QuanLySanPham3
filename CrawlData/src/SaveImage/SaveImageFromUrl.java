package SaveImage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SaveImageFromUrl {
    public static String saveImage(String imageUrl, String imageFile) throws IOException {
        int indexname = imageUrl.lastIndexOf("/");
        if (indexname == imageUrl.length()) {
            imageUrl = imageUrl.substring(1, indexname);
        }
        indexname = imageUrl.lastIndexOf("/");
        String name = imageUrl.substring(indexname + 1, imageUrl.length());
        //System.out.println(name);

        URL url = new URL("https:" + imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(imageFile + name);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
        return name;
    }
}
