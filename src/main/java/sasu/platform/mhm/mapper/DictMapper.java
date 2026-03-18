package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.DictItem;
import sasu.platform.mhm.pojo.entity.DictType;

import java.util.List;

@Mapper
public interface DictMapper {

    List<DictType> selectTypePage(PageQueryDTO query);

    int insertType(DictType type);

    int updateType(DictType type);

    List<DictItem> selectItemPage(@Param("typeCode") String typeCode,
                                  @Param("offset") int offset,
                                  @Param("limit") int limit);

    int insertItem(DictItem item);

    int updateItem(DictItem item);

    int deleteItem(@Param("id") String id);
}


