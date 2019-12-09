package spring.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import domain.ChartData;
import domain.ResultVO;

public interface ResultMapper {
	static String column_all = " * ";

	List<ChartData> getAvgOfDepByHour();

	List<ChartData> getAvgOfArrByHour();

	List<ChartData> getAllOfDep();

	List<ChartData> getAllOfArr();

	@Select("select * from T_H_RESULT where status=1 order by DT_DEPARTURE limit #{cursor}, #{offset}")
	@ResultMap("resultResultMap")
	List<ResultVO> getBusList(@Param("cursor") Integer cursor, @Param("offset") Integer offset);

	@Select("select count(*) from T_H_RESULT where status=1")
	Integer getResultCount();

	@Select("select count(*) from T_H_RESULT where status=1 and N_CARNUM = #{carNum}")
	Integer getResultCountByCarNum(@Param("carNum") Integer carNum);

	@Select("select * from T_H_RESULT where status=1 and N_CARNUM = #{carNum} order by DT_DEPARTURE limit #{cursor}, #{offset}")
	@ResultMap("resultResultMap")
	List<ResultVO> getSchduleList(@Param("carNum") Integer carNum, @Param("cursor") Integer cursor,
			@Param("offset") Integer offset);

	@Select("select * from T_H_RESULT where N_RESULTSID = #{resultId}")
	@ResultMap("resultResultMap")
	ResultVO getResultInfo(@Param("resultId") Integer resultId);
}
