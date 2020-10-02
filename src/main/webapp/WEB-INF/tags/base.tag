<%@tag description="Base page template" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <!-- Head -->
    <jsp:include page="/WEB-INF/view/fragments/head.jsp" flush="true"/>

    <body>
        <!-- Nav -->
        <jsp:include page="/WEB-INF/view/fragments/nav.jsp" flush="true"/>

        <!-- Body content -->
        <jsp:doBody/>

        <!-- Footer -->
        <jsp:include page="/WEB-INF/view/fragments/footer.jsp" flush="true"/>
    </body>
</html>
