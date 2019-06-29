package com.ncepu.feilong505.LabManage.mapper;

import com.ncepu.feilong505.LabManage.pojo.Attend;
import com.ncepu.feilong505.LabManage.pojo.AttendExample;
import com.ncepu.feilong505.LabManage.vo.AttendFileOut;
import com.ncepu.feilong505.LabManage.vo.AttendFileOutWithCondition;
import com.ncepu.feilong505.LabManage.vo.AttendStatis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

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
	 * TODO 通过课堂ID与签到id查询签到列表
	 *
	 * @param courseId
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月20日
	 */
	List<AttendStatis> selectAttendByCourse(Long courseId, Long id, Integer flag);

	/**
	 * TODO 查找某课堂签到的签到时间  （第一个签到者的时间）
	 *
	 * @param courseId
	 * @param id
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月13日
	 */
	@Select("select max(attend_arrive_time) from attend join course_user cu on cu.id=attend.attend_course_user_id where cu.course_id=#{courseId} and attend.id=#{id}")
	Date selectAttendTime(Long courseId, Long id);

	/**
	 * TODO 获取某课堂某次签到的签到人数统计
	 *
	 * @param courseId
	 * @param id
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月27日
	 */
	int selectAttendCount(Long courseId, Long id);

	/**
	 * TODO 获取班级签到统计列表
	 *
	 * @param courseId
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月20日
	 */
	List<AttendStatis> selectAttendStatis(Long courseId);

	/**
	 * TODO 获取某个班级中的所有用户的所有签到记录，按照由内到外（由先到后）分别按照userId，id，排序
	 *
	 * @param courseId
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月11日
	 */
	List<AttendFileOut> selectAttendCourseRecord(Long courseId);

	/**
	 * TODO 按条件查询用户记录
	 *
	 * @param userNum
	 * @param userName
	 * @param userClass
	 * @param courseId
	 * @param startTime
	 * @param endTime
	 * @param startNum
	 * @param pageSize
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月12日
	 */
	List<AttendFileOutWithCondition> selectAttendWithCondition(String userNum, String userName, String userClass,

	                                                           String courseId, Date startTime, Date endTime, Integer startNum, Integer pageSize);

	/**
	 * TODO 查询结果总数
	 *
	 * @param userNum
	 * @param userName
	 * @param userClass
	 * @param courseId
	 * @param startTime
	 * @param endTime
	 * @return
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年6月13日
	 */
	Integer selectAttendWithConditionCount(String userNum, String userName, String userClass,

	                                       String courseId, Date startTime, Date endTime);
}