package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.NoticeCreateDTO;
import sasu.platform.mhm.pojo.dto.NoticeListQueryDTO;
import sasu.platform.mhm.pojo.dto.NoticeUpdateDTO;
import sasu.platform.mhm.pojo.vo.CmsContentVO;

/**
 * 通知公告服务接口
 */
public interface NoticeService {

    /**
     * 查询通知列表
     */
    PageResult list(NoticeListQueryDTO query);

    /**
     * 查询通知详情
     */
    CmsContentVO detail(Long id);

    /**
     * 创建通知
     */
    CmsContentVO create(NoticeCreateDTO dto);

    /**
     * 更新通知
     */
    CmsContentVO update(NoticeUpdateDTO dto);

    /**
     * 删除通知
     */
    void delete(Long id);
}

