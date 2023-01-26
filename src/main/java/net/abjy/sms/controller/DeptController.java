package net.abjy.sms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
//import net.abjy.sms.service.impl.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * ���� 前端控制器
 * </p>
 *
 * @author liangzz
 * @since 2022-12-28
 */
@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {

//    @Autowired
//    private DeptServiceImpl deptService;

//    @RequestMapping("/save")
//    public DeptEntity save()
//    {
//        log.info("test...1");
//        DeptEntity  dept    =   new DeptEntity();
//        dept.setDeptId(new BigDecimal(1));
//        dept.setDeptName("测试部门");
//        deptService.save(dept);
//        log.info("test...2");
//        return dept;
//    }
//
//    @RequestMapping("/query")
//    public List<DeptEntity> query()
//    {
//        log.info("test...3");
//        return deptService.list();
//    }
//
//    @RequestMapping("/pageQuery/{page}/{limit}")
//    public IPage<DeptEntity> pageQuery(@PathVariable(value = "page") Long page,
//                                      @PathVariable(value = "limit") Long limit)
//    {
//        Page pageParam = new Page(page, limit);
//        IPage<DeptEntity> pageResult=deptService.pageMaps(pageParam);
//        log.info("test...4");
//        return pageResult;
//    }

}
