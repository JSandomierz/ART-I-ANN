/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BinaryImage {
	private BufferedImage colorImg;
	private BufferedImage greyImg;
	private Boolean[][] binaryArray;
	
	BinaryImage(File imgFile) throws IOException{
		if(imgFile.canRead()){
			colorImg = ImageIO.read(imgFile);
			binaryArray = new Boolean[colorImg.getHeight()][colorImg.getWidth()];
			byte[] channel = new byte[] { (byte) 0, (byte) 0xFF };//black or white
			IndexColorModel icm = new IndexColorModel(1, 2, channel, channel, channel);
				  
			greyImg = new BufferedImage(colorImg.getWidth(), colorImg.getHeight(),BufferedImage.TYPE_BYTE_BINARY,icm);
			ColorConvertOp cco = new ColorConvertOp(colorImg.getColorModel().getColorSpace(),greyImg.getColorModel().getColorSpace(),null);
			cco.filter(colorImg, greyImg);
			for(int y=0; y<greyImg.getHeight(); y++){
				for(int x=0; x< greyImg.getWidth(); x++){
					//System.out.println("G("+x+","+y+")="+Integer.toHexString(greyImg.getRGB(x, y)<<8));
					binaryArray[y][x] = ((greyImg.getRGB(x, y)<<8)==0);
				}
			}
		}
	}
	
	BinaryImage(String src) throws IOException{
		this(new File(src));
	}
	
	Boolean[][] getBinaryArray(){
		return binaryArray;
	}
	
	int getWidth(){
		return greyImg.getWidth();
	}
	
	int getHeight(){
		return greyImg.getHeight();
	}
	
	Boolean getPixel(int x, int y){
		return binaryArray[y][x];
	}
	
	public String toString(){
		String tmp="";
		for(Boolean[] ba : binaryArray){
			for(Boolean b : ba){
				tmp+=((b)?1:0)+" ";
			}
			tmp+="\n";
		}
		return tmp;
	}
	
	public ImageIcon getImageIcon(){
		ImageIcon icon=new ImageIcon(greyImg);
		return icon;
	}
	
	public ImageIcon getScaledImageIcon(int w, int h){
		ImageIcon icon = new ImageIcon(getImageIcon().getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		return icon;
	}
	
	void show(){
		ImageIcon icon=new ImageIcon(greyImg);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(greyImg.getWidth()+40,greyImg.getHeight()+40);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
