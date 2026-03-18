package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sasu.platform.mhm.mapper.CmsMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.NoticeCreateDTO;
import sasu.platform.mhm.pojo.dto.NoticeListQueryDTO;
import sasu.platform.mhm.pojo.dto.NoticeUpdateDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.CmsContent;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.pojo.vo.CmsContentVO;
import sasu.platform.mhm.service.CmsService;
import sasu.platform.mhm.service.NoticeService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 通知公告服务实现
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private CmsMapper cmsMapper;

    @Autowired
    private CmsService cmsService;

    /**
     * 通知公告类型
     */
    private static final Integer NOTICE_TYPE = 2;

    /**
     * 获取当前用户ID
     */
    private String currentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getUser().getUserId();
        }
        return null;
    }

    @Override
    public PageResult list(NoticeListQueryDTO query) {
        if (query == null) {
            query = new NoticeListQueryDTO();
        }
        if (query.getPage() == null || query.getPage() <= 0) {
            query.setPage(1);
        }
        if (query.getPageSize() == null || query.getPageSize() <= 0) {
            query.setPageSize(10);
        }

        // 构建查询DTO
        PageQueryDTO pageQueryDTO = new PageQueryDTO();
        pageQueryDTO.setPage(query.getPage());
        pageQueryDTO.setPageSize(query.getPageSize());
        pageQueryDTO.setType(NOTICE_TYPE);
        pageQueryDTO.setKeyWords(query.getKeyword());
        pageQueryDTO.setStatus(query.getStatus() != null ? query.getStatus() : 1);

        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<CmsContent> page = cmsMapper.getCmss(pageQueryDTO);
        
        // 转换为VO
        java.util.List<CmsContentVO> voList = page.getResult().stream().map(content -> {
            CmsContentVO vo = new CmsContentVO();
            org.springframework.beans.BeanUtils.copyProperties(content, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        
        return new PageResult(page.getTotal(), voList);
    }

    @Override
    public CmsContentVO detail(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("通知ID不能为空");
        }
        CmsContentVO content = cmsService.getCmsDetail(id);
        if (content == null || !NOTICE_TYPE.equals(content.getType())) {
            throw new IllegalArgumentException("通知不存在");
        }
        return content;
    }

    @Override
    public CmsContentVO create(NoticeCreateDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("通知内容不能为空");
        }
        if (!StringUtils.hasText(dto.getTitle())) {
            throw new IllegalArgumentException("标题不能为空");
        }

        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new IllegalArgumentException("未登录");
        }

        CmsContent content = new CmsContent();
        content.setType(NOTICE_TYPE);
        content.setTitle(dto.getTitle());
        content.setSummary(dto.getSummary());
        content.setContent(dto.getContent());
        content.setIsTop(dto.getIsTop() != null && dto.getIsTop());
        content.setStatus(dto.getStatus() != null ? dto.getStatus() : 0);
        content.setSortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : 0);
        content.setCreatorId(userId);

        if (StringUtils.hasText(dto.getPublishTime())) {
            try {
                content.setPublishTime(LocalDateTime.parse(dto.getPublishTime(), DateTimeFormatter.ISO_DATE_TIME));
            } catch (Exception e) {
                content.setPublishTime(LocalDateTime.now());
            }
        } else if (content.getStatus() == 1) {
            content.setPublishTime(LocalDateTime.now());
        }

        int result = cmsMapper.insertCmsContent(content);
        if (result <= 0) {
            throw new RuntimeException("创建通知失败");
        }

        return cmsService.getCmsDetail(content.getId());
    }

    @Override
    public CmsContentVO update(NoticeUpdateDTO dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("通知ID不能为空");
        }

        // 检查通知是否存在
        CmsContentVO existing = cmsService.getCmsDetail(dto.getId());
        if (existing == null || !NOTICE_TYPE.equals(existing.getType())) {
            throw new IllegalArgumentException("通知不存在");
        }

        CmsContent content = new CmsContent();
        content.setId(dto.getId());
        content.setTitle(dto.getTitle());
        content.setSummary(dto.getSummary());
        content.setContent(dto.getContent());
        content.setIsTop(dto.getIsTop());
        content.setStatus(dto.getStatus());
        content.setSortOrder(dto.getSortOrder());

        if (StringUtils.hasText(dto.getPublishTime())) {
            try {
                content.setPublishTime(LocalDateTime.parse(dto.getPublishTime(), DateTimeFormatter.ISO_DATE_TIME));
            } catch (Exception e) {
                // 忽略解析错误
            }
        }

        int result = cmsMapper.updateCmsContent(content);
        if (result <= 0) {
            throw new RuntimeException("更新通知失败");
        }

        return cmsService.getCmsDetail(dto.getId());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("通知ID不能为空");
        }

        // 检查通知是否存在
        CmsContentVO existing = cmsService.getCmsDetail(id);
        if (existing == null || !NOTICE_TYPE.equals(existing.getType())) {
            throw new IllegalArgumentException("通知不存在");
        }

        int result = cmsMapper.deleteCmsContent(id);
        if (result <= 0) {
            throw new RuntimeException("删除通知失败");
        }
    }
}

