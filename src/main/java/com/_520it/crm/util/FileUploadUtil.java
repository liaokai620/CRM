package com._520it.crm.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;



public class FileUploadUtil {
	private FileUploadUtil(){}
	
	//约束文件上传类型
	private static List<String> ALLOW_UPLOAD_IMAGE_TYPE = Arrays.asList("xls","gif","bmp","png");
	
	public static void upload(HttpServletRequest req,String name){
		try {
			//检测是否有文件上传；
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if (!isMultipart) {
				return;//如果没有上传文件就结束方法；
			}
			//2创建一个磁盘文件单向工程
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置缓存大小
			factory.setSizeThreshold(1024*10);
			//3.创建Servlet上传的工具栏；
			ServletFileUpload upload = new ServletFileUpload(factory);
			//设置单个文件上传大小
			upload.setFileSizeMax(1024*50);
			//设置多个文件上传大小；
			upload.setSizeMax(1024*60);
			//4.通过Servlet文件上传工具类解析request，返回文件单向集合；
			List<FileItem> items = upload.parseRequest(req);
				for (FileItem item : items) {

					//判断是否是文件上传控件
					if (!item.isFormField()) {
						//把文件上传到应用制定文件夹中；
						String path = req.getSession().getServletContext().getRealPath("/uploads");//让代码来获取绝对路径下的文件夹；
						String ext =FilenameUtils.getExtension(item.getName());
						String namexls = name+ext;
						
						//判断文件类型再储存
						if (ALLOW_UPLOAD_IMAGE_TYPE.contains(ext.toLowerCase())) {
							item.write(new File(path,namexls));
						}else{

						throw new RuntimeException("请上传xls类型的文件；");
						}
					}

				}
		}catch (RuntimeException e) {
			
			throw new RuntimeException(e.getMessage(),e);
		}catch (FileSizeLimitExceededException e) {
			throw new RuntimeException("请上传单个文件小于10KB",e);
		}catch (SizeLimitExceededException e) {
			throw new RuntimeException("请上传多个文件小于60KB",e);
	}catch (Exception e) {
		e.printStackTrace();
	}
				
		
	} 
	
}

