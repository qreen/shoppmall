package com.shinkeer.shoppmall.service;

import com.shinkeer.shoppmall.dao.MerchandiseDao;
import com.shinkeer.shoppmall.entity.Merchandise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MerchandiseService {
    @Autowired
    MerchandiseDao merchandiseDao;

    public List<Merchandise> merchandiseList(){
        return merchandiseDao.findAll();
    }

    public Merchandise findById(String id){
        return merchandiseDao.findById(id).get();
    }

    public String saveMerchandise(Merchandise merchandise){
        String msg = "";
        try {
            merchandiseDao.save(merchandise);
            msg = "保存成功";
        }catch (Exception e){
            e.printStackTrace();
            msg = "保存失败";
        }
        return msg;
    }

    public String updateMerchandise(Merchandise merchandise){
        String msg = "";
        try {
            merchandiseDao.saveAndFlush(merchandise);
            msg = "编辑成功";
        }catch (Exception e){
            e.printStackTrace();
            msg = "编辑失败";
        }
        return msg;
    }
    public String deleteMerchandise(String id){
        String msg = "";
        try {
            merchandiseDao.deleteById(id);
            msg = "删除成功";
        }catch (Exception e){
            e.printStackTrace();
            msg = "删除失败";
        }
        return msg;
    }
}
