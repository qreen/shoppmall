package com.shinkeer.shoppmall.action;

import com.alibaba.fastjson.JSONObject;
import com.shinkeer.shoppmall.common.exception.ActException;
import com.shinkeer.shoppmall.common.exception.SerException;
import com.shinkeer.shoppmall.common.result.ActResult;
import com.shinkeer.shoppmall.common.result.Result;
import com.shinkeer.shoppmall.entity.SummaryData;
import com.shinkeer.shoppmall.service.SummaryDataService;
import com.shinkeer.shoppmall.to.SummaryDataListTO;
import com.shinkeer.shoppmall.to.SummaryDataTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SummaryDataAction {
    @Autowired
    private SummaryDataService summaryDataService;

    //批量添加
    @ResponseBody
    @RequestMapping(value = "/summaryData/insertData",method = RequestMethod.POST)
    public Result insertData(String summaryDataList) throws ActException {
        try {
//            JSONObject obj = JSONObject.parseObject(summScanList);
//            JSONObject obj_ss = obj.get("summScanList")
            List<SummaryDataTO> summaryDataTOList = JSONObject.parseArray(summaryDataList,SummaryDataTO.class);
            summaryDataService.insertDataList(summaryDataTOList);
            return new ActResult("success");
        }catch (Exception e){
            throw new ActException(e.getMessage());
        }
    }

    //根据编号查询
    @ResponseBody
    @RequestMapping(value = "/summaryData/summaryList",method = RequestMethod.GET)
    public Result summaryList(@RequestParam String serialNum) throws ActException {
        try {
            List<SummaryData> summaryDataList = summaryDataService.summaryList(serialNum);
            return new ActResult(summaryDataList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    //删除所有
    @ResponseBody
    @RequestMapping(value = "/summaryData/deleteList",method = RequestMethod.POST)
    public Result deleteList() throws ActException {
        try {
            summaryDataService.deleteList();
            return new ActResult("success");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }
}
