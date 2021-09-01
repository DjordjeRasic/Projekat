$(document).ready(function () {  
    let role = localStorage.getItem("role");
    if(role == null){
        role == 0;
        localStorage.setItem("role", 0);
    } else if (role == 1){
        alert("Vec ste ulogovani!");
        window.location.href = "Admin/prikazTrenera.html";
    } else if (role == 2){
        alert("Vec ste ulogovani!");
        window.location.href = "Trener/terminiTrenera.html";
    } else if (role == 3){
        alert("Vec ste ulogovani!");
        window.location.href = "Clan/clan.html";
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
    var uloga  = $("#uloga").val();
    if(uloga === "clan") {
        var aktivan = true;
    } else {
        var aktivan = false;
        var centarID = $("#centar").val();
        if(centarID == 0) {
            alert("Morate odabrati centar!");
            return false;
        }
    }
    if(lozinka !== potvrdaLozinke) {   
        alert("Lozinke se ne poklapaju!");
        return false;
    } 
    if(isNaN(telefon)) {
        alert("Kontakt telefon mora biti broj!");
        return false;
    }

    var noviKorisnik = {
        korisnickoIme,
        lozinka,
        ime,
        prezime,
        email,
        datumRodjenja,
        telefon,
        uloga,
        aktivan,
        centarID
    }
    console.log(noviKorisnik);
    if(noviKorisnik.uloga === "clan") {
        $.ajax({
            type: "POST",                                               
            url: "http://localhost:8080/api/clan/registracija",                 
            dataType: "json",                                           
            contentType: "application/json",                            
            data: JSON.stringify(noviKorisnik),                          
            success: function (res) {                                   
                console.log(res);
                if(res.retVal !== 0) {
                    alert(res.poruka);
                } else { 
                    alert(`${noviKorisnik.korisnickoIme} se uspešno registrovao!`);
                    window.location.href = "login.html";
                }
            },
            error: function () {                                        
                alert("Greška!");
            }
        });
    } else {
        $.ajax({
            type: "POST",                                               
            url: "http://localhost:8080/api/trener/registracija",                 
            dataType: "json",                                           
            contentType: "application/json",                            
            data: JSON.stringify(noviKorisnik),                          
            success: function (res) {                                   
                console.log(res);
                if(res.retVal !== 0) {
                    alert(res.poruka);
                } else { 
                    alert(`${noviKorisnik.korisnickoIme} je poslao zahtev za registraciju!`);
                    window.location.href = "login.html";
                }
            },
            error: function () {                                        
                alert("Greška!");
            }
        });
    }
    
});