package codedriver.module.test.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codedriver.framework.asynchronization.threadlocal.UserContext;
import codedriver.framework.process.dao.mapper.ProcessMapper;
import codedriver.framework.process.exception.process.ProcessNameRepeatException;
import codedriver.module.process.constvalue.ProcessStepType;
import codedriver.module.process.dto.ProcessFormVo;
import codedriver.module.process.dto.ProcessStepFormAttributeVo;
import codedriver.module.process.dto.ProcessStepRelVo;
import codedriver.module.process.dto.ProcessStepTimeoutPolicyVo;
import codedriver.module.process.dto.ProcessStepVo;
import codedriver.module.process.dto.ProcessStepWorkerPolicyVo;
import codedriver.module.process.dto.ProcessVo;

@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private ProcessMapper processMapper;


	@Override
	public ProcessVo getProcessByUuid(String processUuid) {
		return processMapper.getProcessByUuid(processUuid);
	}

	@Override
	public ProcessFormVo getProcessFormByProcessUuid(String processUuid) {
		return processMapper.getProcessFormByProcessUuid(processUuid);
	}

	@Override
	public List<ProcessStepFormAttributeVo> getProcessStepFormAttributeByStepUuid(ProcessStepFormAttributeVo processStepFormAttributeVo) {
		return processMapper.getProcessStepFormAttributeByStepUuid(processStepFormAttributeVo);
	}

	@Override
	public ProcessStepVo getProcessStartStep(String processUuid) {
		ProcessStepVo processStepVo = new ProcessStepVo();
		processStepVo.setProcessUuid(processUuid);
		processStepVo.setType(ProcessStepType.START.getValue());
		List<ProcessStepVo> processStepList = processMapper.searchProcessStep(processStepVo);
		if (processStepList != null && processStepList.size() == 1) {
			ProcessStepVo startStep = processStepList.get(0);
			startStep.setFormAttributeList(processMapper.getProcessStepFormAttributeByStepUuid(new ProcessStepFormAttributeVo(startStep.getUuid(), null)));
			ProcessFormVo processFormVo = processMapper.getProcessFormByProcessUuid(processUuid);
			if (processFormVo != null) {
				startStep.setFormUuid(processFormVo.getFormUuid());
			}
			return startStep;
		}
		return null;
	}

	@Override
	public List<ProcessStepVo> searchProcessStep(ProcessStepVo processStepVo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int saveProcess(ProcessVo processVo) {
		//TODO linbq等流程图config字段格式确定后，再解析config数据保存到对应的表中
		if(processMapper.checkProcessNameIsRepeat(processVo) > 0) {
			throw new ProcessNameRepeatException(processVo.getName());
		}
		if (processMapper.checkProcessIsExists(processVo.getUuid()) > 0) {
//			processMapper.deleteProcessStepWorkerPolicyByProcessUuid(processVo.getUuid());
//			processMapper.deleteProcessStepByProcessUuid(processVo.getUuid());
//			processMapper.deleteProcessStepRelByProcessUuid(processVo.getUuid());
//			processMapper.deleteProcessStepFormAttributeByProcessUuid(processVo.getUuid());
//			processMapper.deleteProcessStepTimeoutPolicyByProcessUuid(processVo.getUuid());
			processMapper.updateProcess(processVo);
		}else {
			processVo.setFcu(UserContext.get().getUserId());
			processMapper.insertProcess(processVo);
		}
		
//		if (StringUtils.isNotBlank(processVo.getFormUuid())) {
//			processMapper.replaceProcessForm(processVo.getUuid(), processVo.getFormUuid());
//		}
//
//		if (processVo.getStepList() != null && processVo.getStepList().size() > 0) {
//			for (ProcessStepVo stepVo : processVo.getStepList()) {
//				processMapper.insertProcessStep(stepVo);
//				if (stepVo.getFormAttributeList() != null && stepVo.getFormAttributeList().size() > 0) {
//					for (ProcessStepFormAttributeVo processStepAttributeVo : stepVo.getFormAttributeList()) {
//						processMapper.insertProcessStepFormAttribute(processStepAttributeVo);
//					}
//				}
//				if (stepVo.getWorkerPolicyList() != null && stepVo.getWorkerPolicyList().size() > 0) {
//					for (ProcessStepWorkerPolicyVo processStepWorkerPolicyVo : stepVo.getWorkerPolicyList()) {
//						processMapper.insertProcessStepWorkerPolicy(processStepWorkerPolicyVo);
//					}
//				}
//				if (stepVo.getTimeoutPolicyList() != null && stepVo.getTimeoutPolicyList().size() > 0) {
//					for (ProcessStepTimeoutPolicyVo processStepTimeoutPolicyVo : stepVo.getTimeoutPolicyList()) {
//						processMapper.insertProcessStepTimeoutPolicy(processStepTimeoutPolicyVo);
//					}
//				}
//			}
//		}
//
//		if (processVo.getStepRelList() != null && processVo.getStepRelList().size() > 0) {
//			for (ProcessStepRelVo stepRelVo : processVo.getStepRelList()) {
//				processMapper.insertProcessStepRel(stepRelVo);
//			}
//		}

		return 1;
	}

}
