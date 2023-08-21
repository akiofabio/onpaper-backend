package com.LES.EcommerceOnPaper.repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.model.Pedido;
import com.LES.EcommerceOnPaper.model.StatusItem;

@Repository
public interface  StatusItemRepository extends JpaRepository<StatusItem,Long>{

}
