package com.kanper.controller;

import com.kanper.service.ISoldGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistic")
@Slf4j
public class StatisticController {
    @Autowired
    private ISoldGoodsService soldGoodsService;

    @GetMapping("/findSoldNumberByCategory")
    public List<Map<String, Object>> findSoldNumberByCategory() {
        List<Map<String, Object>> list1 = soldGoodsService.findSoldNumberByCategory();
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "Name" + i);
            map.put("data", new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
            list.add(map);
        }
        return list;
    }
}
