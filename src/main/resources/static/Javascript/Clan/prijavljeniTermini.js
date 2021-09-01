$(document).ready(function () { 
    let role = localStorage.getItem("role");
    if(role == null || role == 0){
        role == 0;
        localStorage.setItem("role", 0);
        window.location.href = "../../index.html";
    } else if (role == 2){
        window.location.href = "../Trener/terminiTrenera.html";
    } else if (role == 1){
        window.location.href = "../Admin/prikazTrenera.html";
    }     
    var clanID = localStorage.getItem("id");
    var zastita = localStorage.getItem("role");
    var provera = {
        clanID, 
        zastita
    }
    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/clan/prijavljeni",                 
        dataType: "json",  
        contentType: "application/json",                            
        data: JSON.stringify(provera),                                              
        success: function (res) {                                      
            for (i = 0; i < res.length; i++) {                  
                let row = "<tr id='toEmpty" + res[i].id + "'>";
                row += "<td>" + res[i].id + "</td>";    
                row += "<td>" + substring(res[i].pocetakTermina) + "</td>";
                row += "<td>" + substring(res[i].krajTermina) + "</td>";
                row += "<td>" + res[i].trajanjeTermina + "</td>";  
                row += "<td>" + res[i].nazivTreninga + "</td>";           
                row += "<td>" + res[i].nazivSale + "</td>";     
                row += "<td>" + res[i].nazivCentra + "</td>";
                row += "<td>" + res[i].nazivTrenera + "</td>";
                let btn = "<button class='deleteButton' id='odjavaButton' data-id=" + res[i].id + ">Odjavi se</button>"
                row += "<td>" + btn + "</td>";                          
                row += "</tr>";                                     
                $('#prijavljeni').append(row);                      
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on('click', '#odjavaButton', function () {
    var id = this.dataset.id;
    let string = "#toEmpty" + id;
    localStorage.setItem("termin-id", this.dataset.id);
    $(string).empty();
    var clanID = localStorage.getItem("id");
    var zastita = localStorage.getItem("role");
    var odjavljenTermin = {
        id,
        clanID,
        zastita
    }
    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/clan/odjava",                 
        dataType: "json",
        contentType: "application/json",   
        data: JSON.stringify(odjavljenTermin),   
        success: function (res) {                               
            console.log("SUCCESS:\n", res);
            if(res.zastita == 1) {
                alert("Morate biti član da biste odjavili termin!");
            } else {
                alert("Termin uspešno odjavljen!");
            }
        },
        error: function (res) {                               
            console.log("ERROR:\n", res);
        }
    });
});

function substring(string) {
    return `${string.substring(0,10)} ${string.substring(11, 16)}`;
}

function odjaviSe() {
    window.location.href = "../../index.html";
    window.localStorage.setItem("role", 0); 
    window.localStorage.setItem("id", 0); 
}