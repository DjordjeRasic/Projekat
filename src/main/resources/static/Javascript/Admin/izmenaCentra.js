$(document).on("submit", "form", function (event) {           
    event.preventDefault();                                   

    var nazivCentra = $("#naziv").val();
    var adresa = $("#adresa").val();
    var brojTelefonaCentra = $("#broj").val();
    var emailCentra = $("#email").val();
    var id = localStorage.getItem("CentarID");
    if(isNaN(brojTelefonaCentra)) {
        alert("Kontakt telefon mora biti broj!");
        return false;
    }
    var noviCentar = {
        nazivCentra,
        adresa,
        brojTelefonaCentra,
        emailCentra,
        id
    }
    console.log(noviCentar);
    let role = localStorage.getItem("role");
        $.ajax({
            type: "POST",                                               
            url: "http://localhost:8080/api/fitnessCentar/izmeni/" + role,                 
            dataType: "json",                                           
            contentType: "application/json",                            
            data: JSON.stringify(noviCentar),                          
            success: function (res) {                                   
                console.log(res);
                if(res.retVal == 0) {
                    alert(res.poruka);
                    window.location.href = "fitnessCentar.html";
                } else if(res.retVal == 1){ 
                    alert(res.poruka);
                    window.location.href = "../../index.html";
                } else {
                    alert(res.poruka);
                }
            },
            error: function () {                                        
                alert("Greška!");
            }
        });
    
    
});

function odjaviSe() {
    window.location.href = "../../index.html";
    window.localStorage.setItem("role", 0); 
    window.localStorage.setItem("id", 0); 
}

$(document).ready(function () { 
    let role = localStorage.getItem("role");
    if(role == null || role == 0){
        role == 0;
        localStorage.setItem("role", 0);
        window.location.href = "../../index.html";
    } else if (role == 2){
        window.location.href = "../Trener/terminiTrenera.html";
    } else if (role == 3){
        window.location.href = "../Clan/clan.html";
    }
});