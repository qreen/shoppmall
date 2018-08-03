package com.shinkeer.shoppmall.action;

import com.shinkeer.shoppmall.common.exception.ActException;
import com.shinkeer.shoppmall.common.exception.SerException;
import com.shinkeer.shoppmall.common.result.ActResult;
import com.shinkeer.shoppmall.common.result.Result;
import com.shinkeer.shoppmall.entity.ScanCommData;
import com.shinkeer.shoppmall.service.ScanCommDataService;
import com.shinkeer.shoppmall.to.ScanCommDataTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ScanCommDataAction {
    @Autowired
    private ScanCommDataService scanCommDataService;

    //根据编号查询集合
    @ResponseBody
    @RequestMapping(value = "/scanData/findBySerialNum",method = RequestMethod.GET)
    public List<ScanCommData> findBySerialNum(@RequestParam String serialNum) throws ActException {
        try {
            List<ScanCommData> scanCommDataList = scanCommDataService.findBySerialNum(serialNum);
            return scanCommDataList;
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    //根据主数据
    @ResponseBody
    @RequestMapping(value = "/scanData/findMainData",method = RequestMethod.GET)
    public List<ScanCommData> findMainData() throws ActException {
        try {
            List<ScanCommData> scanCommDataList = scanCommDataService.findMainData();
            return scanCommDataList;
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    //插入一个
    @ResponseBody
    @RequestMapping(value = "/scanData/insertDataOne",method = RequestMethod.POST)
    public Result insertDataOne(ScanCommDataTO scanCommDataTO) throws ActException {
        try {
            scanCommDataService.insertDataOne(scanCommDataTO);
            return new ActResult("success");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    //删除所以数据
    @ResponseBody
    @RequestMapping(value = "/scanData/deleteData",method = RequestMethod.POST)
    public Result deleteData() throws ActException {
        try {
            scanCommDataService.deleteAll();
            return new ActResult("success");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }



}
