$(document).ready(function () {
    let role = localStorage.getItem("role");
    if(role == null || role == 0){
        role == 0;
        localStorage.setItem("role", 0);
        window.location.href = "../../index.html";
    } else if (role == 1){
        window.location.href = "../Admin/prikazTrenera.html";
    } else if (role == 3){
        window.location.href = "../Clan/clan.html";
    }
    var zastita = localStorage.getItem("role");
    var id = localStorage.getItem("id");

    var provera = {
        zastita,
        id
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/sala/sve",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(provera),
        success: function (res) {

            for (i = 0; i < res.length; i++) {
                let sala = "<option>" + res[i].oznakaSale + "</option>";
                $('#sala').append(sala);


            }
        },
        error: function (res) {
            console.log("ERROR:\n", res);
        }
    });
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/trening/svi",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(provera),
        success: function (res) {
            const nazivi = [];
            //const sale = [];
            for (i = 0; i < res.length; i++) {
                let naziv = "<option value='" + res[i].naziv + "'>" + res[i].naziv + " | " + res[i].opis + " | " + res[i].tip + "</option>";
                $("#naziv").append(naziv);
            }
        },
        error: function (res) {
            console.log("ERROR:\n", res);
        }
    });
});