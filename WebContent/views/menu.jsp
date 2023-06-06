<%@ page language="java" contentType="text/html; charset=UTFhtml 8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%@ page import="jp.co.aforce.constant.Constant" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>会員情報管理メニュー</h2>

<p id="menu_btn">
	<button onclick="MM00regist()">登録</button>
	<button onclick="MM00update()">更新</button>
	<button onclick="MM00delete()">削除</button>
</p>
<script src="/MemberInformation/js/script.js"></script>
<%@ include file="footer.jsp" %>