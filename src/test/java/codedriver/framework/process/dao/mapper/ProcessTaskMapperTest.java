package codedriver.framework.process.dao.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import codedriver.BaseTest;
import codedriver.framework.process.dto.ProcessTaskStepRelVo;
import codedriver.framework.process.dto.ProcessTaskStepUserVo;
import codedriver.framework.process.dto.ProcessTaskStepVo;
import codedriver.framework.process.dto.ProcessTaskStepWorkerVo;
import codedriver.framework.process.dto.ProcessTaskVo;

public class ProcessTaskMapperTest extends BaseTest {
    
    @Autowired
    private ProcessTaskMapper processTaskMapper;
    
//    @Test
//    public void test() {
//        List<Long> processTaskIdList = new ArrayList<>();
//        processTaskIdList.add(249659905810432L);
//        processTaskIdList.add(249660929220608L);
//        List<ProcessTaskVo> processTaskList = processTaskMapper.getProcessTaskDetailListByIdList(processTaskIdList);
//        System.out.println(processTaskList);
//        for(ProcessTaskVo processTaskVo : processTaskList) {
//            System.out.println("工单:" + processTaskVo.getId() + "-" + processTaskVo.getTitle());
//            for(ProcessTaskStepVo processTaskStepVo : processTaskVo.getStepList()) {
//                System.out.println("步骤:" + processTaskStepVo.getId() + "-" + processTaskStepVo.getName());
//                for(ProcessTaskStepWorkerVo workerVo : processTaskStepVo.getWorkerList()) {
//                   System.out.println("worder:" + workerVo.getProcessTaskStepId() + "-" + workerVo.getUserType() + "-" + workerVo.getType() +"-" + workerVo.getUuid());
//                }
//                for(ProcessTaskStepUserVo stepUserVo : processTaskStepVo.getUserList()) {
//                   System.out.println("user:" + stepUserVo.getProcessTaskStepId() + "-" + stepUserVo.getUserType() + "-" + stepUserVo.getUserUuid());
//                }
//            }
//            for(ProcessTaskStepRelVo stepRelVo : processTaskVo.getStepRelList()) {
//                System.out.println("连线:" + stepRelVo.getProcessTaskId() + "-" + stepRelVo.getProcessStepRelUuid() + "-" + stepRelVo.getFromProcessTaskStepId() + "-" + stepRelVo.getToProcessTaskStepId());
//            }
//        }
//    }
}
