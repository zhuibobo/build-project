package com.build4d.web.project.controller.project;

import com.build4d.base.dbaccess.general.DBProp;
import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.JsonUtility;
import com.build4d.base.tools.common.UUIDUtility;
import com.build4d.base.tools.common.search.GeneralSearchUtility;
import com.build4d.project.constructionproject.service.*;
import com.build4d.project.constructionproject.service.impl.ArolEFileInfoService;
import com.build4d.project.constructionproject.service.impl.ArolFileInfoService;
import com.build4d.project.constructionproject.service.impl.MergeImageToPDFService;
import com.build4d.project.dbaccess.dbentities.*;
import com.build4d.web.general.model.Build4DResponseVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

@Controller
@RequestMapping("project/engineering/catalogfile")
public class EngineeringFileController {
    private Lock lock = new ReentrantLock();
    //文件保存根目录
    private final static String SAVEPATH = DBProp.getValue("FileRoot");
    @Autowired
    private IProFileService proFileService;


    @Autowired
    IArolFileInfoService arolFileInfoService;


    @Autowired
    IProEngConfMateService proEngConfMateService;

    @Autowired
    IProOrganService proOrganService;

    @Autowired
    IArolEngBaseInfoService arolEngBaseInfoService;

    @Autowired
    IArolEFileInfoService arolEFileInfoService;

    @Autowired
    MergeImageToPDFService mergeImageToPDFService;

    @RequestMapping("uploadfile")
    @ResponseBody
    public Build4DResponseVo singleFile(@RequestParam("file") MultipartFile multipartFile, String outer_sid, String outer_table_name, String groupValue, HttpServletRequest request) throws IOException, Build4DGenerallyException {
        //FileUtils.writeByteArrayToFile(new File("e:/" + upload.getOriginalFilename()),upload.getBytes());
        //存储文件
        String fileId = UUIDUtility.getUUID();
        String relativelyPath = "";
        String storeName = "";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream inputStream = multipartFile.getInputStream();
        String fileName = multipartFile.getOriginalFilename();
        long fileSize = multipartFile.getSize();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        relativelyPath = File.separator + outer_table_name + File.separator + outer_sid;
        String absolutedir = SAVEPATH + relativelyPath;
        this.CreateDir(absolutedir);
        storeName = fileId + "." + extension;
        File uploadFile = new File(absolutedir, storeName);
        FileUtils.copyInputStreamToFile(inputStream, uploadFile);

        //存储文件信息
        B4DSession b4DSession = B4DSessionUtility.getSession();
        ProFileEntity proFileEntity = new ProFileEntity();
        proFileEntity.setFileSid(proFileService.getNextId());
        proFileEntity.setFileCreater(b4DSession.getUserName());
        proFileEntity.setFileName(fileName);
        proFileEntity.setFileStoreName(storeName);
        proFileEntity.setFileStorePath(relativelyPath);
        proFileEntity.setFileOrder(proFileService.getNextOrderNum());
        proFileEntity.setFileType("");
        proFileEntity.setFileSize(String.valueOf(fileSize));
        proFileEntity.setFileCreateTime(new Date());
        proFileEntity.setFileExtension(extension);
        proFileEntity.setFileUnitId(b4DSession.getOrganId());
        proFileEntity.setFileUnitName(b4DSession.getOrganName());
        proFileEntity.setFileGroup1(groupValue);
        proFileEntity.setFileGroup2("");
        proFileEntity.setOuterSid(outer_sid);
        proFileEntity.setOuterTableName(outer_table_name);
        proFileService.add(proFileEntity);

        return Build4DResponseVo.opSuccess();
    }

    @RequestMapping("getfilelist")
    @ResponseBody
    public Build4DResponseVo getFileList(String outer_sid, String outer_table_name) {
        List<ProFileEntity> proFileEntityList = new ArrayList<>();
        proFileEntityList = proFileService.getByOuter(outer_table_name, outer_sid);
        return Build4DResponseVo.success("", proFileEntityList);
    }

