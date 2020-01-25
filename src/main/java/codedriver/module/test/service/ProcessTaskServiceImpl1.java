package codedriver.module.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codedriver.framework.process.dao.mapper.ProcessTaskMapper;
import codedriver.module.process.dto.ProcessTaskStepRelVo;
import codedriver.module.process.dto.ProcessTaskStepVo;
import codedriver.module.process.dto.ProcessTaskVo;

@Service
public class ProcessTaskServiceImpl1 implements ProcessTaskService1{
	@Autowired
	private ProcessTaskMapper processTaskMapper;
	
	@Override
	public List<ProcessTaskStepRelVo> getProcessTaskStepRelByProcessTaskId(Long processTaskId) {
		return processTaskMapper.getProcessTaskStepRelByProcessTaskId(processTaskId);
	}
	
	@Override
	public List<ProcessTaskStepVo> getProcessTaskStepStatusByFlowJobId(Long flowJobId) {
		List<ProcessTaskStepVo> processTaskStepList = processTaskMapper.getProcessTaskStepBaseInfoByProcessTaskId(flowJobId);
		/*for (ProcessTaskStepVo step : processTaskStepList) {
			IProcessStepHandler component = ProcessStepHandlerFactory.getHandler(step.getType());
			if (component != null) {
				component.makeupFlowJobStepVo(step);
			}
		}*/
		return processTaskStepList;
	}
	
	@Override
	public ProcessTaskVo getProcessTaskBaseInfoById(Long processTaskId) {
		return processTaskMapper.getProcessTaskBaseInfoById(processTaskId);
	}
}
