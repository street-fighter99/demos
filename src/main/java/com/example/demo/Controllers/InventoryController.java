package com.example.demo.Controllers;


import com.example.demo.inventory.Entity.InventoryEntity;
import com.example.demo.model.UpdateModel;
import com.example.demo.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;


    @PostMapping("/update")
    ResponseEntity UpdateInOut(@RequestBody UpdateModel updateModel){
        ResponseEntity<InventoryEntity> responseEntity=inventoryService.updateInOut(updateModel);
        return responseEntity;
    }
}
