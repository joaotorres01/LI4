<%--@elvariable id="Locais" type="java.lang.String"--%>
<%@ page import="java.util.Map" %>
<%@ page import="Model.Localizacao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>GuideMeTo</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        #map {
            height: 935px;
            width: 100%;
        }

        body {
            font-family: "Lato", sans-serif;
        }

        .sidenav {
            height: 100%;
            width: 300px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #111;
            overflow-x: hidden;
            padding-top: 20px;
        }

        #tableHorario td, tr {
            border: 1px solid white;
        }

        .sidenav a {
            text-decoration: none;
            font-size: 17px;
            color: #818181;
            display: block;
        }


        .sidenav h1 {
            padding: 6px 8px 6px 16px;
            text-decoration: none;
            font-size: 35px;
            color: #E6A018;
            display: block;
            text-align: center;
        }

        .sidenav h2 {
            text-decoration: none;
            font-size: 20px;
            color: #C1C2C2;
            text-align: center;
            vertical-align: middle;
        }

        .sidenav p {
            text-decoration: none;
            font-size: 15px;
            color: #C1C2C2;
            text-align: center;
            vertical-align: middle;
        }

        .sidenav td {
            text-decoration: none;
            font-size: 15px;
            color: #C1C2C2;
            text-align: center;
            vertical-align: middle;
        }

        .sidenav td img{
            display: block;
            margin-left: auto;
            margin-right: auto;
        }

        .sidenav img {
            border-radius: 10px;
            padding: 5px 5px 5px 5px;
        }


        .sidenav a:hover {
            color: #f1f1f1;
        }

        .sidenav {
            text-align: center;
        }

        .main {
            margin-left: 160px;
            /* Same as the width of the sidenav */
            font-size: 28px;
            /* Increased text to enable scrolling */
            padding: 0px 10px;
        }

        @media screen and (max-height: 450px) {
            .sidenav {
                padding-top: 15px;
            }

            .sidenav a {
                font-size: 18px;
            }
        }

        {
            box-sizing: border-box
        ;
        }

        /* Button used to open the contact form - fixed at the bottom of the page */
        .open-button {
            background-color: #555;
            color: white;
            padding: 16px 20px;
            width: 300px;
            border: none;
            cursor: pointer;
            opacity: 0.8;
            position: center;
            display: none;
        }


        .label {
            text-decoration: none;
            font-size: 15px;
            color: #C1C2C2;
            text-align: center;
            vertical-align: middle;
        }


        /* The popup form - hidden by default */
        .form-popup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            border: 3px solid #f1f1f1;
            z-index: 9;
            width: 300px;
        }

        form table {
            width: 100%;
        }

        /* Add styles to the form container */
        .form-container {
            max-width: 300px;
            padding: 10px;
            background-color: #111;
        }


        /* Full-width input fields */
        .form-container input[type=text] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            border: none;
            background: #C1C2C2;
        }


        .form-container h2 {
            padding: 6px 8px 6px 16px;
            text-decoration: none;
            font-size: 25px;
            color: #E6A018;
            display: block;
            text-align: center;
        }

        .contaImg {
            width: 20px;
        }

        /* Set a style for the submit/login button */
        .btn {
            background-color: #04AA6D;
            color: white;
            padding: 16px 20px;
            border: none;
            cursor: pointer;
            width: 100%;
            margin-bottom: 10px;
            opacity: 0.8;
        }

        /* Add a red background color to the cancel button */
        .form-container .cancel {
            background-color: red;
        }

        /* Add some hover effects to buttons */
        .btn:hover, .open-button:hover {
            opacity: 1;
        }

        #myForm {
            display: none;
        }

        nav ul {
            height: 200px;
            width: 100%;
        }

        nav ul {
            display: none;
            overflow: hidden;
            overflow-y: scroll;
        }

        #mediaTable img {
            height: 40px;
        }

        #centroFoto{
            width: 90%;
        }


    </style>
</head>
<body>

