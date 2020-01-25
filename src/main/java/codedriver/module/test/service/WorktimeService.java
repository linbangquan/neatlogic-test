package codedriver.module.test.service;

import java.util.List;

import codedriver.module.process.dto.WorktimeRangeVo;

public interface WorktimeService {

	long calculateTimeoutPoint(long startTime, long timeLimit, String worktimeUuid);
	
	long calculateCostTime(List<WorktimeRangeVo> worktimeRangeList);
}
