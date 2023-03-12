<%--
  Created by IntelliJ IDEA.
  User: facsc
  Date: 20/01/2022
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GuideMeTo</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

<div class="flex items-center justify-center min-h-screen bg-blue-100">
    <div  class="px-8 py-6 mt-4 text-left bg-white shadow-lg">
        <h3 class="text-2xl font-bold text-center">Entre na sua conta</h3>
        <h4 class="bg-red-300 text-red-900 font-semibold text-xl w-80 rounded-lg my-2 mx-auto">${error}</h4>
        <form onsubmit="return createCookie('email', this.querySelector('#email').value)" method="post" action="http://localhost:8080/login">
            <div class="mt-4">
                <div>
                    <label class="block" >Email</label>
                    <input type="text" placeholder="Email" id="email" name="email"
                           class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
                </div>
                <div class="mt-4">
                    <label class="block">Palavra-passe</label>
                    <input type="password" placeholder="Palavra-passe" id="password" name="password"
                           class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
                </div>
                <div class="flex items-baseline justify-between">
                    <button class="px-6 py-2 mt-4 text-white bg-blue-600 rounded-lg hover:bg-blue-900">Login</button>
                    <a href="http://localhost:8080/register" class="text-sm text-blue-600 hover:underline">Create Account</a>
                </div>
            </div>
        </form>
    </div>
</div>

</body>

<script>
    function createCookie(name,value) {
        var expires = "";
        document.cookie = name+"="+value+expires+"; path=/";
    }
</script>
</html>

