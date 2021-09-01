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
    $.ajax({
        type: "GET",                                              
        url: "http://localhost:8080/api/fitnessCentar/regList",                 
        dataType: "json",
        contentType: "application/json",                                          
        success: function (res) {
            
            for (i = 0; i < res.length; i++) {
                                     
                let opcija = "<option value='" + res[i].id + "'>" + res[i].nazivCentra + "</option>";  

                $('#fitnessCentar').append(opcija);              
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

$(document).on("submit", "form", function (event) {           
    event.preventDefault();                                   

    var oznakaSale = $("#oznaka").val();
    var kapacitet = $("#kapacitet").val();
    if(kapacitet == ""){
        kapacitet = -1;
    }
    var idFc = $("#fitnessCentar").val();
    
    if(isNaN(kapacitet)) {
        alert("Kapacitet mora biti broj!");
        return false;
    }
    let id = localStorage.getItem("SalaID");

    var novaSala = {
        idFc,
        oznakaSale,
        kapacitet,
        id
        
    }
    console.log(novaSala);
    let role = localStorage.getItem("role");
        $.ajax({
            type: "POST",                                               
            url: "http://localhost:8080/api/sala/azuriraj/" + role,                 
            dataType: "json",                                           
            contentType: "application/json",                            
            data: JSON.stringify(novaSala),                          
            success: function (res) {                                   
                console.log(res);
                if(res.retVal == 0) {
                    alert(res.poruka);
                    window.location.href = "prikazSala.html";
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