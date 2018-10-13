package com.kanper.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ISoldGoodsRepositoryTest {
    @Autowired
    private ISoldGoodsRepository soldGoodsRepository;

   /* @Test
    public void findAllByMonth() {
        List<Map<String, Object>> result = new ArrayList<>();
        List list = soldGoodsRepository.findAllByMonth();
        for (Object o : list) {
            Object[] cells = (Object[]) o;
            System.out.println(cells[0]);
            System.out.println(cells[1]);
        }

        System.out.println(result);
    }*/
}