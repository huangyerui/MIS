package com.example.milktea.controller;

import com.example.milktea.entity.Store;
import com.example.milktea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")

public class StoreController {
    @Autowired
    private StoreService storeService;

    @RequestMapping("/update")
    @ResponseBody
    public Store update(HttpServletRequest request) {
        Store store = new Store();

        String store_id=request.getParameter("store_id");
        Integer id = Integer.valueOf(store_id);
        String store_number=request.getParameter("store_number");
        Integer number = Integer.valueOf(store_number);

        store.setStoreId(id);
        store.setStoreNumber(number);

        int result = this.storeService.update(store);
        System.out.print(result);
        return store;
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Map<String, Object>> getAll(){
        List<Map<String, Object>> list =  this.storeService.getAll();
        return list;
    }
}
