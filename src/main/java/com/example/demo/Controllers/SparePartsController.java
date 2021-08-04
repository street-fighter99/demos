package com.example.demo.Controllers;

import com.example.demo.model.SparePartsModel;
import com.example.demo.model.UpdateModel;
import com.example.demo.services.SparePartsServices;
import com.example.demo.spareParts.Entity.SparePartsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spare")
public class SparePartsController {


    @Autowired
    SparePartsServices sparePartsServices;

    @PostMapping("/addNewSpare")
    ResponseEntity AddNewSpare(@RequestBody SparePartsModel spareModel){

        ResponseEntity<SparePartsEntity> responseEntity= sparePartsServices.AddNewItem(spareModel);
        return responseEntity;

    }

    @GetMapping("/getAll")
    ResponseEntity GetAllSpare(){
        List<SparePartsEntity> spareParts=sparePartsServices.GetAllSpare();
        return new ResponseEntity<>(spareParts, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateEntry")
    ResponseEntity newEntry(@RequestBody UpdateModel updateModel){

        ResponseEntity<SparePartsEntity> responseEntity=sparePartsServices.UpdateEntry(updateModel);
        return responseEntity;

    }
}
