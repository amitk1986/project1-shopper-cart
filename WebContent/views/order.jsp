<%@include file="header.jsp" %>
<br><div class="container">
	<div class="row">
		<div class="col-xs-8 col-xs-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">
						<div class="row">
							<div class="col-xs-6">
								<h5><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</h5>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body">
				<form method="POST" action='cartUpdate'>
				<c:set var="count" value="0"/>
 				<c:forEach items="${cartList}" var="cart">
				<c:set var="count" value="${count+1}"/>
					<div class="row">
						<div class="col-xs-2"><img class="img-responsive" src="http://placehold.it/100x70">
						</div>
						<div class="col-xs-4">
							<input type="hidden" name="cartId${count}" id="cartId" value="${cart.cartId}"/>
							<h4 class="product-name"><strong>${cart.productName}</strong></h4>
						</div>
						<div class="col-xs-6">
							<div class="col-xs-6 text-right">
								<h6><strong>${cart.productPrice} <span class="text-muted">x</span></strong></h6>
							</div>
							<div class="col-xs-4">
								<input type="text" disabled="disabled" name="cartQty${count}" class="form-control input-sm" value="${cart.qty}">
							</div>
						</div>
					</div>
				</c:forEach>
					<hr>
					<div class="row">
						<div class="text-center">
							<div class="col-xs-9">
								<h6 class="text-right">Added items?</h6>
							</div>
						</div>
					</div>
					</form>
				</div>
				<div class="panel-footer">
					<div class="row text-center">
						<div class="col-xs-9">
							<h4 class="text-right">Total <strong>${totalValue}</strong></h4>
						</div>
						<div class="col-xs-3">
							<a href="billing"><button type="button" class="btn btn-success btn-block">
								 Billing
							</button>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div><br><br>
<%@include file="footer.jsp" %>