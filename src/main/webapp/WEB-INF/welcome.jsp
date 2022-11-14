<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:pagetemplate>
    <jsp:attribute name="header">
         Shopping page
    </jsp:attribute>

    <jsp:attribute name="footer">
        Shopping page
    </jsp:attribute>

    <jsp:body>

        <h1>Shop amok</h1>

        <form action="addtocart" method="post">
            <select name="top">
                <c:forEach var="topItem" items="${sessionScope.topList}">
                    <option value="${topItem.id}">${topItem.name} (${topItem.price} kr)</option>
                </c:forEach>
            </select>
            <select name="bottom">
                <c:forEach var="bottomItem" items="${sessionScope.bottomList}">
                    <option value="${bottomItem.id}">${bottomItem.name} (${bottomItem.price} kr)</option>
                </c:forEach>
            </select>
            <select name="quantity">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
            <button class="btn btn-primary" name="addToCart">Add to cart</button>
        </form>

        <h2>Antal linier i kurven: ${requestScope.cartsize}</h2>

        <h2>Indhold i kurv:</h2>
        <c:forEach var="item" items="${sessionScope.cart.cupcakeList}">
            TopId: ${item.top.id} name: ${item.top.name} BottomId: ${item.bottom.id} Name: ${item.bottom.name} Antal: ${item.quantity}<br/>
        </c:forEach>

        <p class="mt-4"><a class="btn btn-primary" href="order">Udfør bestilling</a></p>

    </jsp:body>

</t:pagetemplate>