package com.example.demo01.mapper;

import com.example.demo01.model.Question;
import com.example.demo01.model.QuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface QuestionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    long countByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    int deleteByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    int insert(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    int insertSelective(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    List<Question> selectByExampleWithBLOBsWithRowbounds(QuestionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    List<Question> selectByExampleWithBLOBs(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    List<Question> selectByExampleWithRowbounds(QuestionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    List<Question> selectByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    Question selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") Question record, @Param("example") QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    int updateByPrimaryKeySelective(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Sat Mar 21 18:42:07 CST 2020
     */
    int updateByPrimaryKey(Question record);
}