package com.wh.util;




import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

public class ImageYasuoAndJieya {

	/**
	 * 对图像进行压缩
	 * @param image 图像
	 * @return 图像数据包装类
	 * @throws ImageFormatException
	 * @throws IOException 
	 * @throws IOException
	 */
//	public static ImageTransferObject encodeImage(BufferedImage image)
//	        throws ImageFormatException, IOException{
//	 
//	    ImageTransferObject ito = new ImageTransferObject();
//	 
//	    ByteArrayOutputStream out = new ByteArrayOutputStream();
//	    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//	 
//	    JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
//	    param.setQuality(0.5f, false);
//	    encoder.setJPEGEncodeParam(param);
//	    encoder.encode(image);
//	    ito.setImgbuf(out.toByteArray());
//	     
//	    return ito;
//	}
	 
	/**
	 * 对图像进行解压缩
	 * @param ito 图像数据包装类
	 * @return BufferedImage 图像
	 * @throws ImageFormatException
	 * @throws IOException 
	 * @throws IOException
	 */
//	public static BufferedImage decodeImage(ImageTransferObject ito)
//	        throws ImageFormatException, IOException{
//	 
//	    byte [] buf = ito.getImgbuf();
//	    ByteArrayInputStream in = new ByteArrayInputStream(buf);
//	    JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(in);
//	    BufferedImage image = decoder.decodeAsBufferedImage();
//	 
//	    return image;
//	}
}