<div class="sidenav">
    <div style="align-self: center;width: 90%">
        <img style="width: 90% " src="https://i.ibb.co/n6BX6gB/logo.png">
    </div>
    <table style="width:100%">
        <tr>
            <td style="width: 5%;"><img class="contaImg" src="https://i.ibb.co/v3yBNZG/user.png"
                                         style="background-color: white;"></td>
            <td>
                <h2 id="userNome"></h2>
            </td>
        </tr>
        <tr>
            <td><img class="contaImg" id="emailImg" src="https://i.ibb.co/qsh415P/email.png"
                     style="background-color: white;display: none"></td>
            <td>
                <h2 id="mailText"></h2>
            </td>
        </tr>
        <tr>
            <td>
                <a href="http://localhost:8080/change">
                    <img class="contaImg" id="settingsImg" style=" display: none;background-color: white" src="https://i.ibb.co/JzKdBQq/gear.png" alt="settings">
                </a>
            </td>
            <td>
                <a href="http://localhost:8080/change">
                    <h2 id="alterarConta"></h2>
                </a>
            </td>
        </tr>
        <tr>
            <td>
                <a onclick="eraseCookie('email')" href="http://localhost:8080/">
                    <img class="contaImg" id="logoutImg"  style="background-color: white" src="https://i.ibb.co/RBwjHXN/logout.png" alt="open-door">
                </a>
            </td>
            <td>
                <a onclick="eraseCookie('email')" href="http://localhost:8080/">
                    <h2 id="loginORlogout"></h2>
                </a>
            </td>
        </tr>
    </table>
    <img id="centroFoto">
    <div class="centro">
        <h2 id="centroNome"></h2>
        <table id="mediaTable">
        </table>
        <p id="centroLocal"></p>
        <h2 id="centroHorario"></h2>
        <p id="alwaysOpen"></p>
        <table id="tableHorario" style="width: 100%">
        </table>
        <a id="centroWeb"></a>
        <nav>
            <ul id="list"></ul>
        </nav>
        <button id="buttonAvaliacao" class="open-button" onclick="openForm()">Adicionar Avaliação</button>

        <div class="form-popup" id="myForm">
            <form method="post" action="http://localhost:8080/map" class="form-container">
                <h2>Avaliação</h2>
                <input type="hidden" name="form" value="review">

                <input type="hidden" name="centroForm" value="${Key}"/>

                <table>
                    <tr>
                        <td>
                            <label class="label">Facilidade de Acesso</label>
                        </td>
                        <td>
                            <input style="float: right;clear: both;" type="number" min="1" max="5"
                                   name="facilidadeAcesso" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="label">Preservacao</label>
                        </td>
                        <td>
                            <input style="float: right;clear: both;" type="number" min="1" max="5" name="preservacao"
                                   required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="label">Estetica</label>
                        </td>
                        <td>
                            <input style="float: right;clear: both;" type="number" min="1" max="5" name="estetica"
                                   required>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="label">Experiencia</label>
                        </td>
                        <td>
                            <input style="float: right;clear: both;" type="number" min="1" max="5" name="experiencia"
                                   required>
                        </td>
                    </tr>


                </table>


                <button type="submit" class="btn">Submeter</button>
                <button type="button" class="btn cancel" onclick="closeForm()">Fechar</button>
            </form>
        </div>

        <div id="AddVisita">
            <form method="post" action="http://localhost:8080/map">

                <input type="hidden" name="centroForm" value="${Key}"/>
                <input type="hidden" name="form" value="visitados">

                <button id="buttonAddVisita" type="submit" class="open-button">Adicionar Visitado</button>
            </form>
        </div>

        <div id="Visitados">
            <form method="get" action="http://localhost:8080/map">
                <input type="hidden" name="visitados" value="false">
                <button class="open-button" id="buttonVisitados" type="submit">Visitados</button>
            </form>
        </div>

        <div id="ListaCentros">
            <form method="get" action="http://localhost:8080/map">
                <input type="hidden" name="listar" value="false">
                <button class="open-button" style="display: block" type="submit">Listar Centros</button>
            </form>
        </div>


    </div>
