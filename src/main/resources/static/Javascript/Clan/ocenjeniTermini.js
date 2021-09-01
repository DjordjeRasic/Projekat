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
        url: "http://localhost:8080/api/clan/ocenjeni",                 
        dataType: "json",  
        contentType: "application/json",                            
        data: JSON.stringify(provera),                                              
        success: function (res) {                                      
            for (i = 0; i < res.length; i++) {                     
                let row = "<tr>";
                row += "<td>" + res[i].id + "</td>";    
                row += "<td>" + res[i].ocena + "</td>";    
                row += "<td>" + res[i].nazivTreninga + "</td>";           
                row += "<td>" + res[i].nazivSale + "</td>";     
                row += "<td>" + res[i].nazivCentra + "</td>";
                row += "<td>" + res[i].trajanjeTermina + "</td>";
                row += "<td>" + res[i].nazivTrenera + "</td>";                     
                row += "</tr>";                                     
                $('#ocenjeni').append(row);                      
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

function odjaviSe() {
    window.location.href = "../../index.html";
    window.localStorage.setItem("role", 0); 
    window.localStorage.setItem("id", 0); 
}