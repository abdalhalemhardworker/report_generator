package com.Reports;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

public class LoatReport {

    Graphics2D graphics2d;
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    private int width, height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public LoatReport(int width, int height) {
        super();
        setHeight(height);
        setWidth(width);
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        graphics2d = image.createGraphics();
    }

    public void render() {
        graphics2d.setColor(Color.WHITE);
        graphics2d.fillRect(0, 0, width, height);
        BufferedImage backGroundImage = null;
        try {
            backGroundImage = ImageIO
                    .read(new File("C:\\Users\\C.m™ Lap\\DEV\\demo\\src\\main\\resources\\report_background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (backGroundImage != null)
            graphics2d.drawImage(backGroundImage, null, 0, 0);

        int rows = 5, cols = 3;
        int tableWidth = 1200, tableHeight = 500;
        int tableX = 500, tableY = 1000;
        int cellWidth = tableWidth / cols, cellHeight = tableHeight / rows;

        graphics2d.setColor(Color.black);
        graphics2d.setStroke(new BasicStroke(10));

        for (int row = 0; row <= rows; row++)
            graphics2d.drawLine(tableX, (row * cellHeight) + tableY, tableX + tableWidth, (row * cellHeight) + tableY);

        for (int col = 0; col <= cols; col++) {
            graphics2d.drawLine((col * cellWidth) + tableX, tableY, (col * cellWidth) + tableX, tableHeight + tableY);
        }

    }

    public boolean save(String filePath) throws Exception {
        File outPutFile = new File(filePath);
        if (!outPutFile.exists()) {
            outPutFile.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(outPutFile);
        ImageIO.write(image, "jpg", fileOutputStream);
        return true;
    }

}
