package com.shinkeer.shoppmall.service;

import com.shinkeer.shoppmall.base.service.ServiceImpl;
import com.shinkeer.shoppmall.common.exception.SerException;
import com.shinkeer.shoppmall.dto.MerchandiseDTO;
import com.shinkeer.shoppmall.entity.Merchandise;
import com.shinkeer.shoppmall.to.MerchandiseTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class MerchandiseService extends ServiceImpl<Merchandise,MerchandiseDTO>{

    public void saveMerchandise(MerchandiseTO merchandiseTO) throws SerException{
        Merchandise merchandise = new Merchandise();
        BeanUtils.copyProperties(merchandiseTO,merchandise);
        merchandise.setCreateDate(LocalDateTime.now());
        super.save(merchandise);
    }

    public void updateMerchandise(MerchandiseTO merchandiseTO) throws SerException{
        Merchandise merchandise = super.findById(merchandiseTO.getId());
        BeanUtils.copyProperties(merchandiseTO,merchandise);
        super.update(merchandise);
    }

}
