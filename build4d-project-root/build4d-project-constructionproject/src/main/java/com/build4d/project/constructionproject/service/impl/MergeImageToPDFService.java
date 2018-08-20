package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.general.DBProp;
import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.PathUtility;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.project.constructionproject.service.IProFileService;
import com.build4d.project.dbaccess.dbentities.ArolEFileInfoEntity;
import com.build4d.project.dbaccess.dbentities.ArolFileInfoEntity;
import com.build4d.project.dbaccess.dbentities.ProFileEntity;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MergeImageToPDFService {
    //文件保存根目录
    private final static String SAVEPATH = DBProp.getValue("FileRoot");

    public PathUtility pathUtility;

    public IProFileService getProFileService() {
        return proFileService;
    }

    public void setProFileService(IProFileService proFileService) {
        this.proFileService = proFileService;
    }

    public IProFileService proFileService;

    public PathUtility getPathUtility() {
        return pathUtility;
    }

    public void setPathUtility(PathUtility pathUtility) {
        this.pathUtility = pathUtility;
    }

    private void ValidateToPDFEnable(List<ProFileEntity> proFileEntityList) throws Build4DGenerallyException {
        for (ProFileEntity proFileEntity : proFileEntityList) {
            if(proFileEntity.getFileExtension().toLowerCase().equals("jpg")||proFileEntity.getFileExtension().toLowerCase().equals("tif")){

            }
            else{
                throw new Build4DGenerallyException("只能合并jpg或tiff格式的文件");
            }
        }
    }

    public byte[] ToPDF(List<Integer> fileIdList) throws IOException, Build4DGenerallyException {
        List<ProFileEntity> proFileEntityList=new ArrayList<>();

        for (Integer id : fileIdList) {
            ProFileEntity proFileEntity=proFileService.getByPrimaryKey(id);
            proFileEntityList.add(proFileEntity);
        }

        ValidateToPDFEnable(proFileEntityList);

        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(bos);
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        // Initialize document
        Document document = GetSizeDocument(pdf, "A4");

        //URL url=pathUtility.getResource("/resources/fonts/simsun.ttc");
        URL url = this.getClass().getResource("/resources/fonts/simsun.ttc");
        if(url!=null) {
            PdfFont sysFont = PdfFontFactory.createFont(url.getPath().replaceFirst("/", "") + ",1", PdfEncodings.IDENTITY_H, false);//中文设置,解决特殊字符错误，
            document.setFont(sysFont);
        }

        //Image fox = new Image(ImageDataFactory.create("D:\\图片\\壁纸\\153_140718170754_1.jpg"));
        //Image fox1 = new Image(ImageDataFactory.create("D:\\图片\\壁纸\\06160432-1-12H10.jpg"));
        //document.add(new Paragraph("Roseindia"));
        //document.add(fox);
        //document.add(fox1);
        for (ProFileEntity proFileEntity : proFileEntityList) {
            String path=pathUtility.getServletContextRealPath(proFileEntity.getFileStorePath()+"/"+proFileEntity.getFileStoreName());
            Image img = new Image(ImageDataFactory.create(path));
            document.add(img);
        }

        document.close();
        return bos.toByteArray();
    }

    public byte[] ToPDFByArch(List<ArolEFileInfoEntity> eFileInfoEntities) throws IOException, Build4DGenerallyException {
        String allowType="jpg,tif,";
        for (ArolEFileInfoEntity fileEntity : eFileInfoEntities) {
            if(allowType.indexOf(fileEntity.getFiletype().toLowerCase()+",")>=0){

            }
            else{
                throw new Build4DGenerallyException("只能合并jpg或tiff格式的文件");
            }
        }

        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(bos);
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        // Initialize document
        Document document = GetSizeDocument(pdf, "A4");

        //URL url=pathUtility.getResource("/resources/fonts/simsun.ttc");
        URL url = this.getClass().getResource("/resources/fonts/simsun.ttc");
        if(url!=null) {
            PdfFont sysFont = PdfFontFactory.createFont(url.getPath().replaceFirst("/", "") + ",1", PdfEncodings.IDENTITY_H, false);//中文设置,解决特殊字符错误，
            document.setFont(sysFont);
        }

        //Image fox = new Image(ImageDataFactory.create("D:\\图片\\壁纸\\153_140718170754_1.jpg"));
        //Image fox1 = new Image(ImageDataFactory.create("D:\\图片\\壁纸\\06160432-1-12H10.jpg"));
        //document.add(new Paragraph("Roseindia"));
        //document.add(fox);
        //document.add(fox1);
        for (ArolEFileInfoEntity fileEntity : eFileInfoEntities) {
            String path=SAVEPATH+fileEntity.getFilepath();
            Image img = new Image(ImageDataFactory.create(path));
            document.add(img);
        }

        document.close();
        return bos.toByteArray();
    }

    private Document GetSizeDocument(PdfDocument pdf,String pageSize){
        //String pageSizeAttr= XMLUtility.getAttribute(vo.getExportNode(),"PageSize");
        if(pageSize.equals("")){
            return new Document(pdf, PageSize.A4);
        }
        else {
            if (pageSize.equals("A0")) {
                return new Document(pdf, PageSize.A0);
            } else if (pageSize.equals("A1")) {
                return new Document(pdf, PageSize.A1);
            } else if (pageSize.equals("A2")) {
                return new Document(pdf, PageSize.A2);
            } else if (pageSize.equals("A3")) {
                return new Document(pdf, PageSize.A3);
            } else if (pageSize.equals("A4")) {
                return new Document(pdf, PageSize.A4);
            } else if (pageSize.equals("A5")) {
                return new Document(pdf, PageSize.A5);
            }
        }
        return new Document(pdf,PageSize.A4);
    }
}
