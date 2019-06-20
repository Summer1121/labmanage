package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.Attend;
import com.ncepu.feilong505.LabManage.pojo.AttendExample;
import com.ncepu.feilong505.LabManage.vo.AttendFileOut;
import com.ncepu.feilong505.LabManage.vo.AttendFileOutWithCondition;
import com.ncepu.feilong505.LabManage.vo.AttendStatis;

import java.util.Date;
import java.util.List;

import javax.annotation.sql.DataSourceDefinition;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Mapper
public interface AttendMapper {
    long countByExample(AttendExample example);

    int deleteByExample(AttendExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    List<Attend> selectByExample(AttendExample example);

    Attend selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Attend record, @Param("example") AttendExample example);

    int updateByExample(@Param("record") Attend record, @Param("example") AttendExample example);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    /**
     * 
     * TODO 通过课堂ID与签到id查询签到列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月20日
     * @param courseId
     * @return
     */
    List<AttendStatis> selectAttendByCourse(Long courseId, Long id, Integer flag);
    
    /**
     * 
     * TODO 查找某课堂签到的签到时间  （第一个签到者的时间）
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月13日
     * @param courseID
     * @param id
     * @return
     */
    @Select("select max(attend_arrive_time) from attend join course_user cu on cu.id=attend.attend_course_user_id where cu.course_id=#{courseId} and attend.id=#{id}")
    Date selectAttendTime(Long courseId,Long id);

    /**
     * 
     * TODO 获取某课堂某次签到的签到人数统计
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月27日
     * @param courseId
     * @param id
     * @param flag
     * @return
     */
    int selectAttendCount(Long courseId, Long id);

    /**
     * 
     * TODO 获取班级签到统计列表
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年4月20日
     * @param courseId
     * @return
     */
    List<AttendStatis> selectAttendStatis(Long courseId);

    /**
     * 
     * TODO 获取某个班级中的所有用户的所有签到记录，按照由内到外（由先到后）分别按照userId，id，排序
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月11日
     * @param courseId
     * @return
     */
    List<AttendFileOut> selectAttendCourseRecord(Long courseId);

    /**
     * 
     * TODO 按条件查询用户记录
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月12日
     * @param userNum
     * @param userName
     * @param userClass
     * @param courseId
     * @param startTime
     * @param endTime
     * @param startNum
     * @param pageSize
     * @return
     */
    List<AttendFileOutWithCondition> selectAttendWithCondition(String userNum, String userName, String userClass,

	    String courseId, Date startTime, Date endTime, Integer startNum, Integer pageSize);

    /**
     * 
     * TODO 查询结果总数
     * 
     * @author xtysummer1121@foxmail.com
     * @date 2019年6月13日
     * @param userNum
     * @param userName
     * @param userClass
     * @param courseId
     * @param startTime
     * @param endTime
     * @return
     */
    Integer selectAttendWithConditionCount(String userNum, String userName, String userClass,

	    String courseId, Date startTime, Date endTime);
}