</div>

<div id="map"></div>

<script>
    function createCookie(name, value) {
        var expires = "";
        document.cookie = name + "=" + value + expires + "; path=/";
    }

    function readCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
        }
        return null;
    }

    function eraseCookie(name) {
        document.cookie = name+'=; Max-Age=-99999999;';
    }
</script>

<script>
    function openForm() {
        document.getElementById("myForm").style.display = "block";
    }

    function closeForm() {
        document.getElementById("myForm").style.display = "none";
    }
</script>

<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCAFdErN2BAGg2inePn_mSQ-sno_r4wuxY=&libraries=places&callback=initMap"></script>
<script>
    function initMap() {
        const location = {lat: 41.559, lng: -8.427};
        let map = new google.maps.Map(document.getElementById("map"), {
            zoom: 13,
            center: location,
        });
        let mapCookie = readCookie('Map');
        if (mapCookie != null) LoadMap(map);
        map.addListener("center_changed", () => {
            SaveMap(map);
        });
        map.addListener("zoom_changed", () => {
            SaveMap(map);
        });



        var customStyled = [
            {
                "featureType": "administrative",
                "elementType": "geometry",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "poi",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "road",
                "elementType": "labels.icon",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "transit",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            }
        ];

        map.set('styles', customStyled);
        var mapLocais = new Map(Object.entries(JSON.parse('${Locais}')))
        console.log(mapLocais);
        for (let [key, value] of mapLocais) {
            let marker = new google.maps.Marker({
                position: new google.maps.LatLng(value["latitude"], value["longitude"]),
                content: key
            });
            marker.setMap(map);

            marker.addListener("click", function () {
                window.location.replace("map?key=" + marker.content);
            });
        }
        document.getElementById("userNome").innerHTML = '${Nome}';

        var email = readCookie('email');
        if (email != null) {
            document.getElementById("mailText").innerHTML = email;
            document.getElementById("emailImg").style.display = "revert";
            document.getElementById("alterarConta").innerHTML ="Gestão Conta";
            document.getElementById("settingsImg").style.display="block";
            document.getElementById("loginORlogout").innerHTML = "Sair da Conta";
            document.getElementById("buttonVisitados").style.display = "revert";
            if ('${Visitados}' !== "") {
                document.getElementById("list").style.display = "block";
                var visitados = new Map(Object.entries(JSON.parse('${Visitados}')));
                dolista(visitados);
            }
        } else {
            document.getElementById("loginORlogout").innerHTML = "Menu inicial";
        }

        if ('${ListaCentros}' !== "") {
            document.getElementById("list").style.display = "block";
            var str = String('${ListaCentros}');
            str = str.substring(1, str.length - 1);
            var listaCentros = new Map(Object.entries(JSON.parse(str)));
            dolista(listaCentros);
        }
        if ('${Centro}' !== "") {
            if (email !== null) {
                document.getElementById("buttonAvaliacao").style.display = "block";
                document.getElementById("buttonAddVisita").style.display = "block";
            }
            var centro = new Map(Object.entries(JSON.parse('${Centro}')));
            var latCentro = centro.get("localizacao")["latitude"];
            var lngCentro = centro.get("localizacao")["longitude"];
            var locationCentro = {lat:latCentro, lng: lngCentro};
            map.setCenter(locationCentro);
            document.getElementById("centroNome").innerHTML = centro.get("nome");
            document.getElementById("centroLocal").innerHTML = centro.get("rua");
            var horario = centro.get("horario");
            if (horario.length > 0) {
                doHorario(horario);
            }
            var media = new Map(Object.entries(JSON.parse('${Media}')));
            doTable(media);
            var id = centro.get("key");
            const request = {
                placeId: id,
                fields: ["photos", "website"],
            };
            const service = new google.maps.places.PlacesService(map);

            service.getDetails(request, callback);


        }

        function callback(place, status) {
            if (status === google.maps.places.PlacesServiceStatus.OK) {
                var photo = document.getElementById("centroFoto");
                photo.src = place.photos[0].getUrl();
                var web = place.website;
                if (typeof web !== 'undefined') {
                    var site = document.getElementById("centroWeb");
                    site.href = web;
                    site.innerHTML = "Website";
                }
            }
        }

    }

    function dolista(list) {
        for (let [key, value] of list) {
            var ul = document.getElementById("list");
            var a = document.createElement("a");
            a.href = "http://localhost:8080/map?key=" + key;
            a.text = value;
            ul.appendChild(a);
        }
    }

    function doTable(media) {
        if (media.get("media") === 0) return;
        var table = document.getElementById('mediaTable');
        var row = table.insertRow();
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var imgFA = document.createElement("img");
        imgFA.src = "https://i.ibb.co/zJNx1yt/walking.png";
        imgFA.style = "width=15px"
        cell1.appendChild(imgFA);

        var imgEXP = document.createElement("img");
        imgEXP.src = "https://i.ibb.co/44cM77j/smile.png";
        cell2.appendChild(imgEXP);

        var imgPRES = document.createElement("img");
        imgPRES.src = "https://i.ibb.co/fYYn0jN/column.png";
        cell3.appendChild(imgPRES);

        var imgEST = document.createElement("img");
        imgEST.src = "https://i.ibb.co/KqFcYVw/eye.png";
        cell4.appendChild(imgEST);


        var imgGER = document.createElement("img");
        imgGER.src = "https://i.ibb.co/XpGN8Fb/star.png";
        cell5.appendChild(imgGER);
        row = table.insertRow();
        cell1 = row.insertCell(0);
        cell2 = row.insertCell(1);
        cell3 = row.insertCell(2);
        cell4 = row.insertCell(3);
        cell5 = row.insertCell(4);
        cell1.innerHTML = media.get("facilidadeAcesso");
        cell2.innerHTML = media.get("experiencia");
        cell3.innerHTML = media.get("preservacao");
        cell4.innerHTML = media.get("estetica");
        cell5.innerHTML = media.get("media");
    }

    function doHorario(horario) {
        var weekday = new Array(7);
        weekday[0] = "Domingo";
        weekday[1] = "Segunda";
        weekday[2] = "Terça";
        weekday[3] = "Quarta";
        weekday[4] = "Quinta";
        weekday[5] = "Sexta";
        weekday[6] = "Sábado";
        document.getElementById("centroHorario").innerHTML = "Horário";
        if (horario.length === 1 && horario[0].abertura === horario[0].fecho) {
            document.getElementById("alwaysOpen").innerHTML = "Sempre aberto";
            return;
        }
        var table = document.getElementById('tableHorario');
        var row = table.insertRow();
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        cell1.innerHTML = "Dia";
        cell2.innerHTML = "Abertura";
        cell3.innerHTML = "Fecho";
        for (let x in horario) {
            row = table.insertRow();
            cell1 = row.insertCell(0);
            cell2 = row.insertCell(1);
            cell3 = row.insertCell(2);
            cell1.innerHTML = weekday[horario[x].dia];
            cell2.innerHTML = horario[x].abertura;
            cell3.innerHTML = horario[x].fecho;
        }

    }

    function SaveMap(map){
        var mapzoom = map.getZoom();
        var mapcenter = map.getCenter();
        var maplat = mapcenter.lat();
        var maplng = mapcenter.lng();
        var cookiestring = maplat + "_" + maplng + "_" + mapzoom;
        createCookie("Map", cookiestring);
    }

    function LoadMap(map) {
        var loadedstring = readCookie("Map");
        var splitstr = loadedstring.split("_");
        var latlng = new google.maps.LatLng(parseFloat(splitstr[0]), parseFloat(splitstr[1]));
        map.setCenter(latlng);
        map.setZoom(parseFloat(splitstr[2]));
    }

</script>


</body>
</html>