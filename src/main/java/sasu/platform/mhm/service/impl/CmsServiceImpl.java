package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import sasu.platform.mhm.mapper.CmsMapper;
import sasu.platform.mhm.pojo.vo.CmsContentVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.CmsCreateDTO;
import sasu.platform.mhm.pojo.dto.CmsUpdateDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.CmsContent;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.pojo.vo.CmsContentVO;
import sasu.platform.mhm.service.CmsService;
import com.github.pagehelper.PageHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CmsServiceImpl implements CmsService {

    @Autowired
    private CmsMapper cmsMapper;
    @Override
    public PageResult getCmss(int status, PageQueryDTO pageQueryDTO) {

        pageQueryDTO.setStatus(status);
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        //下一条sql进行分页，自动加入limit关键字分页
        Page<CmsContent> page = cmsMapper.getCmss(pageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    @Override
    public CmsContentVO getCmsDetail(Long id) {
        CmsContent cmsContent =  cmsMapper.getCmsDetail(id);
        if (cmsContent != null) {
            CmsContentVO cmsContentVO = new CmsContentVO();
            BeanUtils.copyProperties(cmsContent, cmsContentVO);
            return cmsContentVO;
        }
        return null;
    }

    @Override
    public CmsContentVO create(CmsCreateDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("内容不能为空");
        }
        if (!StringUtils.hasText(dto.getTitle())) {
            throw new IllegalArgumentException("标题不能为空");
        }

        String userId = getCurrentUserId();
        CmsContent content = new CmsContent();
        content.setType(dto.getType());
        content.setTitle(dto.getTitle());
        content.setSummary(dto.getSummary());
        content.setContent(dto.getContent());
        content.setCoverUrl(dto.getCoverUrl());
        content.setLinkUrl(dto.getLinkUrl());
        content.setAuthor(dto.getAuthor());
        content.setSourceOrg(dto.getSourceOrg());
        content.setIsTop(dto.getIsTop());
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
            throw new RuntimeException("创建内容失败");
        }

        return getCmsDetail(content.getId());
    }

    @Override
    public CmsContentVO update(CmsUpdateDTO dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("内容ID不能为空");
        }

        CmsContent content = new CmsContent();
        content.setId(dto.getId());
        content.setTitle(dto.getTitle());
        content.setSummary(dto.getSummary());
        content.setContent(dto.getContent());
        content.setCoverUrl(dto.getCoverUrl());
        content.setLinkUrl(dto.getLinkUrl());
        content.setAuthor(dto.getAuthor());
        content.setSourceOrg(dto.getSourceOrg());
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
            throw new RuntimeException("更新内容失败");
        }

        return getCmsDetail(dto.getId());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("内容ID不能为空");
        }
        int result = cmsMapper.deleteCmsContent(id);
        if (result <= 0) {
            throw new RuntimeException("删除内容失败");
        }
    }

    @Override
    public void publish(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("内容ID不能为空");
        }
        CmsContent content = new CmsContent();
        content.setId(id);
        content.setStatus(1);
        content.setPublishTime(LocalDateTime.now());
        int result = cmsMapper.updateCmsContent(content);
        if (result <= 0) {
            throw new RuntimeException("发布内容失败");
        }
    }

    private String getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getUser().getUserId();
        }
        return null;
    }
}
