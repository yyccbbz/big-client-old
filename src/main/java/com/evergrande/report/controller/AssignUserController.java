package com.evergrande.report.controller;

import com.evergrande.base.controller.BaseController;
import com.evergrande.report.entity.AssignUser;
import com.evergrande.report.service.AssignUserService;
import com.evergrande.report.service.ExportExcelService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cc on 16-7-25.
 */
@RequestMapping("/report/pla/")
@Controller
public class AssignUserController extends BaseController {
    @SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(ExtUserController.class);

    @Autowired
    private AssignUserService assignUserService;

    @Autowired
    private ExportExcelService exportExcelService;

    /**
     * 跳转到页面
     *
     * @param request
     * @return
     */
    @RequestMapping("assignList")
    public String toAssignList(HttpServletRequest request){
        String strCurrPage = request.getParameter("currPage");
        int currPage = 1;
        if (strCurrPage != null) {
            currPage = Integer.parseInt(strCurrPage);
        }
        request.setAttribute("currPage", currPage);
        return "/report/pla/assignList";
    }


    /**
     * 分页显示所有用户列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("findAssignUserListByPage")
    @ResponseBody
    public String findExtUserListByPage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramMap = initRequestParam(request);
        String resultJson = assignUserService.findAssignUserListByPage(paramMap);
        return resultJson;
    }


    /**
     * 导出所有用户名单到excel文件
     *
     * 需要导出当前资产(AUM)在[5万-50万)(不含50)的潜力客户名单
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("export_assignUserList")
    public void export_officialUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
//        List<AssignUser> list = assignUserService.findAllAssignUser(map);
        List<AssignUser> list = assignUserService.findAssignUserByAUM(map);
        XSSFWorkbook wb = exportExcelService.export_assignUserList(list);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=assign_customer_report.xlsx");
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }


    /**
     * 跳转到添加用户页面
     *
     * @param request
     * @return
     */
    @RequestMapping("assignUserAddEdit")
    public String toAssignUserAddEdit(HttpServletRequest request){
        String action = request.getParameter("action");
        request.setAttribute("action", action);
        String strCurrPage = request.getParameter("currPage");
        int currPage = 1;
        if (strCurrPage != null) {
            currPage = Integer.parseInt(strCurrPage);
        }
        request.setAttribute("currPage", currPage);
        return "/report/pla/assignUserAddEdit";
    }
}
