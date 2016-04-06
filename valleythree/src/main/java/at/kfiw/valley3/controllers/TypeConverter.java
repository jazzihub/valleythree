package at.kfiw.valley3.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialException;
import javax.swing.ImageIcon;

@ManagedBean
public class TypeConverter
{
	// private BufferedImage image;
	// private File file;

	public ImageIcon loadImage(byte[] poster) throws SerialException,
			SQLException
	{

		ImageIcon image;
		image = new ImageIcon(poster);
		return image;

		// try
		// {
		// image = ImageIO.read(new ByteArrayInputStream(poster));
		//
		// file = new File("MyFile.jpg");
		// ImageIO.write(image, "JPEG", file);
		//
		// } catch (IOException e)
		// {
		//
		// e.printStackTrace();
		// }
	}

	// public File getFile()
	// {
	// return file;
	// }
	//

}
