package codedriver.module.test.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codedriver.framework.attribute.dto.AttributeVo;
import codedriver.framework.common.util.PageUtil;
import codedriver.framework.process.dao.mapper.FormMapper;
import codedriver.module.process.dto.FormAttributeVo;
import codedriver.module.process.dto.FormVersionVo;
import codedriver.module.process.dto.FormVo;

@Service("ProcessFormService")
public class FormServiceImpl implements FormService {

	@Autowired
	private FormMapper formMapper;
	
	@Override
	public List<AttributeVo> getAttributeByFormUuid(String formUuid){
		return formMapper.getAttributeByFormUuid(formUuid);
	}

	@Override
	public FormVo getFormDetailByUuid(String formUuid) {
		FormVo formVo = formMapper.getFormByUuid(formUuid);
		if (formVo != null) {
			formVo.setVersionList(formMapper.getFormVersionByFormUuid(formUuid));
		}
		return formVo;
	}

	@Override
	public FormVersionVo getFormVersionByUuid(String formVersionUuid) {
		return formMapper.getFormVersionByUuid(formVersionUuid);
	}

	@Override
	public List<FormVo> searchForm(FormVo formVo) {
		if (formVo.getNeedPage()) {
			int rowNum = formMapper.searchFormCount(formVo);
			formVo.setRowNum(rowNum);
			formVo.setPageCount(PageUtil.getPageCount(rowNum, formVo.getPageSize()));
		}
		return formMapper.searchFormList(formVo);
	}

	@Override
	public int saveForm(FormVo formVo) {
		formMapper.insertForm(formVo);
		FormVersionVo formVersionVo = new FormVersionVo();
		formVersionVo.setContent(formVo.getContent());
		formVersionVo.setFormUuid(formVo.getUuid());
		formMapper.resetFormVersionIsActiveByFormUuid(formVo.getUuid());
		formVersionVo.setIsActive(1);
		if (StringUtils.isBlank(formVo.getCurrentVersionUuid())) {
			Integer version = formMapper.getMaxVersionByFormUuid(formVo.getUuid());
			if (version == null) {
				version = 1;
			} else {
				version += 1;
			}
			formVersionVo.setVersion(version);
			formMapper.insertFormVersion(formVersionVo);
		} else {
			formVersionVo.setUuid(formVo.getCurrentVersionUuid());
			formMapper.updateFormVersion(formVersionVo);
		}
		formMapper.deleteFormAttributeByFormUuid(formVo.getUuid());
		List<FormAttributeVo> attributeList = formVersionVo.getFormAttributeList();
		if (attributeList != null && attributeList.size() > 0) {
			for (FormAttributeVo formAttributeVo : attributeList) {
				formMapper.insertFormAttribute(formAttributeVo);
			}
		}
		return 1;
	}

}
