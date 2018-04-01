package com.selfcreate.qingxie.controller.system;

import com.selfcreate.qingxie.bean.Msg;
import com.selfcreate.qingxie.exception.QingxieInnerException;
import com.selfcreate.qingxie.service.file.ExcelTransfer;
import com.selfcreate.qingxie.service.user.ClassInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author evans 2018/3/30 23:08
 */

@RequestMapping(value = "system/")
@Controller
public class DataImportController {
    final Logger logger=Logger.getLogger(this.getClass());

    @Autowired
    ExcelTransfer excelTransfer;
    ClassInfoService classInfoService;

    /**
     * FIXME:事务操作
     * 文件读写失败时是否回滚数据库操作？
     * @param file
     * @return
     */
    @RequestMapping(value = "/users/import")
    @ResponseBody
    public Msg importUserData(@RequestParam(value = "studentData", required = false) MultipartFile file) {
        logger.info("》》》正在导入用户数据");
        if(file==null||file.isEmpty()){
            logger.info("》》》file is null");
            return Msg.error("文件为空");
        }
        try {
            excelTransfer.excel2DB(file.getInputStream(), file.getOriginalFilename());
            logger.info("》》》import successfully");
            logger.info("》》》 saving file to local");
            excelTransfer.save2Local(file.getInputStream(), file.getOriginalFilename());
            return Msg.success("成功");
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return Msg.error("服务器磁盘读写失败");
        }catch (QingxieInnerException e){
            logger.error(e.getMessage());
            return Msg.error(e.getMessage());
        }catch (Exception e){
            logger.error(e.getMessage());
            return Msg.error("系统内部异常");
        }
    }
}
