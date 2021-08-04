package com.example.demo.services;

import com.example.demo.model.SparePartsModel;
import com.example.demo.model.UpdateModel;
import com.example.demo.spareParts.Entity.SparePartsEntity;
import com.example.demo.spareParts.SparePartsReposetory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class SparePartsServices {

    @Autowired
    SparePartsReposetory sparePartsReposetory;

    @Autowired
    InventoryService inventoryService;


    private final ModelMapper modelMapper=new ModelMapper();
    public ResponseEntity AddNewItem(SparePartsModel spareModel) {


        SparePartsEntity spareEntity=isExisitingSpare(spareModel);

        if (spareEntity==null){
            spareEntity=modelMapper.map(spareModel,SparePartsEntity.class);
            spareEntity.setName(spareModel.getName());
            spareEntity.setDevice_model(spareModel.getDevice_model());
            spareEntity.setActual_price(spareModel.getActual_price());
            spareEntity.setSell_price(spareModel.getSell_price());
            spareEntity.setIs_active(spareModel.getIs_active());
            spareEntity.setCr_user(spareModel.getCr_user());
            spareEntity.setCr_date(new Timestamp(new Date().getTime()));
            sparePartsReposetory.saveAndFlush(spareEntity);
            inventoryService.AddInventory(spareModel);


            return  new ResponseEntity<>("Product is added", HttpStatus.ACCEPTED);

        }else{

            inventoryService.AddInventory(spareModel);
            return new ResponseEntity<>("This Product is ALready Exists",HttpStatus.CONFLICT);
        }

    }

    private SparePartsEntity isExisitingSpare(SparePartsModel spareModel) {
        SparePartsEntity sparePartsEntity=sparePartsReposetory.getByName(spareModel.getName());
        return sparePartsEntity;
    }

    public List<SparePartsEntity> GetAllSpare() {

        List<SparePartsEntity> spareParts=sparePartsReposetory.findAll();
        return spareParts;

    }

    public SparePartsEntity GetbyIdno(int id){
        SparePartsEntity spareParts=sparePartsReposetory.getById(id);
        return spareParts;
    }

    public ResponseEntity UpdateEntry(UpdateModel updateModel) {

        SparePartsEntity sparePartsEntity=GetbyIdno(updateModel.getId());

        sparePartsReposetory.updateSpareEntry(
                updateModel.getName()==null?sparePartsEntity.getName():updateModel.getName()
                , updateModel.getDevice_model() ==0 ?sparePartsEntity.getDevice_model():updateModel.getDevice_model()
                ,updateModel.getActual_price()==0?sparePartsEntity.getActual_price():updateModel.getActual_price(),
                updateModel.getSell_price()==0?sparePartsEntity.getSell_price():updateModel.getSell_price(),
                updateModel.getIs_active()==0?0:1,
                updateModel.getId()
                );

        return new ResponseEntity<>("Updated Sucessfully",HttpStatus.ACCEPTED);

               }
}
