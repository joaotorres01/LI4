<%--
  Created by IntelliJ IDEA.
  User: diogo
  Date: 24/01/22
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <div class="px-10 py-6 mt-4 text-left bg-white shadow-lg">
        <h3 class="text-2xl font-bold text-center">Gestão conta</h3>
        <h4 class="bg-red-300 text-red-900 font-semibold text-center text-xl rounded-lg">${error}</h4>
        <form method="post" action="http://localhost:8080/change">
            <input type="hidden" name="email" value=${email}>
            <div class="mt-4">
                <div class="mt-4">
                    <label class="block">Nome</label>
                    <input type="text" placeholder=${name} id="nameNew" name="nameNew"
                           class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
                </div>
                <div class="mt-4">
                    <label class="block">Telemóvel</label>
                    <input type="number" placeholder="${phone}" id="phoneNew" name="phoneNew" min="100000" max="999999999999999"
                           class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
                </div>
            </div>
            <div class="mt-4">
                <label class="block">Palavra-Passe(Obrigatório)</label>
                <input type="password" placeholder="Palavra-Passe" id="oldPassword" name="oldPassword"
                       class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
            </div>
            <div class="mt-4">
                <label class="block">Palavra-Passe Nova</label>
                <input type="password" placeholder="Palavra-Passe Nova" id="passwordNew" name="passwordNew"
                       class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
            </div>
            <div class="mt-4">
                <label class="block">Confirmar Palavra-Passe</label>
                <input type="password" placeholder="Confirmar Palavra-Passe" id="confirmPassword" name="confirmPassword"
                       class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
            </div>
            <div class="flex items-baseline justify-between">
                <button class="px-6 py-2 mt-4 text-white bg-blue-600 rounded-lg hover:bg-blue-900">Guardar</button>
                <a href="http://localhost:8080/" class="text-sm text-blue-600 hover:underline">Sair</a>
            </div>
    </div>
    </form>
</div>
</div>
</body>
</html>