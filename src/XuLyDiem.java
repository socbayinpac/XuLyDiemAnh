import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static java.lang.Math.log;
import static java.lang.Math.pow;

public class XuLyDiem {

    public static void main(String[] args) {
        BufferedImage img = null;
        File f = null;
        //read image
        try {
            f = new File("bulldog.jpg");
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }
        //lay chieu cao chieu rong
        int rong = img.getWidth();
        int cao = img.getHeight();

        // duyet tung pixel de xu ly diem
        for (int y = 0; y < cao; y++) {
            for (int x = 0; x < rong; x++) {
                // lay cac mau cua pixel
                int p = img.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                //Bai1: pixel am ban
//                r = 255 - r;
//                g = 255 - g;
//                b = 255 - b;

                //Bai3: logarit
//                r = (int) log(1+r/255f)*255;
//                g = (int) log(1+g/255f)*255;
//                b = (int) log(1+b/255f)*255;

                //Bai4: power
                // anh sang hon khi gamma be hon 1
//                r = (int) (pow(r/255f,0.3)*255);
//                g = (int) (pow(g/255f,0.3)*255);
//                b = (int) (pow(b/255f,0.3)*255);

                // anh toi hon khi gamma lon hon 1
//                r = (int) (pow(r/255f,10)*255);
//                g = (int) (pow(g/255f,10)*255);
//                b = (int) (pow(b/255f,10)*255);

                // Bai 5: bit plane slicing bit 7,6
                r = r | 0x60; // or voi 0110 0000 de bat 2 bit  7 6
                g = g | 0x60;
                b = b | 0x60;


                //gan pixel tro lai
                p = (a << 24) | (r << 16) | (g << 8) | b;
                img.setRGB(x, y, p);

            }
        }

        //luu anh
        try {
            f = new File("output.jpg");
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}