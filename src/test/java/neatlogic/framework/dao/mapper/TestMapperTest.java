package neatlogic.framework.dao.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import neatlogic.framework.asynchronization.threadlocal.TenantContext;
import neatlogic.framework.dto.TeamVo;
import neatlogic.framework.dto.TenantVo;
import neatlogic.BaseTest;

public class TestMapperTest extends BaseTest{

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
    
    @Test
    public void test3() {
        TenantContext.init("oppo");//切换到codedriver_oppo租户库
        TeamVo team = teamMapper.getTeamByUuid("02b55b7818eb41d6b7710a6f0055353f");
        System.out.println(team);
    }
}
