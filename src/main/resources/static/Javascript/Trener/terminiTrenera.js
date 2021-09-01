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
    var id = localStorage.getItem("id");
    var zastita = localStorage.getItem("role");

    var provera = {
        id, 
        zastita
    }
    console.log(provera);
    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/trener/prikazTermina",                 
        dataType: "json",  
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(provera),                                              
        success: function (res) {   
            for (i = 0; i < res.length; i++) {                   
                let row = "<tr>";  
                let pocetak = substring(res[i].pocetakTermina);
                let kraj = substring(res[i].krajTermina);
                row += "<td>" + res[i].id + "</td>";
                row += "<td>" + res[i].cenaTermina + "</td>";  
                row += "<td>" + res[i].nazivSale + "</td>"; 
                row += "<td>" + res[i].nazivTreninga + "</td>";
                row += "<td>" + pocetak + "</td>";         
                row += "<td>" + kraj + "</td>";
                row += "<td>" + res[i].trajanjeTermina + "</td>";
                let btn = "<button class='addButton' id='editButton' data-id=" + res[i].id + ">Izmeni</button>"
                row += "<td>" + btn + "</td>";  
                row += "</tr>";      
                $('#tabelaTermina').append(row);                               
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on('click', '#editButton', function () {
    localStorage.setItem("termin-id", this.dataset.id);
    window.location.href = "menjanjeTermina.html"

});

function substring(string) {
    return `${string.substring(0,10)} ${string.substring(11, 16)}`;
}

function odjaviSe() {
    window.location.href = "../../index.html";
    window.localStorage.setItem("role", 0); 
    window.localStorage.setItem("id", 0); 
}