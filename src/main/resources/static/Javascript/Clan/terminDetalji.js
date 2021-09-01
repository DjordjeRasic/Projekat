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
    var zastita = localStorage.getItem("role");
    var id = localStorage.getItem("id");
    var terminID = localStorage.getItem("termin-id");
    var provera = {
        zastita,
        id,
        terminID
    }
    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/clan/detalji",                 
        dataType: "json",  
        contentType: "application/json",                            
        data: JSON.stringify(provera),                                              
        success: function (res) { 
            let c1 = "<td>" + res.id + "</td>";       
            let c2 = "<td>" + res.nazivTreninga + "</td>";        
            let c3 = "<td>" + res.opisTreninga + "</td>";   
            let c4 = "<td>" + res.tipTreninga + "</td>";    
            let c5 = "<td>" + substring(res.pocetakTermina) + "</td>";   
            let c6 = "<td>" + substring(res.krajTermina) + "</td>";       
            let c7 = "<td>" + res.trajanjeTermina + "</td>";        
            let c8 = "<td>" + res.cenaTermina + "</td>";   
            let c9 = "<td>" + res.nazivTrenera + "</td>";  
            let c10 = "<td>" + (res.prosecnaOcena).toFixed(2) + "</td>";      
            let c11 = "<td>" + res.clanovi + "</td>";  
            $('#trening-id').append(c1);      
            $('#naziv').append(c2);  
            $('#opis').append(c3);  
            $('#tip').append(c4);  
            $('#pocetak').append(c5); 
            $('#kraj').append(c6);      
            $('#trajanje').append(c7);  
            $('#cena').append(c8);  
            $('#trener').append(c9);  
            $('#prosecna').append(c10);  
            $('#clanovi').append(c11);      
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