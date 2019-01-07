package github.beginner.noname;

import github.beginner.noname.domain.entity.sys.org.OrgEntity;
import github.beginner.noname.repository.sys.OrgRepository;
import github.beginner.noname.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.*;



/**
 * @author zyp on 2018/12/17
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgTests {
    @Autowired
    private OrgService orgService;

    @Autowired
    private OrgRepository orgRepository;

    @Test
    public void OrgBasicTest() {
//        OrgEntity root = buildNode("root", "0");
//        OrgEntity child1 = buildNode("child1", "1");
//        OrgEntity child2 = buildNode("child2", "2");
//        OrgEntity child3 = buildNode("child3", "3");
//        child1.setParent(root);
//        child2.setParent(root);
//        child3.setParent(root);
//        orgRepository.save(root);
//        orgRepository.save(child1);
//        orgRepository.save(child3);
//        orgRepository.save(child2);

        OrgEntity root = buildNode("root1", "00");
        orgRepository.save(root);
    }

    @Test
    public void OrgQuery() {
        Optional<OrgEntity> o = orgRepository.findById(1L);
        o.ifPresent(orgEntity -> log.info(orgEntity.toString()));
    }

    @Test
    public void OrgUpdate() {
        OrgEntity root = buildNode("root", "110");
        root.setId(1L);
        orgRepository.save(root);
    }

    @Test
    public void OrgDel() {
        orgRepository.deleteById(5L);
    }

    private OrgEntity buildNode(String name, String code) {
        OrgEntity entity = new OrgEntity();
        entity.setCreateDate(System.currentTimeMillis());
        entity.setUpdateDate(System.currentTimeMillis());
        entity.setDesc("test");
        entity.setOrder(0);
        entity.setType("0");
        entity.setUpdateBy(1);
        entity.setName(name);
        entity.setCode(code);
        return entity;
    }

    /**
     * 构造一棵树
     */
    private OrgEntity buildTree(OrgEntity root) {

        return null;
    }


}
