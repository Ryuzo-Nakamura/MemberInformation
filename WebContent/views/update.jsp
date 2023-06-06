<%@ page language="java" contentType="text/html; charset=UTFhtml 8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%@ page import="jp.co.aforce.constant.Constant" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>会員情報更新</h2>

<form action="/MemberInformation/servlet/MM02search" method="post">
	<p>■会員番号</p>
	<p>
		<input type="hidden" name="URI" value="<%=request.getRequestURI() %>">
		<input type="text" name="member_id" id="member_id" value="<c:if test="${member_id != null}">${member_id}</c:if>" maxlength="13">
		<input type="submit" class="search_btn" value="検索" >
	</p>
	<p class="error">${search_message}</p>
</form>
<form action="/MemberInformation/servlet/MM02update" method="post">
	<h4 id='member_number'>
		<c:choose>
			<c:when test="${search_message == null}">会員番号：${member_id}</c:when>
			<c:otherwise>会員番号を入力してください</c:otherwise>
		</c:choose>
	</h4>
	<input type="hidden" name="member_id" value="<c:if test="${member_id != null}">${member_id}</c:if>">
	<p>■名前</p>
	姓 <input type="text" name="last_name" id="last_name" value="<c:if test="${last_name != null}">${last_name}</c:if>" maxlength="32"> 
	名 <input type="text" name="first_name" id="first_name" value="<c:if test="${first_name != null}">${first_name}</c:if>" maxlength="32">
	<p>■性別</p>
	<c:choose>
		<c:when test="${gender.equals(\"女\")}">
			<input type="radio" name="gender" value="<%=Constant.Gender.KK_00011 %>"><%=Constant.Gender.KK_00011 %> <input type="radio" name="gender" value="<%=Constant.Gender.KK_00012 %>" checked><%=Constant.Gender.KK_00012 %>
		</c:when>
		<c:otherwise>
			<input type="radio" name="gender" value="<%=Constant.Gender.KK_00011 %>" checked><%=Constant.Gender.KK_00011 %> <input type="radio" name="gender" value="<%=Constant.Gender.KK_00012 %>"><%=Constant.Gender.KK_00012 %>
		</c:otherwise>
	</c:choose>
	<p>■生年月日</p>
	<select name="birth_year" id="birth_year">
		<option value=""></option>
		<% for(int y = 2023; y >= 1900; y--) { %>
			<c:set var="y" value="<%=y %>"></c:set>
			<option value="<%=y %>" <c:if test="${y==birth_year}">selected</c:if>><%=y %></option>
		<% } %>
	</select>年
	<select name="birth_month" id="birth_month">
		<option value=""></option>
		<% for(int m = 1; m <= 12; m++) { %>
			<c:set var="m" value="<%=m %>"></c:set>
			<option value="<%=m %>" <c:if test="${m==birth_month}">selected</c:if>><%=m %></option>
		<% } %>
	</select>月
	<select name="birth_day" id="birth_day">
		<option value=""></option>
		<% for(int d = 1; d <= 31; d++) { %>
			<c:set var="d" value="<%=d %>"></c:set>
			<option value="<%=d %>" <c:if test="${d==birth_day}">selected</c:if>><%=d %></option>
		<% } %>
	</select>日
	<p>■電話番号</p>
	<input type="tel" name="phone_number" id="phone_number" pattern="\d{2,4}-?\d{2,4}-?\d{3,4}" placeholder="ハイフン省略可" value="<c:if test="${phone_number != null}">${phone_number}</c:if>" maxlength="32">
	<p>■メールアドレス</p>
	<input type="email" name="mail_address" id="mail_address" value="<c:if test="${mail_address != null}">${mail_address}</c:if>" maxlength="32">
	<p>■職業</p>
	<select name="job" id="job">
		<option value=""></option>
		<c:forEach var="job_code" items="<%=Constant.Job.getJobCodes() %>">
			<option value="${job_code}" <c:if test="${job_code.equals(job)}">selected</c:if>>${job_code}</option>
		</c:forEach>
	</select>
	<p class="error">${update_message}</p>
	<p class="btn_p">
		<input type="submit" value="更新">
		<input type="button" value="戻る" onclick="MM02back()">
	</p>
</form>

<script src="/MemberInformation/js/script.js"></script>
<%@ include file="footer.jsp" %>