package neatlogic.framework.message.dao.mapper;

import neatlogic.BaseTest;
import neatlogic.framework.common.constvalue.GroupSearch;
import neatlogic.framework.message.dto.MessageRecipientVo;
import neatlogic.framework.message.dto.MessageSearchVo;
import neatlogic.framework.message.dto.MessageVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: NewsMapperTest
 * @Package neatlogic.framework.news.dao.mapper
 * @Description: TODO
 * @Author: 14378
 * @Date: 2020/12/31 17:12
 **/
public class MessageMapperTest extends BaseTest {

    @Autowired
    public MessageMapper messageMapper;

    @Test
    public void generateData() {
        String lvzk = "20f120b897cf11ea94ff005056c00001";
        String handler = "neatlogic.framework.process.news.handler.ProcessTaskNewsHandler";
        List<MessageVo> messageVoList = new ArrayList<>(1000);
        List<MessageRecipientVo> messageRecipientVoList = new ArrayList<>(1000);
        List<MessageSearchVo> messageSearchVoList = new ArrayList<>(1000);
        for (int i = 1; i <= 1; i++) {
            for (int j = 1; j <= 1000; j++) {
                MessageVo messageVo = new MessageVo();
                messageVo.setTitle("title_" + messageVo.getId());
                messageVo.setContent("content_" + messageVo.getId());
                messageVo.setHandler(handler);
                messageVoList.add(messageVo);

                MessageRecipientVo messageRecipientVo = new MessageRecipientVo();
                messageRecipientVo.setMessageId(messageVo.getId());
                messageRecipientVo.setType(GroupSearch.USER.getValue());
                messageRecipientVo.setUuid(lvzk);
                messageRecipientVoList.add(messageRecipientVo);

                MessageSearchVo messageSearchVo = new MessageSearchVo();
                messageSearchVo.setMessageId(messageVo.getId());
                messageSearchVo.setUserUuid(lvzk);
                messageSearchVoList.add(messageSearchVo);
            }
            messageMapper.insertMessage(messageVoList);
            messageMapper.insertMessageRecipient(messageRecipientVoList);
            messageMapper.insertMessageUser(messageSearchVoList);
            messageVoList.clear();
            messageRecipientVoList.clear();
            messageSearchVoList.clear();
        }
    }
}
