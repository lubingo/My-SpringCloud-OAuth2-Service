package com.bingo.auth.core.dao.resource;

import com.bingo.auth.core.entity.resource.OauthResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OauthResourceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_resource
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_resource
     *
     * @mbggenerated
     */
    int insert(OauthResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_resource
     *
     * @mbggenerated
     */
    int insertSelective(OauthResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_resource
     *
     * @mbggenerated
     */
    OauthResource selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_resource
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(OauthResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_resource
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OauthResource record);

    List<OauthResource> selectByResourceUrl(String resourceUrl);
}