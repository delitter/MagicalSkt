package com.sktelecom.t1.Daos;

import com.sktelecom.t1.Models.Players;
import java.util.List;

public interface PlayersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table players
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table players
     *
     * @mbg.generated
     */
    int insert(Players record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table players
     *
     * @mbg.generated
     */
    Players selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table players
     *
     * @mbg.generated
     */
    List<Players> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table players
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Players record);
}