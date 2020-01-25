package codedriver.module.test.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import codedriver.framework.attribute.dto.AttributeVo;
import codedriver.module.process.dto.FormVersionVo;
import codedriver.module.process.dto.FormVo;

public interface FormService {
	public FormVo getFormDetailByUuid(String formUuid);

	@Transactional
	public int saveForm(FormVo formVo);

	public FormVersionVo getFormVersionByUuid(String formVersionUuid);

	public List<FormVo> searchForm(FormVo formVo);

	public List<AttributeVo> getAttributeByFormUuid(String formUuid);
}