    private void CreateDir(String path) throws Build4DGenerallyException {
        File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new Build4DGenerallyException(0, "创建文件夹失败！路径" + path);
            }
        }
    }

    //上传文件列表
    @RequestMapping(value = "engineeringuploadfile", method = RequestMethod.GET)
    public ModelAndView engineeringUploadFile(int sid) throws IOException, ParseException {
        ModelAndView modelAndView = new ModelAndView("Project/Project/Engineering/CatalogFile/EngineeringUploadFiles");
        ArolFileInfoEntity arolFileInfoEntity = arolFileInfoService.getByPrimaryKey(sid);
        modelAndView.addObject("arolFileInfoEntity", JsonUtility.toObjectString(arolFileInfoEntity));
        return modelAndView;
    }

    //获取上传文件列表数据
    @RequestMapping(value = "getuploadfilelistdata", method = RequestMethod.GET)
    @ResponseBody
    public Build4DResponseVo getUploadFileListData(Integer page_size, Integer page_num, String search_condition) throws IOException, ParseException {
        Map<String, Object> searchMap = GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ArolEFileInfoEntity> eFileInfoEntities = arolEFileInfoService.getPage(page_num, page_size, searchMap);
        return Build4DResponseVo.success("获取成功", eFileInfoEntities);
    }


    //上传目录对应的文件
    @RequestMapping("uploadCatalogfile")
    @ResponseBody
    public Build4DResponseVo uploadCatalogfile(@RequestParam("file") MultipartFile multipartFile, Integer engSid, Integer catalogSid, String filedescription, HttpServletRequest request) throws IOException, Build4DGenerallyException {
        lock.lock();
        try {
            //存储文件
            String fileId = UUIDUtility.getUUID();
            String relativelyPath = "";
            String storeName = "";
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            InputStream inputStream = multipartFile.getInputStream();

            String originalFilename = multipartFile.getOriginalFilename();
            long fileSize = multipartFile.getSize();
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            relativelyPath = File.separator + "iams_arol_eng_base_info" + File.separator + engSid;
            String absolutedir = SAVEPATH + relativelyPath;
            this.CreateDir(absolutedir);
            storeName = fileId + "." + extension;
            File uploadFile = new File(absolutedir, storeName);
            FileUtils.copyInputStreamToFile(inputStream, uploadFile);

            //存储文件信息
            B4DSession b4DSession = B4DSessionUtility.getSession();
            ArolEFileInfoEntity eFileInfoEntity = new ArolEFileInfoEntity();
            eFileInfoEntity.setSid(arolEFileInfoService.getNextId());
            eFileInfoEntity.setEngSid(engSid);
            eFileInfoEntity.setFileSid(catalogSid);
            eFileInfoEntity.setFilename(originalFilename);
            eFileInfoEntity.setFiledescription(filedescription);
            eFileInfoEntity.setFilepath(relativelyPath + File.separator + storeName);
            eFileInfoEntity.setOrderRank(String.valueOf(arolEFileInfoService.getNextOrderNum()));
            eFileInfoEntity.setFiletype(extension);
            eFileInfoEntity.setFilelen(String.valueOf(fileSize));
            eFileInfoEntity.setCreatedBy(b4DSession.getUserName());
            eFileInfoEntity.setCreatedDt(new Date());
            eFileInfoEntity.setUpdatedBy(b4DSession.getUserName());
            eFileInfoEntity.setUpdatedDt(new Date());
            arolEFileInfoService.add(eFileInfoEntity);

            if (catalogSid != null) {
                ArolFileInfoEntity arolFileInfoEntity = arolFileInfoService.getByPrimaryKey(catalogSid);
                arolFileInfoEntity.setUpdatedDt(new Date());
                arolFileInfoEntity.setUpdatedBy(b4DSession.getUserName());
                Integer s = arolFileInfoEntity.getStatus();
                //目录审核状态
                if (s == null || s == 0)
                    s = 1;
                else if (s == 3)
                    s = 4;
                arolFileInfoEntity.setStatus(s);
                Integer n = arolFileInfoEntity.getUploadPageNums() == null ? 1 : arolFileInfoEntity.getUploadPageNums() + 1;
                arolFileInfoEntity.setUploadPageNums(n);
                arolFileInfoService.updateByKey(arolFileInfoEntity);
                out.println(n);
                return Build4DResponseVo.success("上传成功", arolFileInfoEntity);
            } else
                return Build4DResponseVo.error("上传失败");
        } catch (Exception ex) {
            return Build4DResponseVo.error("上传失败");
        }
        finally {
            lock.unlock();
        }
    }

    //flv视频文件
    @RequestMapping(value = "videoplay", method = RequestMethod.GET)
    public ModelAndView videoPlay(int sid) throws IOException, ParseException {
        ModelAndView modelAndView = new ModelAndView("Project/Project/Engineering/CatalogFile/VideoPlay");
        ArolEFileInfoEntity arolEFileInfoEntity = arolEFileInfoService.getByPrimaryKey(sid);
        modelAndView.addObject("arolFileInfoEntity", arolEFileInfoEntity);
        return modelAndView;
    }

    //删除上传的文件
    @RequestMapping(value = "deluploadfile")
    @ResponseBody
    public Build4DResponseVo delUploadFile(Integer sid, HttpServletRequest request) throws Exception {
        try {
            ArolEFileInfoEntity eFileInfoEntity = arolEFileInfoService.getByPrimaryKey(sid);
            String filePath = request.getSession().getServletContext().getRealPath("/") + eFileInfoEntity.getFilepath();

            ArolFileInfoEntity arolFileInfoEntity = arolFileInfoService.getByPrimaryKey(eFileInfoEntity.getFileSid());
            Integer n = arolFileInfoEntity.getUploadPageNums() - 1;
            n = n < 0 ? 0 : n;
            arolFileInfoEntity.setUploadPageNums(n);
            Integer s = arolFileInfoEntity.getStatus();
            if (n == 0 && arolFileInfoEntity.getStatus() == 1)
                s = 0;
            else if (n == 0 && arolFileInfoEntity.getStatus() == 3)
                s = 4;
            arolFileInfoEntity.setStatus(s);
            arolFileInfoService.updateByKey(arolFileInfoEntity);

            arolEFileInfoService.deleteByKey(sid);
            File file = new File(filePath);
            if (file.exists())
                file.delete();
            return Build4DResponseVo.success("删除成功", arolFileInfoEntity);
        } catch (Exception ex) {
            return Build4DResponseVo.error("删除失败！错误：" + ex.getMessage());
        }
    }

    //文件登记列表
    @RequestMapping(value = "cataloglistbyarch", method = RequestMethod.GET)
    public ModelAndView CatalogListByArch(int sid) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("Project/Project/Engineering/CatalogFile/CatalogFilesByArch");
        modelAndView.addObject("sid", sid);
        return modelAndView;
    }

    //文件登记列表
    @RequestMapping(value = "cataloglist", method = RequestMethod.GET)
    public ModelAndView engineeringCatalogList(int sid) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("Project/Project/Engineering/CatalogFile/EngineeringCatalogList");
        modelAndView.addObject("sid", sid);
        B4DSession session = B4DSessionUtility.getSession();
        ProOrganEntity organEntity = proOrganService.getByPrimaryKey(Integer.parseInt(session.getOrganId()));
        ArolEngBaseInfoEntity engEntity = arolEngBaseInfoService.getByPrimaryKey(sid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("engType", engEntity.getEngType());
        map.put("isdirectory", 1);
        List<ProEngConfMateEntity> catalogDirEntities = proEngConfMateService.searchByMap(map);
        modelAndView.addObject("catalogDirEntities", JsonUtility.toObjectString(catalogDirEntities));
        modelAndView.addObject("organEntity", JsonUtility.toObjectString(organEntity));
        return modelAndView;
    }

    //分页获取文件登记列表数据
    @RequestMapping(value = "getcataloglistdata")
    @ResponseBody
    public Build4DResponseVo getCatalogListData(Integer engSid, Integer engType, Integer page_size, Integer page_num, String search_condition) throws IOException, ParseException {
        Map<String, Object> searchMap = GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ArolFileInfoEntity> fileEntities = arolFileInfoService.getFileListByEng(engSid, engType, searchMap, page_num, page_size);
        return Build4DResponseVo.success("获取成功", fileEntities);
    }

    //分页获取文件登记列表数据
    @RequestMapping(value = "getcataloglistdatabypage")
    @ResponseBody
    public Build4DResponseVo getCatalogListDataByPage(Integer page_num, Integer page_size, String search_condition) throws IOException, ParseException {
        Map<String, Object> searchMap = GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ArolFileInfoEntity> fileEntities = arolFileInfoService.getPage(page_num, page_size, searchMap);
        return Build4DResponseVo.success("获取成功", fileEntities);
    }

    //获取文件登记列表数据
    @RequestMapping(value = "getcataloglistdata2")
    @ResponseBody
    public Build4DResponseVo getCatalogListData(String search_condition) throws IOException, ParseException {
        Map<String, Object> searchMap = GeneralSearchUtility.deserializationToMap(search_condition);
        List<ArolFileInfoEntity> fileEntities = arolFileInfoService.searchByMap(searchMap);
        return Build4DResponseVo.success("获取成功", fileEntities);
    }

    //条目的上传进度页面
    @RequestMapping(value = "fileuploadprogress", method = RequestMethod.GET)
    public ModelAndView fileUploadProgress(int engSid) throws IOException, ParseException {
        ModelAndView modelAndView = new ModelAndView("Project/Project/Engineering/CatalogFile/FileUploadProgress");
        ArolEngBaseInfoEntity engBaseInfoEntity = arolEngBaseInfoService.getByPrimaryKey(engSid);
        modelAndView.addObject("engSid", engSid);
        modelAndView.addObject("engName", engBaseInfoEntity.getEngName());
        return modelAndView;
    }

    //依据工程SID统计上传的目录
    @RequestMapping(value = "countuploadbyeng")
    @ResponseBody
    public Build4DResponseVo countUploadByEng(Integer engSid, String fileNo) throws IOException, ParseException {
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("engSid", engSid);
        searchMap.put("fileNo", fileNo);
        List<Map<String, Object>> countUpload = arolFileInfoService.countUploadByMap(searchMap);
        return Build4DResponseVo.success("获取成功", countUpload);
    }

    //更新单个文件登记的目录数据
    @RequestMapping(value = "savesingleengcatalogdata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveSingleEngCatalogData(@RequestBody ArolFileInfoEntity entity) throws Exception {
        try {
            B4DSession session = B4DSessionUtility.getSession();
            entity.setCheckDate(new Date());
            entity.setCheckPerson(session.getUserName());
            if (entity.getStatus().equals(2))
                entity.setLockStatus(1);
            arolFileInfoService.updateByKey(entity);
            return Build4DResponseVo.success("保存成功!");
        } catch (Exception ex) {
            return Build4DResponseVo.error(ex.getMessage());
        }
    }

    //复制文件登记的目录数据
    @RequestMapping(value = "copyengcatalogdata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo copyEngCatalogData(@RequestBody ArolFileInfoEntity entity) throws Exception {
        try {
            entity.setSid(arolFileInfoService.getNextId());
            String fno = entity.getFileNo();
            fno = arolFileInfoService.cloneNextId(entity.getEngSid(), fno);
            entity.setFileNo(fno);
            entity.setStatus(0);
            entity.setLockStatus(0);
            entity.setPdfPath(null);
            entity.setPdfFilename(null);
            entity.setPdfCreateDt(new Date());
            entity.setPdfCreateFlag(0);
            entity.setUploadPageNums(0);
            arolFileInfoService.add(entity);
            return Build4DResponseVo.saveSuccess();
        } catch (Exception ex) {
            return Build4DResponseVo.error("出错了：" + ex.getMessage());
        }
    }

    //批量更新文件登记的目录数据
    @RequestMapping(value = "saveengcataloglistdata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveEngCatalogListData(@RequestBody List<ArolFileInfoEntity> entities) throws Exception {
        try {
            arolFileInfoService.saveBatchSelective(entities);
            return Build4DResponseVo.saveSuccess();
        } catch (Exception ex) {
            return Build4DResponseVo.error(ex.getMessage());
        }
    }

    //目录详细
    @RequestMapping(value = "catalogdetail", method = RequestMethod.GET)
    public ModelAndView catalogDetail(int sid) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("Project/Project/Engineering/CatalogFile/CatalogDetail");
        ArolFileInfoEntity fileInfoEntity = arolFileInfoService.getByPrimaryKey(sid);
        modelAndView.addObject("fileInfoEntity", JsonUtility.toObjectString(fileInfoEntity));
        return modelAndView;
    }

    //保存目录详细页数据
    @RequestMapping(value = "savecatalogdetail", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveCatalogDetail(@RequestBody ArolFileInfoEntity fileInfoEntity) throws JsonProcessingException {
        arolFileInfoService.saveBySelective(fileInfoEntity.getSid(), fileInfoEntity);
        return Build4DResponseVo.saveSuccess();
    }

    //合并目录中上传的文件，生成PDF
    @RequestMapping(value = "bulidpdfbyarch", method = RequestMethod.GET)
    @ResponseBody
    public Build4DResponseVo bulidPDFByArch(Integer engSid, Integer fileSid, HttpServletRequest request) throws IOException, Build4DGenerallyException {
        try {
            Map<String, Object> searchMap = new HashMap<String, Object>();
            searchMap.put("fileSid", fileSid);
            List<ArolEFileInfoEntity> entities = arolEFileInfoService.searchByMap(searchMap);
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i).getFiletype().equals("flv"))
                    entities.remove(i);
            }
            byte[] data = mergeImageToPDFService.ToPDFByArch(entities);
            String relativelyPath = File.separator + "iams_arol_eng_base_info" + File.separator + engSid;
            String absolutedir = SAVEPATH + relativelyPath;
            this.CreateDir(absolutedir);
            String storeName = fileSid + ".pdf";
            FileUtils.writeByteArrayToFile(new File(absolutedir + File.separator + storeName), data);

            Date dt = new Date();
            B4DSession session = B4DSessionUtility.getSession();
            ArolFileInfoEntity arolFileInfoEntity = new ArolFileInfoEntity();
            arolFileInfoEntity.setSid(fileSid);
            arolFileInfoEntity.setPdfCreateFlag(1);
            arolFileInfoEntity.setPdfCreateDt(dt);
            arolFileInfoEntity.setPdfFilename(storeName);
            arolFileInfoEntity.setPdfPath(relativelyPath + File.separator + storeName);
            arolFileInfoEntity.setUpdatedDt(dt);
            arolFileInfoEntity.setUpdatedBy(session.getUserName());
            arolFileInfoService.updateByKeySelective(arolFileInfoEntity);
            return Build4DResponseVo.success("生成PDF成功！");
        } catch (Exception ex) {
            return Build4DResponseVo.error("生成失败！原因：" + ex.getMessage());
        }
    }


    //pdf合并测试
    @RequestMapping(value = "merPDF", method = RequestMethod.GET)
    @ResponseBody
    public Build4DResponseVo merPDF() throws IOException, Build4DGenerallyException {
        List<Integer> files = new ArrayList<>();
        files.add(4);
        files.add(5);
        files.add(6);
        files.add(7);
        byte[] data = mergeImageToPDFService.ToPDF(files);
        FileUtils.writeByteArrayToFile(new File("D:\\test1.pdf"), data);
        return Build4DResponseVo.saveSuccess();
    }

    //文件预览
    @RequestMapping(value = "downfile", method = RequestMethod.GET)
    public void downFile(HttpServletRequest request, String path, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String absolutePath = SAVEPATH + path;
        try {
            File file = new File(absolutePath);
            if (file.exists()) {
                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                bis = new BufferedInputStream(new FileInputStream(absolutePath));
                bos = new BufferedOutputStream(response.getOutputStream());

                URL u = new URL("file:///" + absolutePath);
                response.setContentType(u.openConnection().getContentType());
                response.setHeader("Content-disposition", "inline; filename="
                        + URLEncoder.encode(file.getName(), "UTF-8").replaceAll("\\+", "%20"));
                response.setHeader("Content-Length", String.valueOf(file.length()));

                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                bis.close();
                bos.close();
            } else {
                out.println("文件不存在！");
            }
        } catch (Exception ex) {
            out.println(ex.getMessage());
        }
    }
}
