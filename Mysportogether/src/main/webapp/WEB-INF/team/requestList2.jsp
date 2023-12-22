<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value='/js/jquery-1.12.4.min.js'/>" type="text/javascript"></script>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>대결 및 가입 신청 현황</title>
    <Style>
        #form2 {
            border-radius: 10px;
            border: 1px solid;
            border-color: #7d7c7c;
            width: 427px;
            height: 473px;
            /* float: right; */
            display: inline-block;
            overflow: auto;
        }

        #form1 {
            border-radius: 10px;
            border: 1px solid;
            border-color: #7d7c7c;
            margin: auto;
            text-align: center;
            width: 427px;
            /* float: left; */
            height: 473px;
            display: inline-block;
            overflow: auto;
        }


        #form1 form table,
        #form2 form table {
            width: 96%;
            padding-left: 1%;
            padding-right: 1%;
            margin: auto;
            /* display: inline; */
        }

        #form1 table td,
        #form2 table td {
            border-bottom: 1px solid #7d7c7c;
        }

        #approve {
            background-image: url("../images/approve.png");
            /* float: right; */
            width: 56px;
            height: 32px;
        }

        #form2 {
            text-align: center;
        }

        #reject {
            background-image: url("../images/reject.png");
            /* float: right; */
            width: 56px;
            height: 32px;
        }

        #form2 .partition_name {
            text-align: left;
        }

        .empty {
            width: 100px;
            display: inline-block;
        }

        .partition_name {
            text-align: left;
        }

        .side_menu {
            /* border-right: 1px solid #7d7c7c; */
            margin-right: 5%;
            width: 5%;
            padding-left: 1%;
            float: left;
            display: inline;
        }

        .side_menu ul {
            list-style: none;
            text-align: left;
            font-weight: 400;
            padding-left: 1%;
            color: #7d7c7c;
            font-size: 20px;
            width: 100%;

        }

        .side_menu li {
            padding: 20% 1%;
            width: max-content;
            padding-left: 1%;
        }


        #mini_title_container {
            /* text-align: left; */
            padding: 1.5% 0;
            display: flex;
            flex-direction: row;
            justify-content: space-around;
            text-align: left;
        }

        #mini_title {
            /* flex-direction: row; */
            display: inline-block;
            color: #7d7c7c;
            font-weight: bold;
            font-size: 15px;
        }

        #main_Section_center {
            width: 90%;
            clear: both;
            display: flex;

        }
    </Style>
    <link rel="stylesheet" href="../css/requestList.css" />
</head>

<body>
    <div id="container">
        <div id="header_wrap">
            <div id="title-upper"><img src="../images/sportogether.png"></div>
            <div id="title-bottom"><img src="../images/JOINUS.png"></div>
        </div>

        <div id="nevigation">
            <ul>
                <li>팀 관리</li>
                <li>></li>
                <li>팀 전체관리</li>
                <!-- <li>></li> -->

            </ul>
            <hr>
            <!-- <div id="title">신청 현황</div> -->
        </div>


        <div class="side_menu">
            <ul>
                <li>팀 등록</li>
                <li>멤버 관리</li>
                <li>경기 결과</li>
                <li>팀 정보 변경</li>
            </ul>
        </div>
        <div id="main_section">

            <div id="main_section_center">

                <div id="mini_title_container">
                    <div id="mini_title">가입 신청 현황</div>
                    <div id="mini_title">대결 신청 현황</div>
                </div>

                <div id="form1">
                    <form method="post" action="login.jsp">
                        <table>
                            <tr>
                                <td class="partition_name">battle_request_1</td>
                                <td><button id="approve"></button><button id="reject"></button></td>
                            </tr>
                            <tr>
                                <td class="partition_name">battle_request_2</td>
                                <td><button id="approve"></button><button id="reject"></button></td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="empty"></div>
                <div id="form2">
                    <form>
                        <table>
                            <tr>
                                <td class="partition_name">join_request_1</td>
                                <td><button id="approve"></button><button id="reject"></button></td>
                            </tr>
                            <tr>
                                <td class="partition_name">join_request_2</td>
                                <td><button id="approve"></button><button id="reject"></button></td>
                            </tr>
                            <tr></tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <!-- <div id="footer"><button id="prevbutton" type="reset"></button><button id="nextbutton" type="submit"></button> 
        </div>-->
    </div>
</body>

</html>