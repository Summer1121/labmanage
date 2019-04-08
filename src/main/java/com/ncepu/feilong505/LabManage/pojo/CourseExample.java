package com.ncepu.feilong505.LabManage.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdIsNull() {
            addCriterion("course_teacher_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdIsNotNull() {
            addCriterion("course_teacher_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdEqualTo(Long value) {
            addCriterion("course_teacher_id =", value, "courseTeacherId");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdNotEqualTo(Long value) {
            addCriterion("course_teacher_id <>", value, "courseTeacherId");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdGreaterThan(Long value) {
            addCriterion("course_teacher_id >", value, "courseTeacherId");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdGreaterThanOrEqualTo(Long value) {
            addCriterion("course_teacher_id >=", value, "courseTeacherId");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdLessThan(Long value) {
            addCriterion("course_teacher_id <", value, "courseTeacherId");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdLessThanOrEqualTo(Long value) {
            addCriterion("course_teacher_id <=", value, "courseTeacherId");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdIn(List<Long> values) {
            addCriterion("course_teacher_id in", values, "courseTeacherId");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdNotIn(List<Long> values) {
            addCriterion("course_teacher_id not in", values, "courseTeacherId");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdBetween(Long value1, Long value2) {
            addCriterion("course_teacher_id between", value1, value2, "courseTeacherId");
            return (Criteria) this;
        }

        public Criteria andCourseTeacherIdNotBetween(Long value1, Long value2) {
            addCriterion("course_teacher_id not between", value1, value2, "courseTeacherId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIsNull() {
            addCriterion("course_status is null");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIsNotNull() {
            addCriterion("course_status is not null");
            return (Criteria) this;
        }

        public Criteria andCourseStatusEqualTo(Integer value) {
            addCriterion("course_status =", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNotEqualTo(Integer value) {
            addCriterion("course_status <>", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusGreaterThan(Integer value) {
            addCriterion("course_status >", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_status >=", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusLessThan(Integer value) {
            addCriterion("course_status <", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusLessThanOrEqualTo(Integer value) {
            addCriterion("course_status <=", value, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIn(List<Integer> values) {
            addCriterion("course_status in", values, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNotIn(List<Integer> values) {
            addCriterion("course_status not in", values, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusBetween(Integer value1, Integer value2) {
            addCriterion("course_status between", value1, value2, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("course_status not between", value1, value2, "courseStatus");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeIsNull() {
            addCriterion("course_build_time is null");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeIsNotNull() {
            addCriterion("course_build_time is not null");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeEqualTo(Date value) {
            addCriterion("course_build_time =", value, "courseBuildTime");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeNotEqualTo(Date value) {
            addCriterion("course_build_time <>", value, "courseBuildTime");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeGreaterThan(Date value) {
            addCriterion("course_build_time >", value, "courseBuildTime");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("course_build_time >=", value, "courseBuildTime");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeLessThan(Date value) {
            addCriterion("course_build_time <", value, "courseBuildTime");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeLessThanOrEqualTo(Date value) {
            addCriterion("course_build_time <=", value, "courseBuildTime");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeIn(List<Date> values) {
            addCriterion("course_build_time in", values, "courseBuildTime");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeNotIn(List<Date> values) {
            addCriterion("course_build_time not in", values, "courseBuildTime");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeBetween(Date value1, Date value2) {
            addCriterion("course_build_time between", value1, value2, "courseBuildTime");
            return (Criteria) this;
        }

        public Criteria andCourseBuildTimeNotBetween(Date value1, Date value2) {
            addCriterion("course_build_time not between", value1, value2, "courseBuildTime");
            return (Criteria) this;
        }

        public Criteria andCourseDetailIsNull() {
            addCriterion("course_detail is null");
            return (Criteria) this;
        }

        public Criteria andCourseDetailIsNotNull() {
            addCriterion("course_detail is not null");
            return (Criteria) this;
        }

        public Criteria andCourseDetailEqualTo(String value) {
            addCriterion("course_detail =", value, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseDetailNotEqualTo(String value) {
            addCriterion("course_detail <>", value, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseDetailGreaterThan(String value) {
            addCriterion("course_detail >", value, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseDetailGreaterThanOrEqualTo(String value) {
            addCriterion("course_detail >=", value, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseDetailLessThan(String value) {
            addCriterion("course_detail <", value, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseDetailLessThanOrEqualTo(String value) {
            addCriterion("course_detail <=", value, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseDetailLike(String value) {
            addCriterion("course_detail like", value, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseDetailNotLike(String value) {
            addCriterion("course_detail not like", value, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseDetailIn(List<String> values) {
            addCriterion("course_detail in", values, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseDetailNotIn(List<String> values) {
            addCriterion("course_detail not in", values, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseDetailBetween(String value1, String value2) {
            addCriterion("course_detail between", value1, value2, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseDetailNotBetween(String value1, String value2) {
            addCriterion("course_detail not between", value1, value2, "courseDetail");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupIsNull() {
            addCriterion("course_is_group is null");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupIsNotNull() {
            addCriterion("course_is_group is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupEqualTo(Integer value) {
            addCriterion("course_is_group =", value, "courseIsGroup");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupNotEqualTo(Integer value) {
            addCriterion("course_is_group <>", value, "courseIsGroup");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupGreaterThan(Integer value) {
            addCriterion("course_is_group >", value, "courseIsGroup");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_is_group >=", value, "courseIsGroup");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupLessThan(Integer value) {
            addCriterion("course_is_group <", value, "courseIsGroup");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupLessThanOrEqualTo(Integer value) {
            addCriterion("course_is_group <=", value, "courseIsGroup");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupIn(List<Integer> values) {
            addCriterion("course_is_group in", values, "courseIsGroup");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupNotIn(List<Integer> values) {
            addCriterion("course_is_group not in", values, "courseIsGroup");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupBetween(Integer value1, Integer value2) {
            addCriterion("course_is_group between", value1, value2, "courseIsGroup");
            return (Criteria) this;
        }

        public Criteria andCourseIsGroupNotBetween(Integer value1, Integer value2) {
            addCriterion("course_is_group not between", value1, value2, "courseIsGroup");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumIsNull() {
            addCriterion("course_attend_sum is null");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumIsNotNull() {
            addCriterion("course_attend_sum is not null");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumEqualTo(Integer value) {
            addCriterion("course_attend_sum =", value, "courseAttendSum");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumNotEqualTo(Integer value) {
            addCriterion("course_attend_sum <>", value, "courseAttendSum");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumGreaterThan(Integer value) {
            addCriterion("course_attend_sum >", value, "courseAttendSum");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_attend_sum >=", value, "courseAttendSum");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumLessThan(Integer value) {
            addCriterion("course_attend_sum <", value, "courseAttendSum");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumLessThanOrEqualTo(Integer value) {
            addCriterion("course_attend_sum <=", value, "courseAttendSum");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumIn(List<Integer> values) {
            addCriterion("course_attend_sum in", values, "courseAttendSum");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumNotIn(List<Integer> values) {
            addCriterion("course_attend_sum not in", values, "courseAttendSum");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumBetween(Integer value1, Integer value2) {
            addCriterion("course_attend_sum between", value1, value2, "courseAttendSum");
            return (Criteria) this;
        }

        public Criteria andCourseAttendSumNotBetween(Integer value1, Integer value2) {
            addCriterion("course_attend_sum not between", value1, value2, "courseAttendSum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}