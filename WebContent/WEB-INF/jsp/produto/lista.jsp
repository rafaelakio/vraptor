<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript">
	function remove(id) {
		$.get('remove?produto.id'+id, function(){
			alert('produto removido com sucesso');
			$('#produto-'+id).remove();
		});
	}
</script>
<title>Lista de Produtos</title>
</head>
<body>
<h1>${mensagem }</h1>
<table>
	<thead>
		<tr>
			<td>Nome</td>
			<td>Descrição</td>
			<td>Preço</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="produto" items="${produtoList }">
		<tr id="produto-${produto.id }">
			<td>${produto.nome }</td>
			<td>${produto.descricao }</td>
			<td>${produto.preco }</td>
			<td><a href="">Remove</a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
</html>