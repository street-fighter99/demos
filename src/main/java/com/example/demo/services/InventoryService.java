package com.example.demo.services;


import com.example.demo.inventory.Entity.InventoryEntity;
import com.example.demo.inventory.InventoryReposetory;
import com.example.demo.model.InventoryModel;
import com.example.demo.model.SparePartsModel;
import com.example.demo.model.UpdateModel;
import com.example.demo.spareParts.Entity.SparePartsEntity;
import com.example.demo.spareParts.SparePartsReposetory;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class InventoryService {
    @Autowired
    InventoryReposetory inventoryReposetory;

    @Autowired
    SparePartsReposetory sparePartsReposetory;


    private final ModelMapper modelMapper=new ModelMapper();


    public void AddInventory(SparePartsModel spareModel) {
        InventoryEntity inventoryEntity=new InventoryEntity();

        inventoryEntity.setSpare_id(spareModel.getId());
        inventoryEntity.setCategory(spareModel.getCategory());
        if (spareModel.getCategory()=="in"){

            inventoryEntity.setQtyIn(spareModel.getQty());
            inventoryEntity.setQtyOut(0);

        }else
        {
            inventoryEntity.setQtyOut(spareModel.getQty());
            inventoryEntity.setQtyIn(0);
        }
        inventoryEntity.setDate(new Timestamp(new Date().getTime()));
        double total=spareModel.getQty()*spareModel.getSell_price();
        inventoryEntity.setTotal_amount(total);

       inventoryReposetory.saveAndFlush(inventoryEntity);
    }


    public ResponseEntity<InventoryEntity> updateInOut(UpdateModel updateModel) {

        SparePartsEntity spareParts=sparePartsReposetory.getBySpareId(updateModel.getId());
        In




    }
}
