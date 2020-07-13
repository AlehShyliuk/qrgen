import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите текст QR-кода:");
        String qrCodeText = reader.readLine();
        System.out.println("Введите имя файла (без расширения):");
        String filePath = "C:/qr/" + reader.readLine() + ".png";
        reader.close();

        File qrFile = new File(filePath);
        boolean mkdirs = qrFile.mkdirs();
        if (mkdirs) {
            generateQRCodeImage(qrCodeText, qrFile);
            System.out.println("Готово");
        } else System.out.println("Ошибка создания директории");
    }

    public static void generateQRCodeImage(String barcodeText, File qrFile) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ImageIO.write(image, "png", qrFile);
    }
}