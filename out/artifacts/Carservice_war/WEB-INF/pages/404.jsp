<%--
  Created by IntelliJ IDEA.
  User: daniil
  Date: 08.05.2020
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

<script>
    function goBack() {
        window.history.back();
    }
</script>

    <style>

        a, abbr, acronym, address, applet, article, aside, audio, b, big, blockquote, body, canvas, caption, center, cite, code, dd, del, details, dfn, div, dl, dt, em, embed, fieldset, figcaption, figure, footer, form, h1, h2, h3, h4, h5, h6, header, hgroup, html, i, iframe, img, ins, kbd, label, legend, li, mark, menu, nav, object, ol, output, p, pre, q, ruby, s, samp, section, small, span, strike, strong, sub, summary, sup, table, tbody, td, tfoot, th, thead, time, tr, tt, u, ul, var, video {
            margin: 0;
            padding: 0;
            border: 0;
            font-size: 100%;
            font: inherit;
            vertical-align: baseline;
        }

        body {
            font-family: -apple-system,\.SFNSText-Regular,Helvetica Neue,Roboto,Segoe UI,sans-serif;
            -webkit-font-smoothing: antialiased;
            font-size: 16px;
            min-height: 100%;
            background-color: #000;
            color: #fff;
        }

        .background {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: url("../../404.gif") 50% no-repeat;
            background-size: cover;
            z-index: -1;
            opacity: .5;
        }

        .message h1 {
            font-size: 144px;
            font-weight: 100;
            margin-bottom: 16px;
        }

        .message h2 {
            margin-bottom: 24px;
        }

        .message h3 {
            font-weight: 700;
            font-size: 16px;
            margin-bottom: 32px;
        }
        .message {
            text-align: center;
            position: absolute;
            width: 100%;
            top: 50%;
            transform: translateY(-50%);
        }

        .btn {
            display: inline-block;
            line-height: 20px;
            padding: 12px 24px 12px 24px;
            background: #09f ;
            color: #fff;
            font-weight: 700;
            border-radius: 2px;
            text-align: center;
            cursor: pointer;
        }

    </style>

</head>
<body>
<div class="background">
</div>
<div class="message">
    <h1>404</h1>
    <h2>ups....page not found</h2>

    <h3> <a onclick="goBack()" class="btn" >Bring me back</a> </h3>
</div>
</body>
</html>
