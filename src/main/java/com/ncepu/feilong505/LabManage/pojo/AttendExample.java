package com.ncepu.feilong505.LabManage.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AttendExample() {
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

        public Criteria andAttendCourseUserIdIsNull() {
            addCriterion("attend_course_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAttendCourseUserIdIsNotNull() {
            addCriterion("attend_course_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttendCourseUserIdEqualTo(Long value) {
            addCriterion("attend_course_user_id =", value, "attendCourseUserId");
            return (Criteria) this;
        }

        public Criteria andAttendCourseUserIdNotEqualTo(Long value) {
            addCriterion("attend_course_user_id <>", value, "attendCourseUserId");
            return (Criteria) this;
        }

        public Criteria andAttendCourseUserIdGreaterThan(Long value) {
            addCriterion("attend_course_user_id >", value, "attendCourseUserId");
            return (Criteria) this;
        }

        public Criteria andAttendCourseUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("attend_course_user_id >=", value, "attendCourseUserId");
            return (Criteria) this;
        }

        public Criteria andAttendCourseUserIdLessThan(Long value) {
            addCriterion("attend_course_user_id <", value, "attendCourseUserId");
            return (Criteria) this;
        }

        public Criteria andAttendCourseUserIdLessThanOrEqualTo(Long value) {
            addCriterion("attend_course_user_id <=", value, "attendCourseUserId");
            return (Criteria) this;
        }

        public Criteria andAttendCourseUserIdIn(List<Long> values) {
            addCriterion("attend_course_user_id in", values, "attendCourseUserId");
            return (Criteria) this;
        }

        public Criteria andAttendCourseUserIdNotIn(List<Long> values) {
            addCriterion("attend_course_user_id not in", values, "attendCourseUserId");
            return (Criteria) this;
        }

        public Criteria andAttendCourseUserIdBetween(Long value1, Long value2) {
            addCriterion("attend_course_user_id between", value1, value2, "attendCourseUserId");
            return (Criteria) this;
        }

        public Criteria andAttendCourseUserIdNotBetween(Long value1, Long value2) {
            addCriterion("attend_course_user_id not between", value1, value2, "attendCourseUserId");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeIsNull() {
            addCriterion("attend_arrive_time is null");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeIsNotNull() {
            addCriterion("attend_arrive_time is not null");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeEqualTo(Date value) {
            addCriterion("attend_arrive_time =", value, "attendArriveTime");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeNotEqualTo(Date value) {
            addCriterion("attend_arrive_time <>", value, "attendArriveTime");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeGreaterThan(Date value) {
            addCriterion("attend_arrive_time >", value, "attendArriveTime");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("attend_arrive_time >=", value, "attendArriveTime");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeLessThan(Date value) {
            addCriterion("attend_arrive_time <", value, "attendArriveTime");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeLessThanOrEqualTo(Date value) {
            addCriterion("attend_arrive_time <=", value, "attendArriveTime");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeIn(List<Date> values) {
            addCriterion("attend_arrive_time in", values, "attendArriveTime");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeNotIn(List<Date> values) {
            addCriterion("attend_arrive_time not in", values, "attendArriveTime");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeBetween(Date value1, Date value2) {
            addCriterion("attend_arrive_time between", value1, value2, "attendArriveTime");
            return (Criteria) this;
        }

        public Criteria andAttendArriveTimeNotBetween(Date value1, Date value2) {
            addCriterion("attend_arrive_time not between", value1, value2, "attendArriveTime");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeIsNull() {
            addCriterion("attend_leave_time is null");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeIsNotNull() {
            addCriterion("attend_leave_time is not null");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeEqualTo(Date value) {
            addCriterion("attend_leave_time =", value, "attendLeaveTime");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeNotEqualTo(Date value) {
            addCriterion("attend_leave_time <>", value, "attendLeaveTime");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeGreaterThan(Date value) {
            addCriterion("attend_leave_time >", value, "attendLeaveTime");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("attend_leave_time >=", value, "attendLeaveTime");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeLessThan(Date value) {
            addCriterion("attend_leave_time <", value, "attendLeaveTime");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeLessThanOrEqualTo(Date value) {
            addCriterion("attend_leave_time <=", value, "attendLeaveTime");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeIn(List<Date> values) {
            addCriterion("attend_leave_time in", values, "attendLeaveTime");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeNotIn(List<Date> values) {
            addCriterion("attend_leave_time not in", values, "attendLeaveTime");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeBetween(Date value1, Date value2) {
            addCriterion("attend_leave_time between", value1, value2, "attendLeaveTime");
            return (Criteria) this;
        }

        public Criteria andAttendLeaveTimeNotBetween(Date value1, Date value2) {
            addCriterion("attend_leave_time not between", value1, value2, "attendLeaveTime");
            return (Criteria) this;
        }

        public Criteria andAttendStatusIsNull() {
            addCriterion("attend_status is null");
            return (Criteria) this;
        }

        public Criteria andAttendStatusIsNotNull() {
            addCriterion("attend_status is not null");
            return (Criteria) this;
        }

        public Criteria andAttendStatusEqualTo(Integer value) {
            addCriterion("attend_status =", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusNotEqualTo(Integer value) {
            addCriterion("attend_status <>", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusGreaterThan(Integer value) {
            addCriterion("attend_status >", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("attend_status >=", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusLessThan(Integer value) {
            addCriterion("attend_status <", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusLessThanOrEqualTo(Integer value) {
            addCriterion("attend_status <=", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusIn(List<Integer> values) {
            addCriterion("attend_status in", values, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusNotIn(List<Integer> values) {
            addCriterion("attend_status not in", values, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusBetween(Integer value1, Integer value2) {
            addCriterion("attend_status between", value1, value2, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("attend_status not between", value1, value2, "attendStatus");
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