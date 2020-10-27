package codedriver.module.knowledge.dao.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import codedriver.BaseTest;
import codedriver.module.knowledge.constvalue.KnowledgeDocumentVersionStatus;
import codedriver.module.knowledge.dto.KnowledgeDocumentFileVo;
import codedriver.module.knowledge.dto.KnowledgeDocumentLineConfigVo;
import codedriver.module.knowledge.dto.KnowledgeDocumentLineContentVo;
import codedriver.module.knowledge.dto.KnowledgeDocumentLineVo;
import codedriver.module.knowledge.dto.KnowledgeDocumentTagVo;
import codedriver.module.knowledge.dto.KnowledgeDocumentVersionVo;
import codedriver.module.knowledge.dto.KnowledgeDocumentVo;

public class KnowledgeDocumentMapperTest extends BaseTest {
    
    @Autowired
    private KnowledgeDocumentMapper knowledgeDocumentMapper;
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document表的增删改查语句
     */
    @Test
    public void knowledgeDocument() {
        Integer version = 0;
        String knowledgeDocumentTypeUuid = "abcedfghijklmnopqrstuvwxyz123456";
        Long knowledgeCircleId = 34L;
        String fcu = "linbq";
        KnowledgeDocumentVo insertVo = new KnowledgeDocumentVo();
        insertVo.setVersion(0);
        insertVo.setKnowledgeDocumentTypeUuid(knowledgeDocumentTypeUuid);
        insertVo.setKnowledgeCircleId(knowledgeCircleId);
        insertVo.setFcu(fcu);
        assert knowledgeDocumentMapper.insertKnowledgeDocument(insertVo) == 1;
        
        KnowledgeDocumentVo getVo = knowledgeDocumentMapper.getKnowledgeDocumentById(insertVo.getId());
        assert getVo.getVersion() == version;
        assert Objects.equals(getVo.getKnowledgeDocumentTypeUuid(), knowledgeDocumentTypeUuid);
        assert getVo.getKnowledgeCircleId() == knowledgeCircleId;
        assert getVo.getFcu().equals(fcu);
        assert getVo.getIsDelete() == 0;
        
        assert knowledgeDocumentMapper.updateKnowledgeDocumentToDeleteById(insertVo.getId()) == 1;
        
        getVo = knowledgeDocumentMapper.getKnowledgeDocumentById(insertVo.getId());
        assert getVo.getVersion() == version;
        assert Objects.equals(getVo.getKnowledgeDocumentTypeUuid(), knowledgeDocumentTypeUuid);
        assert getVo.getKnowledgeCircleId() == knowledgeCircleId;
        assert getVo.getFcu().equals(fcu);
        assert getVo.getIsDelete() == 1;
        
        Long knowledgeDocumentVersionId = 1L;
        Integer updateVersion = 1;
        KnowledgeDocumentVo updateVo = new KnowledgeDocumentVo();
        updateVo.setId(insertVo.getId());
        updateVo.setKnowledgeDocumentVersionId(knowledgeDocumentVersionId);
        updateVo.setVersion(updateVersion);
        assert knowledgeDocumentMapper.updateKnowledgeDocumentById(updateVo) == 1;
        getVo = knowledgeDocumentMapper.getKnowledgeDocumentById(insertVo.getId());
        assert getVo.getVersion() == updateVersion;
        assert getVo.getKnowledgeDocumentVersionId() == knowledgeDocumentVersionId;
        assert Objects.equals(getVo.getKnowledgeDocumentTypeUuid(), knowledgeDocumentTypeUuid);
        assert getVo.getKnowledgeCircleId() == knowledgeCircleId;
        assert getVo.getFcu().equals(fcu);
        assert getVo.getIsDelete() == 1;
        
        assert knowledgeDocumentMapper.deleteKnowledgeDocumentById(insertVo.getId()) == 1;
        
        getVo = knowledgeDocumentMapper.getKnowledgeDocumentById(insertVo.getId());
        
        assert getVo == null;
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_version表的增删改查语句
     */
    @Test
    public void knowledgeDocumentVersion() {
        String title = "title";
        Long knowledgeDocumentId = 12L;
        Integer version = 1;
        String lcu = "linbq";     
        KnowledgeDocumentVersionVo insertVo = new KnowledgeDocumentVersionVo();
        insertVo.setTitle(title);
        insertVo.setKnowledgeDocumentId(knowledgeDocumentId);
        insertVo.setVersion(version);
        insertVo.setStatus(KnowledgeDocumentVersionStatus.DRAFT.getValue());
        insertVo.setLcu(lcu);
        assert knowledgeDocumentMapper.insertKnowledgeDocumentVersion(insertVo) == 1;
        
        KnowledgeDocumentVersionVo getVo = knowledgeDocumentMapper.getKnowledgeDocumentVersionById(insertVo.getId());
        assert Objects.equals(getVo.getTitle(), title);
        assert Objects.equals(getVo.getKnowledgeDocumentId(), knowledgeDocumentId);
        assert Objects.equals(getVo.getVersion(), version);
        assert Objects.equals(getVo.getStatus(), KnowledgeDocumentVersionStatus.DRAFT.getValue());
        assert Objects.equals(getVo.getLcu(), lcu);
        assert Objects.equals(getVo.getSize(), null);
        
        
        KnowledgeDocumentVersionVo updateVo = new KnowledgeDocumentVersionVo();
        updateVo.setId(insertVo.getId());
        assert knowledgeDocumentMapper.updateKnowledgeDocumentVersionById(updateVo) == 1;
        
        getVo = knowledgeDocumentMapper.getKnowledgeDocumentVersionById(insertVo.getId());
        assert Objects.equals(getVo.getTitle(), title);
        assert Objects.equals(getVo.getKnowledgeDocumentId(), knowledgeDocumentId);
        assert Objects.equals(getVo.getVersion(), version);
        assert Objects.equals(getVo.getStatus(), KnowledgeDocumentVersionStatus.DRAFT.getValue());
        assert Objects.equals(getVo.getLcu(), lcu);
        assert Objects.equals(getVo.getSize(), null);

        
        Integer maxVersion = knowledgeDocumentMapper.getKnowledgeDocumentVersionMaxVerionByKnowledgeDocumentId(knowledgeDocumentId);
        assert maxVersion == version;
        
        String updateTitle = "updateTitle";
        String updateLcu = "lvzk";
        Integer updateSize = 12345;
        Integer updateVersion = maxVersion + 1;
        String reviewer = "reviewer";
        updateVo.setTitle(updateTitle);
        updateVo.setStatus(KnowledgeDocumentVersionStatus.SUBMITTED.getValue());
        updateVo.setLcu(updateLcu);
        updateVo.setSize(updateSize);
        updateVo.setVersion(updateVersion);
        updateVo.setReviewer(reviewer);
        assert knowledgeDocumentMapper.updateKnowledgeDocumentVersionById(updateVo) == 1;
        
        getVo = knowledgeDocumentMapper.getKnowledgeDocumentVersionById(insertVo.getId());
        assert Objects.equals(getVo.getTitle(), updateTitle);
        assert Objects.equals(getVo.getKnowledgeDocumentId(), knowledgeDocumentId);
        assert Objects.equals(getVo.getVersion(), updateVersion);
        assert Objects.equals(getVo.getStatus(), KnowledgeDocumentVersionStatus.SUBMITTED.getValue());
        assert Objects.equals(getVo.getLcu(), updateLcu);
        assert Objects.equals(getVo.getSize(), updateSize);
        assert Objects.equals(getVo.getReviewer(), reviewer);
        
        assert knowledgeDocumentMapper.updateKnowledgeDocumentVersionStatusByKnowledgeDocumentIdAndVersionAndStatus(knowledgeDocumentId, updateVersion, KnowledgeDocumentVersionStatus.SUBMITTED.getValue(), KnowledgeDocumentVersionStatus.PASSED.getValue()) == 1;
        
        getVo = knowledgeDocumentMapper.getKnowledgeDocumentVersionById(insertVo.getId());
        assert Objects.equals(getVo.getTitle(), updateTitle);
        assert Objects.equals(getVo.getKnowledgeDocumentId(), knowledgeDocumentId);
        assert Objects.equals(getVo.getVersion(), updateVersion);
        assert Objects.equals(getVo.getStatus(), KnowledgeDocumentVersionStatus.PASSED.getValue());
        assert Objects.equals(getVo.getLcu(), updateLcu);
        assert Objects.equals(getVo.getSize(), updateSize);
        assert Objects.equals(getVo.getReviewer(), reviewer);
        
        assert knowledgeDocumentMapper.deleteKnowledgeDocumentVersionById(insertVo.getId()) == 1;
        
        getVo = knowledgeDocumentMapper.getKnowledgeDocumentVersionById(insertVo.getId());
        assert getVo == null;
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_file表的增删改查语句
     */
    @Test
    public void knowledgeDocumentFile() {
        Long knowledgeDocumentId = 12L;
        Long knowledgeDocumentVersionId = 34L;
        List<Long> fileIdList = Arrays.asList(56L, 67L,78L);
        KnowledgeDocumentFileVo insertVo = new KnowledgeDocumentFileVo();
        insertVo.setKnowledgeDocumentId(knowledgeDocumentId);
        insertVo.setKnowledgeDocumentVersionId(knowledgeDocumentVersionId);
        for(Long fileId : fileIdList) {
            insertVo.setFileId(fileId);
            assert knowledgeDocumentMapper.insertKnowledgeDocumentFile(insertVo) == 1;
        }        
        
        List<Long> idList = knowledgeDocumentMapper.getKnowledgeDocumentFileIdListByKnowledgeDocumentIdAndVersionId(insertVo);
        assert Objects.equals(idList, fileIdList);
        
        knowledgeDocumentMapper.deleteKnowledgeDocumentFileByKnowledgeDocumentIdAndVersionId(insertVo);
        idList = knowledgeDocumentMapper.getKnowledgeDocumentFileIdListByKnowledgeDocumentIdAndVersionId(insertVo);
        assert CollectionUtils.isEmpty(idList);
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_tag表的增删改查语句
     */
    @Test
    public void knowledgeDocumentTag() {
        Long knowledgeDocumentId = 12L;
        Long knowledgeDocumentVersionId = 34L;
        List<Long> tagIdList = Arrays.asList(56L, 67L,78L);
        KnowledgeDocumentTagVo insertVo = new KnowledgeDocumentTagVo();
        insertVo.setKnowledgeDocumentId(knowledgeDocumentId);
        insertVo.setKnowledgeDocumentVersionId(knowledgeDocumentVersionId);
        for(Long tagId : tagIdList) {
            insertVo.setTagId(tagId);
            assert knowledgeDocumentMapper.insertKnowledgeDocumentTag(insertVo) == 1;
        }        
        
        List<Long> idList = knowledgeDocumentMapper.getKnowledgeDocumentTagIdListByKnowledgeDocumentIdAndVersionId(insertVo);
        assert Objects.equals(idList, tagIdList);
        
        knowledgeDocumentMapper.deleteKnowledgeDocumentTagByKnowledgeDocumentIdAndVersionId(insertVo);
        idList = knowledgeDocumentMapper.getKnowledgeDocumentTagIdListByKnowledgeDocumentIdAndVersionId(insertVo);
        assert CollectionUtils.isEmpty(idList);
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_line表的增删改查语句
     */
    @Test
    public void knowledgeDocumentLine() {
        String config = "{\"a1\":\"b1\"}";
        KnowledgeDocumentLineConfigVo insertConfitVo = new KnowledgeDocumentLineConfigVo(config);
        assert knowledgeDocumentMapper.insertKnowledgeDocumentLineConfig(insertConfitVo) == 1;
        
        String content = "content-linbq";
        KnowledgeDocumentLineContentVo insertContentVo = new KnowledgeDocumentLineContentVo(content);
        assert knowledgeDocumentMapper.insertKnowledgeDocumentLineContent(insertContentVo) == 1;
        
        String uuid = "abcedfghijklmnopqrstuvwxyz123456";
        String handler = "p";
        Long knowledgeDocumentId = 12L;
        Long knowledgeDocumentVersionId = 34L;
        String contentHash = insertContentVo.getHash();
        String configHash = insertConfitVo.getHash();
        Integer lineNumber = 1;
        KnowledgeDocumentLineVo insertVo = new KnowledgeDocumentLineVo();
        insertVo.setUuid(uuid);
        insertVo.setHandler(handler);
        insertVo.setKnowledgeDocumentId(knowledgeDocumentId);
        insertVo.setKnowledgeDocumentVersionId(knowledgeDocumentVersionId);
        insertVo.setContentHash(contentHash);
        insertVo.setConfigHash(configHash);
        insertVo.setLineNumber(lineNumber);
        List<KnowledgeDocumentLineVo> insertVoList = new ArrayList<>();
        insertVoList.add(insertVo);
        
        String uuid2 = "abcedfghijklmnopqrstuvwxyz123457";
        Integer lineNumber2 = 2;
        KnowledgeDocumentLineVo insertVo2 = new KnowledgeDocumentLineVo();
        insertVo2.setUuid(uuid2);
        insertVo2.setHandler(handler);
        insertVo2.setKnowledgeDocumentId(knowledgeDocumentId);
        insertVo2.setKnowledgeDocumentVersionId(knowledgeDocumentVersionId);
        insertVo2.setContentHash(contentHash);
        insertVo2.setConfigHash(configHash);
        insertVo2.setLineNumber(lineNumber2);
        insertVoList.add(insertVo2);
        assert knowledgeDocumentMapper.insertKnowledgeDocumentLineList(insertVoList) == 2;
        
        List<KnowledgeDocumentLineVo> getVoList = knowledgeDocumentMapper.getKnowledgeDocumentLineListByKnowledgeDocumentVersionId(knowledgeDocumentVersionId);
        assert getVoList.size() == 2;
        assert Objects.equals(getVoList.get(0).getUuid(), uuid);
        assert Objects.equals(getVoList.get(0).getHandler(), handler);
        assert Objects.equals(getVoList.get(0).getKnowledgeDocumentId(), knowledgeDocumentId);
        assert Objects.equals(getVoList.get(0).getKnowledgeDocumentVersionId(), knowledgeDocumentVersionId);
        assert Objects.equals(getVoList.get(0).getContentHash(), contentHash);
        assert Objects.equals(getVoList.get(0).getConfigHash(), configHash);
        assert Objects.equals(getVoList.get(0).getContent(), content);
        assert Objects.equals(getVoList.get(0).getConfigStr(), config);
        assert Objects.equals(getVoList.get(0).getLineNumber(), lineNumber);
        
        assert knowledgeDocumentMapper.deleteKnowledgeDocumentLineByKnowledgeDocumentVersionId(knowledgeDocumentVersionId) == 2;
        getVoList = knowledgeDocumentMapper.getKnowledgeDocumentLineListByKnowledgeDocumentVersionId(knowledgeDocumentVersionId);
        assert getVoList.size() == 0;
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_line_config表的增查语句
     */
    @Test
    public void knowledgeDocumentLineConfig() {
        String config = "{\"a\":\"b\"}";
        KnowledgeDocumentLineConfigVo insertConfitVo = new KnowledgeDocumentLineConfigVo(config);
        assert knowledgeDocumentMapper.insertKnowledgeDocumentLineConfig(insertConfitVo) == 1;
        assert knowledgeDocumentMapper.checkKnowledgeDocumentLineConfigHashIsExists(insertConfitVo.getHash()) == 1;
    }
    /**
     * 
    * @Time:2020年10月19日
    * @Description: 测试knowledge_document_line_config表的增查语句
     */
    @Test
    public void knowledgeDocumentLineContent() {
        String content = "content1";
        KnowledgeDocumentLineContentVo insertContentVo = new KnowledgeDocumentLineContentVo(content);
        assert knowledgeDocumentMapper.insertKnowledgeDocumentLineContent(insertContentVo) == 1;
        assert knowledgeDocumentMapper.checkKnowledgeDocumentLineContentHashIsExists(insertContentVo.getHash()) == 1;
    }
}
