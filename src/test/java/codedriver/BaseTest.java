package codedriver;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import codedriver.framework.asynchronization.threadlocal.TenantContext;

/**
 * 
* @Time:2020年10月14日
* @ClassName: BaseTest 
* @Description: 测试基类，所有测试类都继承这个类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class BaseTest {

    @Before
    public void before() {
        /** 默认租户为techsure，默认查询codedriver_techsure库，如需查询codedriver库，执行这条语句切换TenantContext.get().setUseDefaultDatasource(true); **/
        TenantContext.init("techsure");
    }
    
    @After
    public void after() {
        TenantContext.get().release();
    }
}
