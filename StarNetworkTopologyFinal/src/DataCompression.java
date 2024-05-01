import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class DataCompression {

    public static byte[] compress(String data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflater = new DeflaterOutputStream(outputStream);
        deflater.write(data.getBytes());
        deflater.close();
        return outputStream.toByteArray();
    }

    public static String decompress(byte[] compressedData) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedData);
        InflaterInputStream inflater = new InflaterInputStream(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inflater.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        inflater.close();
        return new String(outputStream.toByteArray());
    }
}

