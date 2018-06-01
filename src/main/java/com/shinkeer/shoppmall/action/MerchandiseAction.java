package com.shinkeer.shoppmall.action;

import com.alibaba.fastjson.JSON;
import com.shinkeer.shoppmall.base.dto.RT;
import com.shinkeer.shoppmall.common.aspect.ADD;
import com.shinkeer.shoppmall.common.aspect.EDIT;
import com.shinkeer.shoppmall.common.exception.ActException;
import com.shinkeer.shoppmall.common.exception.SerException;
import com.shinkeer.shoppmall.common.result.ActResult;
import com.shinkeer.shoppmall.common.result.Result;
import com.shinkeer.shoppmall.dto.MerchandiseDTO;
import com.shinkeer.shoppmall.entity.Merchandise;
import com.shinkeer.shoppmall.service.MerchandiseService;
import com.shinkeer.shoppmall.to.MerchandiseTO;
import com.shinkeer.shoppmall.util.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
public class MerchandiseAction {
    @Autowired
    MerchandiseService merchandiseService;

    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.GET)
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
            System.out.print("echostr=" + echostr);
            return echostr;
        } else {
            return "fail";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/merchandise/findList", method = RequestMethod.GET)
    public List<Merchandise> findList() throws ActException {
        List<Merchandise> merchandises = new ArrayList<>();
        try {
            merchandises = merchandiseService.findAll();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        return merchandises;
    }

    /**
     * 后台页面的分页查询
     *
     * @return
     * @throws ActException
     */
    @ResponseBody
    @RequestMapping(value = "/merchandise/merchList", method = RequestMethod.GET)
    public String merchList(MerchandiseDTO merchandiseDTO) throws ActException {
        Map<String, Object> maps = new HashMap<>();
        try {
            // 可以不用判断，自动会识别
            if (StringUtils.isNotBlank(merchandiseDTO.getDetailName())) {
                merchandiseDTO.addRT(RT.like("detailName", merchandiseDTO.getDetailName()));
            }
            if (StringUtils.isNotBlank(merchandiseDTO.getSort()) && StringUtils.isNotBlank(merchandiseDTO.getSortOrder())) {
                merchandiseDTO.addSort(merchandiseDTO.getSort() + "=" + merchandiseDTO.getSortOrder());
            }
            maps.put("total", merchandiseService.count(merchandiseDTO));
            maps.put("rows", merchandiseService.findByPage(merchandiseDTO));
            return JSON.toJSONString(maps);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/merchandise/findOne", method = RequestMethod.GET)
    public Merchandise findOne(@RequestParam String id) throws ActException {
        try {
            return merchandiseService.findById(id);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/merchandise/saveMerchandise", method = RequestMethod.POST)
    public Result saveMerchandise(@Validated(ADD.class) MerchandiseTO merchandiseTO, BindingResult rs) throws ActException {
        try {
            merchandiseService.saveMerchandise(merchandiseTO);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/merchandise/updateMerchandise", method = RequestMethod.POST)
    public Result updateMerchandise(@Validated(EDIT.class) MerchandiseTO merchandiseTO, BindingResult rs) throws ActException {
        try {
            merchandiseService.updateMerchandise(merchandiseTO);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     * @throws ActException
     */
    @ResponseBody
    @RequestMapping(value = "/merchandise/deleteMerchandises", method = RequestMethod.POST)
    public Result deleteMerchandises(String[] ids) throws ActException {
        try {
            List<String> pic_list = new ArrayList<>();
            for (String id : ids){
                Merchandise merchandise = merchandiseService.findById(id);
                String detailPic = merchandise.getDetailPic();
                String pic = merchandise.getPic();
                if(detailPic!=null){
                    String[] detailPic_array = detailPic.split(",");
                    pic_list.addAll(Arrays.asList(detailPic_array));
                }
                pic_list.add(pic);
            }
            String[] pic_arr = new String[pic_list.size()];
            pic_arr = pic_list.toArray(pic_arr);
            //删除图片
            deleteFile(pic_arr);
//            删除数据
            merchandiseService.remove(ids);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/merchandise/upload", method = RequestMethod.POST)
    public Result fildUpload(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> mfiles = mRequest.getFiles("files");
//        String rootPath = "/root/java";
        String rootPath = "c:/root/java";
        String path ="/static/images/";
//        Merchandise merchandise = merchandiseService.findById(id);
        if (mfiles.size() > 0) {
            String[] paths = new String[mfiles.size()];
            File dir = new File(rootPath+path);
            if(!dir.exists()){
                dir.mkdirs();
            }
            int index=0;
            for(MultipartFile mf:mfiles){

                String filePath =rootPath+path+UUID.randomUUID().toString()+"."+StringUtils.substringAfterLast(mf.getOriginalFilename(),".");
                //mf.getOriginalFilename().split("\\.")[1]
                mf.transferTo(new File(filePath));
                paths[index++]=StringUtils.substringAfter(filePath,rootPath);
            }
//            if (1 == type) {
//                merchandise.setPic(paths[0]);
//            } else {
//                merchandise.setDetailPic(StringUtils.join(paths,","));
//            }

//            merchandiseService.update(merchandise);
            return new ActResult(StringUtils.join(paths,","));
        } else {
            return new ActResult("上传失败");
        }
    }
    /**
     * 读取图片
     * @param path
     * @param response
     */
    @ResponseBody
    @GetMapping("/images/read")
    public void images( String path, HttpServletResponse response) {
        String rootPath = "c:/root/java";
//        String rootPath = "/root/java";
        File file = new File(rootPath + path);
        try {
            writeOutFile(response, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 输出文件
     *
     * @param response
     * @param file
     * @throws IOException
     */
    private void writeOutFile (HttpServletResponse response, File file) throws IOException {
        try {
            if (file.exists()) {
                String dfileName = file.getName();
                InputStream fis = new BufferedInputStream(new FileInputStream(file));
                response.reset();
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(dfileName.getBytes(), "iso-8859-1"));
                response.addHeader("Content-Length", "" + file.length());
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                byte[] buffer = new byte[1024 * 1024 * 4];
                int i = -1;
                while ((i = fis.read(buffer)) != -1) {
                    toClient.write(buffer, 0, i);
                }
                fis.close();
                toClient.flush();
                toClient.close();

            } else {
                PrintWriter out = response.getWriter();
                out.print("<script>alert(\"not find the file\")</script>");
            }
        } catch (IOException ex) {
            PrintWriter out = response.getWriter();
            out.print("<script>alert(\"not find the file\")</script>");
        }
    }
    /**
     * 删除图片
     * @param paths
     */
    @ResponseBody
    @PostMapping("/images/delete")
    public Result deleteFile( String[] paths) {
        String rootPath = "c:/root/java";
//        String rootPath = "/root/java";
        if(null!=paths && paths.length>0){
            for (int i=0;i<paths.length;i++){
                File file = new File(rootPath + paths[i]);
                if(file.exists()){
                    file.delete();
                }
            }
        }
       return new ActResult("删除成功");
    }
}
