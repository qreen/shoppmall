package com.shinkeer.shoppmall.service;

import com.shinkeer.shoppmall.base.dto.RT;
import com.shinkeer.shoppmall.base.service.ServiceImpl;
import com.shinkeer.shoppmall.common.exception.SerException;
import com.shinkeer.shoppmall.dto.SummaryDataDTO;
import com.shinkeer.shoppmall.entity.SummaryData;
import com.shinkeer.shoppmall.to.SummaryDataTO;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class SummaryDataService extends ServiceImpl<SummaryData, SummaryDataDTO> {

    //批量添加
    public void insertDataList(List<SummaryDataTO> summaryDataTOs)throws SerException {
        for (SummaryDataTO summaryDataTO : summaryDataTOs){
            SummaryData summaryData =  new SummaryData();
            BeanUtils.copyProperties(summaryDataTO,summaryData);
            summaryData.setCreateDate(LocalDateTime.now());
            super.save(summaryData);
        }
    }

    //根据编号查询
    public List<SummaryData> summaryList(String serialNum)throws SerException {
        SummaryDataDTO summaryDataDTO = new SummaryDataDTO();
        summaryDataDTO.addRT(RT.eq("serialNum",serialNum));
        return super.findByRTS(summaryDataDTO);
    }

    //删除所有
    public void deleteList()throws SerException {
        List<SummaryData> summaryDataList = super.findAll();
        for (SummaryData summaryData : summaryDataList){
            super.remove(summaryData.getId());
        }
    }
}
