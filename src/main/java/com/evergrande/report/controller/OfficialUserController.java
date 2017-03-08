package com.evergrande.report.controller;

import com.evergrande.base.controller.BaseController;
import com.evergrande.report.entity.OfficialUser;
import com.evergrande.report.service.ExportExcelService;
import com.evergrande.report.service.OfficialUserService;
import com.evergrande.report.util.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by cc on 16-7-22.
 */
@RequestMapping("/report/off/")
@Controller
public class OfficialUserController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(OfficialUserController.class);

    @Autowired
    private OfficialUserService officialUserService;

    @Autowired
    private ExportExcelService exportExcelService;

    /**
     * 跳转到页面
     *
     * @param request
     * @return
     */
    @RequestMapping("officialUserList")
    public String toExtList(HttpServletRequest request) {
        logger.info("跳转到正式名单列表的页面。。。");
        String strCurrPage = request.getParameter("currPage");
        int currPage = 1;
        if (strCurrPage != null) {
            currPage = Integer.parseInt(strCurrPage);
        }
        request.setAttribute("currPage", currPage);
        return "/report/off/officialUserList";
    }


    /**
     * 分页显示所有用户列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("findOfficialUserListByPage")
    @ResponseBody
    public String findOfficialUserListByPage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramMap = initRequestParam(request);
        String resultJson = officialUserService.findOfficialUserListByPage(paramMap);
        return resultJson;
    }


    /**
     * 跳转到添加用户页面
     *
     * @param request
     * @return
     */
    @RequestMapping("extUserAddEdit")
    public String toExtUserAddEdit(HttpServletRequest request) {
//        String action = request.getParameter("action");
//        request.setAttribute("action", action);
        String strCurrPage = request.getParameter("currPage");
        int currPage = 1;
        if (strCurrPage != null) {
            currPage = Integer.parseInt(strCurrPage);
        }
        request.setAttribute("currPage", currPage);
        return "/report/ext/extUserAddEdit";
    }


    /**
     * 手动添加用户
     *
     * @param request
     * @return
     */
    @RequestMapping("saveOneUser")
    @ResponseBody
    public Map<String, Object> saveOneUser(HttpServletRequest request) {
        //定义map集合,用来返回页面信息
        Map<String, Object> map = new HashMap<String, Object>();
        //首先判断该手机号是否存在
        String mobileNo = request.getParameter("mobile_no");
        OfficialUser user = officialUserService.findOneUserByMobileNO(mobileNo);
        if (user != null) {
            map.put("result", "fail");
            map.put("msg", com.evergrande.report.util.ErrorMsg.EXTUSER_ERROR_MSG);
        } else {
            String action = request.getParameter("action");
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String user_name = request.getParameter("user_name");
            String mobile_no = request.getParameter("mobile_no");
            String report_or_allot = request.getParameter("report_or_allot");
            String report_allot_date = request.getParameter("report_allot_date");
            String invest_adviser = request.getParameter("invest_adviser");
            String customer_ids = request.getParameter("customer_ids");

            if ("add".equals(action)) {
                paramMap.put("user_name", user_name);
                paramMap.put("mobile_no", mobile_no);
                paramMap.put("report_or_allot", report_or_allot);
                paramMap.put("report_allot_date", report_allot_date);
                paramMap.put("invest_adviser", invest_adviser);
                paramMap.put("customer_ids", customer_ids);
                officialUserService.saveOneUser(paramMap);
            }
//        else if("edit".equals(action)){
//            int edit_user_id = 0;
//            String strEditUserId = request.getParameter("edit_user_id");
//            if(strEditUserId != null){
//                edit_user_id = Integer.parseInt(strEditUserId);
//                paramMap.put("edit_user_id", edit_user_id);
//            }
//            officialUserService.modifyUserInfo(paramMap);
//        }
            map.put("result", "success");
            map.put("msg", "保存成功");
        }
        return map;
    }


    /**
     * 上传excel文件,
     * 导入数据到数据库
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                //String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
                // 转存文件
                //file.transferTo(new File(filePath));

                /**
                 * 全量导入前，清空rpt_official_list这张表
                 */
                this.officialUserService.deleteAll();

                //操作解析ExcelUtils,file.getOriginalFilename()获取上传文件的原名
                List<Map<String, String>> valueList = ExcelUtils.readExcel(file.getOriginalFilename(), file);

                /**遍历valueList集合,获取其中每个Map集合的信息,并调用service添加到数据库*/
                if (valueList != null && !valueList.isEmpty()) {
                    // 构建传参map集合
                    Map<String, Object> paramMap = new HashMap<String, Object>();
                    for (int i = 0; i < valueList.size(); i++) {
                        // 获取单个的Map数据
                        Map<String, String> map = valueList.get(i);
                        // 使用迭代器，获取map里的键值对对象
                        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
                        // 遍历 键值对对象 获取里面的value值，作为model的属性值
                        while (entries.hasNext()) {
                            Map.Entry<String, String> entry = entries.next();
//                            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                            // 封装model的属性值
                            if (entry.getKey().equals("手机号码")) {
                                paramMap.put("mobile_no", entry.getValue());
                            }
                            if (entry.getKey().equals("上报/分配")) {
                                String str = null;
                                if (entry.getValue().equals("上报")) {
                                    str = "1";
                                } else {
                                    str = "2";
                                }
                                paramMap.put("report_or_allot", str);
                            }
                            if (entry.getKey().equals("上报/分配时间")) {
                                paramMap.put("report_allot_date", entry.getValue());
                            }
                            if (entry.getKey().equals("投资顾问")) {
                                paramMap.put("invest_adviser", entry.getValue());
                            }
                            if (entry.getKey().equals("客户标识")) {
                                paramMap.put("customer_ids", entry.getValue());
                            }
                        }
                        officialUserService.saveOneUser(paramMap);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String strCurrPage = request.getParameter("currPage");
        int currPage = 1;
        if (strCurrPage != null) {
            currPage = Integer.parseInt(strCurrPage);
        }
        request.setAttribute("currPage", currPage);
        //此处最好返回一个可以显示添加是否成功,是否有错误的信息的页面
        return "redirect:/report/off/officialUserList.do";
    }


    /**
     * 导出所有用户名单到excel文件
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("export_officialUserList")
    public void export_officialUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<OfficialUser> list = officialUserService.findAllOfficialUser(map);
        XSSFWorkbook wb = exportExcelService.export_officialUserList(list);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=official_customer_report.xlsx");
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }


    /**
     * 根据ID删除用户
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteUserById", method = RequestMethod.POST)
    public String deleteUserById(HttpServletRequest request, HttpServletResponse response) {

        String strCurrPage = request.getParameter("currPage");
        int currPage = 1;
        if (strCurrPage != null) {
            currPage = Integer.parseInt(strCurrPage);
        }
        request.setAttribute("currPage", currPage);
        String id = request.getParameter("id");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        officialUserService.deleteUserById(paramMap);
        return "success";
    }


}
