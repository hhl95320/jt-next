package com.jt.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.jt.vo.ImageVo;

@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService {
		
		@Value("${img.localDir}")
		 String localDir;
		@Value("${img.imgend}")
		String imgcheck1;//在提取到properties
		@Value("${img.imageUrl}")
		String imageUrl;
	@Override
	public ImageVo uploadFile(MultipartFile fileImage) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String dataPath=sdf.format( new Date())+"/";
		File file=new File(localDir+dataPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String filename = fileImage.getOriginalFilename();
		String imgend=filename.substring(filename.lastIndexOf("."));
		imgend=imgend.toLowerCase();//小写
		
		//校验--三种：字符串、数组与集合----------最为高效简便
		//第一种
		//String imgcheck1=".jpg.jpeg.bmp.png";//在提取到properties
		if(!imgcheck1.contains(imgend)) return ImageVo.fail();
		//第二种
		//String[] imgcheck= {".jpg",".jpeg",".bmp",".png"};
		//boolean tag=false;
		//for(String ck: imgcheck) {
		//	if(imgend.equals(ck)) tag=true; //校验成功继续
		//}
		//if(!tag) return ImageVo.fail();
		//获取长度

		//修改原图长度比例
		Image in=null;
		try {
			in = ImageIO.read(fileImage.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedImage bimg =  new BufferedImage(240, 135, BufferedImage.TYPE_INT_RGB);
		bimg.getGraphics().drawImage(in, 0, 0, 240, 135, null); 
		//原图长度
//		BufferedImage bimg = ImageIO.read(fileImage.getInputStream());
		int width = bimg.getWidth();
		int height = bimg.getHeight();
		
		String realName=UUID.randomUUID().toString()+imgend;
		File newfile = new File(localDir+dataPath+realName);
		try {
				fileImage.transferTo(newfile);
				return ImageVo.success(imageUrl+dataPath+realName,width,height);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return ImageVo.fail();
			}
		
				//return ImageVo.success("/upload/"+dataPath+realName,width,height);
	}




	
	
	
}
