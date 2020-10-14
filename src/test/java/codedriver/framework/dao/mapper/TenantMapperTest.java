package codedriver.framework.dao.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import codedriver.framework.asynchronization.threadlocal.TenantContext;
import codedriver.framework.dto.TeamVo;
import codedriver.framework.dto.TenantVo;
import codedriver.BaseTest;

public class TenantMapperTest extends BaseTest{

    @Autowired
    private TenantMapper tenantMapper;
    
    @Autowired
    private TeamMapper teamMapper;
    
    @Test
    public void test() {
        TenantContext.get().setUseDefaultDatasource(true);//切换到codedriver库
        List<TenantVo> tenantList = tenantMapper.getAllActiveTenant();
        tenantList.forEach(System.out::println);
    }
    
    @Test
    public void test2() {
        TeamVo team = teamMapper.getTeamByUuid("63676fb475804492a4bf509beb62340a");
        System.out.println(team);
    }
}
