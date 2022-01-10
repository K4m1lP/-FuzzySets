package visualization;

import utils.Rocket;
import utils.Vector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MyCanvas extends Canvas{

    private final Rocket rocket;

    private BufferedImage originalImage = null;

    public MyCanvas(Rocket rocket){
        this.rocket = rocket;
    }

    @Override
    public void paint(Graphics g){
        if(this.originalImage==null){
            try {
                originalImage = ImageIO.read(new File("./rocket01.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int h = 500;
        int w = 500;
        Vector mid = new Vector(w /2.0, h /2.0);
        int imageW = 500;
        int imageH = 500;
        BufferedImage curr = rotateImage(originalImage, -Math.toDegrees(rocket.getAngle())+90);
        g.drawImage(curr, (int)mid.getX()-imageW/2, (int)mid.getY()-imageH/2+10, this);
        Vector vec = rocket.getOrientation();
        g.drawLine((int)mid.getX(), (int)mid.getY(), (int)(mid.getX()+(vec.getX()*100)), (int)(mid.getY()-(vec.getY())*100));
        g.drawLine((int)mid.getX(), (int)mid.getY(), (int)(mid.getX()-(vec.getX()*100)), (int)(mid.getY()+(vec.getY())*100));
        g.drawLine(0, h /2, w, h /2);
        if(rocket.getLeftEngine())
            g.drawLine((int)(mid.getX()+(vec.getX()*100)), (int)(mid.getY()-(vec.getY())*100), (int)(mid.getX()+(vec.getX()*100))-100, (int)(mid.getY()-(vec.getY())*100));
        if(rocket.getRightEngine())
            g.drawLine((int)(mid.getX()+(vec.getX()*100)), (int)(mid.getY()-(vec.getY())*100), (int)(mid.getX()+(vec.getX()*100))+100, (int)(mid.getY()-(vec.getY())*100));
    }

    private static BufferedImage rotateImage(BufferedImage imageToRotate, double degree) {
        int widthOfImage = imageToRotate.getWidth();
        int heightOfImage = imageToRotate.getHeight();
        int typeOfImage = imageToRotate.getType();
        BufferedImage newImageFromBuffer = new BufferedImage(widthOfImage, heightOfImage, typeOfImage);
        Graphics2D graphics2D = newImageFromBuffer.createGraphics();
        graphics2D.rotate(Math.toRadians(degree), (widthOfImage) /2.0, (heightOfImage) / 2.0);
        graphics2D.drawImage(imageToRotate, null, 0, 0);
        return newImageFromBuffer;
    }
}

