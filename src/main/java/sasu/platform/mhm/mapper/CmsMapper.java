package sasu.platform.mhm.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.CmsContent;

@Mapper
public interface CmsMapper {

    Page<CmsContent> getCmss(PageQueryDTO pageQueryDTO);

    CmsContent getCmsDetail(Long id);

    /**
     * 插入CMS内容
     */
    int insertCmsContent(CmsContent content);

    /**
     * 更新CMS内容
     */
    int updateCmsContent(CmsContent content);

    /**
     * 删除CMS内容
     */
    int deleteCmsContent(Long id);
}
