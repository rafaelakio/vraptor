<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Novo Produto</title>
</head>
<body>
<!--  recuperando os dados do validator presente na controller -->
<c:forEach items="${errors }" var="erro">
	${error.category } - ${error.message }
</c:forEach>
<form action="<c:url value="/produto/adiciona"/>" method="post" >
<table>
	<tr>
		<td>Nome</td><td><input name="produto.nome" value="${produto.nome}"/></td>
	</tr>
	<tr>
		<td>Descrição</td><td><input name="produto.descricao" value="${produto.descricao}"/></td>
	</tr>
	<tr>
		<td>Preço</td><td><input name="produto.preco" value="${produto.preco}"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Adicionar"/></td>
	</tr>
</table>
</form>
</body>
</html>