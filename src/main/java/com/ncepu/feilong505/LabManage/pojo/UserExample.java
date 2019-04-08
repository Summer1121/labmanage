package com.ncepu.feilong505.LabManage.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserClassIsNull() {
            addCriterion("user_class is null");
            return (Criteria) this;
        }

        public Criteria andUserClassIsNotNull() {
            addCriterion("user_class is not null");
            return (Criteria) this;
        }

        public Criteria andUserClassEqualTo(String value) {
            addCriterion("user_class =", value, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserClassNotEqualTo(String value) {
            addCriterion("user_class <>", value, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserClassGreaterThan(String value) {
            addCriterion("user_class >", value, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserClassGreaterThanOrEqualTo(String value) {
            addCriterion("user_class >=", value, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserClassLessThan(String value) {
            addCriterion("user_class <", value, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserClassLessThanOrEqualTo(String value) {
            addCriterion("user_class <=", value, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserClassLike(String value) {
            addCriterion("user_class like", value, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserClassNotLike(String value) {
            addCriterion("user_class not like", value, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserClassIn(List<String> values) {
            addCriterion("user_class in", values, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserClassNotIn(List<String> values) {
            addCriterion("user_class not in", values, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserClassBetween(String value1, String value2) {
            addCriterion("user_class between", value1, value2, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserClassNotBetween(String value1, String value2) {
            addCriterion("user_class not between", value1, value2, "userClass");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("user_phone is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("user_phone is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("user_phone =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("user_phone <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("user_phone >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("user_phone >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("user_phone <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("user_phone <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("user_phone like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("user_phone not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("user_phone in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("user_phone not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("user_phone between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("user_phone not between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserWxidIsNull() {
            addCriterion("user_wxid is null");
            return (Criteria) this;
        }

        public Criteria andUserWxidIsNotNull() {
            addCriterion("user_wxid is not null");
            return (Criteria) this;
        }

        public Criteria andUserWxidEqualTo(String value) {
            addCriterion("user_wxid =", value, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserWxidNotEqualTo(String value) {
            addCriterion("user_wxid <>", value, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserWxidGreaterThan(String value) {
            addCriterion("user_wxid >", value, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserWxidGreaterThanOrEqualTo(String value) {
            addCriterion("user_wxid >=", value, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserWxidLessThan(String value) {
            addCriterion("user_wxid <", value, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserWxidLessThanOrEqualTo(String value) {
            addCriterion("user_wxid <=", value, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserWxidLike(String value) {
            addCriterion("user_wxid like", value, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserWxidNotLike(String value) {
            addCriterion("user_wxid not like", value, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserWxidIn(List<String> values) {
            addCriterion("user_wxid in", values, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserWxidNotIn(List<String> values) {
            addCriterion("user_wxid not in", values, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserWxidBetween(String value1, String value2) {
            addCriterion("user_wxid between", value1, value2, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserWxidNotBetween(String value1, String value2) {
            addCriterion("user_wxid not between", value1, value2, "userWxid");
            return (Criteria) this;
        }

        public Criteria andUserNumIsNull() {
            addCriterion("user_num is null");
            return (Criteria) this;
        }

        public Criteria andUserNumIsNotNull() {
            addCriterion("user_num is not null");
            return (Criteria) this;
        }

        public Criteria andUserNumEqualTo(String value) {
            addCriterion("user_num =", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotEqualTo(String value) {
            addCriterion("user_num <>", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumGreaterThan(String value) {
            addCriterion("user_num >", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumGreaterThanOrEqualTo(String value) {
            addCriterion("user_num >=", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLessThan(String value) {
            addCriterion("user_num <", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLessThanOrEqualTo(String value) {
            addCriterion("user_num <=", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLike(String value) {
            addCriterion("user_num like", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotLike(String value) {
            addCriterion("user_num not like", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumIn(List<String> values) {
            addCriterion("user_num in", values, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotIn(List<String> values) {
            addCriterion("user_num not in", values, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumBetween(String value1, String value2) {
            addCriterion("user_num between", value1, value2, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotBetween(String value1, String value2) {
            addCriterion("user_num not between", value1, value2, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIsNull() {
            addCriterion("user_password is null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIsNotNull() {
            addCriterion("user_password is not null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordEqualTo(String value) {
            addCriterion("user_password =", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotEqualTo(String value) {
            addCriterion("user_password <>", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThan(String value) {
            addCriterion("user_password >", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("user_password >=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThan(String value) {
            addCriterion("user_password <", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThanOrEqualTo(String value) {
            addCriterion("user_password <=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLike(String value) {
            addCriterion("user_password like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotLike(String value) {
            addCriterion("user_password not like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIn(List<String> values) {
            addCriterion("user_password in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotIn(List<String> values) {
            addCriterion("user_password not in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordBetween(String value1, String value2) {
            addCriterion("user_password between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotBetween(String value1, String value2) {
            addCriterion("user_password not between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserDetailIsNull() {
            addCriterion("user_detail is null");
            return (Criteria) this;
        }

        public Criteria andUserDetailIsNotNull() {
            addCriterion("user_detail is not null");
            return (Criteria) this;
        }

        public Criteria andUserDetailEqualTo(String value) {
            addCriterion("user_detail =", value, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserDetailNotEqualTo(String value) {
            addCriterion("user_detail <>", value, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserDetailGreaterThan(String value) {
            addCriterion("user_detail >", value, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserDetailGreaterThanOrEqualTo(String value) {
            addCriterion("user_detail >=", value, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserDetailLessThan(String value) {
            addCriterion("user_detail <", value, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserDetailLessThanOrEqualTo(String value) {
            addCriterion("user_detail <=", value, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserDetailLike(String value) {
            addCriterion("user_detail like", value, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserDetailNotLike(String value) {
            addCriterion("user_detail not like", value, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserDetailIn(List<String> values) {
            addCriterion("user_detail in", values, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserDetailNotIn(List<String> values) {
            addCriterion("user_detail not in", values, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserDetailBetween(String value1, String value2) {
            addCriterion("user_detail between", value1, value2, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserDetailNotBetween(String value1, String value2) {
            addCriterion("user_detail not between", value1, value2, "userDetail");
            return (Criteria) this;
        }

        public Criteria andUserMajorIsNull() {
            addCriterion("user_major is null");
            return (Criteria) this;
        }

        public Criteria andUserMajorIsNotNull() {
            addCriterion("user_major is not null");
            return (Criteria) this;
        }

        public Criteria andUserMajorEqualTo(String value) {
            addCriterion("user_major =", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorNotEqualTo(String value) {
            addCriterion("user_major <>", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorGreaterThan(String value) {
            addCriterion("user_major >", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorGreaterThanOrEqualTo(String value) {
            addCriterion("user_major >=", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorLessThan(String value) {
            addCriterion("user_major <", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorLessThanOrEqualTo(String value) {
            addCriterion("user_major <=", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorLike(String value) {
            addCriterion("user_major like", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorNotLike(String value) {
            addCriterion("user_major not like", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorIn(List<String> values) {
            addCriterion("user_major in", values, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorNotIn(List<String> values) {
            addCriterion("user_major not in", values, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorBetween(String value1, String value2) {
            addCriterion("user_major between", value1, value2, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorNotBetween(String value1, String value2) {
            addCriterion("user_major not between", value1, value2, "userMajor");
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