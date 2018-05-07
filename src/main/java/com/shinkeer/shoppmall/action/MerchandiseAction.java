package com.shinkeer.shoppmall.action;

import com.shinkeer.shoppmall.entity.Merchandise;
import com.shinkeer.shoppmall.service.MerchandiseService;
import com.shinkeer.shoppmall.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
public class MerchandiseAction {
    @Autowired
    MerchandiseService merchandiseService;

    @ResponseBody
    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public String check(HttpServletRequest request) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.print("echostr="+echostr);
            return echostr;
        }else{
            return "fail";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/merchandise/findList",method = RequestMethod.GET)
    public List<Merchandise> findList(){
        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
        return merchandiseService.merchandiseList();
    }
    @ResponseBody
    @RequestMapping(value = "/merchandise/findOne",method = RequestMethod.GET)
    public Merchandise findOne(String id){
        return merchandiseService.findById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/merchandise/saveMerchandise",method = RequestMethod.POST)
    public String saveMerchandise(Merchandise merchandise){
        return merchandiseService.saveMerchandise(merchandise);
    }

    @ResponseBody
    @RequestMapping(value = "/merchandise/updateMerchandise",method = RequestMethod.POST)
    public String updateMerchandise(Merchandise merchandise){
        return merchandiseService.updateMerchandise(merchandise);
    }

    @ResponseBody
    @RequestMapping(value = "/merchandise/deleteMerchandise",method = RequestMethod.POST)
    public String deleteMerchandise(String id){
        return merchandiseService.deleteMerchandise(id);
    }

    @ResponseBody
    @RequestMapping(value="/merchandise/upload",method=RequestMethod.POST)
    private String fildUpload(HttpServletRequest request, String id, int type)throws Exception {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> mfiles =mRequest.getFiles("files");
        //获得物理路径webapp所在路径
//        String pathRoot = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String pathRoot = "/root/java";
//        System.out.println(pathRoot);

        StringBuffer pathsb = new StringBuffer();
        Merchandise merchandise = merchandiseService.findById(id);
        if (mfiles.size() > 0) {
            String path;
            for (MultipartFile file : mfiles) {
                //生成uuid作为文件名称
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                //获得文件类型（可以判断如果不是图片，禁止上传）
                String contentType = file.getContentType();
                //获得文件后缀名称
                String imageName = contentType.substring(contentType.indexOf("/") + 1);
                String pathScend = "/static/";
                path=pathScend+uuid+"."+imageName;

                pathsb.append(path+",");
                File root =  new File(pathRoot+pathScend);
                if(!root.exists()){
                    root.mkdirs();
                }
                file.transferTo(new File(pathRoot+path));
            }
//                path = StringUtils.join(Arrays.asList(pathsb),",");
                if(1==type){
                    merchandise.setPic(pathsb.toString());
                }else {
                    merchandise.setDetailPic(pathsb.toString());
                }
                merchandiseService.updateMerchandise(merchandise);


            System.out.println(pathsb.toString());
            return "success";
        }
        return "error";
    }
}
