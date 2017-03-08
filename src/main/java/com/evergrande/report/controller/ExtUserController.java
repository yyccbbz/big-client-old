package com.evergrande.report.controller;

import com.evergrande.base.controller.BaseController;
import com.evergrande.report.entity.ExtUser;
import com.evergrande.report.service.ExportExcelService;
import com.evergrande.report.service.ExtPhoneService;
import com.evergrande.report.service.ExtUserService;
import com.evergrande.report.util.ErrorMsg;
import com.evergrande.report.util.ExcelUtils;
import com.evergrande.report.util.Validator;
import org.apache.commons.lang3.StringUtils;
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
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cc on 16-7-22.
 */
@RequestMapping("/report/ext/")
@Controller
public class ExtUserController extends BaseController {

    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(ExtUserController.class);

    @Autowired
    private ExtUserService extUserService;

    @Autowired
    private ExtPhoneService extPhoneService;

    @Autowired
    private ExportExcelService exportExcelService;

    /**
     * 跳转到页面
     *
     * @param request
     * @return
     */
    @RequestMapping("extList")
    public String toExtList(HttpServletRequest request) {
        String strCurrPage = request.getParameter("currPage");
        int currPage = 1;
        if (strCurrPage != null) {
            currPage = Integer.parseInt(strCurrPage);
        }
        request.setAttribute("currPage", currPage);
        return "/report/ext/extList";
    }


