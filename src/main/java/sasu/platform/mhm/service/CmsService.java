package sasu.platform.mhm.service;

import org.springframework.stereotype.Service;
import sasu.platform.mhm.pojo.vo.CmsContentVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.vo.CmsContentVO;

@Service
public interface CmsService {
    PageResult getCmss(int status, PageQueryDTO pageQueryDTO);

    CmsContentVO getCmsDetail(Long id);

    /**
     * 创建CMS内容
     */
    CmsContentVO create(sasu.platform.mhm.pojo.dto.CmsCreateDTO dto);

    /**
     * 更新CMS内容
     */
    CmsContentVO update(sasu.platform.mhm.pojo.dto.CmsUpdateDTO dto);

    /**
     * 删除CMS内容
     */
    void delete(Long id);

    /**
     * 发布CMS内容
     */
    void publish(Long id);
}
