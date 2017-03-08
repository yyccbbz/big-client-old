package com.evergrande.report.service;

import com.evergrande.report.entity.AssignUser;
import com.evergrande.report.entity.ExtUser;
import com.evergrande.report.entity.OfficialUser;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * Created by cc on 16-7-25.
 */
public interface ExportExcelService {

    XSSFWorkbook export_extUserList(List<ExtUser> list);

    XSSFWorkbook export_officialUserList(List<OfficialUser> list);

    XSSFWorkbook export_assignUserList(List<AssignUser> list);
}
