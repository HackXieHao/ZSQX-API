package com.selfcreate.qingxie.service.file;

import com.selfcreate.qingxie.bean.user.ClassInfo;
import com.selfcreate.qingxie.bean.user.User;
import com.selfcreate.qingxie.dao.user.ClassInfoMapper;
import com.selfcreate.qingxie.dao.user.UserMapper;
import com.selfcreate.qingxie.exception.InvalidFileException;
import com.selfcreate.qingxie.exception.QingxieInnerException;
import com.selfcreate.qingxie.util.EncryptionUtils;
import com.selfcreate.qingxie.util.FileUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author evans 2018/3/30 13:12
 */
@Service
public class UserDataImport implements ExcelTransfer {
    final Logger logger=Logger.getLogger(this.getClass());
    @Autowired
    ClassInfoMapper classInfoMapper;
    @Autowired
    UserMapper userMapper;
    /**
     * 将excel中的数据导入到List中
     * 需将班级信息等字段转换为对应字段
     *
     * @param in
     * @param name
     * @return
     * @throws IOException
     */
    private Map<ClassInfo,List<User>> excel2Map(InputStream in,String name) throws IOException {
        Workbook workbook;
        if (name.endsWith(".xls")) {
            workbook = new HSSFWorkbook(in);
        } else if (name.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(in);
        } else {
            throw new InvalidFileException("文件类型错误");
        }

        Map<ClassInfo, List<User>> classUserMap = new HashMap<>();

        Sheet s = null;
        Row r = null;
        Cell c = null;
        ClassInfo classObj = null;
        // 遍历sheet
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            List<User> studentsList = new ArrayList<>();
            s = workbook.getSheetAt(i);
            if (s == null || "".equals(s.getSheetName())) {
                continue;
            }
            // 遍历row---》遍历每个user

            for (int j = 0; j < s.getLastRowNum(); j++) {
                // 出去前三行非user数据
                if (j == 1 || j == 2 || j == 3) {
                    continue;
                }
                r = s.getRow(j);
                // 若当前行为空/当前行的第二个cell为空或者值为空则跳过
                if (r == null || r.getCell(1) == null || "".equals(r.getCell(1).getStringCellValue())) {
                    continue;
                }
                User stuObj = new User(
                        r.getCell(1).getStringCellValue().trim(),
                        r.getCell(2).getStringCellValue().trim(),
                        EncryptionUtils.encryptPassword(r.getCell(2).getStringCellValue().trim()));
                // 每个班级保存团支书名字
                if ("团支书".equals(r.getCell(4).getStringCellValue().trim())) {
                    classObj = new ClassInfo(s.getSheetName().trim(), stuObj.getStudentId());
                }
                studentsList.add(stuObj);
            }
            classUserMap.put(classObj, studentsList);
        }
        return classUserMap;
    }

    @Override
    public void excel2DB(InputStream in, String name) throws IOException {
        try{
            //excel 转map
            Map<ClassInfo,List<User>> classUserMap=excel2Map(in, name);
            //批量插入数据
            for(Map.Entry<ClassInfo, List<User>> entry:classUserMap.entrySet()) {
                System.out.println(entry.getKey());
                //先存入class 的数据并绑定classid
                classInfoMapper.insert(entry.getKey());
                //获取class的id，并绑定到班内的学生
                int classId=entry.getKey().getId();
                for(User user:entry.getValue()) {
                    user.setClassId(classId);
                }
                //存入学生数据
                userMapper.insertBatch(entry.getValue());
            }
        }catch (InvalidFileException | IOException e){
            logger.error(e.getMessage(),e);
            throw e;
        }catch (Exception e){
            logger.info(e.getMessage(),e);
            throw new QingxieInnerException("系统内部异常");
        }

    }

    @Override
    public void save2Local(InputStream in, String name) throws IOException {
        try{
            FileUtil.save(in, name);
        }catch (IOException | QingxieInnerException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
