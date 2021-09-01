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

                $('#centar').append(opcija);              
            }
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});
$(document).on("submit", "form", function (event) {           
    event.preventDefault();                                   

    var korisnickoIme = $("#username").val();
    var lozinka = $("#password").val();
    var potvrdaLozinke = $("#confirmPassword").val();
    var ime = $("#firstName").val();
    var prezime = $("#lastName").val();
    var email = $("#email").val();
    var datumRodjenja = $("#date").val();
    var telefon = $("#contact").val();
    var centarID = $("#centar").val();
    
    
    if(lozinka !== potvrdaLozinke) {   
        alert("Lozinke se ne poklapaju!");
        return false;
    } 
    if(isNaN(telefon)) {
        alert("Kontakt telefon mora biti broj!");
        return false;
    }
    let aktivan = true;
    var noviTrener = {
        korisnickoIme,
        lozinka,
        ime,
        prezime,
        email,
        datumRodjenja,
        telefon,
        aktivan,
        centarID
    }
    let role = localStorage.getItem("role");
    console.log(noviTrener);
        $.ajax({
            type: "POST",                                               
            url: "http://localhost:8080/api/trener/registracijaAdmin/" + role,                 
            dataType: "json",                                           
            contentType: "application/json",                            
            data: JSON.stringify(noviTrener),                          
            success: function (res) {                                   
                console.log(res);
                if(res.retVal == 1) {
                    alert(res.poruka);
                } else if(res.retVal == 0){ 
                    alert(`${noviTrener.korisnickoIme} je uspesno registrovan!`);
                    window.location.href = "prikazTrenera.html";
                } else {
                    alert(res.poruka);
                    window.location.href = "../../index.html";
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