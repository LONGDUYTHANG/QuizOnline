
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="head.jsp" />
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="sidebar.jsp" />

            <div class="main">
                <jsp:include page="navbar.jsp"/>

                <main class="content">
                    //code here
                </main>

                <jsp:include page="footer.jsp"/>    
            </div>
        </div>
            <jsp:include page="script.jsp"/>
        </body>

</html>