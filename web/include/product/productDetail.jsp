<!-- 模仿天猫整站j2ee 教程 为how2j.cn 版权所有-->
<!-- 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关-->
<!-- 供购买者学习，请勿私自传播，否则自行承担相关法律责任-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

	
<div class="productDetailDiv" >
	<div class="productDetailTopPart">
		<a href="#nowhere" class="productDetailTopPartSelectedLink selected">商品详情</a>
		<a href="#nowhere" class="productDetailTopReviewLink">累计评价 <span class="productDetailTopReviewLinkNumber">${p.reviewCount}</span> </a>
	</div>
	
	
	<div class="productParamterPart">
		<div class="productParamter">产品参数：</div>
		
		<div class="productParamterList">
			<c:forEach items="${pvs}" var="pv">
				<span>${pv.property.name}:  ${fn:substring(pv.value, 0, 10)} </span>
			</c:forEach>
		</div>
		<div style="clear:both"></div>
	</div>
	
	<div class="productDetailImagesPart">
		<c:forEach items="${p.productDetailImages}" var="pi">
			<img src="img/productDetail/${pi.id}.jpg">
		</c:forEach>
	</div>
</div>

