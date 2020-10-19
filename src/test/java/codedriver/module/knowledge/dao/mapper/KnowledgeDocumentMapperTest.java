package codedriver.module.knowledge.dao.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import codedriver.BaseTest;
import codedriver.module.knowledge.dto.KnowledgeDocumentVo;

public class KnowledgeDocumentMapperTest extends BaseTest{
    
    @Autowired
    private KnowledgeDocumentMapper knowledgeDocumentMapper;
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document表的增删改查语句
     */
    @Test
    public void knowledgeDocument() {
        Long knowledgeDocumentVersionId = 12L;
        Long knowledgeTypeId = 12L;
        Long knowledgeCircleId = 34L;
        String fcu = "linbq";
        KnowledgeDocumentVo insertKnowledgeDocumentVo = new KnowledgeDocumentVo();
        Long id = insertKnowledgeDocumentVo.getId();
        System.out.println("id:" + id);
        insertKnowledgeDocumentVo.setKnowledgeDocumentVersionId(knowledgeDocumentVersionId);
        insertKnowledgeDocumentVo.setKnowledgeTypeId(knowledgeTypeId);
        insertKnowledgeDocumentVo.setKnowledgeCircleId(knowledgeCircleId);
        insertKnowledgeDocumentVo.setFcu(fcu);
        assert knowledgeDocumentMapper.insertKnowledgeDocument(insertKnowledgeDocumentVo) == 1;
//        KnowledgeDocumentVo getKnowledgeDocumentVo = knowledgeDocumentMapper.getKnowledgeDocumentById(insertKnowledgeDocumentVo.getId());
//        assert getKnowledgeDocumentVo.getKnowledgeDocumentVersionId() == knowledgeDocumentVersionId;
//        assert getKnowledgeDocumentVo.getKnowledgeTypeId() == knowledgeTypeId;
//        assert getKnowledgeDocumentVo.getKnowledgeCircleId() == knowledgeCircleId;
//        assert getKnowledgeDocumentVo.getFcu().equals(fcu);
//        assert getKnowledgeDocumentVo.getIsDelete() == 0;
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_version表的增删改查语句
     */
    @Test
    public void knowledgeDocumentVersion() {
        
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_file表的增删改查语句
     */
    @Test
    public void knowledgeDocumentFile() {
        
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_tag表的增删改查语句
     */
    @Test
    public void knowledgeDocumentTag() {
        
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_line表的增删改查语句
     */
    @Test
    public void knowledgeDocumentLine() {
        
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_line_config表的增删改查语句
     */
    @Test
    public void knowledgeDocumentLineConfig() {
        
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_line_config表的增删改查语句
     */
    @Test
    public void knowledgeDocumentLineContent() {
        
    }
}
