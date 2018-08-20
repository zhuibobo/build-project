package com.build4d.platform.convert.pdf;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.UUIDUtility;
import com.build4d.platform.convert.pdf.Impl.ITextConvertWordToPDF;
import com.build4d.platform.convert.pdf.Impl.MSOfficeConvertWordToPDF;
import com.build4d.platform.convert.pdf.Impl.OpenOfficeConvertWordToPDF;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ConvertToPDFManager {


    public final static String ROOTPATH="WEB-INF";
    //文件保存目录
    public final static String SAVEPATH = File.separator +ROOTPATH+File.separator+"upload"+File.separator+"ConvertToPDFSouce";

    private void CreateDir(String path) throws Build4DGenerallyException {
        File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new Build4DGenerallyException(0, "创建文件夹失败！路径" + path);
            }
        }
    }

    public String StartConvert(String recordId, String fileSaveLocaton, String extension, String custfileName, InputStream inputStream, String convertComponent, HttpServletRequest request, HttpServletResponse response) throws IOException, Build4DGenerallyException {
        String sourceFilePath=saveTempFile(recordId,custfileName,inputStream,request,response);
        IWordConvertToPDFComponent wordConvertToPDFComponent=null;
        if(convertComponent.equals("msOfficeWord")){
            wordConvertToPDFComponent=new MSOfficeConvertWordToPDF();
        }
        else if(convertComponent.equals("itext")){
            wordConvertToPDFComponent=new ITextConvertWordToPDF();
        }
        else if(convertComponent.equals("openOffice")){
            wordConvertToPDFComponent=new OpenOfficeConvertWordToPDF();
        }
        String pdfSavePath=wordConvertToPDFComponent.convertToPDF(sourceFilePath);
        //String pdfFileName=custfileName.substring(0,custfileName.lastIndexOf("."))+".pdf";
        return pdfSavePath;
        //return saveToDB(recordId,fileSaveLocaton,pdfSavePath,pdfFileName);
    }

    private String saveTempFile(String recordId,String fileName,InputStream inputStream, HttpServletRequest request, HttpServletResponse response) throws Build4DGenerallyException, IOException {
        String absolutedir = request.getSession().getServletContext().getRealPath(SAVEPATH) + File.separator + recordId;
        this.CreateDir(absolutedir);
        String tempPath= UUIDUtility.getUUID();
        absolutedir = absolutedir + File.separator + tempPath;
        this.CreateDir(absolutedir);
        String storeName=fileName;

        String relativelyPath = SAVEPATH + File.separator + recordId + File.separator + tempPath;
        String absolutePath = request.getSession().getServletContext().getRealPath(relativelyPath);
        File uploadFile = new File(absolutePath, storeName);
        FileUtils.copyInputStreamToFile(inputStream, uploadFile);

        String SourceFileFullPath=uploadFile.getAbsolutePath();
        return SourceFileFullPath;
    }

    private String saveToDB(String recordId,String fileSaveLocaton,String pdfSavePath,String fileName) throws IOException {
        return "";
    }
}
