package sasu.platform.mhm.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sasu.platform.mhm.mapper.TeacherMapper;
import sasu.platform.mhm.pojo.dto.CounselorListQueryDTO;
import sasu.platform.mhm.pojo.dto.TeacherInboxQueryDTO;
import sasu.platform.mhm.pojo.dto.TeacherReplyDTO;
import sasu.platform.mhm.pojo.entity.TeacherMessage;
import sasu.platform.mhm.pojo.vo.CounselorVO;
import sasu.platform.mhm.pojo.vo.TeacherMessageVO;
import sasu.platform.mhm.service.TeacherService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper teacherMapper;

    @Override
    public List<TeacherMessageVO> inbox(TeacherInboxQueryDTO query) {
        String currentUserId = null;
        return teacherMapper.selectInbox(currentUserId, query);
    }

    @Override
    @Transactional
    public void reply(TeacherReplyDTO dto) {
        String currentUserId = null;

        TeacherMessage original = teacherMapper.selectMessageById(dto.getMessageId());
        if (original == null) {
            log.warn("原始消息不存在, messageId={}", dto.getMessageId());
            return;
        }

        // 标记原消息已读
        original.setIsRead(true);
        original.setReplied(true);
        original.setReplyContent(dto.getContent());
        original.setUpdatedAt(LocalDateTime.now());
        teacherMapper.updateMessage(original);

        // 生成回复消息，sender=当前教师, receiver=原发送人
        TeacherMessage reply = new TeacherMessage();
        reply.setMessageId(UUID.randomUUID().toString());
        reply.setSenderId(currentUserId);
        reply.setReceiverId(original.getSenderId());
        reply.setStudentId(original.getStudentId());
        reply.setParentId(original.getParentId());
        reply.setSubject("Re: " + (original.getSubject() == null ? "" : original.getSubject()));
        reply.setContent(dto.getContent());
        reply.setFromRole("teacher");
        reply.setReplyToId(original.getMessageId());
        reply.setIsRead(false);
        reply.setReplied(false);
        reply.setReplyContent(null);
        LocalDateTime now = LocalDateTime.now();
        reply.setCreatedAt(now);
        reply.setUpdatedAt(now);

        teacherMapper.insertMessage(reply);
    }

    @Override
    public List<CounselorVO> counselorList(CounselorListQueryDTO query) {
        return teacherMapper.selectCounselorList(query);
    }
}


