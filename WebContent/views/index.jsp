<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp" %>
<div class="container">    
  <div class="row">
<c:set var="userName" value='<%=session.getAttribute("userName")%>'/>
 <c:forEach items="${productList}" var="product">
   <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">${product.productName}</div>
        <div class="panel-body"><img src="http://creativity103.com/collections/Technology/2dbarcode.jpg" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">
		<c:if test="${userName!='' && userName != null}">
			<a href="addCart?productId=${product.productId}"><input type="button" value="Add To Cart" class="btn btn-primary" style="font-weight: bold"></a>
		</c:if>
		&#8377; ${product.productPrice}</div>
      </div>
    </div>
</c:forEach>
  </div>
</div><br>

<br><br>

<%@include file="footer.jsp" %>