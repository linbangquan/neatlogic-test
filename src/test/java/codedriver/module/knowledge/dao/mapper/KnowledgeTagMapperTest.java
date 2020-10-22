package codedriver.module.knowledge.dao.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import codedriver.BaseTest;
import codedriver.framework.common.dto.BasePageVo;
import codedriver.framework.common.dto.ValueTextVo;
import codedriver.module.knowledge.dto.KnowledgeTagVo;

public class KnowledgeTagMapperTest extends BaseTest {

    @Autowired
    private KnowledgeTagMapper knowledgeTagMapper;
    
    @Test
    public void KnowledgeTag() {
        String prefix = "linbq20201022202020";
        String name1 = prefix + "标签1";
        KnowledgeTagVo knowledgeTagVo1 = new KnowledgeTagVo(name1);
        assert knowledgeTagMapper.insertKnowledgeTag(knowledgeTagVo1) == 1;
        assert knowledgeTagMapper.getKnowledgeTagIdByName(name1) == knowledgeTagVo1.getId().longValue();
        
        String name2 = prefix + "标签2";
        KnowledgeTagVo knowledgeTagVo2 = new KnowledgeTagVo(name2);
        assert knowledgeTagMapper.insertKnowledgeTag(knowledgeTagVo2) == 1;
        
        String name3 = prefix + "标签3";
        KnowledgeTagVo knowledgeTagVo3 = new KnowledgeTagVo(name3);
        assert knowledgeTagMapper.insertKnowledgeTag(knowledgeTagVo3) == 1;
        
        BasePageVo basePageVo = new BasePageVo();
        basePageVo.setKeyword(prefix);
        assert knowledgeTagMapper.getKnowledgeTagCount(basePageVo) == 3;
        
        List<ValueTextVo> tagList = knowledgeTagMapper.getKnowledgeTagList(basePageVo);
        assert tagList.get(0).getText().equals(name1);
        assert tagList.get(1).getText().equals(name2);
        assert tagList.get(2).getText().equals(name3);
    }
}
