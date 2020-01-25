package codedriver.module.test.service;

import java.util.List;

import codedriver.framework.attribute.dto.AttributeVo;
import codedriver.framework.attribute.dto.DataCubeVo;
import codedriver.module.process.dto.ProcessStepVo;
import codedriver.module.process.dto.ProcessVo;

public interface AttributeService {

	public AttributeVo getAttributeByUuid(String uuid);

	public List<AttributeVo> searchAttribute(AttributeVo attributeVo);

	public DataCubeVo getDataCubeByUuid(String uuid);

}
