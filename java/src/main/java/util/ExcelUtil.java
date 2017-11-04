package util;

import model.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by coder on 2017/11/4.
 */
public class ExcelUtil {

    public static void export(OutputStream out, List<User> users) throws Exception {

        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            hssfSheet.setDefaultColumnWidth(15);
            hssfSheet.setColumnWidth(1,20*256);
            hssfSheet.setColumnWidth(2,20*256);
            hssfSheet.setColumnWidth(9,20*256);
            HSSFRow hssfRow = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            //居中样式
            hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
            HSSFCell hssfCell = null;
            String[] titles = {"姓名","地址","手机号","微信","邮件","qq","个性语言"};
            for (int i = 0; i < titles.length; i++) {
                hssfCell = hssfRow.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }
            // 第五步，写入实体数据
            if(users != null && !users.isEmpty()){
                for (int i = 0 ; i < users.size(); i++) {
                    hssfRow = hssfSheet.createRow(i+1);
                    User user = users.get(i);
                    hssfRow.createCell(0).setCellValue(user.getUserName());
                    hssfRow.createCell(1).setCellValue(user.getAddress());
                    hssfRow.createCell(2).setCellValue(user.getPhone());
                    hssfRow.createCell(3).setCellValue(user.getWeixin());
                    hssfRow.createCell(4).setCellValue(user.getEmail());
                    hssfRow.createCell(5).setCellValue(user.getQq());
                    hssfRow.createCell(6).setCellValue(user.getNote());
                }
            }
            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("导出信息失败！");
        }
    }
}
