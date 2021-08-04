package com.example.demo.spareParts;

import com.example.demo.spareParts.Entity.SparePartsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SparePartsReposetory extends JpaRepository<SparePartsEntity,Integer> {
    SparePartsEntity getByName(String name);

    @Modifying
    @Query("update SparePartsEntity e set e.name = ?1, e.device_model = ?2, e.actual_price = ?3, e.sell_price = ?4, e.is_active = ?5 where e.id = ?6")
    void updateSpareEntry(String name, int device, double actualp, double sellp, int isActive,int id);

    SparePartsEntity getBySpareId(int id);
}
