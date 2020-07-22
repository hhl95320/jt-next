package com.jt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.service.FileService;
import com.jt.vo.ImageVo;


@Controller
@RequestMapping({"/pic/"})
public class FileController {
	
	@Autowired
	FileService fileService;
	/**
	 * 数据回传条件的要求
	 * @param dir
	 */
	@RequestMapping("upload")
	@ResponseBody
	public ImageVo upload(MultipartFile uploadFile) {//uploadFile固定定义的
		//参数接收上传文件，
		return  fileService.uploadFile(uploadFile);
	}
	

}


