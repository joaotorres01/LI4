<%@ page import="Model.GuideMeTo" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GuideMeTo</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<script>
    var email = readCookie('email');
    if (email !== null){
        window.location.replace('map');
    }

    function readCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for(var i=0;i < ca.length;i++) {
            var c = ca[i];
            while (c.charAt(0)===' ') c = c.substring(1,c.length);
            if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length,c.length);
        }
        return null;
    }
</script>


<div class="flex items-center justify-center min-h-screen bg-blue-100">
    <div class="px-8 py-6 mt-4 text-left bg-white shadow-lg">
        <h3 class="text-2xl font-bold text-center">Bem vindo à GuideMeTo</h3>
        <div class="mt-4">
            <div>
                <label class="block">Se queres entrar com convidado clica no botão!</label>
                <div class="flex items-baseline justify-between">
                    <button class="px-6 py-2 mt-4 text-white bg-blue-600 rounded-lg hover:bg-blue-900" onclick="location.href='http://localhost:8080/map';" value="Go to Map">Guest</button>
                </div>
            </div>
            <div class="mt-4">
                <label class="block">Se já tens uma conta faz o Login!</label>
            </div>
            <div class="flex items-baseline justify-between">
                <button class="px-6 py-2 mt-4 text-white bg-blue-600 rounded-lg hover:bg-blue-900" onclick="location.href='http://localhost:8080/login';" value="Go to Login">Login</button>
            </div>
            <div class="mt-4">
                <div>
                    <label class="block">Se queres criar uma conta clica no botão!</label>
                    <div class="flex items-baseline justify-between">
                        <button class="px-6 py-2 mt-4 text-white bg-blue-600 rounded-lg hover:bg-blue-900" onclick="location.href='http://localhost:8080/register';" value="Go to Register">Criar Conta</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>


</html>
