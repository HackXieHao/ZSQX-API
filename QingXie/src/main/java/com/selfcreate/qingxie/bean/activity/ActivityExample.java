package com.selfcreate.qingxie.bean.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNull() {
            addCriterion("manager_id is null");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNotNull() {
            addCriterion("manager_id is not null");
            return (Criteria) this;
        }

        public Criteria andManagerIdEqualTo(Integer value) {
            addCriterion("manager_id =", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotEqualTo(Integer value) {
            addCriterion("manager_id <>", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThan(Integer value) {
            addCriterion("manager_id >", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("manager_id >=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThan(Integer value) {
            addCriterion("manager_id <", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThanOrEqualTo(Integer value) {
            addCriterion("manager_id <=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdIn(List<Integer> values) {
            addCriterion("manager_id in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotIn(List<Integer> values) {
            addCriterion("manager_id not in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdBetween(Integer value1, Integer value2) {
            addCriterion("manager_id between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("manager_id not between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andHoursIsNull() {
            addCriterion("hours is null");
            return (Criteria) this;
        }

        public Criteria andHoursIsNotNull() {
            addCriterion("hours is not null");
            return (Criteria) this;
        }

        public Criteria andHoursEqualTo(Integer value) {
            addCriterion("hours =", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursNotEqualTo(Integer value) {
            addCriterion("hours <>", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursGreaterThan(Integer value) {
            addCriterion("hours >", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursGreaterThanOrEqualTo(Integer value) {
            addCriterion("hours >=", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursLessThan(Integer value) {
            addCriterion("hours <", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursLessThanOrEqualTo(Integer value) {
            addCriterion("hours <=", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursIn(List<Integer> values) {
            addCriterion("hours in", values, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursNotIn(List<Integer> values) {
            addCriterion("hours not in", values, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursBetween(Integer value1, Integer value2) {
            addCriterion("hours between", value1, value2, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursNotBetween(Integer value1, Integer value2) {
            addCriterion("hours not between", value1, value2, "hours");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeIsNull() {
            addCriterion("hour_per_time is null");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeIsNotNull() {
            addCriterion("hour_per_time is not null");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeEqualTo(Integer value) {
            addCriterion("hour_per_time =", value, "hourPerTime");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeNotEqualTo(Integer value) {
            addCriterion("hour_per_time <>", value, "hourPerTime");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeGreaterThan(Integer value) {
            addCriterion("hour_per_time >", value, "hourPerTime");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("hour_per_time >=", value, "hourPerTime");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeLessThan(Integer value) {
            addCriterion("hour_per_time <", value, "hourPerTime");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeLessThanOrEqualTo(Integer value) {
            addCriterion("hour_per_time <=", value, "hourPerTime");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeIn(List<Integer> values) {
            addCriterion("hour_per_time in", values, "hourPerTime");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeNotIn(List<Integer> values) {
            addCriterion("hour_per_time not in", values, "hourPerTime");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeBetween(Integer value1, Integer value2) {
            addCriterion("hour_per_time between", value1, value2, "hourPerTime");
            return (Criteria) this;
        }

        public Criteria andHourPerTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("hour_per_time not between", value1, value2, "hourPerTime");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersIsNull() {
            addCriterion("need_volunteers is null");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersIsNotNull() {
            addCriterion("need_volunteers is not null");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersEqualTo(Integer value) {
            addCriterion("need_volunteers =", value, "needVolunteers");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersNotEqualTo(Integer value) {
            addCriterion("need_volunteers <>", value, "needVolunteers");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersGreaterThan(Integer value) {
            addCriterion("need_volunteers >", value, "needVolunteers");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_volunteers >=", value, "needVolunteers");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersLessThan(Integer value) {
            addCriterion("need_volunteers <", value, "needVolunteers");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersLessThanOrEqualTo(Integer value) {
            addCriterion("need_volunteers <=", value, "needVolunteers");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersIn(List<Integer> values) {
            addCriterion("need_volunteers in", values, "needVolunteers");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersNotIn(List<Integer> values) {
            addCriterion("need_volunteers not in", values, "needVolunteers");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersBetween(Integer value1, Integer value2) {
            addCriterion("need_volunteers between", value1, value2, "needVolunteers");
            return (Criteria) this;
        }

        public Criteria andNeedVolunteersNotBetween(Integer value1, Integer value2) {
            addCriterion("need_volunteers not between", value1, value2, "needVolunteers");
            return (Criteria) this;
        }

        public Criteria andPlaceIsNull() {
            addCriterion("place is null");
            return (Criteria) this;
        }

        public Criteria andPlaceIsNotNull() {
            addCriterion("place is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceEqualTo(String value) {
            addCriterion("place =", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotEqualTo(String value) {
            addCriterion("place <>", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceGreaterThan(String value) {
            addCriterion("place >", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("place >=", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLessThan(String value) {
            addCriterion("place <", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLessThanOrEqualTo(String value) {
            addCriterion("place <=", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLike(String value) {
            addCriterion("place like", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotLike(String value) {
            addCriterion("place not like", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceIn(List<String> values) {
            addCriterion("place in", values, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotIn(List<String> values) {
            addCriterion("place not in", values, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceBetween(String value1, String value2) {
            addCriterion("place between", value1, value2, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotBetween(String value1, String value2) {
            addCriterion("place not between", value1, value2, "place");
            return (Criteria) this;
        }

        public Criteria andGeneralIsNull() {
            addCriterion("general is null");
            return (Criteria) this;
        }

        public Criteria andGeneralIsNotNull() {
            addCriterion("general is not null");
            return (Criteria) this;
        }

        public Criteria andGeneralEqualTo(String value) {
            addCriterion("general =", value, "general");
            return (Criteria) this;
        }

        public Criteria andGeneralNotEqualTo(String value) {
            addCriterion("general <>", value, "general");
            return (Criteria) this;
        }

        public Criteria andGeneralGreaterThan(String value) {
            addCriterion("general >", value, "general");
            return (Criteria) this;
        }

        public Criteria andGeneralGreaterThanOrEqualTo(String value) {
            addCriterion("general >=", value, "general");
            return (Criteria) this;
        }

        public Criteria andGeneralLessThan(String value) {
            addCriterion("general <", value, "general");
            return (Criteria) this;
        }

        public Criteria andGeneralLessThanOrEqualTo(String value) {
            addCriterion("general <=", value, "general");
            return (Criteria) this;
        }

        public Criteria andGeneralLike(String value) {
            addCriterion("general like", value, "general");
            return (Criteria) this;
        }

        public Criteria andGeneralNotLike(String value) {
            addCriterion("general not like", value, "general");
            return (Criteria) this;
        }

        public Criteria andGeneralIn(List<String> values) {
            addCriterion("general in", values, "general");
            return (Criteria) this;
        }

        public Criteria andGeneralNotIn(List<String> values) {
            addCriterion("general not in", values, "general");
            return (Criteria) this;
        }

        public Criteria andGeneralBetween(String value1, String value2) {
            addCriterion("general between", value1, value2, "general");
            return (Criteria) this;
        }

        public Criteria andGeneralNotBetween(String value1, String value2) {
            addCriterion("general not between", value1, value2, "general");
            return (Criteria) this;
        }

        public Criteria andDescriptionsIsNull() {
            addCriterion("descriptions is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionsIsNotNull() {
            addCriterion("descriptions is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionsEqualTo(String value) {
            addCriterion("descriptions =", value, "descriptions");
            return (Criteria) this;
        }

        public Criteria andDescriptionsNotEqualTo(String value) {
            addCriterion("descriptions <>", value, "descriptions");
            return (Criteria) this;
        }

        public Criteria andDescriptionsGreaterThan(String value) {
            addCriterion("descriptions >", value, "descriptions");
            return (Criteria) this;
        }

        public Criteria andDescriptionsGreaterThanOrEqualTo(String value) {
            addCriterion("descriptions >=", value, "descriptions");
            return (Criteria) this;
        }

        public Criteria andDescriptionsLessThan(String value) {
            addCriterion("descriptions <", value, "descriptions");
            return (Criteria) this;
        }

        public Criteria andDescriptionsLessThanOrEqualTo(String value) {
            addCriterion("descriptions <=", value, "descriptions");
            return (Criteria) this;
        }

        public Criteria andDescriptionsLike(String value) {
            addCriterion("descriptions like", value, "descriptions");
            return (Criteria) this;
        }

        public Criteria andDescriptionsNotLike(String value) {
            addCriterion("descriptions not like", value, "descriptions");
            return (Criteria) this;
        }

        public Criteria andDescriptionsIn(List<String> values) {
            addCriterion("descriptions in", values, "descriptions");
            return (Criteria) this;
        }

        public Criteria andDescriptionsNotIn(List<String> values) {
            addCriterion("descriptions not in", values, "descriptions");
            return (Criteria) this;
        }

        public Criteria andDescriptionsBetween(String value1, String value2) {
            addCriterion("descriptions between", value1, value2, "descriptions");
            return (Criteria) this;
        }

        public Criteria andDescriptionsNotBetween(String value1, String value2) {
            addCriterion("descriptions not between", value1, value2, "descriptions");
            return (Criteria) this;
        }

        public Criteria andRegTimeIsNull() {
            addCriterion("reg_time is null");
            return (Criteria) this;
        }

        public Criteria andRegTimeIsNotNull() {
            addCriterion("reg_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegTimeEqualTo(Date value) {
            addCriterion("reg_time =", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeNotEqualTo(Date value) {
            addCriterion("reg_time <>", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeGreaterThan(Date value) {
            addCriterion("reg_time >", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reg_time >=", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeLessThan(Date value) {
            addCriterion("reg_time <", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeLessThanOrEqualTo(Date value) {
            addCriterion("reg_time <=", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeIn(List<Date> values) {
            addCriterion("reg_time in", values, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeNotIn(List<Date> values) {
            addCriterion("reg_time not in", values, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeBetween(Date value1, Date value2) {
            addCriterion("reg_time between", value1, value2, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeNotBetween(Date value1, Date value2) {
            addCriterion("reg_time not between", value1, value2, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeIsNull() {
            addCriterion("reg_end_time is null");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeIsNotNull() {
            addCriterion("reg_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeEqualTo(Date value) {
            addCriterion("reg_end_time =", value, "regEndTime");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeNotEqualTo(Date value) {
            addCriterion("reg_end_time <>", value, "regEndTime");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeGreaterThan(Date value) {
            addCriterion("reg_end_time >", value, "regEndTime");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reg_end_time >=", value, "regEndTime");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeLessThan(Date value) {
            addCriterion("reg_end_time <", value, "regEndTime");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("reg_end_time <=", value, "regEndTime");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeIn(List<Date> values) {
            addCriterion("reg_end_time in", values, "regEndTime");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeNotIn(List<Date> values) {
            addCriterion("reg_end_time not in", values, "regEndTime");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeBetween(Date value1, Date value2) {
            addCriterion("reg_end_time between", value1, value2, "regEndTime");
            return (Criteria) this;
        }

        public Criteria andRegEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("reg_end_time not between", value1, value2, "regEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeIsNull() {
            addCriterion("interview_time is null");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeIsNotNull() {
            addCriterion("interview_time is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeEqualTo(Date value) {
            addCriterion("interview_time =", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeNotEqualTo(Date value) {
            addCriterion("interview_time <>", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeGreaterThan(Date value) {
            addCriterion("interview_time >", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("interview_time >=", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeLessThan(Date value) {
            addCriterion("interview_time <", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeLessThanOrEqualTo(Date value) {
            addCriterion("interview_time <=", value, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeIn(List<Date> values) {
            addCriterion("interview_time in", values, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeNotIn(List<Date> values) {
            addCriterion("interview_time not in", values, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeBetween(Date value1, Date value2) {
            addCriterion("interview_time between", value1, value2, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andInterviewTimeNotBetween(Date value1, Date value2) {
            addCriterion("interview_time not between", value1, value2, "interviewTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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