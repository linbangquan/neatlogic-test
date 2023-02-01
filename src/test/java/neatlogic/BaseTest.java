/*
 * Copyright(c) 2023 TechSure Co., Ltd. All Rights Reserved.
 * 本内容仅限于深圳市赞悦科技有限公司内部传阅，禁止外泄以及用于其他的商业项目。
 */

package neatlogic;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import neatlogic.framework.asynchronization.threadlocal.TenantContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional(transactionManager = "transactionManager")//开启事务
@Rollback(value = true)//事务回滚，数据不会插入到数据库
public class BaseTest {

    @BeforeClass
    public static void beforeClass() {
        /** 默认租户为techsure，默认查询codedriver_techsure库，如需查询codedriver库，执行这条语句切换TenantContext.get().setUseDefaultDatasource(true); **/
        TenantContext.init("develop");
    }

    @AfterClass
    public static void afterClass() {
        TenantContext.get().release();
    }
}
