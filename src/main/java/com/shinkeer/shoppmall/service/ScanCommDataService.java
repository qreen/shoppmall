package com.shinkeer.shoppmall.service;

import com.shinkeer.shoppmall.base.dto.RT;
import com.shinkeer.shoppmall.base.service.ServiceImpl;
import com.shinkeer.shoppmall.common.exception.SerException;
import com.shinkeer.shoppmall.dao.ScanCommDataDao;
import com.shinkeer.shoppmall.dto.ScanCommDataDTO;
import com.shinkeer.shoppmall.entity.ScanCommData;
import com.shinkeer.shoppmall.to.ScanCommDataTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScanCommDataService extends ServiceImpl<ScanCommData, ScanCommDataDTO> {

    //根据编号查询
    public List<ScanCommData> findBySerialNum(String serialNum) throws SerException {
        ScanCommDataDTO scanCommDataDTO = new ScanCommDataDTO();
        scanCommDataDTO.addRT(RT.eq("serialNum",serialNum));
        List<ScanCommData> scanCommDataList = super.findByRTS(scanCommDataDTO);
        return scanCommDataList;
    }
    //查询主单
    public List<ScanCommData> findMainData() throws SerException {
        List<ScanCommData> scanCommDataList = super.findBySql("select DISTINCT serial_num from scancommdata",
                ScanCommData.class,new String[]{"serialNum"});

        return scanCommDataList;
    }
    //批量添加
    public void insertData(List<ScanCommDataTO> scanCommDataTOs)throws SerException {
        for (ScanCommDataTO scanCommDataTO : scanCommDataTOs){
            ScanCommData scanCommData =  new ScanCommData();
            BeanUtils.copyProperties(scanCommDataTO,scanCommData);
            scanCommData.setCreateDate(LocalDateTime.now());
            super.save(scanCommData);
        }
    }

    //添加一个
    public void insertDataOne(ScanCommDataTO scanCommDataTO)throws SerException {
        ScanCommData scanCommData =  new ScanCommData();
        BeanUtils.copyProperties(scanCommDataTO,scanCommData);
        scanCommData.setCreateDate(LocalDateTime.now());
        super.save(scanCommData);
    }

    //删除
    public void deleteAll()throws SerException {
         List<ScanCommData> scanCommDataList = super.findAll();
         for (ScanCommData scanCommData : scanCommDataList){

             super.remove(scanCommData.getId());
         }
    }
}
