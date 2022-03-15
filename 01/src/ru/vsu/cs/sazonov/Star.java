package ru.vsu.cs.sazonov;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

public class Star {
    private int x;
    private int y;
    private int numberRays;
    private double bigRadius;
    private double smallRadius;

   public Star(){
       numberRays=5;
       bigRadius=100;
       smallRadius=50;
       x=(int)bigRadius/2;
       y=(int)bigRadius/2;
   }

    public Star (int x, int y,int numberRays, int bigRadius,int smallRadius){
        this.x=x;
        this.y=y;
        this.smallRadius=smallRadius;
        this.numberRays=numberRays;
        this.bigRadius=bigRadius;
    }

    public void move(int dx, int dy){
       x=x+dx;
       y=y+dy;
    }

    public void scaling(int scale){
        System.out.println(scale);
       bigRadius= (bigRadius*scale/100);
       smallRadius= ( smallRadius*scale/100);
        System.out.println(bigRadius);
        System.out.println(smallRadius);
    }

    public void paint(int width, int height, Graphics2D g2d){
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = g2d.getColor();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,width,height);
        g2d.setColor(Color.black);
        Path2D.Double path= new Path2D.Double();
        int m=(int)(Math.sqrt(numberRays));
        path.moveTo(x,y+bigRadius);
        double deltaAngle=360.0/numberRays;
        for (double angle=0;angle<360;angle+=deltaAngle){
            System.out.println(angle);
            path.lineTo(x+bigRadius*Math.sin(Math.toRadians(angle)),y+bigRadius*Math.cos(Math.toRadians(angle)));
            path.lineTo(x+smallRadius*Math.sin(Math.toRadians(angle+deltaAngle/2)),y+smallRadius*Math.cos(Math.toRadians(angle+deltaAngle/2)));
        }
        path.closePath();
        g2d.draw(path);
    }

}