    /**
     * 分页显示所有用户列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("findExtUserListByPage")
    @ResponseBody
    public String findExtUserListByPage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramMap = initRequestParam(request);
        String resultJson = extUserService.findExtUserListByPage(paramMap);
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
        String action = request.getParameter("action");
        request.setAttribute("action", action);
        String strCurrPage = request.getParameter("currPage");
        int currPage = 1;
        if (strCurrPage != null) {
            currPage = Integer.parseInt(strCurrPage);
        }
        request.setAttribute("currPage", currPage);
//        String order_id = request.getParameter("order_id");
//        if(order_id == null){
//            order_id = "user_id";
//        }
//        request.setAttribute("order_id", order_id);
//        String order_type = request.getParameter("order_type");
//        if(order_type == null){
//            order_type = "asc";
//        }
//        request.setAttribute("order_type", order_type);
        if ("edit".equals(action)) {
            String id = request.getParameter("id");
            ExtUser editUser = extUserService.findOneUserByMobileId(id);
            request.setAttribute("editUser", editUser);
        }
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
        // 定义map集合,用来返回页面信息
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取当前action是保存还是修改
        String action = request.getParameter("action");
        // 首先判断该手机号是否存在
        String mobileNo = request.getParameter("mobile_no");
        ExtUser user = extUserService.findOneUserByMobileNO(mobileNo);
        if (user != null) {
            map.put("result", "fail");
            map.put("msg", ErrorMsg.EXTUSER_ERROR_MSG);
        } else {
            //定义传参集合
            Map<String, Object> paramMap = new HashMap<String, Object>();

            String mobile_no = request.getParameter("mobile_no");
            String user_name = request.getParameter("user_name");
            String is_reg = request.getParameter("is_reg");
            String reg_date = request.getParameter("reg_date");
            if (StringUtils.isEmpty(reg_date)) {
                reg_date = null;
            }
            String is_real_name = request.getParameter("is_real_name");
            String is_tie_card = request.getParameter("is_tie_card");
            String is_trans = request.getParameter("is_trans");
            String scan_code_rec = request.getParameter("scan_code_rec");
            String rebate_expire_date = request.getParameter("rebate_expire_date");
            if (StringUtils.isEmpty(rebate_expire_date)) {
                rebate_expire_date = null;
            }
            paramMap.put("mobile_no", mobile_no);
            paramMap.put("user_name", user_name);
            paramMap.put("is_reg", is_reg);
            paramMap.put("reg_date", reg_date);
            paramMap.put("is_real_name", is_real_name);
            paramMap.put("is_tie_card", is_tie_card);
            paramMap.put("is_trans", is_trans);
            paramMap.put("scan_code_rec", scan_code_rec);
            paramMap.put("rebate_expire_date", rebate_expire_date);
            if ("add".equals(action)) {
                extUserService.saveOneUser(paramMap);
            } else if ("edit".equals(action)) {
                int edit_user_id = 0;
                String strEditUserId = request.getParameter("edit_user_id");
                if (strEditUserId != null) {
                    edit_user_id = Integer.parseInt(strEditUserId);
                    paramMap.put("edit_user_id", edit_user_id);
                }
                extUserService.modifyUserInfo(paramMap);
            }
            map.put("result", "success");
            map.put("msg", "保存成功");
        }
        return map;
    }


    /**
     * 导出所有用户名单到excel文件
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("export_extUserList")
    public void export_extUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ExtUser> list = extUserService.findAllExtUser(map);
        XSSFWorkbook wb = exportExcelService.export_extUserList(list);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=external_customer_report.xlsx");
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
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
                 * 导入前，先把表rpt_ext_phone全部清空，用以全量导入
                 */
                this.extPhoneService.deleteAllExtPhone();
                //操作解析ExcelUtils,file.getOriginalFilename()获取上传文件的原名
                List<Map<String, String>> valueList = ExcelUtils.readExcel(file.getOriginalFilename(), file);
                /**遍历valueList集合,获取其中每个Map集合的信息,并调用service添加到数据库*/
                if (valueList != null && !valueList.isEmpty()) {
                    //构建参数集合
                    Map<String, Object> paramMap = new HashMap<String, Object>();
                    // 获取单个的Map数据
                    for (int i = 0; i < valueList.size(); i++) {
                        Map<String, String> map = valueList.get(i);
                        for (Map.Entry<String, String> entry : map.entrySet()) {
//                            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            String key = entry.getValue();
                            //只添加手机号
                            if (entry.getKey().equals("手机号码")) {
                                paramMap.put("mobile_no", key);
                                this.extPhoneService.saveExtPhone(paramMap);
                            }
                        }
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
        return "redirect:/report/ext/extList.do";
    }


    /**
     * ajax校验表单输入的手机号
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkMobile", method = RequestMethod.POST)
    public String checkMobile(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        String mobile_no = request.getParameter("mobile");

        if (mobile_no == null) {
            return "手机号不能为空,请检查输入!";
        } else {
            //正则校验
            Pattern p = Pattern.compile(Validator.REGEX_MOBILE);
            Matcher m = p.matcher(mobile_no);
            flag = m.matches();

            if (flag) {
                //查询该手机号是否已经存在
                ExtUser user = extUserService.findOneUserByMobileNO(mobile_no);
                if (user == null) {
                    return ErrorMsg.MOBILE_OK_MSG;
                } else {
                    return ErrorMsg.EXTUSER_ERROR_MSG;
                }
            } else {
                return ErrorMsg.MOBILE_ERROR_MSG;
            }
        }

    }

    /**
     * 根据手机号删除用户
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteUserByMobile", method = RequestMethod.POST)
    public String deleteUserByMobile(HttpServletRequest request, HttpServletResponse response) {

        String strCurrPage = request.getParameter("currPage");
        int currPage = 1;
        if (strCurrPage != null) {
            currPage = Integer.parseInt(strCurrPage);
        }
        request.setAttribute("currPage", currPage);
        String mobile_no = request.getParameter("mobile_no");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("mobile_no", mobile_no);
        extUserService.deleteUserByMobile(paramMap);
        return "success";
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

        ExtUser extUser = this.extUserService.findOneUserByMobileId(id);
        String mobile_no = extUser.getMobile_no();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mobile_no", mobile_no);
        this.extPhoneService.deleteByMobile(params);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        extUserService.deleteUserById(paramMap);
        return "success";
    }


}
